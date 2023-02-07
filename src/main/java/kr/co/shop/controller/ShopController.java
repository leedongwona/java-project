package kr.co.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {

		return "shop/list";
	}
	@RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
	public String shopdetail() {

		return "shop/shopdetail";
	}
	
}
