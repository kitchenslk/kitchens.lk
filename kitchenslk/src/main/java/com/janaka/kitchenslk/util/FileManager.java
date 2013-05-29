package com.janaka.kitchenslk.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.janaka.kitchenslk.commons.CommonFunctions;

@Component
public class FileManager {

    private String DESTINATION_DIR_PATH = ApplicationConstants.DIRECTORY_ROOT;
    private String FILE_PATH_TO_RETURN = ApplicationConstants.FILE_FINDER_URL;
    private File destinationDir;

    public Map<String,String> constructFile(CommonsMultipartFile file, HttpServletRequest request, String uploadedFileName) {

    	Map<String,String> map=new HashMap<String,String>();
        byte[] bits = file.getBytes();
        
        MagicMatch match = null;
        try {
            match = Magic.getMagicMatch(bits);
        } catch (MagicParseException e) {
            System.out.println(e.getMessage());
        } catch (MagicMatchNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (MagicException e) {
            System.out.println(e.getMessage());
        }
        String mimeType = match.getMimeType();
        
        String extension = ".txt";
        if (mimeType.equals("text/plain")) {
            extension = ".txt";
        } else if (mimeType.equals("application/pdf")) {
            extension = ".pdf";
        } else if (mimeType.equals("application/msword")) {
            extension = ".doc";
        } else if (mimeType.equals("application/zip")) {
            extension = ".docx";
        } else if (mimeType.equals("image/gif")) {
        	DESTINATION_DIR_PATH=ApplicationConstants.ROOT_IMAGE_FOLDER;
        	FILE_PATH_TO_RETURN=ApplicationConstants.IMAGE_FINDER_URL;
            extension = ".gif";
        } else if (mimeType.equals("image/png")) {
        	DESTINATION_DIR_PATH=ApplicationConstants.ROOT_IMAGE_FOLDER;
        	FILE_PATH_TO_RETURN=ApplicationConstants.IMAGE_FINDER_URL;
            extension = ".png";            
        } else if (mimeType.equals("image/jpeg")) {
        	DESTINATION_DIR_PATH=ApplicationConstants.ROOT_IMAGE_FOLDER;
        	FILE_PATH_TO_RETURN=ApplicationConstants.IMAGE_FINDER_URL;
            extension = ".jpg";
        }
      
        new File(DESTINATION_DIR_PATH).mkdirs();
        //set the destination dir
        destinationDir = new File(DESTINATION_DIR_PATH);
        map.put(ApplicationConstants.UPLOADED_FILE_NAME, uploadedFileName);
        String fileName = CommonFunctions.constructUploadedImageName(uploadedFileName, extension);
        map.put(ApplicationConstants.CONSTRUCTED_FILE_NAME, fileName);
        try {
            FileItem item = file.getFileItem();
            File file1 = new File(destinationDir, fileName);
            item.write(file1);
            map.put(ApplicationConstants.PHYSICAL_FILE_PATH, file1.getAbsolutePath());
        } catch (FileUploadException ex) {
            System.out.println("Error encountered while parsing the request" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error encountered while uploading file" + ex.getMessage());
        }

        FILE_PATH_TO_RETURN = FILE_PATH_TO_RETURN + fileName;
        System.out.println("FILE_PATH_TO_RETURN" + FILE_PATH_TO_RETURN);
        map.put(ApplicationConstants.FILE_URL, FILE_PATH_TO_RETURN);
        return map;
    }
}
