package com.umpay.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JarUtil {
    public static final Logger APP = LoggerFactory.getLogger("info"); 
	public static void main(String[] args) {
		
	}
	public static<T> boolean isStartupFromJar(Class<T> clazz) {
	    File file = new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath());
	    return file.isFile();
	}


	public static byte[] getbyteForJar(String path) throws IOException {
		String jarPath = JarUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		URL url = new URL("jar:file:" + jarPath + "!/" + path);
		APP.info("URL:"+url.toString());
		InputStream is = url.openStream();
		return toByteArray(is);
	}

	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static int copy(InputStream input, OutputStream output) throws IOException {
		long count = copyLarge(input, output);
		if (count > 2147483647L) {
			return -1;
		}
		return (int) count;
	}

	public static long copyLarge(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[4096];
		long count = 0L;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
}
