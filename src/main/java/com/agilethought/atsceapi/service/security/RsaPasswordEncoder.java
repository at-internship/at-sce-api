
package com.agilethought.atsceapi.service.security;

import com.agilethought.atsceapi.exception.BadRequestException;
import com.sun.istack.NotNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class RsaPasswordEncoder {

    public static final String RSA_ECB_PKCS_1_PADDING = "RSA/ECB/PKCS1Padding";

    private KeyFactory keyFactory;

    @Value("${sce.enc.key.public}")
    private String PUBLIC_KEY;

    @Value("${sce.enc.key.private}")
    private String PRIVATE_KEY;
    
    public RsaPasswordEncoder() { }
    
    public RsaPasswordEncoder(String PUBLIC_KEY, String PRIVATE_KEY) {
		this.PUBLIC_KEY = PUBLIC_KEY;
		this.PRIVATE_KEY = PRIVATE_KEY;
	}
    
    public String encode(String plainText) {
        try {
			return encrypt(plainText);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			throw new BadRequestException("The password must be in plain text");
		}
    }
    
    public String decode(String encryptedString) {
        try {
			return decrypt(encryptedString);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			
			throw new BadRequestException("The password must be encoded");
		}
    }
    
    @SneakyThrows
    public boolean matches(String receivedPassword, String dbPassword) {
        String decryptedDBPass = decrypt(dbPassword);
        return decryptedDBPass.equals(receivedPassword);
    }

    private String encrypt(String data) throws NoSuchAlgorithmException, 
	NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher;
		byte [] encyryptedBytes;
		String encryptedStr = "";
		cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(PUBLIC_KEY));
		encyryptedBytes = cipher.doFinal(data.getBytes());
		encryptedStr = Base64.getEncoder().encodeToString(encyryptedBytes);
		
		return encryptedStr;
	}

	
	private String decrypt(String encryptedStr) throws NoSuchAlgorithmException, 
	NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {
		Cipher cipher;
		byte [] encryptedBytes; 
		byte [] decryptedBytes = null;
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(PRIVATE_KEY));
			encryptedBytes = Base64.getDecoder().decode(encryptedStr.getBytes());
			decryptedBytes = cipher.doFinal(encryptedBytes);

		return new String(decryptedBytes);
	}
    private PublicKey getPublicKey(String base64PublicKey) {
		PublicKey publicKey = null;
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	private PrivateKey getPrivateKey(String base64PrivateKey) {
		PrivateKey privateKey = null;
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			privateKey = keyFactory.generatePrivate(keySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privateKey;
	}
}
