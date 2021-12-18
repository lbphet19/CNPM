package team.cnpm.DTOs.response;


import team.cnpm.models.DongGop;
import team.cnpm.models.SoHoKhau;

public class HKDongGopDTO {
	
	private SoHoKhau hoKhau;
	private String idHo;
	private String tenChuHo;
	private String address;
	private String contact;
	private int amount;
	

	public String getAddress() {
		return this.hoKhau.getAddress();
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return this.hoKhau.getOwner().getPhoneNumber();
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public HKDongGopDTO(SoHoKhau hoKhau, int amount) {
		super();
		this.hoKhau = hoKhau;
		this.amount = amount;
	}

	public void setHoKhau(SoHoKhau hoKhau) {
		this.hoKhau = hoKhau;
	}

	public String getIdHo() {
		return this.hoKhau.getId();
	}

	public void setIdHo(String idHo) {
		this.idHo = idHo;
	}

	public String getTenChuHo() {
		return this.hoKhau.getOwner().getFirstName()+" "+this.hoKhau.getOwner().getLastName();
	}

	public void setTenChuHo(String tenChuHo) {
		this.tenChuHo = tenChuHo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
