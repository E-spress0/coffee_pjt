package com.coffee_admin.www.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffee_admin.www.model.HomeModel;
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

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String slash() throws Exception {
		return "redirect:/login.do";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @ModelAttribute HomeModel homeModel) throws Exception {
		log.info("Welcome home! The client locale is {}.", locale);

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

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "login/login";
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck() {

		return "";
	}
}
