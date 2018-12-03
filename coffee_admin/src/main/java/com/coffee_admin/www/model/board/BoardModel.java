package com.coffee_admin.www.model.board;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Alias("BoardModel")
@EqualsAndHashCode(callSuper = false)
public class BoardModel {
	private BoardModel() {}

	private Integer seq;
	private String writer;
	private String title;
	private String content;
	/** 한 페이지에 생성되는 페이지 개수 bottom_row */
	private static final int pageSize = 10;

	/** 한 페이지에 생성되는 게시물 개수 col */
	private static final int board_Size = 10;

	/** 사용자에게 출력되는 현재 페이지 번호 */
	private int nowPage; //curPage

	/** 총 게시물 수 */
	private int listCnt;

	/** 마지막 페이지 번호 */
	private int lastPage;

	private int startPage = 1;
	private int endPage = 1;

	private int startBoard = 1;
	private int endBoard = 1;

	private int prevPage;
	private int nextPage;

	public BoardModel(int listCnt, int nowPage) {
		setListCnt(listCnt);
		setNowPage(nowPage);
		setLastPage(listCnt);
		if (nowPage > 10) {
			setStartPage(nowPage);
		}
		setEndPage(nowPage);
		setStartBoard(nowPage);
		setEndBoard(listCnt, nowPage);
	}

	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public void setLastPage(int listCnt) {
		this.lastPage = (listCnt % pageSize > 0) ? listCnt / pageSize + 1 : listCnt / pageSize;
	}

	public void setStartPage(int nowPage) {
		this.startPage = nowPage / pageSize * pageSize + 1;
	}

	public void setEndPage(int nowPage) {
		if (nowPage > 10 && lastPage > 10) {
			this.endPage = ((nowPage / 10 * 10 + 10) >= lastPage) ? lastPage : nowPage / 10 * 10 + 10;
		} else if (nowPage > 0 && lastPage > 10) {
			this.endPage = 10;
		} else {
			this.endPage = lastPage;
		}
	}

	public void setStartBoard(int nowPage) {
		this.startBoard = (nowPage - 1) * board_Size + 1;
	}

	public void setEndBoard(int listCnt, int nowPage) {
		this.endBoard = ((nowPage - 1) * board_Size + 10 <= listCnt) ? (nowPage - 1) * board_Size + 10 : listCnt;
	}

}
