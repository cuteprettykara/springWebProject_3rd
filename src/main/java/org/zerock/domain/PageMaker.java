package org.zerock.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;

	private int startPage;
	private int endPage;
	
	private boolean prev;
	private boolean next = true;
	
	private int displayPageNum = 10;
	
	private Criteria cri;

	
	public int getTotalCount() {
		return totalCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	public Criteria getCri() {
		return cri;
	}
	
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}


	private void calcData() {
		endPage = (int) Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum;
		
		startPage = endPage - displayPageNum + 1;
		
		int realEndPage = (int) Math.ceil(totalCount / (double)cri.getPerPageNum());
		
		if (realEndPage < endPage) {
			endPage = realEndPage;
			next = false;
		}
		
		prev = startPage == 1 ? false : true;
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
					.queryParam("page", page)
					.queryParam("perPageNum", this.cri.getPerPageNum())
					.build();
		
		return uriComponents.toString();
	}
	
	
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
	
}
