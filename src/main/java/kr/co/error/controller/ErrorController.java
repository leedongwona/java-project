package kr.co.error.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.error.domain.ErrorDTO;
import kr.co.error.service.ErrorService;


@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@Autowired
	private ErrorService eService;
	
	// 에러목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		
		List<ErrorDTO> list = eService.errorList();
		model.addAttribute("list", list);
		
		return "/error/list";
	}
	
	// 에러목록(error)
	@RequestMapping(value = "/list_error", method = RequestMethod.GET)
	public String list_error(Model model) {
		
		List<ErrorDTO> list = eService.error_errorList();
		model.addAttribute("list", list);
		
		return "/error/list_error";
	}
	
	// 에러목록(fixing)
	@RequestMapping(value = "/list_fixing", method = RequestMethod.GET)
	public String list_fixing(Model model) {
		
		List<ErrorDTO> list = eService.error_fixingList();
		model.addAttribute("list", list);
		
		return "/error/list_fixing";
	}
	
	// 에러목록(complete)
	@RequestMapping(value = "/list_complete", method = RequestMethod.GET)
	public String list_complete(Model model) {
			
		List<ErrorDTO> list = eService.error_completeList();
		model.addAttribute("list", list);
			
		return "/error/list_complete";
	}
	
	
	// 에러정보확인
	@RequestMapping(value = "/read/{eno}", method = RequestMethod.GET)
	public String read(@PathVariable("eno") int eno, Model model) {

		ErrorDTO dto = eService.read(eno);
		model.addAttribute("dto", dto);

		return "error/read";
	}
	
	// 에러정보변경 화면이동
	@RequestMapping(value = "/update/{eno}", method = RequestMethod.GET)
	public String update(@PathVariable("eno") int eno, Model model) {
		ErrorDTO dto = eService.updateui(eno);
		
		model.addAttribute("dto", dto);
		
		return "error/update";
		
	}
	
	// 에러정보변경
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ErrorDTO dto, Model model) {
		dto = eService.update(dto);
		model.addAttribute("dto", dto);
		
		return "redirect:/error/read/" +dto.getEno();
	}

}
