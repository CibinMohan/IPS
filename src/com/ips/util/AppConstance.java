package com.ips.util;

import java.io.File;

import android.os.Environment;

public class AppConstance {

	// web configurations 
	private static String IP = "192.168.1.3";
	public static final String Name_Space = "http://webservice.ips.com/";
	public static final String URL = "http://" + IP
			+ ":8084/IPS/IpsWebService?wsdl";
	
	// file management
	public static final String SHARED_PREF = "IPS";
	public static final String CREATED_FILE_NAME = "IPS_FILES";
	public static final String TEMP_FILE_NAME = Environment
			.getExternalStorageDirectory().getPath()
			+ File.separator
			+ CREATED_FILE_NAME
			+ File.separator
			+ "IPS_TEMP_FILE"
			+ File.separator;

	// webservice methods
	public static final String LOGIN = "login";
	public static final String SEARCH_ADHAR = "searchAadhaarNo";
	public static final String SEARCH_VAHICLE = "SearchVehicleNo";
	public static final String GET_ALL_CASES = "getAllCases";
	public static final String ADD_NEW_CASES = "AddNewCase";
	public static final String ADD_IMAGE = "AddImage";
	public static final String ADD_VIDEO = "addVideo";

}
