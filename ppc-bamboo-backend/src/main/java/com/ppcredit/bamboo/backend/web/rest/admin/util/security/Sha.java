package com.ppcredit.bamboo.backend.web.rest.admin.util.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: Sha
 * @Description: Sha加密算法
 * @author yusonghai
 * @date 2015-1-30 下午06:13:15
 *
 */ 
public class Sha {

	private static Log log = LogFactory.getLog(Sha.class);
	
	/**
	 * @Title: sha1
	 * @Description: 返回SHA-1加密字符串
	 * @param decript
	 * @return
	 */ 
	public static String sha1(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * @Title: sha
     * @Description: 返回SHA加密字符串
     * @param decript
     * @return
     */
    public static String sha(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
        	log.error(e.getMessage(), e);
        }
        return "";
    }

    /**
	 * @Title: sha1Byte
	 * @Description: 返回SHA-1加密字符串
	 * @param decript
	 * @return
	 */
	public static byte[] sha1Byte(String decript) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			return messageDigest;
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
