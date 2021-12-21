package team.cnpm.DTOs.response;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import team.cnpm.models.HoKhauDongGop;

public class ListDongGopDTO {
	
	
	private int id;
	
	private String eventName;

	private Date date;

	private String descriptions;
	
	public int tongtien;
	public Integer mucphi;
	
	public Integer getMucphi() {
		return mucphi;
	}


	public void setMucphi(Integer mucphi) {
		this.mucphi = mucphi;
	}


	public int getTongtien() {
		for(HoKhauDongGop i : this.listHoKhauDongGop)
			if(i.getAmount() != 0) tongtien+=i.getAmount();
			else continue;
		return tongtien;
	}


	public void setTongtien(int tongtien) {
		this.tongtien = tongtien;
	}

	private List<HoKhauDongGop> listHoKhauDongGop = new ArrayList<HoKhauDongGop>();

	public ListDongGopDTO(int id, String eventName, Date date, String descriptions,
			List<HoKhauDongGop> listHoKhauDongGop, Integer mucphi) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.date = date;
		this.descriptions = descriptions;
		this.listHoKhauDongGop = listHoKhauDongGop;
		this.mucphi=mucphi;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

//	public List<HoKhauDongGop> getListHoKhauDongGop() {       // chỉ hiện thị list trong detail
//		return listHoKhauDongGop;
//	}

	public void setListHoKhauDongGop(List<HoKhauDongGop> listHoKhauDongGop) {
		this.listHoKhauDongGop = listHoKhauDongGop;
	}

}
