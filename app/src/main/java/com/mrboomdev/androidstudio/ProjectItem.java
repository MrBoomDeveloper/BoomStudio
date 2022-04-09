package com.mrboomdev.androidstudio;

public class ProjectItem {
	private String mLang, mName, mPath;
	
	public ProjectItem(String lang, String name, String path) {
		mLang = lang;
		mName = name;
		mPath = path;
	}
	
	public String getLang() {
		return mLang;
	}
	public String getName() {
		return mName;
	}
	public String getPath() {
		return mPath;
	}
}