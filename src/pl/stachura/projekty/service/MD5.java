package pl.stachura.projekty.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class represents MD5 message-digest It's algorithm is a widely used
 * cryptographic hash function producing a 128-bit (16-byte) hash value.
 * Typically expressed in text format as a 32 digit hexadecimal number.
 * 
 * @author Stachura Bartlomiej
 */
public class MD5 {
	private static MessageDigest digester;

	static {
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method returns the string md5
	 * @param String vale
	 * @return md5 vale
	 */
	public static String crypt(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException(
					"String to encript cannot be null or zero length");
		}

		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}
}
