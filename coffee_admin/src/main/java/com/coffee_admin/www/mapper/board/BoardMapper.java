package com.coffee_admin.www.mapper.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.coffee_admin.www.model.board.BoardModel;
import com.coffee_admin.www.model.board.BoardPagingModel;

@Repository
public interface BoardMapper {
	List<BoardModel> selectBoardList(BoardPagingModel boardPagingModel) throws Exception;

	BoardPagingModel selectBoardCount(BoardPagingModel boardPagingModel) throws Exception;

}
