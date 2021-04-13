package com.employee.api.decrypt;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.employee.api.exceptions.ProjectException;

public class DecryptPassword {

	private static final String SECRET="=*@x/WrWt4T7w@E_";
	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	private DecryptPassword() {
	}

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (Exception ex) {
			throw new ProjectException(ex.getMessage());
		} 
	}

	public static String decrypt(String strToDecrypt) {
		try {
			setKey(SECRET);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception ex) {
			throw new ProjectException(ex.getMessage());
		}
	}
}
