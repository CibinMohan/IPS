package com.ips.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.util.Base64;

import com.ips.bean.CaseBean;

/**
 * 
 * @author Administrator
 */
public class StringToArrayList {

	@SuppressWarnings("unchecked")
	public ArrayList<CaseBean> getObject(String value) {
		ArrayList<CaseBean> placeObject = null;
		ObjectInputStream inputStream = null;
		try {
			String response = value;
			byte[] decode = Base64.decode(response, Base64.DEFAULT);
			ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(
					decode);
			inputStream = new ObjectInputStream(arrayInputStream);
			Object readObject = inputStream.readObject();
			if (readObject instanceof ArrayList) {
				placeObject = (ArrayList<CaseBean>) readObject;
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(StringToArrayList.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(StringToArrayList.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (NullPointerException e) {
			return null;
		} finally {
			try {
				inputStream.close();
			} catch (IOException ex) {
				Logger.getLogger(StringToArrayList.class.getName()).log(
						Level.SEVERE, null, ex);
				return null;
			} catch (NullPointerException e2) {
				return null;
			}
		}
		return placeObject;
	}
}
