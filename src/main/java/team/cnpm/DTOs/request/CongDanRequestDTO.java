package team.cnpm.DTOs.request;

import java.sql.Date;

import javax.persistence.Column;

public class CongDanRequestDTO {
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
	
	//add theo ho
	private String idSHK;

	public int getId() {
		return id;
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

	public String getIdSHK() {
		return idSHK;
	}

	public void setIdSHK(String idSHK) {
		this.idSHK = idSHK;
	}

	public CongDanRequestDTO() {
		super();
	}
	
	
}
