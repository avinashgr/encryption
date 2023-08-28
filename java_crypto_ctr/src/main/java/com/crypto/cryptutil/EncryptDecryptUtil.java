package com.crypto.cryptutil;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecryptUtil {
	
	/** Initialization vector block size. */
    private static final int INIT_VECTOR_SIZE = 16;
    public static EncryptDecryptUtil t;
    
    private EncryptDecryptUtil() {
        // TODO Auto-generated constructor stub
    }
    
    public static EncryptDecryptUtil createInstance(){
    	if(null==t){
    		t =  new EncryptDecryptUtil();
    	}
    	return t;
    }
	    

	public static String encrypt(String toEncrypt, String key){
		final byte[] keyBytes = Base64.getDecoder().decode(key.getBytes());
		String encrypted = "";
		byte[] encryptedBytes;
		try {
			encryptedBytes = t.encryptIOT(toEncrypt.getBytes(), keyBytes);
			encrypted = new String(Base64.getEncoder().encode(encryptedBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}        	
		return encrypted;		
	}
	
	public static String decrypt(String toDecrypt, String key){
		final byte[] keyBytes = Base64.getDecoder().decode(key.getBytes());
		String decrypted = "";
		byte[] bytesToDecrypt  = Base64.getDecoder().decode(toDecrypt.getBytes());
		try {
			bytesToDecrypt = t.decryptIOT(bytesToDecrypt, keyBytes);
			decrypted = new String(bytesToDecrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}        	
		return decrypted;		
	}
	protected byte[] encryptIOT(byte[] dataToEncrypt, byte[] encryptionKey) throws Exception {	
		  
		  final byte[] initVector = new byte[INIT_VECTOR_SIZE];		
		  SecureRandom secuRand = new SecureRandom();
		  // set IV into cipher to prefix encrypted bytes
		  secuRand.nextBytes(initVector);
		  SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
		  IvParameterSpec ivspec = new IvParameterSpec(initVector);
		  
		  byte[] encryptedData = null;
		  
		  try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CTR/NoPadding");
			  cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, keySpec, ivspec);
			  encryptedData = cipher.doFinal(dataToEncrypt);
		  } catch (Exception e) {
			e.printStackTrace();
			throw new Exception("::::::ENCRYPTION FAILED::::::");
		  }
		  
		  byte[] envelopedData = new byte[initVector.length + encryptedData.length];
		  System.arraycopy(initVector, 0, envelopedData, 0, initVector.length);
		  System.arraycopy(encryptedData, 0, envelopedData, initVector.length, encryptedData.length);
		  return envelopedData;
	}
	
	protected byte[] decryptIOT(byte[] encryptedData, byte[] encryptionKey) throws Exception {
		
		byte[] initVector = Arrays.copyOfRange(encryptedData, 0, INIT_VECTOR_SIZE);
		byte[] dataToDecrypt = Arrays.copyOfRange(encryptedData, INIT_VECTOR_SIZE, encryptedData.length);
		SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
		IvParameterSpec initVectorSpec = new IvParameterSpec(initVector);
		
		
		
		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/CTR/NoPadding");
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, keySpec, initVectorSpec);
			byte[] decryptedData = cipher.doFinal(dataToDecrypt);
			return decryptedData;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("::::::DECRYPTION FAILED::::::");
		}
		
	}

}