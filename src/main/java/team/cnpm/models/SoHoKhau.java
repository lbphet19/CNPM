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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "SoHoKhau")
public class SoHoKhau {
	@Id
	@Column(name = "Id",columnDefinition = "char(9)")
//	@GeneratedValue(strategy = GenerationType.)
	private String id;
	@Column(name = "Address",columnDefinition = "nvarchar(200)")
	private String address;
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "OwnerId",referencedColumnName = "id")
	private CongDan owner;
	@JsonIgnore
	@OneToMany(mappedBy = "hoKhau", cascade = CascadeType.ALL)
	private List<CongDan> members = new ArrayList<CongDan>();
	
//	@JsonProperty(access = Access.WRITE_ONLY)
//	@OneToMany(mappedBy = "hoKhauRoiDi")
//	private List<SoHoKhauHistory> LichSuRoiDi = new ArrayList<SoHoKhauHistory>();
//	@JsonIgnore
//	@OneToMany(mappedBy = "hoKhau")
//	private List<HoKhauDongGop> listHoKhauDongGop = new ArrayList<HoKhauDongGop>();
	
	
	public String getAddress() {
		return address;
	}
	

	public void setAddress(String address) {
		this.address = address;
	}



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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
	public void addMemberCheck(CongDan congDan) {
		if(congDan.getStatus()!="Qua Đời") {
		this.getMembers().add(congDan);
		congDan.setHoKhau(this);}
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
	public SoHoKhau(String id, String address) {
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SoHoKhau other = (SoHoKhau) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
}
