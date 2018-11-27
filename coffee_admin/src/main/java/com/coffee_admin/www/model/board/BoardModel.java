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
	/** 한 페이지에 생성되는 게시물 개수 col */
	private int pageSize = 10;

	/** 한 페이지에 생성되는 페이지 개수 bottom_row */
	private int rangeSize = 10;

	private int curPage = 1;
	private int curRange = 1;
	private int listCnt;
	private int pageCnt;
	private int rangeCnt;
	private int startPage = 1;
	private int endPage = 1;
	private int startIndex = 0;
	private int prevPage;
	private int nextPage;



	public BoardModel(int listCnt, int curPage) {
		setCurPage(curPage);
		setListCnt(listCnt);
		setPageCnt(listCnt);
		setRangeCnt(pageCnt);
		rangeSetting(curPage);
		setStartIndex(curPage);
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getListCnt() {
		return listCnt;
	}

	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}

	public void setPageCnt(int listCnt) {
		this.pageCnt = (int) Math.ceil(listCnt * 1.0 / pageSize);
	}

	public void setRangeCnt(int pageCnt) {
		this.rangeCnt = (int) Math.ceil(pageCnt * 1.0 / rangeSize);
	}

	public void rangeSetting(int curPage) {
		setCurRange(curPage);
		this.startPage = (curRange - 1) * rangeSize + 1;
		if (endPage > pageCnt) {
			this.endPage = pageCnt;
		}
		this.prevPage = curPage - 1;
		this.nextPage = curPage + 1;
	}

	public void setCurRange(int curPage) {
		this.curRange = (curPage - 1) / rangeSize + 1;
	}

	public void setStartIndex(int curPage) {
		this.startIndex = (curPage - 1) * pageSize;
	}
}
