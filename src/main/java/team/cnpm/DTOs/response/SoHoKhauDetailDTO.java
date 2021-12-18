package team.cnpm.DTOs.response;

import java.util.List;

public class SoHoKhauDetailDTO {
	private String id;
	private String ownerName;
	private String address;
	private String contact;
	private int soTVien;
	private List<CongDanOfSHK_DTO> members;
	
	
	
	public SoHoKhauDetailDTO(String id, String ownerName, String address, String contact, int soTVien,
			List<CongDanOfSHK_DTO> members) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.address = address;
		this.contact = contact;
		this.soTVien = soTVien;
		this.members = members;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getSoTVien() {
		return soTVien;
	}
	public void setSoTVien(int soTVien) {
		this.soTVien = soTVien;
	}
	public List<CongDanOfSHK_DTO> getMembers() {
		return members;
	}
	public void setMembers(List<CongDanOfSHK_DTO> members) {
		this.members = members;
	}
	
	
}
