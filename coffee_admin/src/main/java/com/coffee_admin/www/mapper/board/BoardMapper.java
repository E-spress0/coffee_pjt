package com.coffee_admin.www.mapper.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.coffee_admin.www.model.board.BoardModel;

@Repository
public interface BoardMapper {
	List<BoardModel> selectBoardList() throws Exception;
}
