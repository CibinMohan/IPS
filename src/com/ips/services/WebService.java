package com.ips.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.ips.util.AppConstance;

public class WebService {

	String SOAP_ACTION = "";
	String response = "";
	public Context context;

	public WebService(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public String upload(SoapObject object) {
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(object);
		HttpTransportSE transport = new HttpTransportSE(AppConstance.URL);
		try {
			transport.call(SOAP_ACTION, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			response = primitive.toString();
			return response;
		} catch (IOException e) {
			return "error";
		} catch (XmlPullParserException e) {
			return "error";
		} catch (NullPointerException e) {
			return "error";
		} catch (ClassCastException e1) {
			// TODO: handle exception
			e1.printStackTrace();
			return "error";

		}

	}

	public String login(String username, String pswd) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstance.Name_Space + AppConstance.LOGIN;
		SoapObject object = new SoapObject(AppConstance.Name_Space,
				AppConstance.LOGIN);
		object.addProperty("username", username);
		object.addProperty("password", pswd);
		String result = upload(object);
		return result;
	}

	public String serachResultForAdhar(String typeValues) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstance.Name_Space + AppConstance.SEARCH_ADHAR;
		SoapObject object = new SoapObject(AppConstance.Name_Space,
				AppConstance.SEARCH_ADHAR);
		object.addProperty("value", typeValues);
		String upload = upload(object);
		return upload;
	}

	public String serachResultVahicle(String typeValues) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstance.Name_Space + AppConstance.SEARCH_VAHICLE;
		SoapObject object = new SoapObject(AppConstance.Name_Space,
				AppConstance.SEARCH_VAHICLE);
		object.addProperty("value", typeValues);
		String upload = upload(object);
		return upload;
	}

	public String getAllTodayCases(String userName) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstance.Name_Space + AppConstance.GET_ALL_CASES;
		SoapObject object = new SoapObject(AppConstance.Name_Space,
				AppConstance.GET_ALL_CASES);
		object.addProperty("username", userName);
		String upload = upload(object);
		return upload;
	}

	public String sendCaseFile(String beanOnly, String imageFilePath,
			String videoFilePath) {
		// TODO Auto-generated method stub
		String uploadFile = null;
		String sendVideoFile = "";
		// created for sending
		File beanFiles = new File(beanOnly);
		File imageFile = new File(imageFilePath);
		File videoFile = new File(videoFilePath);
		// ===================================
		byte[] bs = new byte[2048];

		try {
			FileInputStream fileInputStream = new FileInputStream(beanFiles);

			while (fileInputStream.read(bs) != -1) {

				String encodeToString = android.util.Base64.encodeToString(bs,
						android.util.Base64.CRLF);
				uploadFile(encodeToString);

			}

			uploadFile = uploadFile("ok");
			String[] split = null;
			fileInputStream.close();

			if (!uploadFile.equals("") || !uploadFile.equals(null)) {
				split = uploadFile.split(",");
			} else {
				return "error";
			}

			if (split[0].equals("true")) {
				if (beanFiles.exists()) {
					beanFiles.delete();

				}
				Log.d("TAG", split[1]);

				byte[] imageBlock = new byte[2048];

				FileInputStream stream = new FileInputStream(imageFile);

				while (stream.read(imageBlock) != -1) {

					String encodeToString = Base64.encodeToString(imageBlock,
							Base64.CRLF);
					sendImageFile(split[1], encodeToString);

				}

				String rsp = sendImageFile(split[1], "ok");
				stream.close();
				if (rsp.equals("true")) {

					// converting to video to string
					byte[] videoBlocks = new byte[2048];

					FileInputStream inputStream = new FileInputStream(videoFile);

					while (inputStream.read(videoBlocks) != -1) {
						String encodeToString = Base64.encodeToString(
								videoBlocks, Base64.CRLF);
						sendVideoFile(split[1], encodeToString);
					}

					sendVideoFile = sendVideoFile(split[1], "ok");
					inputStream.close();
				} else {
					return "error";
				}

			} else {
				return "error";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
		if (beanFiles.exists() && imageFile.exists() && videoFile.exists()) {
			beanFiles.delete();
			imageFile.delete();
			videoFile.delete();
		}
		return sendVideoFile;
	}

	private String sendVideoFile(String caseID, String videoFile) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstance.Name_Space + AppConstance.ADD_VIDEO;
		SoapObject object = new SoapObject(AppConstance.Name_Space,
				AppConstance.ADD_VIDEO);
		object.addProperty("video", videoFile);
		object.addProperty("id", caseID);
		String upload = upload(object);
		return upload;
	}

	private String sendImageFile(String caseId, String imageFile) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstance.Name_Space + AppConstance.ADD_IMAGE;
		SoapObject object = new SoapObject(AppConstance.Name_Space,
				AppConstance.ADD_IMAGE);
		object.addProperty("id", caseId);
		object.addProperty("image", imageFile);
		String upload = upload(object);
		return upload;
	}

	private String uploadFile(String encodeToString) {
		// TODO Auto-generated method stub
		SOAP_ACTION = AppConstance.Name_Space + AppConstance.ADD_NEW_CASES;
		SoapObject object = new SoapObject(AppConstance.Name_Space,
				AppConstance.ADD_NEW_CASES);
		object.addProperty("object", encodeToString);
		String upload = upload(object);
		return upload;
	}

}
