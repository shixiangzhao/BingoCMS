package com.shixzh.bcms.framework.shiro;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;

public class ShiroMessageDigest {

	public void messageDigest() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = "hello world".getBytes();
			md.digest(bytes);
			byte[] hashed = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String myFile = "fileName";
		String hex = new Md5Hash(myFile).toHex();
		String encodedPassword = new Sha512Hash("password", "salt").toBase64();
		
		// Listing 13. Apache Shiro’s Encryption API

		AesCipherService cipherService = new AesCipherService();
		cipherService.setKeySize(256);
		//create a test key:
		byte[] testKey = cipherService.generateNewKey().getEncoded();

		byte[] fileBytes = "hello world".getBytes();
		//encrypt a file’s bytes:
		byte[] encrypted = cipherService.encrypt(fileBytes, testKey).getBytes();
	}
}
