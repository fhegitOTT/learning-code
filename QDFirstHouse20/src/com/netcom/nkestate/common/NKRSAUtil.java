package com.netcom.nkestate.common;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 服务端RSA算法加解密工具类
 */
public class NKRSAUtil {

	private static final String KEY_ALGORITHM = "RSA";
	private static final String SIGNATURE_ALGORITHM = "NKRAIRSA";
	private static final String PUBLIC_KEY = "NKRAIRSAPublicKey";
	private static final String PRIVATE_KEY = "NKRAIRSAPrivateKey";
	//提前生成公钥，提供给页面公共js使用
	public static final String PUBLIC_KEY_STR = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBIxAgguK4LxOWEzZR4CG5iJB28aVR0F0ZXqy/cPS18TC1ML8c69pffn6VOiYS/jQLVrIXWS3+n7k5VAWc0gI+5j7U2JGduVQYRz9PnAul6GxCvCdfKXJOFUNg6/3zDmP2M+2Zl7X5JQ5sKyMHG5QH0OF5g0cH2DuynA9wwNJJYwIDAQAB";
	//与提前生成的公钥匹配的私钥信息
	public static final String PRIVATE_KEY_STR = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIEjECCC4rgvE5YTNlHgIbmIkHbxpVHQXRlerL9w9LXxMLUwvxzr2l9+fpU6JhL+NAtWshdZLf6fuTlUBZzSAj7mPtTYkZ25VBhHP0+cC6XobEK8J18pck4VQ2Dr/fMOY/Yz7ZmXtfklDmwrIwcblAfQ4XmDRwfYO7KcD3DA0kljAgMBAAECgYAnPctpxbw1cgDGqwHnsW8uHQShUF8HVITl77IY3q9OFoPRm4WHuVf7isnWePwW625PP8IQfEYQyOKVPCYUeHC7zky6xifaWdZzlK6Wy8BBq349ndUNcCjbJxzu5+ufRMDWxxRu37+PlQW0WRy2XoUnuwDR74vsduDJhNNTom0WAQJBAPmbjsRboDMl5ouM7XRiYqUXct9mRfGKV+TSYBlIYuikGwadML8r6I7rV6WGVLnCm2eF7AsgSGP/l1VS9yFtNmMCQQCEcbFGc5viY6a7/YM5u+9jxwy3GC9nGsJYzcmnI1ogocT9r1q980nfQCB6pXUdKua/PsWZznzh6EN3AeJaPZEBAkBFQ59oaayYxOmmy+KQq0SIX2tnBrBeCG+/dYlpOqcHu5IsUa5XPk34auIVcqNMMO6C2azYoYq1BRXvfWhKhE3VAkBfr5QYBiY1JTGs17ca2wBU4dX54es9XkPSzLpHmm7e7IDZlvrECSTxue69LRSzge87dCpmuJvYymkTbbUO5TQBAkBeCBRLv/gnAcl3bugoT6DYzQZQQKIRzWl39xYX5rlxa+eQBgVNS8+RTZHjoffk9RzLvjouJe73QzeuKw2TY8Xg";

	//提前生成公钥，提供给页面公共js使用
	public static final String PUBLIC_KEY_STR_DJ = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJcUj/PWtR+E+lh7EMe3Ka/cy/V01RI/j5LYhop62tXNef8mFgYyQ3KWhBrpJt8lekgaDR1PCFXXveaMLmKsPA6k12lglQcQh364qo8NzSYXz5ROVqH0b9214axOIk1UvM8sl5Fuozcw3zMFKq7ERF8aSPyb2NOhiVt6PLm7gvkwIDAQAB";
	//与提前生成的公钥匹配的私钥信息
	public static final String PRIVATE_KEY_STR_DJ = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAIlxSP89a1H4T6WHsQx7cpr9zL9XTVEj+PktiGinra1c15/yYWBjJDcpaEGukm3yV6SBoNHU8IVde95owuYqw8DqTXaWCVBxCHfriqjw3NJhfPlE5WofRv3bXhrE4iTVS8zyyXkW6jNzDfMwUqrsREXxpI/JvY06GJW3o8ubuC+TAgMBAAECgYB1/TeiTlLEYrvVKaC3S36+SaUsmcC5KH2DRDB3T4NMcYh2OfiYa2PaqwRiZoA0PLg6dEHgAMKeavt8mki+mPXEcpjggJ/NsCJF1jCpzp5DIBanfwLGavgDQHB7Uqcdf8zsmZGZWf2bxRiXRi7a0a/UKwS/WDFoT/gYiVvDULqHQQJBAM5bu9WHpkdSxUmDoBVtvcVKz67sMin+UsHZEhqQQwSy75LkQsU5UI/dWuZ8d46Td9sejb8fabFbqTeorsrg3CECQQCqgXmfVFapf5PcukSQWt1zZSZJ16+hT+b3AxJOfpCHsjP+snpCpMB+YfC6QP+2xZ0lgNCEYygS04SWyQZ0KbUzAkEAhF3HUKKggCORtSQfLV+lWMYyDawoSVN/ViSdPjjHJF562ihcxBGNoZqFUk8IiYYCXaVtmujVbRS0qKUk6bHuwQJBAJzg0hF53N7JRjehnnIfWH/TAj6Q18SZKs39InvcUYi4usROXNUylvXxkTcdlOoKuoMGGVSlkvp3aRf/UBPTSCcCQQC8Owv7gXiCjFkwxn/yCB9yYaFWkWBf3JzMqCt+77ymq2zNP55DcyqotF+59R6zKslylGVtRNwkTiiUoBX+e8pr";

	public static byte[] decryptBASE64(String key) {
		return Base64.decodeBase64(key);
	}

	public static String encryptBASE64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	public static String encryptBASE64ForPage(byte[] bytes) {
		return Base64.encodeBase64URLSafeString(bytes);
	}

	/**
	 * 用私钥对信息生成数字签名
	 * @param data
	 * 加密数据
	 * @param privateKey
	 * 私钥
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data,String privateKey) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		return encryptBASE64(signature.sign());
	}

	/**
	 * 校验数字签名
	 * @param data
	 * 加密数据
	 * @param publicKey
	 * 公钥
	 * @param sign
	 * 数字签名
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 */
	public static boolean verify(byte[] data,String publicKey,String sign) throws Exception {
		// 解密由base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));
	}

	public static byte[] decryptByPrivateKey(byte[] data,String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 解密<br>
	 * 用私钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(String data,String key) throws Exception {
		return decryptByPrivateKey(decryptBASE64(data), key);
	}

	/**
	 * 解密<br>
	 * 用公钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data,String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 加密<br>
	 * 用公钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(String data,String key) throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data.getBytes());
	}

	/**
	 * 加密<br>
	 * 用私钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data,String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 取得私钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String , Key> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 取得公钥
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String , Key> keyMap) throws Exception {
		Key key = keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 功能描述：获取公钥信息
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKeyForPage() throws Exception {
		Map<String , Key> keyMap = NKRSAUtil.initKey();
		Key key = keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());
		//		return URLEncoder.encode(encryptBASE64(key.getEncoded()), "UTF-8");
	}

	/**
	 * 初始化密钥
	 * @return
	 * @throws Exception
	 */
	public static Map<String , Key> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		Map<String , Key> keyMap = new HashMap(2);
		keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
		keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
		return keyMap;
	}

	public static void main2(String[] args) throws Exception {
		Map<String , Key> keyMap = NKRSAUtil.initKey();
		String publicKey = NKRSAUtil.getPublicKey(keyMap);
		String privateKey = NKRSAUtil.getPrivateKey(keyMap);
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥： \n\r" + privateKey);
		System.err.println("公钥加密——私钥解密");
		String inputStr = "我爱中国";
		byte[] encodedData = NKRSAUtil.encryptByPublicKey(inputStr, publicKey);
		byte[] decodedData = NKRSAUtil.decryptByPrivateKey(encodedData, privateKey);
		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		if(false){
			//使用预先生成的私钥信息解密
			byte[] decodedDataTemp = NKRSAUtil.decryptByPrivateKey(encodedData, PRIVATE_KEY_STR);
			String outStr = new String(decodedDataTemp);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String str = "1515047187523";
		String result = NKRSAUtil.encryptByPublicKey(str);
		System.err.println("加密后: " + result);

		
		String result2 = NKRSAUtil.decryptByPrivateKey(result);
		System.err.println("加密后: " + result2);
	}

	//私钥解密
	public static String decryptByPrivateKey(String data) throws Exception {
		byte[] decodedData = NKRSAUtil.decryptByPrivateKey(data, PRIVATE_KEY_STR);
		String str = new String(decodedData);
		return str;
	}

	//公钥加密
	public static String encryptByPublicKey(String data) throws Exception {
		byte[] b = encryptByPublicKey(data, PUBLIC_KEY_STR);
		String str = encryptBASE64(b);
		return str;
	}
}
