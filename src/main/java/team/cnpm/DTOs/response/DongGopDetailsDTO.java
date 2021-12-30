package team.cnpm.DTOs.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import team.cnpm.models.HoKhauDongGop;

public class DongGopDetailsDTO {

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTongtien() {
		for(HKDongGopDTO i : this.listHKDG)
			if(i.getAmount() != 0) tongtien+=i.getAmount();
			else continue;
		return tongtien;
		
	}
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(date);
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

	private List<HKDongGopDTO> listHKDG = new ArrayList<HKDongGopDTO>();

	


	
	
	
	
	public DongGopDetailsDTO(int id, String eventName, Date date, String descriptions,
			List<HKDongGopDTO> listHKDG, Integer mucphi) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.date = date;
		this.descriptions = descriptions;
		this.mucphi = mucphi;
		this.listHKDG = listHKDG;
	}
	public List<HKDongGopDTO> getListHKDG() {
		return listHKDG;
	}

	public void setListHoKhauDongGop(List<HKDongGopDTO> listHKDG) {
		this.listHKDG = listHKDG;
	}
}


