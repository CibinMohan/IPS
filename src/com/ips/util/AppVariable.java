package com.ips.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.ips.bean.CaseBean;

import android.widget.EditText;

public class AppVariable {

	public static String curLatitude = "";
	public static String curLongitude = "";
	public static String selected_location = "";
	public static EditText LOCATION_NAME_ASSIGN = null;
	public static String IMAGE_FILE_PATH = "";
	public static File IMAGE_FILE = null;
	public static String VIDEO_FILEPATH = "";
	public static ArrayList<CaseBean> SEARCH_RESULT_ARRY_LIST = null;
	public static String POSI_ID = "";
	public static HashMap<Integer, String> HASH_MAP = null;
}
