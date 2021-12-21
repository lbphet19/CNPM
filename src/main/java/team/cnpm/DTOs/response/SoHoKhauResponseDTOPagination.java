package team.cnpm.DTOs.response;

import java.util.ArrayList;
import java.util.List;

public class SoHoKhauResponseDTOPagination {
	private List<SoHoKhauResponseDTO> dtoList;
	private long totalItems;
	public List<SoHoKhauResponseDTO> getDtoList() {
		return dtoList;
	}
	public void setDtoList(List<SoHoKhauResponseDTO> dtoList) {
		this.dtoList = dtoList;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public SoHoKhauResponseDTOPagination(List<SoHoKhauResponseDTO> dtoList, long totalItems) {
		super();
		this.dtoList = dtoList;
		this.totalItems = totalItems;
	}
	
	
}
