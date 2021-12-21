package team.cnpm.DTOs.response;

import java.util.List;

public class SoHoKhauResponseDTOPagination {
	private List<SoHoKhauResponseDTO> listSHK;
	private long totalNumber;
	private int pageSize;
	public List<SoHoKhauResponseDTO> getListSHK() {
		return listSHK;
	}
	public void setListSHK(List<SoHoKhauResponseDTO> listSHK) {
		this.listSHK = listSHK;
	}
	public long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public SoHoKhauResponseDTOPagination(List<SoHoKhauResponseDTO> listSHK, long totalNumber, int pageSize) {
		super();
		this.listSHK = listSHK;
		this.totalNumber = totalNumber;
		this.pageSize = pageSize;
	}
	
	
}
