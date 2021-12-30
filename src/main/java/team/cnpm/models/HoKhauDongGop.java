package team.cnpm.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HoKhau_DongGop")
public class HoKhauDongGop {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "HoKhauId",referencedColumnName = "id")
	private SoHoKhau hoKhau;
	@ManyToOne
	@JoinColumn(name = "DongGopId",referencedColumnName = "id")
	private DongGop dongGop;
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "Time")
	private Date time;
	
	
	public HoKhauDongGop(SoHoKhau hoKhau, DongGop dongGop, int amount) {
		super();
		this.hoKhau = hoKhau;
		this.dongGop = dongGop;
		this.amount = amount;
	}
	public SoHoKhau getHoKhau() {
		return hoKhau;
	}
	public void setHoKhau(SoHoKhau hoKhau) {
		this.hoKhau = hoKhau;
	}
	public DongGop getDongGop() {
		return dongGop;
	}
	public void setDongGop(DongGop dongGop) {
		this.dongGop = dongGop;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public HoKhauDongGop() {
		super();
	}
	
	
	
}
