package com.crypto.cryptutil;

public class testEncryptDecryptUtil {
	
	private static final EncryptDecryptUtil encryptUtil = EncryptDecryptUtil.createInstance();
//	private static final String CLEAR_TEXT = "{\"EiamIdReq\":\"61F7200E064F400EA4219E5598122E68\"}";
//	private static final String ENCRYPTION_KEY =    "dMmam5dOb6+DTTmo2CLGXjnvlCm0KgIZrNZfRXt8L3E=";
//	private static final String BHF_ENCRYPTED_TEXT = "SVCn85Icldd67XXqgnujll+5P0nCnQxECoiufcR08ZzIhljVFvOtl3kiaRyQeKDE6CJMpMyKpqmYAhQW40Ciog==";
	public static void main(String[] args) {
		EncryptDecryptUtil.createInstance();
		if(args[0].equalsIgnoreCase("ENCRYPT")){
			System.out.println("The plaintext to encrypt:"+args[1]+" with key"+args[2]);
			String encryptedText = EncryptDecryptUtil.encrypt(args[1], args[2]);	
			System.out.println("The encrypted text:"+encryptedText);
		}else{
			System.out.println("The cipher to decrypt:"+args[1]+" with key"+args[2]);
			String decryptedText = EncryptDecryptUtil.decrypt(args[1], args[2]);
			System.out.println("The decrypted text:"+decryptedText);	
		}

	
	}

}

