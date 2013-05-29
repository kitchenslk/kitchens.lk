package com.janaka.kitchenslk.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.janaka.kitchenslk.entity.UploadedFile;
import com.janaka.kitchenslk.service.SystemUserService;
import com.janaka.kitchenslk.util.ApplicationConstants;
import com.janaka.kitchenslk.util.FileManager;

@Controller(value = "fileController")
public class FileController {

	@Autowired
	private FileManager fileManager;

	@Autowired
	private SystemUserService systemUserService;

	private static final int BUFSIZE = 1024 * 1024;

	@RequestMapping(value = "findImage", method = RequestMethod.GET)
	public void findImage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("imageName") String imageName) {
		FileInputStream in = null;
		try {
			ServletContext sc = request.getSession().getServletContext();
			String filename = null;
			filename = ApplicationConstants.ROOT_IMAGE_FOLDER +   imageName;
			// Get the MIME type of the image
			String mimeType = sc.getMimeType(filename);
			if (mimeType == null) {
				sc.log("Could not get MIME type of " + filename);
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			// Set content type
			response.setContentType(mimeType);
			// Set content size
			File file = new File(filename);
			response.setContentLength((int) file.length());
			in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			// Copy the contents of the file to the output stream
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			out.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
				System.out.println(ex);
			}
		}

	}

	@RequestMapping(value = "findFile", method = RequestMethod.GET)
	public void findFile(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("fileName") String reqFileName) {
		ServletOutputStream op = null;
		try {
			String filename = null;
			filename = ApplicationConstants.DIRECTORY_ROOT + reqFileName;
			File f = new File(filename);
			int length = 0;
			op = response.getOutputStream();
			ServletContext context = request.getSession().getServletContext();
			String mimetype = context.getMimeType(filename);
			//
			// Set the response and go!
			//
			//
			response.setContentType((mimetype != null) ? mimetype
					: "application/octet-stream");
			response.setContentLength((int) f.length());
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ reqFileName + "\"");
			//
			// Stream to the requester.
			//
			byte[] bbuf = new byte[BUFSIZE];
			DataInputStream in = new DataInputStream(new FileInputStream(f));
			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}
			in.close();
			op.flush();
			op.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				op.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "deleteFile", method = RequestMethod.POST)
	public void deleteFile(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("pathVar") String pathVar) {
		String fileName = "";
		String filePath = null;
		String constVal = ApplicationConstants.FILE_FINDER_URL;
		if (!(pathVar.isEmpty())) {
			fileName = pathVar.replace(constVal, "");
			filePath = ApplicationConstants.DIRECTORY_ROOT + fileName;
			boolean success = (new File(filePath)).delete();
			response.setContentType("text/html");
			if (!success) {
				try {
					response.getWriter().write("File Not Found..!");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} else {
				try {
					response.getWriter().write("File Successfully Deleted..!");
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		}
	}

	/** ---------------- To upload the image to the disk ------------------- **/
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public void uploadImage(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {

		String imageURL = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				System.out.println("File Not Uploaded");
			} else {
				Map<String, String> map = fileManager.constructFile(file,request,file.getName());
				UploadedFile uploadedFile = new UploadedFile(map);
				imageURL = uploadedFile.getFileUrl();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("text/html");

		try {
			response.getWriter().write(imageURL);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(
    		@RequestParam("fileName") String fileName,
    		@RequestParam("file") CommonsMultipartFile file, 
    		HttpServletRequest request, 
    		HttpServletResponse response) {
	     
      Map<String, String> resultMap=new HashMap<String, String>();
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);

      if (!isMultipart) {
        try {
          response.getWriter().write("File Not Uploaded");
        } catch (IOException e) {
          // Couldn't write the response back to the user either? Not much that can be done then.
          e.printStackTrace();
          return;
        }
      } else {
    	  resultMap = fileManager.constructFile(file, request,fileName);
      }

      response.setContentType("text/html");
      try {    	
        JSONObject jsonObject=new JSONObject();       
		jsonObject.put("filePath", resultMap.get(ApplicationConstants.PHYSICAL_FILE_PATH));		
        jsonObject.put("fileName", resultMap.get(ApplicationConstants.UPLOADED_FILE_NAME));
        jsonObject.put("fileUrl", resultMap.get(ApplicationConstants.FILE_URL));
        jsonObject.put("createdFileName", resultMap.get(ApplicationConstants.CONSTRUCTED_FILE_NAME));
        response.getWriter().write(jsonObject.toString());
      } catch (IOException e) {
        e.printStackTrace();
      } catch (JSONException e) {
		e.printStackTrace();
	}
    }

}
