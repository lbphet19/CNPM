package team.cnpm.DTOs.response;

import java.sql.Date;

public class CongDanOfSHK_DTO {
	private int id;
	private String canCuocCongDan;
	private String firstName;
	private String lastName;
	private String address;
	private Date dateOfBirth;
	private String phoneNumber;
	private String gender; //MALE,FEMALE
	private String job;
	private String relationship;
	private String specialNotes;
	private String status;
	private String image;
	
	
	
	public CongDanOfSHK_DTO(int id, String canCuocCongDan, String firstName, String lastName, String address, Date dateOfBirth,
			String gender, String job, String relationship, String specialNotes, String status, String image) {
		super();
		this.id = id;
		this.canCuocCongDan = canCuocCongDan;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.job = job;
		this.specialNotes= specialNotes;
		this.relationship = relationship;
		this.status = status;
		this.image = image;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSpecialNotes() {
		return specialNotes;
	}


	public void setSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
	}


	public String getCanCuocCongDan() {
		return canCuocCongDan;
	}
	public void setCanCuocCongDan(String canCuocCongDan) {
		this.canCuocCongDan = canCuocCongDan;
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
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public CongDanOfSHK_DTO(int id, String canCuocCongDan, String firstName, String lastName, String address,
			Date dateOfBirth, String phoneNumber, String gender, String job, String relationship, String specialNotes,
			String status, String image) {
		super();
		this.id = id;
		this.canCuocCongDan = canCuocCongDan;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.job = job;
		this.relationship = relationship;
		this.specialNotes = specialNotes;
		this.status = status;
		this.image = image;
	}
	
	
	
}
