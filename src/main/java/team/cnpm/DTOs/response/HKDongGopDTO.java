package team.cnpm.DTOs.response;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import team.cnpm.models.DongGop;
import team.cnpm.models.SoHoKhau;

public class HKDongGopDTO {
	
	
	private String idHo;
	private String tenChuHo;

	private String address;
	private String contact;
	private Date time;
//	private String time;
	private int amount;
	public HKDongGopDTO(String idHo, String tenChuHo, String address, String contact, int amount) {
		super();
		this.idHo = idHo;
		this.tenChuHo = tenChuHo;
		this.address = address;
		this.contact = contact;
		this.amount = amount;
	}
	public String getIdHo() {
		return idHo;
	}
	public void setIdHo(String idHo) {
		this.idHo = idHo;
	}
	public String getTenChuHo() {
		return tenChuHo;
	}
	public void setTenChuHo(String tenChuHo) {
		this.tenChuHo = tenChuHo;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTime() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(time);
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	/* test 

	 * */
	
	
	/*end test */
	
//	public String getTime() {
//		return time;
//	}
	public HKDongGopDTO(String idHo, String tenChuHo, String address, String contact, Date time, int amount) {
		super();
//		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		this.idHo = idHo;
		this.tenChuHo = tenChuHo;
		this.address = address;
		this.contact = contact;
		this.time = time;
		this.amount = amount;
	}
	
	
	
}

