package kr.co.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.admin.domain.DMemberDTO;
import kr.co.admin.service.AdminService;
import kr.co.member.domain.MemberDTO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService aService;
	
	// 회원목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<MemberDTO> list = aService.adminList();
		model.addAttribute("list", list);
		
		return "/admin/list";
	}
	
	// 회원삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public int delete(HttpServletRequest request, @RequestParam(value = "checkboxArr[]")List<String> checkboxArr) {
		int result = 0;
		result = aService.delete(checkboxArr);
		
		return result;
	}
	
	// 탈퇴회원목록
	@RequestMapping(value = "/delete_list", method = RequestMethod.GET)
	public String delete_list(Model model) {
		
		List<DMemberDTO> list = aService.delete_adminList();
		model.addAttribute("list", list);
		
		return "/admin/delete_list";
	}
	
}
