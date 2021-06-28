package com.netcom.nkestate.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PassGenerator {

	public static String generate(String pass) {
		if(pass == null){
			throw new IllegalArgumentException();
		}
		byte[] digest = null;
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(pass.getBytes());

			digest = md.digest();
		}catch (NoSuchAlgorithmException e){
		}

		StringBuffer result = new StringBuffer();
		for(int i = 0; i < digest.length; ++i){
			int x = digest[i] & 0xFF;
			result.append(Integer.toHexString(x));
		}

		return result.toString();
	}

	public static String generateHalfInt() {
		int numBits = 20;
		int length = 6;
		String zero = "0";
		BigInteger rangeFrom = new BigInteger("100000");
		BigInteger rangeTo = new BigInteger("1000000");
		Random rnd = new Random();
		BigInteger result = null;
		result = new BigInteger(numBits, rnd);
		if(result.compareTo(rangeTo) >= 0){
			result = result.subtract(rangeTo);
		}

		if(result.compareTo(rangeFrom) < 0){
			String str = result.toString();
			for(int i = str.length(); i < length; i++){
				str = str + zero;
			}
			result = new BigInteger(str);
		}

		return result.toString();
	}
}
