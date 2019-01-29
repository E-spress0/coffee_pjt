package com.coffee_admin.www.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.coffee_admin.www.model.board.BoardModel;
import com.coffee_admin.www.model.board.BoardPagingModel;

public interface BoardService {
	List<BoardModel> selectBoardList(BoardPagingModel boardPagingModel) throws Exception;

	BoardPagingModel selectBoardCount(BoardPagingModel boardPagingModel) throws Exception;

	void insertBoard(HttpServletRequest req) throws Exception;
}
