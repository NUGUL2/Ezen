package middlego.domain;

public class PageMaker {
	//전체 개수를 담고, 각 개수마다 리스트에 나타내줌
		
		private int totalCount;//전체 데이터 개수
		private int startPage; //첫번째 번호
		private int endPage;   //마지막 번호
		private boolean prev;  //이전버튼
		private boolean next;  //다음버튼
		private int displayPageNum = 10; // 이전페이지 < 1, 2, 3, 4, 5, 6, ...> 다음페이지
		private SearchCriteria scri;  //인스턴스 변수
		
		
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
			calcDate(); 
		}
		public int getStartPage() {
			return startPage;
		}
		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}
		public int getEndPage() {
			return endPage;
		}
		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}
		public boolean isPrev() {
			return prev;
		}
		public void setPrev(boolean prev) {
			this.prev = prev;
		}
		public boolean isNext() {
			return next;
		}
		public void setNext(boolean next) {
			this.next = next;
		}
		public int getDisplayPageNum() {
			return displayPageNum;
		}
		public void setDisplayPageNum(int displayPageNum) {
			this.displayPageNum = displayPageNum;
		}

		public SearchCriteria getScri() {
			return scri;
		}
		public void setScri(SearchCriteria scri) {
			this.scri = scri;
		}
		
		
		//시작 페이지 끝 페이지 이전 다음 버튼 값을 생성하는 메소드
		public void calcDate() {
			
			//ceil : 올림차 / 2번 페이지로 이동해도 끝페이지는 10, 11번페이지로 이동하면 끝페이지는 20
			endPage = (int)(Math.ceil(scri.getPage()/(double)displayPageNum) * displayPageNum);
		
			startPage = (endPage-displayPageNum)+1;
			
			int tempEndPage = (int)(Math.ceil(totalCount/(double)scri.getPerPageNum()));
			
			if(endPage>tempEndPage) {
				endPage=tempEndPage;
			}
			
			prev = startPage ==1 ? false : true;
			next = endPage*scri.getPerPageNum() >=totalCount ? false:true; 
		}
		
		
		
		
		
		
}

