package com.example.shop.models.request;

import java.util.List;

public class ResponseBuyOrderPage {
	private List<ResponseBuyOrder> content;
	private long pageNumber;
	private int totalPages;
	
	private long size;
	private long totalElements;
	private long pageSize;
	private long offset;
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<ResponseBuyOrder> getContent() {
		return content;
	}
	public void setContent(List<ResponseBuyOrder> content) {
		this.content = content;
	}
	public long getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	
}
