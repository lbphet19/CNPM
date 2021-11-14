package team.cnpm.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SoHoKhau")
public class SoHoKhau {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Address",columnDefinition = "nvarchar(200)")
	private String address;
	@OneToOne
	@JoinColumn(name = "OwnerId",referencedColumnName = "id")
	private CongDan owner;
	@OneToMany(mappedBy = "hoKhau", cascade = CascadeType.ALL)
	private List<CongDan> members = new ArrayList<CongDan>();
//	@JsonIgnore
//	@OneToMany(mappedBy = "hoKhau")
//	private List<HoKhauDongGop> listHoKhauDongGop = new ArrayList<HoKhauDongGop>();
	
	
	public String getAddress() {
		return address;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getId() {
		return id;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public CongDan getOwner() {
		return owner;
	}



	public void setOwner(CongDan owner) {
		this.owner = owner;
	}



	public List<CongDan> getMembers() {
		return members;
	}



	public void setMembers(List<CongDan> members) {
		this.members = members;
	}

	public void addMember(CongDan congDan) {
		this.getMembers().add(congDan);
		congDan.setHoKhau(this);
	}
	
	public void removeMember(CongDan congDan) {
		this.getMembers().remove(congDan);
		congDan.setHoKhau(null);
	}

	public SoHoKhau(String address, CongDan owner, List<CongDan> members) {
		super();
		this.address = address;
		this.owner = owner;
		this.members = members;
	}
	public SoHoKhau(int id, String address) {
		super();
		this.id = id;
		this.address = address;
	}
	public SoHoKhau(String address) {
		super();
		this.address = address;
	}

	public SoHoKhau() {
		// TODO Auto-generated constructor stub
	}
}
