package com.coffee_admin.www.service.board;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

	@Override
	public void insertBoard(HttpServletRequest req) throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				System.out.println("------------- file start -------------");
				System.out.println("name : " + multipartFile.getName());
				System.out.println("filename : " + multipartFile.getOriginalFilename());
				System.out.println("size : " + multipartFile.getSize());
				System.out.println("-------------- file end --------------\n");
			}
		}
	}
}
