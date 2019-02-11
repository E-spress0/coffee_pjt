package com.coffee_admin.www.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffee_admin.www.model.board.BoardModel;
import com.coffee_admin.www.model.board.BoardPagingModel;
import com.coffee_admin.www.service.board.BoardServiceImpl;

/**
 * Handles requests for the application home page.
 */
@SessionAttributes("userInfo")
@Controller
public class BoardController {

	@Autowired
	BoardServiceImpl bService;

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	/////////////////////////// upload test/////////////////////////
	@RequestMapping(value = "/uptest", method = RequestMethod.GET)
	public String uptest() {
		return "board/uptest";
	}

	/*@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public void upinsert(MultipartFile file) throws Exception {
		log.info("upload() POST 호출");
		log.info("파일 이름: {}", file.getOriginalFilename());
		log.info("파일 크기: {}", file.getSize());
	
		saveFile(file);
	}*/


	@RequestMapping(value = "/fileuploads", method = RequestMethod.POST)
	public void upinserts(MultipartFile[] files) throws Exception {
		String rs = "";
		for (MultipartFile f : files) {
			rs += saveFile(f);
		}
		System.out.println(rs);
	}

	//업-다운로드 위치 생성
	private static final String UPLOAD_PATH = "C:\\dev_test\\upload";

	//파일저장 메서드
	private String saveFile(MultipartFile file) {

		//날짜 지정
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");

		//		String thisday = sdf.format(today);

		// 파일 이름 변경
		UUID uuid = UUID.randomUUID();
		String saveName = sdf.format(today) + "_" + uuid + "_" + file.getOriginalFilename();

		log.info("saveName: {}", saveName);

		// 저장할 File 객체를 생성(껍데기 파일)
		File saveFile = new File(UPLOAD_PATH, saveName); // 저장할 폴더 이름, 저장할 파일 이름

		try {
			file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return saveName;
	}

	//default 게시판
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(Model model, HttpSession session, @ModelAttribute BoardModel boardModel,
	        @ModelAttribute BoardPagingModel boardPagingModel, @RequestParam(defaultValue = "1") int curPage,
	        HttpServletRequest req) throws Exception {

		boardPagingModel.setKeys(0);
		boardPagingModel.setWord(null);
		boardPagingModel = bService.selectBoardCount(boardPagingModel);
		/*
		String allListCnt = listCnt.toString();
		int startListCnt = allListCnt.indexOf("listCnt") + 8;
		int endListCnt = allListCnt.indexOf(",", startListCnt);
		boardModel = new BoardModel(Integer.parseInt(allListCnt.substring(startListCnt, endListCnt)), curPage);
		*/
		boardPagingModel = new BoardPagingModel(boardPagingModel.getListCnt(), curPage, 0, "");
		List<BoardModel> list = bService.selectBoardList(boardPagingModel);
		if (!ObjectUtils.isEmpty(list)) {
			model.addAttribute("thisBoardInfo", boardPagingModel);
			System.out.println("this info : " + boardPagingModel + "\nnonePickData : " + list.get(0)
			        + "\nTHIS TOP SEQ! : " + list.get(0).getSeq().toString());
		} else {
			System.out.println("게시판 목록 없음");
			System.out.println(list);
		}

		System.out.println("this num : " + curPage);
		//		System.out.println("this statrt : " + startListCnt);
		//		System.out.println("this end : " + endListCnt);

		model.addAttribute("boardInfo", list);

		return "board/board_list";
	}

	@RequestMapping(value = "searchList", method = RequestMethod.GET)
	public String searchBoard(Model model, HttpSession session, @ModelAttribute BoardModel boardModel,
	        @ModelAttribute BoardPagingModel boardPagingModel, @RequestParam(defaultValue = "1") int curPage,
	        HttpServletRequest req) throws Exception {
		if (!ObjectUtils.isEmpty(req.getParameter("sType")) && !ObjectUtils.isEmpty(req.getParameter("sData"))) {

			int key = Integer.parseInt(req.getParameter("sType"));
			String word = req.getParameter("sData");

			//키워드 지정
			boardPagingModel.setKeys(key);
			boardPagingModel.setWord(word);
			boardPagingModel = bService.selectBoardCount(boardPagingModel);

			boardPagingModel = new BoardPagingModel(boardPagingModel.getListCnt(), curPage, key, word);

			List<BoardModel> list = bService.selectBoardList(boardPagingModel);
			model.addAttribute("boardInfo", list);
			model.addAttribute("thisBoardInfo", boardPagingModel);

			System.out.println("this info : " + boardPagingModel);
			System.out.println("key : " + key + "word : " + word);

			return "board/board_list";
		} else {
			return "redirect:/board";
		}
	}

	@RequestMapping(value = "boardWrite", method = RequestMethod.GET)
	public String boardWrite() {
		return "board/boardWrite";
	}

	@RequestMapping(value = "replyWrite", method = RequestMethod.POST)
	public String replyWrite() {

		return "";
	}
}
