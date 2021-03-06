package com.janaka.kitchenslk.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component("desEncrypter")
public class DesEncrypter {

	Cipher ecipher;
    Cipher dcipher;

    public DesEncrypter(SecretKey key) {
        try {
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException e) {
        	e.printStackTrace();
        } catch (java.security.NoSuchAlgorithmException e) {
        	e.printStackTrace();
        } catch (java.security.InvalidKeyException e) {
        	e.printStackTrace();
        }
    }
    
    public DesEncrypter() {
    	
        
    }

   
	public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new sun.misc.BASE64Encoder().encode(enc);
        } catch (javax.crypto.BadPaddingException e) {
        	e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
        
        return null;
    }

    public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
        	if(StringUtils.isNotBlank(str)){
	            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
	
	            // Decrypt
	            byte[] utf8 = dcipher.doFinal(dec);
	
	            // Decode using utf-8
	            return new String(utf8, "UTF8");
        	}
        } catch (javax.crypto.BadPaddingException e) {
        	e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        } catch (java.io.IOException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    
   
}