package com.coffee_admin.www.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee_admin.www.mapper.board.BoardMapper;
import com.coffee_admin.www.model.board.BoardModel;
import com.coffee_admin.www.model.board.BoardPagingModel;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper bMapper;

	@Override
	public List<BoardModel> selectBoardList(BoardPagingModel boardPagingModel) throws Exception {
		return bMapper.selectBoardList(boardPagingModel);
	}

	@Override
	public BoardPagingModel selectBoardCount(BoardPagingModel boardPagingModel) throws Exception {
		return bMapper.selectBoardCount(boardPagingModel);
	}
}
