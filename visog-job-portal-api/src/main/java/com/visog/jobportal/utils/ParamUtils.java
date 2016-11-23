package com.visog.jobportal.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class ParamUtils {

	private static final Logger logger = Logger.getLogger(ParamUtils.class);
	private static final Boolean IS_URL_PARAM_ENCRYPTION_REQUIRED 	= "true".equals(
													PropertyUtil.getProperty("IS_URL_PARAM_ENCRYPTION_REQUIRED"));

	/**
	 * This method decrypts the given param value and returns it
	 * @param paramValue
	 * @param userIdentifier
	 * @return
	 */
	public static String getStringParam(String paramValue, String userIdentifier) {
		
		if(IS_URL_PARAM_ENCRYPTION_REQUIRED) {
			return decryptAESCipher(paramValue, userIdentifier);
		} else {
			return paramValue;
		}
	}

	/**
	 * This method decrypts the given param value and converts it into Long type and returns it
	 * @param paramValue
	 * @param userIdentifier
	 * @return
	 */
	public static Long getLongParam(String paramValue, String userIdentifier) {
		return Long.parseLong(getStringParam(paramValue, userIdentifier));
	}

	/**
	 * This method decrypts the given cipher text with the given key
	 * @param encryptedData
	 * @param key
	 * @return
	 */
	private static String decryptAESCipher(String encryptedData, String key) {

		String decryptedData = null;
		String encryptionKey = null;

		try {
			encryptionKey = key;
			if (encryptionKey.length() > 16)
				encryptionKey = encryptionKey.substring(0, 16);

			byte encryptedDataBytes[] = Base64.getDecoder().decode(encryptedData);
			byte encryptionKeyBytes[] = encryptionKey.getBytes("UTF-8");

			Key keySpec = new SecretKeySpec(encryptionKeyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			byte decryptedDataBytes[] = cipher.doFinal(encryptedDataBytes);

			decryptedData = new String(decryptedDataBytes);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | 
					UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
			logger.error("Error in URL param encryption for the key : " + key + 
					"; value : " + encryptedData, e);
		}

		return decryptedData;

	}
	
	/**
	 * This method decrypts the given cipher text with the given key
	 * @param encryptedData
	 * @param key
	 * @return
	 */
	private static String encryptAESCipher(String encryptedData, String key) {

		String decryptedData = null;
		String encryptionKey = null;

		try {
			encryptionKey = key;
			if (encryptionKey.length() > 16)
				encryptionKey = encryptionKey.substring(0, 16);

			byte encryptionKeyBytes[] = encryptionKey.getBytes("UTF-8");

			Key keySpec = new SecretKeySpec(encryptionKeyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, keySpec);

			byte decryptedDataBytes[] = cipher.doFinal(encryptedData.getBytes());

			decryptedData = new String(decryptedDataBytes);
			

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | 
					UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
			logger.error("Error in URL param encryption for the key : " + key + 
					"; value : " + encryptedData, e);
		}

		return decryptedData;

	}
	
	private static String getBase64EncodedString(String data) {
		return new String(Base64.getEncoder().encode(data.getBytes()));
	}
	
	private static String getBase64DecodedString(String data) {
		return new String(Base64.getDecoder().decode(data.getBytes()));
	}
	
	public static void main(String[] args) {
		String key = "eyJhbGciOiJIUzI1";
		String plainStr = "amourya";
		System.out.println("plainStr : " + plainStr);
		String encryptedval = encryptAESCipher(plainStr, key);
		System.out.println("encryptedval : " + encryptedval);
		String base64EncryptedVal = getBase64EncodedString(encryptedval);
		System.out.println("base64EncryptedVal : " + base64EncryptedVal);
/*		String base64DecryptedVal = getBase64DecodedString(base64EncryptedVal);
		System.out.println("base64DecryptedVal : " + base64DecryptedVal);*/
		String decryptedVal = decryptAESCipher(base64EncryptedVal, key);
		System.out.println("decryptedVal : " + decryptedVal);
		
	}
}
