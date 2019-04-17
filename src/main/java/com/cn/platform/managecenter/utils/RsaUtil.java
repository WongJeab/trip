package com.cn.platform.managecenter.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA工具类
 */
public class RsaUtil {
	private static final String KEY_SHA = "SHA";
	private static final String KEY_MD5 = "MD5";
	/** 签名算法 */
	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	/** 指定加密算法为DESede */
	private static final String KEY_ALGORITHM = "RSA";
	/** 指定公钥存放的MAP key */
	private static final String PUBLIC_KEY = "RSAPublicKey";
	/** 指定私钥存放的MAP key */
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	/** 指定key的大小 */
	private static int KEY_SIZE = 2048;

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Date s = new Date();
		Map<String, Object> keyMap;
		try {
			keyMap = initKey();
			String publicKey = getPublicKey(keyMap);
			System.out.println("公钥(BASE64)：" + publicKey);
			String privateKey = getPrivateKey(keyMap);
			System.out.println("私钥(BASE64)：" + privateKey);

			String sou = "测试test";
			System.out.println("加密前：" + sou);
			// 加密
			byte[] enP = encryptByPublicKey(sou.getBytes(), publicKey);
			String en = encryptBASE64(enP);
			System.out.println("加密后(BASE64)：" + en);
			// 解密
			byte[] dnP = decryptByPrivateKey(decryptBASE64(en), privateKey);
			String dn = new String(dnP);
			System.out.println("解加密后：" + dn);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Date e = new Date();
		String ss = String.valueOf(e.getTime() - s.getTime());
		System.out.println("耗时：" + ss + " ms");

	}

	/**
	 * 加密后的字符串如果存在"/" "+" "=", 在WEB的传输过程中(含有request之类动作）可能会发生改变
	 * "/"在客户端变为"%2F"；
	 * "+"在客户端变为" "；
	 * "="在客户端变为"%3D"；
	 * 
	 * @param base64ForNetwork
	 * @return String(BASE64)
	 * @throws Exception
	 */
	public static String networkToBASE64(String base64ForNetwork)
			throws Exception {
		String base64String = base64ForNetwork.replaceAll(" ", "+")
				.replaceAll("%2F", "/").replaceAll("%3D", "=");
		return base64String;
	}

	/**
	 * 反 BASE64
	 * 
	 * @param data(BASE64)
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String data) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(data);
	}

	/**
	 * BASE64编码
	 * 
	 * @param data
	 * @return String(BASE64)
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] data) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(data);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}

	/**
	 * 取得公钥，并转化为String类型
	 * 
	 * @param keyMap
	 * @return String(BASE64)
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		byte[] publicKey = key.getEncoded();
		return encryptBASE64(publicKey);
	}

	/**
	 * 取得私钥，并转化为String类型
	 * 
	 * @param keyMap
	 * @return String(BASE64)
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		byte[] privateKey = key.getEncoded();
		return encryptBASE64(privateKey);
	}

	/**
	 * 初始化密钥
	 * 
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/**
	 * 用私钥加密
	 * 
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            密钥
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
			throws Exception {
		// 解密密钥
		byte[] keyBytes = decryptBASE64(privateKey);
		// 取私钥
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key private_Key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, private_Key);

		return cipher.doFinal(data);
	}

	/**
	 * 用私钥解密
	 * 
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            密钥
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String privateKey)
			throws Exception {
		// 对私钥解密
		byte[] keyBytes = decryptBASE64(privateKey);
		// 取私钥
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key private_Key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, private_Key);

		return cipher.doFinal(data);
	}

	/**
	 * 用公钥加密
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            密钥
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey)
			throws Exception {
		// 还原公钥
		byte[] keyBytes = decryptBASE64(publicKey);
		// 取公钥
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key public_Key = keyFactory.generatePublic(x509EncodedKeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, public_Key);

		return cipher.doFinal(data);
	}

	/**
	 * 用公钥解密
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            密钥(BASE64)
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String publicKey)
			throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(publicKey);
		// 取公钥
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key public_Key = keyFactory.generatePublic(x509EncodedKeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, public_Key);

		return cipher.doFinal(data);
	}

	/**
	 * 通过RSA加密解密算法，我们可以实现数字签名的功能。
	 * 我们可以用私钥对信息生成数字签名，再用公钥来校验数字签名，当然也可以反过来公钥签名，私钥校验。
	 * 
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            //加密数据
	 * @param privateKey
	 *            //私钥
	 * @return String(BASE64)
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密私钥
		byte[] keyBytes = decryptBASE64(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				keyBytes);
		// 指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取私钥匙对象
		PrivateKey privateKey2 = keyFactory
				.generatePrivate(pkcs8EncodedKeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateKey2);
		signature.update(data);

		return encryptBASE64(signature.sign());
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥(BASE64)
	 * @param sign
	 *            数字签名(BASE64)
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {
		// 解密公钥
		byte[] keyBytes = decryptBASE64(publicKey);
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		// 指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取公钥匙对象
		PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicKey2);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));

	}

}