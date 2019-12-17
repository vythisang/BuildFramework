package com.laptrinhjavaweb.paging;

public class PageRequest implements Pageable{

	private Integer page;
	private Integer limit;
	
	public PageRequest(Integer page, Integer limit) {
		this.page = page;
		this.limit = limit;
	}
	
	@Override
	public Integer getPage() {
		return page;
	}

	@Override
	public Integer getOffset() {
		return (page - 1) * limit;
	}

	@Override
	public Integer getLimit() {
		return limit;
	}

}
