package com.umpay.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.Certificate;  
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;

public class CertUtils {

	/**
	 * 获取证书对象
	 * 
	 * @return
	 * @throws IOException
	 * @throws CertificateException
	 */
	public static X509Certificate getCert(String filePath) throws IOException, CertificateException {
		byte[] bs = FileUtils.readFileToByteArray(new File(filePath));
		ByteArrayInputStream bais = new ByteArrayInputStream(bs);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate x509Certificate = (X509Certificate) cf.generateCertificate(bais);
		return x509Certificate;
	}

	/**
	 * 获取私钥对象
	 * 
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static PrivateKey getPrivateKey(String filePath) throws IOException, GeneralSecurityException {
//		byte[] bs = null ;
//		if(JarUtil.isStartupFromJar(CertUtils.class)){
//			//jar中运行
//			bs = JarUtil.getbyteForJar(filePath);
//		}else{
//			bs = FileUtils.readFileToByteArray(new File(filePath));
//		}
		LogUtil.info("cert-"+filePath);
		byte[]  bs = FileUtils.readFileToByteArray(new File(filePath));
		PKCS8EncodedKeySpec peks = new PKCS8EncodedKeySpec(bs);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(peks);
	}


	
	
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

	/**
	 * 锟教伙拷锟斤拷锟角╋拷锟�
	 * 
	 * @param plain
	 * @param merId
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyException
	 */
	public static String sign(String plain, PrivateKey pkey) throws GeneralSecurityException,
			UnsupportedEncodingException {
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
		sig.initSign(pkey);
		sig.update(plain.getBytes("GBK"));
		byte signData[] = sig.sign();
		String sign = new String(Base64.encode(signData));
		return sign;
	}
	
	/**
	 * 锟教伙拷锟斤拷锟角╋拷锟�
	 * 
	 * @param plain
	 * @param merId
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyException
	 */
	public static String sign(String plain, PrivateKey pkey,String charSet) throws GeneralSecurityException,
			UnsupportedEncodingException {
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
		LogUtil.info(pkey);
		sig.initSign(pkey);
		sig.update(plain.getBytes(charSet));
		byte signData[] = sig.sign();
		String sign = new String(Base64.encode(signData));
		return sign;
	}

	/**
	 * 平台锟斤拷锟斤拷锟角�
	 * 
	 * @param sign
	 * @param plain
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean verify(String sign, String plain, X509Certificate cert) throws GeneralSecurityException,
			UnsupportedEncodingException {
		byte[] signData = Base64.decode(sign.getBytes());
		Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
		sig.initVerify(cert);
		sig.update(plain.getBytes("GBK"));
		return sig.verify(signData);
	}

	/**
	 * 使锟矫癸拷钥锟斤拷锟斤拷
	 * 
	 * @param data
	 * @param cert
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(byte[] data, X509Certificate cert) throws Exception {
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, cert.getPublicKey());

		return new String(Base64.encode(cipher.doFinal(data)));
	}
	
	
	//锟角对称斤拷锟斤拷
	public static String asymmetricDecrypt(String transformation, String cipherText, PrivateKey key) throws Exception{
		byte[] encrypted = Base64.decode(cipherText.getBytes());
	    Cipher cipher = Cipher.getInstance(transformation);
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    cipher.update(encrypted);
	    
	    return new String(Base64.encode(cipher.doFinal()));
//	    return new String(cipher.doFinal());
	}
	
	//锟角对称斤拷锟斤拷
	public static String asymmetricDecrypt(String transformation, byte[] cipherText, Key key) throws Exception{
	    Cipher cipher = Cipher.getInstance(transformation);
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    cipher.update(cipherText);
	    
	    return new String(Base64.encode(cipher.doFinal()));
	}
	
	
	
	public static Key toKey(byte[] Key) throws Exception{
		
		SecretKeySpec secretKey=new SecretKeySpec(Key,"AES");
		return secretKey; 
	} 
	
	
	
	public static String aesDecrypt(byte[] data, byte[] key) throws Exception{ 
		Key k = toKey(key); 
		LogUtil.info(k);
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher=Cipher.getInstance("AES/ECB/NoPadding"); 
        cipher.init(Cipher.DECRYPT_MODE, k); 
        return new String(cipher.doFinal(data)); 
    } 
}
