package com.mrboomdev.androidstudio.utils;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.os.Environment;

public class Files {
	
	public boolean writeFile(String path, String name, String content) {
		File directory = new File(Environment.getExternalStorageDirectory() + name);

        if(!directory.exists())
            directory.mkdir();

        File newFile = new File(directory, name);

        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try  {
            FileOutputStream fOut = new FileOutputStream(newFile);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fOut);
            outputWriter.write(content);
            outputWriter.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
