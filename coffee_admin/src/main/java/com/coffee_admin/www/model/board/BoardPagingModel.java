package com.coffee_admin.www.model.board;

import lombok.Data;

@Data
public class BoardPagingModel {

	/** 객체없는 생성자 인스턴스 초기화 */
	protected BoardPagingModel() {}

	/** 한 페이지에 생성되는 페이지 개수 bottom_row */
	private static final int pageSize = 10;

	/** 한 페이지에 생성되는 게시물 개수 col */
	private static final int board_Size = 10;

	/** 사용자에게 출력되는 현재 페이지 번호 */
	private int nowPage = 1; //curPage

	/** 총 게시물 수 */
	private int listCnt;

	/** 마지막 페이지 번호 */
	private int lastPage;

	/** 시작 페이지 */
	private int startPage = 1;

	/** 끝 페이지 */
	private int endPage = 1;

	/** 게시물 시작 번호 */
	private int startBoard = 1;

	/** 게시물 끝 번호 */
	private int endBoard = 1;

	/** 이전 페이지 */
	private int prevPage;

	/** 다음 페이지 */
	private int nextPage;

	/** 검색 */
	private int numberKey; //sType
	private String key; //sType
	private String word; //sData

	public BoardPagingModel(int listCnt, int nowPage, int key, String word) {
		setListCnt(listCnt);
		setNowPage(nowPage);
		setLastPage(listCnt);
		//		setNowPage(this.lastPage - nowPage + 1);
		if (this.nowPage > 10) {
			setStartPage(this.nowPage);
		}
		setEndPage(this.nowPage);
		setStartBoard();
		setEndBoard(this.listCnt);
		setPrevPage();
		setNextPage();
		setKeys(key);
		setWord(word);
	}

	private void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}

	private void setNowPage(int nowPage) {
		int intLength = (int) (Math.log10(nowPage) + 1);
		this.nowPage = (nowPage < 1) ? 1 : (intLength > 9) ? 1 : nowPage;
	}

	private void setLastPage(int listCnt) {
		this.lastPage = (listCnt % board_Size > 0) ? listCnt / board_Size + 1 : listCnt / board_Size;
	}

	private void setStartPage(int nowPage) {
		this.startPage = (nowPage - 1) / pageSize * pageSize + 1;
	}

	private void setEndPage(int nowPage) {
		/*if (nowPage > 10 && lastPage > 10) {*/
		if (lastPage > 10) {
			this.endPage = (((nowPage - 1) / pageSize * pageSize + pageSize) >= lastPage) ? lastPage
			        : (nowPage - 1) / pageSize * pageSize + pageSize;
		} /*else if (nowPage > 0 && lastPage > 10) {
		  this.endPage = 10;
		  }*/ else {
			this.endPage = lastPage;
		}
	}

	private void setStartBoard() {
		this.startBoard = (this.nowPage - 1) * board_Size;
	}

	private void setEndBoard(int listCnt) {
		this.endBoard = ((this.nowPage - 1) * board_Size + 10 <= listCnt) ? (this.nowPage - 1) * board_Size + 9
		        : listCnt;
	}

	/*public void setStartBoard() {
		this.startBoard = this.listCnt - ((this.nowPage - 1) * board_Size + (board_Size - 1));
		if (this.startBoard < 0) {
			this.startBoard = 0;
		}
	}
	
	public void setEndBoard(int listCnt) {
		this.endBoard = this.listCnt - ((this.nowPage - 1) * board_Size);
		if (this.startBoard == 0) {
			this.endBoard = 0;
		}
	}*/

	private void setNextPage() {
		this.nextPage = ((this.nowPage + 1) > this.lastPage) ? this.lastPage : this.nowPage + 1;
	}

	private void setPrevPage() {
		this.prevPage = ((this.nowPage - 1) < 1) ? 1 : this.nowPage - 1;
	}

	public void setKeys(int key) {
		this.numberKey = key;
		switch (key) {
			case 1:
				this.key = "title";
				break;
			case 3:
				this.key = "content";
				break;
			case 4:
				this.key = "writer";
				break;
			default:
				break;
		}
	}

	public void setWord(String word) {
		this.word = word;
	}

}
