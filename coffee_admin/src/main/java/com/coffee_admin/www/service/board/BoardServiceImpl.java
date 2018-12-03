package com.coffee_admin.www.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee_admin.www.mapper.board.BoardMapper;
import com.coffee_admin.www.model.board.BoardModel;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper bMapper;

	@Override
	public List<BoardModel> selectBoardList(BoardModel boardModel) throws Exception {
		return bMapper.selectBoardList(boardModel);
	}

	@Override
	public BoardModel selectBoardCount() throws Exception {
		return bMapper.selectBoardCount();
	}

}
