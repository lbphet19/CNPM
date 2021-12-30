package team.cnpm.DTOs.response;

import team.cnpm.models.CongDan;

public class SoHoKhauResponseDTO {
	private String id;
	private String owner;
	private String address;
	private String contact;
	private int soTVien;
	
	public SoHoKhauResponseDTO(String id, String owner, String address,String contact, int soTVien) {
		super();
		this.id = id;
		this.owner = owner;
		this.address = address;
		this.soTVien = soTVien;
		this.contact= contact;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSoTVien() {
		return soTVien;
	}
	public void setSoTVien(int soTVien) {
		this.soTVien = soTVien;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
	
	
	
}
