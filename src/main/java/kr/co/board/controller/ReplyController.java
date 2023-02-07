package kr.co.board.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.board.domain.ReplyDTO;
import kr.co.board.service.ReplyService;

// RestController : ajax 통신을 전문으로 하는 컨트롤러 , RESTful app 
@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyService rService;

	@RequestMapping(value = "/{bno}/all", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable Integer bno){
		ResponseEntity<List<ReplyDTO>> entity = null;
		
		try {
			List<ReplyDTO> rList = rService.list(bno);
			entity = new ResponseEntity<List<ReplyDTO>>(rList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestBody Map<String, Object> map) {
		ResponseEntity<String> entity = null;

		try {
			Integer result = rService.delete(map);
			if (result > 0) {
				entity = new ResponseEntity<String>("DELETE_SUCCESS", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("DELETE_FAIL", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	// restcontroller 의 경우 @requestparam 이 아닌 @requestbody 사용
	public ResponseEntity<String> update(@RequestBody Map<String, Object> map) {
		ResponseEntity<String> entity = null;

		try {
			rService.update(map);

			entity = new ResponseEntity<String>("UPDATE_SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			String res = "";
			e.printStackTrace(); // 출력
			res += "오류정보 : " + e.getMessage();
			res += "원인 : " + e.getCause();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			res += "상세정보 : " + errors.toString();
			System.out.println(res);
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody Map<String, Object> map) {
		ResponseEntity<String> entity = null;

		try {
			rService.insert(map);

			entity = new ResponseEntity<String>("INSERT_SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

}
