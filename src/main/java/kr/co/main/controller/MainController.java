package kr.co.main.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.board.service.BoardService;

@Controller
@RequestMapping("/mainpage")
public class MainController {


	// 메인화면 화면이동
	@RequestMapping(value = "/mainhome", method = RequestMethod.GET)
	public String login() {

		return "mainpage/mainhome";
	}
}
