package team.cnpm.DTOs.request;

import java.util.ArrayList;
import java.util.List;

import team.cnpm.models.CongDan;

public class SoHoKhauRequestDTO {
//id address owner member
	private String id;
	private String address;
	private Integer ownerId; //owner's id
	private List<CongDanOfSHKRequestDTO> members = new ArrayList<CongDanOfSHKRequestDTO>(); //members' id
//	private CongDan owner;
//	private List<CongDan> members = new ArrayList<CongDan>();
	
	public String getAddress() {
		return address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	public List<CongDanOfSHKRequestDTO> getMembers() {
		return members;
	}
	public void setMembers(List<CongDanOfSHKRequestDTO> members) {
		this.members = members;
	}
	
	public SoHoKhauRequestDTO(String address, Integer ownerId, List<CongDanOfSHKRequestDTO> members) {
		super();
		this.address = address;
		this.ownerId = ownerId;
		this.members = members;
	}
	public SoHoKhauRequestDTO(String address, Integer ownerId) {
		super();
		this.address = address;
		this.ownerId = ownerId;
	}
	
	public SoHoKhauRequestDTO(String id, String address, Integer ownerId, List<CongDanOfSHKRequestDTO> members) {
		super();
		this.id = id;
		this.address = address;
		this.ownerId = ownerId;
		this.members = members;
	}
	public SoHoKhauRequestDTO() {
		super();
	}
	
//	public CongDan getOwner() {
//		return owner;
//	}
//	public void setOwner(CongDan owner) {
//		this.owner = owner;
//	}
//	public List<CongDan> getMembers() {
//		return members;
//	}
//	public void setMembers(List<CongDan> members) {
//		this.members = members;
//	}
	
}
