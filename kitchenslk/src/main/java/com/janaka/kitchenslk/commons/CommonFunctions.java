package com.janaka.kitchenslk.commons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.janaka.kitchenslk.entity.CommonDomainProperty;
import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.entity.SystemUserDetail;
import com.janaka.kitchenslk.entity.TempSystemUser;
import com.janaka.kitchenslk.enums.Status;
import com.janaka.kitchenslk.util.ApplicationConstants;
import com.janaka.kitchenslk.util.DesEncrypter;
import com.janaka.kitchenslk.util.RandomStringGenerator;



@SuppressWarnings("unchecked")
@Component
public class CommonFunctions {
	
	private static CommonFunctions instance;

    private CommonFunctions() {
    }
    
    public static synchronized CommonFunctions getInstance() {
        if (instance == null) {
            instance = new CommonFunctions();
        }
        return instance;
    }

	

	/**
	 * This creates a {@link CommonDomainProperty} which is used when Saving an
	 * entity to the database
	 * 
	 * @return {@link CommonDomainProperty} cdp
	 */
	public static CommonDomainProperty getCommonDomainPropertyForSavingEntity(SystemUser userId) {

		return generateCommonDomainPropertyFor(null, userId, null);
	}

	/**
	 * This creates modifies {@link CommonDomainProperty} when an entity is to
	 * be updated in the database.
	 * 
	 * @return {@link CommonDomainProperty} cdp
	 */
	public static CommonDomainProperty getCommonDomainPropertyForUpdatingEntity(CommonDomainProperty cdp, SystemUser userId) {

		return generateCommonDomainPropertyFor(cdp, null, userId);
	}

	/**
	 * This creates a {@link CommonDomainProperty} if a null} is passed in or
	 * sets the lastModifiedDate for the passed cdp.
	 * @param updatedUser TODO
	 * 
	 * @param {@link Long } userId - pass null if you are saving an Entity
	 * @param {@link CommonDomainProperty} cdp - - pass null if you are saving
	 *        an Entity
	 * 
	 * @return {@link CommonDomainProperty} cdp
	 */
	private static CommonDomainProperty generateCommonDomainPropertyFor(CommonDomainProperty cdp, SystemUser createdUser, SystemUser updatedUser) {

		Date now = new GregorianCalendar().getTime();
		// If the operation is a save
		if (cdp == null) {
			cdp = new CommonDomainProperty(now, createdUser);
		} else {// If the operation is an update
			cdp.setLastModifiedDate(now);
			cdp.setLastModifiedUser(updatedUser);
		}

		return cdp;
	}

	/**
	 * Reverses the String password provided and appends two random
	 * {@link Integer}'s as a prefix and suffix
	 * 
	 * @param String
	 *            password
	 * @return recordId
	 */
	public static String generateTempUserRecordId(String password) {

		String recordId = StringUtils.reverse(password);
		Random random = new Random();
		String prefix = Integer.toString(random.nextInt(9));
		String suffix = Integer.toString(random.nextInt(9));
		recordId = prefix + recordId + suffix;

		return recordId;
	}

	

	/**
	 * Creates a {@link TempSystemUser} using the username and password and
	 * without setting any of the encrypted data.
	 * 
	 * @param {@link String} userName
	 * @param {@link String} password
	 * @return {@link TempSystemUser} createdTempSystemUser
	 */
	public static TempSystemUser createTempSystemUser(String userName, String password) {
		TempSystemUser tempSystemUser = new TempSystemUser();
		tempSystemUser.setRecordId(generateTempUserRecordId(password));
		tempSystemUser.setTempUserName(userName);
		tempSystemUser.setCommonDomainProperty(generateCommonDomainPropertyFor(null, null, null));
		tempSystemUser.setStatus(Status.PENDING);
		return tempSystemUser;
	}

	

	/**
	 * Will accept the map constructed in toSystemUserMap() method in
	 * {@link SystemUser} constructs the user name either from the first name
	 * and the last name or the user name
	 * 
	 * @param userMap
	 * @return
	 */
	public String getConstructedNameFromSystemUserMap(Map<String, Object> userMap) {
		// first check if the user has a patient detail object
		if(!(userMap.isEmpty())){
			Map<String, Object> userDetail = (Map<String, Object>) userMap.get("systemUserDetail");
			// check if the patientDetail object is available.
			if (!(userDetail.get("patientDetail") == null)) {
				// if it is available, other details are encrypted
				return getConstructedNameFromEncryptedUserMap(userMap);
			} else {
				// other details are not encrypted
				return getConstructedNameFromNormalUserMap(userMap);
			}
		}
		return null;
	}

	/**
	 * @param patientDetail
	 * @return
	 */
	public String getConstructedNameFromPatientDetailMap(Map<String, Object> patientDetail) {
		if(!(patientDetail.isEmpty())){
			byte[] secretKey = ((String) patientDetail.get("secretKeyString")).getBytes();
			StringBuffer nameBuffer = new StringBuffer();
			if (!(patientDetail.get("patientFirstName") == null)) {
				nameBuffer.append(getDecryptedValue(secretKey, (String) patientDetail.get("patientFirstName")));
			}
			if (!(patientDetail.get("patientLastName") == null)) {
				nameBuffer.append(getDecryptedValue(secretKey, (String) patientDetail.get("patientLastName")));
			}
			return nameBuffer.toString();
		}
		return null;
	}

	/**
	 * To get the name when the details are not encrypted in
	 * {@link SystemUserDetail}
	 * 
	 * @param userMap
	 * @return
	 */
	public static String getConstructedNameFromNormalUserMap(Map<String, Object> userMap) {
		if(!(userMap.isEmpty())){
			Map<String, Object> userDetail = (Map<String, Object>) userMap.get("systemUserDetail");
			StringBuffer userNameBuffer = new StringBuffer();
			if (!(userDetail.get("firstName") == null)) {
				String fName = (String) userDetail.get("firstName");
				if (StringUtils.isNotEmpty(fName)) {
					userNameBuffer.append(fName);
					if (!(userDetail.get("lastName") == null)) {
						String lName = (String) userDetail.get("lastName");
						if (StringUtils.isNotEmpty(lName)) {
							userNameBuffer.append(" ");
							userNameBuffer.append(lName);
						}
					}
					return userNameBuffer.toString();
				} else {
					if (!(userDetail.get("lastName") == null)) {
						String lName = (String) userDetail.get("lastName");
						if (StringUtils.isNotEmpty(lName)) {
							userNameBuffer.append(lName);
							return userNameBuffer.toString();
						}
					}
				}
			}
			userNameBuffer.append(getNameFromUserName(userMap));
			return userNameBuffer.toString();
		}
		return null;
	}

	/**
	 * To get the name when the details are encrypted in
	 * {@link SystemUserDetail}
	 * 
	 * @param userMap
	 * @return
	 */
	private String getConstructedNameFromEncryptedUserMap(Map<String, Object> userMap) {
		Map<String, Object> userDetail = (Map<String, Object>) userMap.get("systemUserDetail");
		Map<String, Object> patientDetail = (Map<String, Object>) userDetail.get("patientDetail");
		byte[] secretKey = ((String) patientDetail.get("secretKeyString")).getBytes();
		StringBuffer userNameBuffer = new StringBuffer();
		if (!(userDetail.get("firstName") == null)) {
			userNameBuffer.append(getDecryptedValue(secretKey, (String) userDetail.get("firstName")));
			if (!(userDetail.get("lastName") == null)) {
				userNameBuffer.append(" ");
				userNameBuffer.append(getDecryptedValue(secretKey, (String) userDetail.get("lastName")));
			}
		} else {
			userNameBuffer.append(getNameFromUserName(userMap));
		}
		return userNameBuffer.toString();
	}

	/**
	 * To get the name from user name-removes the part after @ sign
	 * 
	 * @param userMap
	 * @return
	 */
	private static String getNameFromUserName(Map<String, Object> userMap) {
		String userName = (String) userMap.get("userName");
		System.out.println("userName before :" + userName);
		userName = ((userName.split("@")))[0].trim();
		System.out.println("userName after :" + userName);
		return userName;

	}

	/**
	 * To get the decrypted value
	 * 
	 * @param secretKey
	 * @param encryptedValue
	 * @return
	 */
	public static String getDecryptedValue(byte[] secretKey, String encryptedValue) {
		SecretKey key = new SecretKeySpec(secretKey, "DES");
		DesEncrypter desEncrypter = new DesEncrypter(key);
		if (StringUtils.isNotEmpty(encryptedValue)) {
			return desEncrypter.decrypt(encryptedValue);
		}
		return null;
	}

	/**
	 * To get the encrypted value
	 * 
	 * @param secretKey
	 * @param normalValue
	 * @return
	 */
	public static String getEncryptedValue(byte[] secretKey, String normalValue) {
		SecretKey key = new SecretKeySpec(secretKey, "DES");
		DesEncrypter desEncrypter = new DesEncrypter(key);
		return desEncrypter.encrypt(normalValue);
	}

	/**
	 * @param systemUser
	 * @return
	 */
	public String constructSystemUserNameForDisplay(SystemUser systemUser) {
		String userName=systemUser.getUserName();
		if(!(systemUser.getSystemUserDetail()==null)){
			if(StringUtils.isNotEmpty(systemUser.getSystemUserDetail().getFirstName())){
				userName=systemUser.getSystemUserDetail().getFirstName();
				if(StringUtils.isNotEmpty(systemUser.getSystemUserDetail().getLastName())){
					userName=userName + " " + systemUser.getSystemUserDetail().getLastName();
				}
			}else{
				if(StringUtils.isNotEmpty(systemUser.getSystemUserDetail().getLastName())){
					userName=systemUser.getSystemUserDetail().getLastName();
				}
			}
			
		}
		return userName;
	}

	/**
	 * @return
	 */
	public static String constructUploadedImageName(String uploadedImageName, String extension) {
		StringBuffer randomString=new StringBuffer("kitchenslk_");
		if(StringUtils.isNotEmpty(uploadedImageName)){
			uploadedImageName=uploadedImageName.replaceAll("\\s","").trim();
			uploadedImageName=uploadedImageName.replaceAll("\\.","_").trim();
			if(uploadedImageName.length()>10){
				uploadedImageName=uploadedImageName.substring(0,8);
			}
			randomString.append(uploadedImageName);
			randomString.append("_");
		}		
		randomString.append(RandomStringGenerator.getInstance().getRandomString());
		randomString.append(extension);
		System.out.println("GENERATED IMAGE NAME :" + randomString.toString());
		return randomString.toString();
	}

	public static Map<String, String> getMimemap(String mimeType) {
		System.out.println("mimeType :" + mimeType);
		String DESTINATION_DIR_PATH=ApplicationConstants.DIRECTORY_ROOT;
		String  extension = ".txt";
		String THUMBNAIL_PATH="images/text_icon.jpg";
		//application/vnd.openxmlformats-officedocument.spreadsheetml.sheet - xlsx
		//application/vnd.ms-excel - xls
		if(StringUtils.isNotEmpty(mimeType)){
			if (mimeType.equals("text/plain")) {
	        	DESTINATION_DIR_PATH = ApplicationConstants.ROOT_DOCUMENT_FOLDER;
	            extension = ".txt";
	            THUMBNAIL_PATH="images/text_icon.jpg";
	        } else if (mimeType.equals("application/pdf")) {
	        	DESTINATION_DIR_PATH = ApplicationConstants.ROOT_DOCUMENT_FOLDER;
	            extension = ".pdf";
	            THUMBNAIL_PATH="images/pdf_icon.png";
	        } else if (mimeType.equals("application/msword")) {
	        	DESTINATION_DIR_PATH = ApplicationConstants.ROOT_DOCUMENT_FOLDER;
	            extension = ".doc";
	            THUMBNAIL_PATH="images/doc_icon.png";
	        } else if (mimeType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ) {
	        	DESTINATION_DIR_PATH = ApplicationConstants.ROOT_DOCUMENT_FOLDER;
	            extension = ".docx";
	            THUMBNAIL_PATH="images/doc_icon.png";
	        } else if (mimeType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ) {
	        	DESTINATION_DIR_PATH = ApplicationConstants.ROOT_DOCUMENT_FOLDER;
	            extension = ".xlsx";
	            THUMBNAIL_PATH="images/excel-icon.jpg";
	        }else if (mimeType.equals("application/vnd.ms-excel") ) {
		        DESTINATION_DIR_PATH = ApplicationConstants.ROOT_DOCUMENT_FOLDER;
		        extension = ".xls";
		        THUMBNAIL_PATH="images/excel-icon.jpg";
	        } else if (mimeType.equals("image/gif")) {
	        	DESTINATION_DIR_PATH=ApplicationConstants.ROOT_IMAGE_FOLDER;        	
	            extension = ".gif";
	            THUMBNAIL_PATH="images/gif_icon.jpg";
	        } else if (mimeType.equals("image/png")) {
	        	DESTINATION_DIR_PATH=ApplicationConstants.ROOT_IMAGE_FOLDER;
	            extension = ".png"; 
	            THUMBNAIL_PATH="images/png_icon.jpg";
	        } else if (mimeType.equals("image/jpeg")) {
	        	DESTINATION_DIR_PATH=ApplicationConstants.ROOT_IMAGE_FOLDER;
	            extension = ".jpg";
	            THUMBNAIL_PATH="images/jpg_icon.png";
	        }
		}
		Map<String,String> resultMap=new HashMap<String,String>();
		resultMap.put(ApplicationConstants.DESTINATION_DIR_PATH, DESTINATION_DIR_PATH);
		resultMap.put(ApplicationConstants.EXTENSION, extension);
		resultMap.put(ApplicationConstants.THUMBNAIL_PATH, THUMBNAIL_PATH);
		return resultMap;
	}

	
	public static SimpleDateFormat getGlobalSimpleDateFormat() {
		SimpleDateFormat sdf=new SimpleDateFormat(ApplicationConstants.GLOBAL_DATE_FORMAT);
		return sdf;
	}

	

}
