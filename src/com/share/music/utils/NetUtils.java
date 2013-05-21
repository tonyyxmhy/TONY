package com.share.music.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtils {

	public static final String LRCPATH = "/data/data/com.share.music/";

	public static File getLRC(String url, int id) {

		File file = new File(LRCPATH + id + ".lrc");
		if (!file.exists()) {

			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			URLConnection connection = new URL(url).openConnection();
			connection.connect();
			InputStream fin = connection.getInputStream();
			BufferedInputStream in = new BufferedInputStream(fin);

			FileOutputStream f = new FileOutputStream(file);

			byte[] b = new byte[1024];

			while (in.read(b) != -1) {

				f.write(b);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return file;

	}

}
