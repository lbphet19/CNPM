package team.cnpm.DTOs.response;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import team.cnpm.models.CongDan;
import team.cnpm.models.SoHoKhau;
import team.cnpm.models.SoHoKhauHistory;

public class SHKHistoryResponseDTO {
	
	private SoHoKhauHistory history;
	private int id;
	private String status;
	private Date date;
	private String descriptions;

	private CongDan congDan;
	private String firstName;
	private String lastName;
	private String cccd;
	private String phoneNum;
	private String gender;
	

	private SoHoKhau hoKhau;
	private String hoKhauID;

	public SHKHistoryResponseDTO(SoHoKhauHistory history, CongDan congDan, SoHoKhau hoKhau) {
		super();
		this.history = history;
		this.congDan = congDan;
		this.hoKhau = hoKhau;
	}

	public void setHistory(SoHoKhauHistory history) {
		this.history = history;
	}
	public int getId() {
		this.id = this.history.getId();
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


//	public SoHoKhauHistory getHistory() {
//		return history;
//	}


	public String getStatus() {
		this.status = this.history.getStatus();
		return status;
	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		this.date = this.history.getDate();
		return df.format(this.date);
	}

	public String getDescriptions() {
		this.descriptions = this.history.getDescriptions();
		return descriptions;
	}


	public void setCongDan(CongDan congDan) {
		this.congDan = congDan;
	}
	public String getFirstName() {
		if(this.congDan == null) return null;
		this.firstName = this.congDan.getFirstName();
		return firstName;
	}

	public String getLastName() {
		if(this.congDan == null) return null;
		this.lastName = this.congDan.getLastName();
		return lastName;
	}

	public String getCccd() {
		if(this.congDan == null) return null;
		this.cccd = this.congDan.getCanCuocCongDan();
		return cccd;
	}

	public String getPhoneNum() {
		if(this.congDan == null) return null;
		this.phoneNum = this.congDan.getPhoneNumber();
		return phoneNum;
	}

	public String getGender() {
		if(this.congDan == null) return null;
		this.gender = this.congDan.getGender();
		return gender;
	}


	public void setHoKhau(SoHoKhau hoKhau){
		this.hoKhau = hoKhau;
	}
	public String getHoKhauID() {
		if(this.hoKhau == null) return null;
		this.hoKhauID = this.hoKhau.getId();
		return hoKhauID;
	}
	

	
	
}
