package com.share.PACManager.utils;
public class MD5 {
	public static byte[] getMD5(byte[] source) {
		char hexDigits[] = { // �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 �ļ�������һ�� 128 λ�ĳ�������
			// ���ֽڱ�ʾ���� 16 ���ֽ�
			byte str[] = new byte[16 * 2]; // ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ���
			// ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
			int k = 0; // ��ʾת������ж�Ӧ���ַ�λ��
			for (int i = 0; i < 16; i++) { // �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
				// ת���� 16 �����ַ���ת��
				byte byte0 = tmp[i]; // ȡ�� i ���ֽ�
				str[k++] = (byte) hexDigits[byte0 >>> 4 & 0xf]; // ȡ�ֽ��и� 4
																// λ������ת��,
				// >>> Ϊ�߼����ƣ�������λһ������
				str[k++] = (byte) hexDigits[byte0 & 0xf]; // ȡ�ֽ��е� 4 λ������ת��
			}
			return str;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
} 
