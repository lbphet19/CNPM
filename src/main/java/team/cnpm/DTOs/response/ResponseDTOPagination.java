package team.cnpm.DTOs.response;

public class ResponseDTOPagination {
	private boolean status;
	private Object response;
	private int pageSize;
	private int page;
	private long totalItems;
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public ResponseDTOPagination(boolean status, Object response, int pageSize, int page, long totalItems) {
		super();
		this.status = status;
		this.response = response;
		this.pageSize = pageSize;
		this.page = page;
		this.totalItems = totalItems;
	}
	
}
