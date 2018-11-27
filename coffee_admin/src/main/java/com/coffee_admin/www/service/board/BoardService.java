package com.coffee_admin.www.service.board;

import java.util.List;

import com.coffee_admin.www.model.board.BoardModel;

public interface BoardService {
	List<BoardModel> selectBoardList() throws Exception;

	List<BoardModel> selectBoardCount() throws Exception;
}
