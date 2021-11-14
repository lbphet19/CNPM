package team.cnpm.DTOs.request;

import java.util.ArrayList;
import java.util.List;

import team.cnpm.models.CongDan;

public class SoHoKhauRequestDTO {
//id address owner member
	private int id;
	private String address;
	private Integer ownerId; //owner's id
	private List<Integer> membersId = new ArrayList<Integer>(); //members' id
//	private CongDan owner;
//	private List<CongDan> members = new ArrayList<CongDan>();
	
	public String getAddress() {
		return address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public List<Integer> getMembersId() {
		return membersId;
	}
	public void setMembersId(List<Integer> membersId) {
		this.membersId = membersId;
	}
	public SoHoKhauRequestDTO(String address, int ownerId, List<Integer> membersId) {
		super();
		this.address = address;
		this.ownerId = ownerId;
		this.membersId = membersId;
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
