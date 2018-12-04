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

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(Model model, HttpSession session, @ModelAttribute BoardModel boardModel,
	        @ModelAttribute BoardPagingModel boardPagingModel, @RequestParam(defaultValue = "1") int curPage,
	        HttpServletRequest req) throws Exception {
		boardPagingModel = bService.selectBoardCount();
		/*
		String allListCnt = listCnt.toString();
		int startListCnt = allListCnt.indexOf("listCnt") + 8;
		int endListCnt = allListCnt.indexOf(",", startListCnt);
		boardModel = new BoardModel(Integer.parseInt(allListCnt.substring(startListCnt, endListCnt)), curPage);
		*/
		boardPagingModel = new BoardPagingModel(boardPagingModel.getListCnt(), curPage);
		List<BoardModel> list = bService.selectBoardList(boardPagingModel);
		System.out.println("this info : " + boardPagingModel);
		System.out.println("this num : " + curPage);
		//		System.out.println("this statrt : " + startListCnt);
		//		System.out.println("this end : " + endListCnt);

		model.addAttribute("boardInfo", list);


		return "board/board_list";
	}
}
