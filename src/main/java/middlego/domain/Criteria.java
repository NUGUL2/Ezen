package middlego.domain;

public class Criteria {
//리스트 화면에 몇개를 나타내 줄 것인가, 페이지에 대한 값을 담는 용도로 쓰임
	
	
	private int page;		 //페이지번호
	private int perPageNum;	 //화면에 리스트 출력 개수
	
	
	
	public Criteria() {
		this.page=1;
		this.perPageNum = 15;
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		
		if(page<=1) {
			this.page=1;
			return;
		}
		
		this.page = page;
	}



	public int getPerPageNum() {
		return perPageNum;
	}



	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	
	
	
	
	
	
	
	
	
	
}
