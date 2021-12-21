package team.cnpm.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "KhaiBao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KhaiBao {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "SoCCCD", columnDefinition = "varchar(12)")
	private String canCuocCongDan;
	@Column(name = "SDT")
	private String phoneNumber;
	@Column(name = "FirstName",columnDefinition = "nvarchar(30)")
	private String firstName;
	@Column(name = "LastName",columnDefinition = "nvarchar(30)")
	private String lastName;
	
	// địa chỉ tạm trú đối với người tạm trú / địa chỉ tạm vắng đối với người tạm vắng
	@Column(name = "Address",columnDefinition = "nvarchar(200)")
	private String address;
	
	@Column(name = "DOB")
	private Date dateOfBirth;
	
	@Column(name = "Gender", columnDefinition = "varchar(6)")
	private String gender; //MALE,FEMALE
	
	@Column(name = "Job",columnDefinition = "nvarchar(100)")
	private String job;
	
	@Column(name = "Status",columnDefinition = "nvarchar(30)")
	private String status;
	
	@Column(name = "StartTime")
	private Date startTime;
	
	@Column(name = "EndTime")
	private Date endTime;
	
	@Column(name = "ReasonDescription", columnDefinition = "nvarchar(200)")
	private String reasonDescription;
	
	

	public String getReasonDescription() {
		return reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

	public KhaiBao(String canCuocCongDan, String phoneNumber, String firstName, String lastName, Date dateOfBirth,
			String gender, String job) {
		super();
		this.canCuocCongDan = canCuocCongDan;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.job = job;
	}

	public KhaiBao(int id, String canCuocCongDan, String phoneNumber, String firstName, String lastName, String address,
			Date dateOfBirth, String gender, String job, String status, Date startTime, Date endTime, String reasonDescription) {
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
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reasonDescription = reasonDescription;
	}
	
	public KhaiBao() {}
	
	
	
}
