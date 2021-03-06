package com.janaka.kitchenslk.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component(value="encryptionUtil")
public class EncryptionUtil {
	
	private static EncryptionUtil instance;

    private EncryptionUtil() {
    }

    public synchronized String encrypt(String plaintext) {
    	
    	MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(plaintext.getBytes("UTF-8"),0, plaintext.length());  
	    	String hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);  
	    	if (hashedPass.length() < 32) {
	    	  hashedPass = "0" + hashedPass; 
	    	}
	    	return hashedPass;
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
    	    
        return null;
    }

    public static synchronized EncryptionUtil getInstance() {
        if (instance == null) {
            instance = new EncryptionUtil();
        }
        return instance;
    }

}
