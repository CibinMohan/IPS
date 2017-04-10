package com.ips.bean;

import java.io.Serializable;

public class CaseBean extends LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String case_id = "";

	private String suspect_Name = "";

	private String address = "";

	private String suspect_AddarNo = "";

	private String phone = "";

	private String email = "";

	private String location = "";

	private String vehicle_No = "";

	private String time = "";

	private String date = "";

	private String victim_Adhaar_No = "";

	private String case_file = "";

	private String case_file_id = "";

	private String username = "";

	private String status = "";

	private String panalty_atm = "";

	private String panalty_status = "";

	private String lastDate = "";

	private String lat = "";

	private String log = "";

	private String file_id = "";

	private byte[] image;

	private String penalty_id = "";

	private String Case_type = "";

	private String case_Comands = "";

	private String date_diff = "";

	private byte[] video;

	public byte[] getVideo() {
		return video;
	}

	public void setVideo(byte[] video) {
		this.video = video;
	}

	public String getDate_diff() {
		return date_diff;
	}

	public void setDate_diff(String date_diff) {
		this.date_diff = date_diff;
	}

	public String getCase_type() {
		return Case_type;
	}

	public void setCase_type(String Case_type) {
		this.Case_type = Case_type;
	}

	public String getCase_Comands() {
		return case_Comands;
	}

	public void setCase_Comands(String case_Comands) {
		this.case_Comands = case_Comands;
	}

	public String getPenalty_id() {
		return penalty_id;
	}

	public void setPenalty_id(String penalty_id) {
		this.penalty_id = penalty_id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getPanalty_atm() {
		return panalty_atm;
	}

	public void setPanalty_atm(String panalty_atm) {
		this.panalty_atm = panalty_atm;
	}

	public String getPanalty_status() {
		return panalty_status;
	}

	public void setPanalty_status(String panalty_status) {
		this.panalty_status = panalty_status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCase_file() {
		return case_file;
	}

	public void setCase_file(String case_file) {
		this.case_file = case_file;
	}

	public String getCase_file_id() {
		return case_file_id;
	}

	public void setCase_file_id(String case_file_id) {
		this.case_file_id = case_file_id;
	}

	public String getCase_id() {
		return case_id;
	}

	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSuspect_AddarNo() {
		return suspect_AddarNo;
	}

	public void setSuspect_AddarNo(String suspect_AddarNo) {
		this.suspect_AddarNo = suspect_AddarNo;
	}

	public String getSuspect_Name() {
		return suspect_Name;
	}

	public void setSuspect_Name(String suspect_Name) {
		this.suspect_Name = suspect_Name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVehicle_No() {
		return vehicle_No;
	}

	public void setVehicle_No(String vehicle_No) {
		this.vehicle_No = vehicle_No;
	}

	public String getVictim_Adhaar_No() {
		return victim_Adhaar_No;
	}

	public void setVictim_Adhaar_No(String victim_Adhaar_No) {
		this.victim_Adhaar_No = victim_Adhaar_No;
	}
}
