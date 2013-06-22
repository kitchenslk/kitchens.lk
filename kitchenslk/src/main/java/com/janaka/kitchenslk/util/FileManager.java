package com.janaka.kitchenslk.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
    	FILE_PATH_TO_RETURN = ApplicationConstants.FILE_FINDER_URL;
    	Map<String,String> map=new HashMap<String,String>();
    	
    	       
        String mimeType = file.getContentType();
                   
        Map<String,String> mimeMap=CommonFunctions.getMimemap(mimeType);
        DESTINATION_DIR_PATH=mimeMap.get(ApplicationConstants.DESTINATION_DIR_PATH);
        String extension=mimeMap.get(ApplicationConstants.EXTENSION);        
        map.put(ApplicationConstants.THUMBNAIL_PATH, mimeMap.get(ApplicationConstants.THUMBNAIL_PATH));
        
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
