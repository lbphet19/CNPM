package team.cnpm.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "CongDan")
public class CongDan {
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
	
	@Column(name = "Address",columnDefinition = "nvarchar(200)")
	private String address;
	
	@Column(name = "DOB")
	private Date dateOfBirth;
	
	@Column(name = "Gender", columnDefinition = "varchar(6)")
	private String gender; //MALE,FEMALE
	
	@Column(name = "Job",columnDefinition = "nvarchar(100)")
	private String job;
	
	@Column(name = "Specials",columnDefinition = "nvarchar(50)")
	private String specialNotes;
	
	@Column(name = "Status",columnDefinition = "nvarchar(30)")
	private String status;
	
	@Column(name = "DepartmentTime")
	private Date departmentTime;
	
	@Column(name = "Relationship",columnDefinition = "nvarchar(15)")
	private String relationship;
	
	@Column(name = "Img")
	private String image;
	
	@JsonIgnore
	@OneToOne(mappedBy = "owner")
	private SoHoKhau hoKhauSoHuu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "IdSoHoKhau",referencedColumnName = "id")
	private SoHoKhau hoKhau;
	
//	@OneToMany(mappedBy = "congDan")
//	private List<SoHoKhauHistory> history = new ArrayList<SoHoKhauHistory>();
	
	
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

	public SoHoKhau getHoKhauSoHuu() {
		return hoKhauSoHuu;
	}

	public void setHoKhauSoHuu(SoHoKhau hoKhauSoHuu) {
		this.hoKhauSoHuu = hoKhauSoHuu;
	}

	public SoHoKhau getHoKhau() {
		return hoKhau;
	}

	public void setHoKhau(SoHoKhau hoKhau) {
		this.hoKhau = hoKhau;
	}
	
	
}
