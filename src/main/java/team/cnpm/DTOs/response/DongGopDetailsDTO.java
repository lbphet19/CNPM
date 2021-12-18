package team.cnpm.DTOs.response;

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

	private List<HKDongGopDTO> listHKDG = new ArrayList<HKDongGopDTO>();

	private List<HKDongGopDTO> listHKChuaDG = new ArrayList<HKDongGopDTO>();


	public List<HKDongGopDTO> getListHKChuaDG() {
		return listHKChuaDG;
	}
	public void setListHKChuaDG(List<HKDongGopDTO> listHKChuaDG) {
		this.listHKChuaDG = listHKChuaDG;
	}
	public DongGopDetailsDTO(int id, String eventName, Date date, String descriptions, List<HKDongGopDTO> listHKDG, List<HKDongGopDTO> listHKChuaDG) {
		super();
		this.id=id;
		this.eventName = eventName;
		this.date = date;
		this.descriptions = descriptions;
		this.listHKDG = listHKDG;
		this.listHKChuaDG = listHKChuaDG;
	}
	public List<HKDongGopDTO> getListHKDG() {
		return listHKDG;
	}

	public void setListHoKhauDongGop(List<HKDongGopDTO> listHKDG) {
		this.listHKDG = listHKDG;
	}
}


