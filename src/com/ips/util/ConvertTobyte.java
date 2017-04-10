package com.ips.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ConvertTobyte {

	public static byte[] getFileName(String name) throws IOException {

		File file = new File(name);
		@SuppressWarnings("resource")
		FileInputStream stream = new FileInputStream(file);
		byte[] bs = new byte[stream.available()];
		stream.read(bs);
		return bs;
	}

}
