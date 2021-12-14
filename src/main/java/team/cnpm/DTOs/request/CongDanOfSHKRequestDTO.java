package team.cnpm.DTOs.request;

public class CongDanOfSHKRequestDTO {
	//id, relationship
	private int id;
	private String relationship;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public CongDanOfSHKRequestDTO(int id, String relationship) {
		super();
		this.id = id;
		this.relationship = relationship;
	}
	public CongDanOfSHKRequestDTO(int id) {
		super();
		this.id = id;
	}
	public CongDanOfSHKRequestDTO() {
		super();
	}
	
	
}
