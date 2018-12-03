package com.coffee_admin.www.service.board;

import java.util.List;

import com.coffee_admin.www.model.board.BoardModel;

public interface BoardService {
	List<BoardModel> selectBoardList(BoardModel boardModel) throws Exception;

	BoardModel selectBoardCount() throws Exception;
}
