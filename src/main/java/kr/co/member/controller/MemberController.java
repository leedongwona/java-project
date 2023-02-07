package kr.co.member.controller;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.error.service.ErrorService;
import kr.co.member.domain.MemberDTO;
import kr.co.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService mService;
	
	@Autowired 
	private ErrorService eService;
	

	// 회원관리 관련코드
	// 회원가입 화면이동
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {

		return "member/insert";
	}

	// 회원가입
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberDTO dto) {
		mService.insert(dto);

		return "redirect:/member/read/" + dto.getId();
	}

	// 아이디 중복확인
	@RequestMapping(value = "/checkid", method = RequestMethod.POST)
	@ResponseBody
	public String checkid(@RequestParam Map<String, Object> map) {
		MemberDTO dto = mService.checkId(map);
		int result = 0;
		if (dto == null) {
			result = 1;
		}
		return result + "";
	}

	// 회원정보확인
	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public String read(@PathVariable("id") String id, Model model) {

		MemberDTO dto = mService.read(id);
		model.addAttribute("dto", dto);

		return "member/read";
	}

	// 회원정보변경 화면이동
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") String id, Model model) {
		try {
			MemberDTO dto = mService.updateui(id);
			model.addAttribute("dto", dto);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "";
			msg += "오류정보 : " + e.getMessage();
			msg += "원인 : " + e.getCause();
			StringWriter errors = new StringWriter();
			msg += "상세정보 : " + errors.toString();
			System.out.println(msg);
			
		}

		return "member/update";
	}

	// 회원정보변경
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MemberDTO dto, Model model) {
		try {
			dto = mService.update(dto);
			model.addAttribute("dto", dto);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "";
			msg += "오류정보 : " + e.getMessage();
			msg += "원인 : " + e.getCause();
			
			System.out.println(msg);
				
				
			eService.insert(msg);
		}

		return "redirect:/member/read/" + dto.getId();
	}

	// 비밀번호변경
	@RequestMapping(value = "/changepw", method = RequestMethod.POST)
	@ResponseBody
	public String changePw(@RequestParam Map<String, Object> map) {
		int result = mService.changePw(map);

		return result + "";
	}

	// 회원탈퇴
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Integer delete(@PathVariable String id, @RequestParam Map<String, Object> map) {
		Integer result = 0;

		map.put("id", id);

		result = mService.delete(map);

		return result;
	}

	// 로그인 관련코드
	// 로그인 화면이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "member/login";
	}

	// 로그인
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void login(MemberDTO login, Model model) {

		login = mService.login(login);

		model.addAttribute("login", login);
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {

		return "redirect:/";
	}

}
