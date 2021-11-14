package team.cnpm.DTOs.response;

public class ResponseDTO {
	private boolean status;
	private Object response;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	public ResponseDTO(boolean status, Object response) {
		super();
		this.status = status;
		this.response = response;
	}
	public ResponseDTO() {
		super();
	}
	
}
