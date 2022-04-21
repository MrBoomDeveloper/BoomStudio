package com.mrboomdev.androidstudio.utils;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class Files {
	
	public boolean writeFile(String path, String name, String content) {
		try {
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			OutputStream fOut = null;
    		File file = new File(path, name);
    		if(file.exists())
        		file.delete();
    		file.createNewFile();
    		fOut = new FileOutputStream(file);
    		fOut.flush();
    		fOut.close();
    		return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public void writeFolder(String path) {
		
	}
	
	public void deletePath(String path) {
		
	}
	
	public boolean isExist(String path) {
		if(1 ==  2) {
			return true;
		}
		return false;
	}
	
	public boolean unzip(String from, String to, boolean isAsset) {
		return false;
	}
	
	public boolean zip(String from, String to) {
		return false;
	}
}
