package team.cnpm.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SoHoKhauHistory")
public class SoHoKhauHistory {
	

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	// ra-vao, cong dan, ngay, mo ta
	@Column(name = "Status", columnDefinition = "nvarchar(15)")
	private String status;
	//status: chuyển hộ, qua đời...
	@Column(name = "Date")
	private Date date;
	@Column(name = "Descriptions")
	private String descriptions;
	
	@ManyToOne
	@JoinColumn(name = "CongDanId", referencedColumnName = "id")
	private CongDan congDan;
	
	@ManyToOne
	@JoinColumn(name = "HoKhauId", referencedColumnName = "id")
	private SoHoKhau hoKhau;
	
	
	
	public SoHoKhauHistory(String status, Date date, String descriptions, CongDan congDan, SoHoKhau hoKhau) {
		super();
		this.status = status;
		this.date = date;
		this.descriptions = descriptions;
		this.congDan = congDan;
		this.hoKhau = hoKhau;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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



	public CongDan getCongDan() {
		return congDan;
	}



	public void setCongDan(CongDan congDan) {
		this.congDan = congDan;
	}



	public SoHoKhau getHoKhau() {
		return hoKhau;
	}



	public void setHoKhau(SoHoKhau hoKhau) {
		this.hoKhau = hoKhau;
	}



	public SoHoKhauHistory() {
		super();
	}
	

	
}
