package com.mrboomdev.androidstudio.utils;

import android.util.filestream.io;
import java.util.fileEncoding;

public class File {
	
	FileStream io = new FileStream();
	FileEncoding encoding = new FileEncoding(utf8, testRun);
	public boolean writeFile(String path, String content) {
		io.open();
		io.writeFile(encoding, path, content);
		while(io.open()) {
			if(io.content().pos() == io.length()) {
				io.close();
			}
		}
		io.openPath(path);
		if(io.content() != null) {
			return true;
		}
		return false;
	}
}