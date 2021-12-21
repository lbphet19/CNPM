package team.cnpm.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DongGop")
public class DongGop {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "EventName",columnDefinition = "nvarchar(100)")
	private String eventName;
	@Column(name = "Date")
	private Date date;
	@Column(name = "Descriptions",columnDefinition = "nvarchar(200)")
	private String descriptions;
	@Column(name = "ContributionLimit")
	private Integer mucphi;
	
	@OneToMany(mappedBy = "dongGop")
	private List<HoKhauDongGop> listHoKhauDongGop = new ArrayList<HoKhauDongGop>();
	
	
	
	public Integer getMucphi() {
		return mucphi;
	}
	public void setMucphi(int mucphi) {
		this.mucphi = mucphi;
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
	public List<HoKhauDongGop> getListHoKhauDongGop() {
		return listHoKhauDongGop;
	}
	public void setListHoKhauDongGop(List<HoKhauDongGop> listHoKhauDongGop) {
		this.listHoKhauDongGop = listHoKhauDongGop;
	}
	
	public DongGop(int id, String eventName, Date date, String descriptions, Integer mucphi,
			List<HoKhauDongGop> listHoKhauDongGop) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.date = date;
		this.descriptions = descriptions;
		this.mucphi = mucphi;
		this.listHoKhauDongGop = listHoKhauDongGop;
	}
	public DongGop() {
		super();
	}
	
	public void addHoKhauDongGop(SoHoKhau hoKhau,int amount) {
		HoKhauDongGop hkDongGop = new HoKhauDongGop(hoKhau, this, amount);
		this.getListHoKhauDongGop().add(hkDongGop);
	}
		
	
	
}
