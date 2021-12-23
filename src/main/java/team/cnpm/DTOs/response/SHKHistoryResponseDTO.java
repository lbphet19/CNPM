package team.cnpm.DTOs.response;

import java.sql.Date;

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
	

	private SoHoKhau hoKhauRoiDi;
	private String hkRoiDiID;
	
	private SoHoKhau hoKhauChuyenDen;
	private String hkChuyenDenID;
	public SHKHistoryResponseDTO(SoHoKhauHistory history, CongDan congDan, SoHoKhau hoKhauRoiDi, SoHoKhau hoKhauChuyenDen) {
		super();
		this.history = history;
		this.congDan = congDan;
		this.hoKhauRoiDi = hoKhauRoiDi;
		this.hoKhauChuyenDen = hoKhauChuyenDen;
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

	public void setHkRoiDiID(String hkRoiDiID) {
		this.hkRoiDiID = hkRoiDiID;
	}

	public void setHkChuyenDenID(String hkChuyenDenID) {
		this.hkChuyenDenID = hkChuyenDenID;
	}

	public String getStatus() {
		this.status = this.history.getStatus();
		return status;
	}

	public Date getDate() {
		this.date = this.history.getDate();
		return date;
	}

	public String getDescriptions() {
		this.descriptions = this.history.getDescriptions();
		return descriptions;
	}


	public void setCongDan(CongDan congDan) {
		this.congDan = congDan;
	}
	public String getFirstName() {
		this.firstName = this.congDan.getFirstName();
		return firstName;
	}

	public String getLastName() {
		this.lastName = this.congDan.getLastName();
		return lastName;
	}

	public String getCccd() {
		this.cccd = this.congDan.getCanCuocCongDan();
		return cccd;
	}

	public String getPhoneNum() {
		this.phoneNum = this.congDan.getPhoneNumber();
		return phoneNum;
	}

	public String getGender() {
		this.gender = this.congDan.getGender();
		return gender;
	}


	public void setHoKhauRoiDi(SoHoKhau hoKhauRoiDi) {
		this.hoKhauRoiDi = hoKhauRoiDi;
	}
	public String getHkRoiDiID() {
		if(this.hoKhauRoiDi == null) return null;
		this.hkRoiDiID = this.hoKhauRoiDi.getId();
		return hkRoiDiID;
	}


	public void setHoKhauChuyenDen(SoHoKhau hoKhauChuyenDen) {
		this.hoKhauChuyenDen = hoKhauChuyenDen;
	}
	public String getHkChuyenDenID() {
		if(this.hoKhauChuyenDen == null) return null;
		this.hkChuyenDenID = this.hoKhauChuyenDen.getId();
		return hkChuyenDenID;
	}
	

	
	
}
