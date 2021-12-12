package team.cnpm.DTOs.response;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import team.cnpm.models.SoHoKhau;

public class CongDanDetailDTO {

	private int id;
	private String canCuocCongDan;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String address;
	private Date dateOfBirth;
	private String gender; //MALE,FEMALE
	private String job;
	private String specialNotes;
	private String status;
	private Date departmentTime;
	private String relationship;
	private String image;
	
	private String idSHKSoHuu;
//	@OneToOne(mappedBy = "owner")
//	private SoHoKhau hoKhauSoHuu;
	
	private String idSHK;
//	@JoinColumn(name = "IdSoHoKhau",referencedColumnName = "id")
//	private SoHoKhau hoKhau;

	
	
	public int getId() {
		return id;
	}

	public CongDanDetailDTO(int id, String canCuocCongDan, String phoneNumber, String firstName, String lastName,
		String address, Date dateOfBirth, String gender, String job, String specialNotes, String status,
		Date departmentTime, String relationship, String image, String idSHKSoHuu, String idSHK) {
	super();
	this.id = id;
	this.canCuocCongDan = canCuocCongDan;
	this.phoneNumber = phoneNumber;
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.dateOfBirth = dateOfBirth;
	this.gender = gender;
	this.job = job;
	this.specialNotes = specialNotes;
	this.status = status;
	this.departmentTime = departmentTime;
	this.relationship = relationship;
	this.image = image;
	this.idSHKSoHuu = idSHKSoHuu;
	this.idSHK = idSHK;
}

	public void setId(int id) {
		this.id = id;
	}

	public String getCanCuocCongDan() {
		return canCuocCongDan;
	}

	public void setCanCuocCongDan(String canCuocCongDan) {
		this.canCuocCongDan = canCuocCongDan;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSpecialNotes() {
		return specialNotes;
	}

	public void setSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDepartmentTime() {
		return departmentTime;
	}

	public void setDepartmentTime(Date departmentTime) {
		this.departmentTime = departmentTime;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIdSHKSoHuu() {
		return idSHKSoHuu;
	}

	public void setIdSHKSoHuu(String idSHKSoHuu) {
		this.idSHKSoHuu = idSHKSoHuu;
	}

	public String getIdSHK() {
		return idSHK;
	}

	public void setIdSHK(String idSHK) {
		this.idSHK = idSHK;
	}
	
	
	
}
