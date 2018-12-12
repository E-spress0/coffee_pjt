package com.coffee_admin.www;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffee_admin.www.model.HomeModel;
import com.coffee_admin.www.model.board.BoardModel;
import com.coffee_admin.www.model.board.BoardPagingModel;
import com.coffee_admin.www.service.board.BoardServiceImpl;
import com.coffee_admin.www.service.home.HomeServiceImpl;

/**
 * Handles requests for the application home page.
 */
@SessionAttributes("userInfo")
@Controller
public class HomeController {

	@Autowired
	HomeServiceImpl hService;

	@Autowired
	BoardServiceImpl bService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String slash() throws Exception {
		return "redirect:/main";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @ModelAttribute HomeModel homeModel) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);

		List<HomeModel> list = hService.selectHome();
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("userInfo", list);
		return "home";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model, HttpSession session) throws Exception {

		return "main/main";
	}

	//default 게시판
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(Model model, HttpSession session, @ModelAttribute BoardModel boardModel,
	        @ModelAttribute BoardPagingModel boardPagingModel, @RequestParam(defaultValue = "1") int curPage,
	        HttpServletRequest req) throws Exception {
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

			/*switch (key) {
				case 1:
					boardPagingModel.setKey("title");
					break;
				case 2:
					boardPagingModel.setKey("content");
					break;
				case 4:
					boardPagingModel.setKey("writer");
					break;
				default:
					return "redirect:/board/board_list";
			}
			
			boardPagingModel.setNumberKey(key);
			boardPagingModel.setWord(word);*/

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
}
