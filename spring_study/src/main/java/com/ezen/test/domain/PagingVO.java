package com.ezen.test.domain;

public class PagingVO {
	
	//select * from board limit 0,10;
	//페이징 => pageNo / qty
	//검색 => type / keyword
	
	private int pageNo;
	private int qty; //한 화면에 보여줄 게시글 수 (10개)
	
	private String type;
	private String keyword;
	
	
	public PagingVO() {
		this.pageNo= 1;
		this.qty=10;
	}
	
	public int getPageStart() {
		
		//DB상에서 limit의 시작번지를 구하는 메서드
		//1=>0 2=> 10 3=>20 ....
		return (this.pageNo-1)*this.qty;
	}
	
	//여러가지 타입을 같이 검색하기 위해서 타입을 배열로 구분
	public String[] getTypeToArray() {
		return this.type==null ? new String[] {} : this.type.split("");
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "PagingVO [pageNo=" + pageNo + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + "]";
	}
	

}
