package kr.co.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import kr.co.board.domain.BoardDTO;
import kr.co.board.service.BoardService;
import kr.co.common.domain.PageTO;
import kr.co.common.utils.UploadFileUtils;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService bService;

	@Inject
	private ServletContext sc;

	private String uploadPath = "D:" + File.separator + "upload";

	@RequestMapping(value = "/showOrDownload", method = RequestMethod.GET)
	public ResponseEntity<byte[]> showOrDownload(String filename) {
		ResponseEntity<byte[]> entity = null;

		entity = UploadFileUtils.showOrDownload(uploadPath, filename);

		return entity;
	}

	@RequestMapping(value = "/showimgfile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> shwImgFile(String filename) {
		ResponseEntity<byte[]> entity = null;

		InputStream in = null;

		try {
			in = new FileInputStream(uploadPath + filename);

			byte[] arr = IOUtils.toByteArray(in);

			entity = new ResponseEntity<byte[]>(arr, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return entity;
	}

	@RequestMapping(value = "getFilenames", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getFilenames(int bno) {
		return bService.getFilenames(bno);
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public String insert(MultipartHttpServletRequest request) {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Map<String, MultipartFile> map = request.getFileMap();

		Set<String> set = map.keySet();

		List<String> list = new ArrayList<String>(set);

		List<String> uploadedFileList = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);

			MultipartFile file = map.get(key);

			try {
				String uploadedFilename = UploadFileUtils.uploadFile(uploadPath, file, sc);

				uploadedFileList.add(uploadedFilename);

				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		BoardDTO dto = new BoardDTO(id, pw, title, content);
		dto.setUploadedFileList(uploadedFileList);

		int result = 1;

		try {
			bService.insert(dto);
		} catch (Exception e1) {
			e1.printStackTrace();

			result = 0;
		}

		if (result == 0) {
			System.out.println("업로드 한 파일을 삭제하자.");

			try {
				UploadFileUtils.deleteUploadFiles(uploadPath, uploadedFileList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dto.getBno() + "";
	}

	@RequestMapping(value = "fuajaxtest", method = RequestMethod.POST)
	@ResponseBody
	public String fuAjaxTest(MultipartHttpServletRequest request) {

		String id = request.getParameter("id");

		Map<String, MultipartFile> map = request.getFileMap();

		Set<String> set = map.keySet();

		List<String> list = new ArrayList<String>(set);

		for (int i = 0; i < list.size(); i++) {
			String key = list.get(i);

			MultipartFile file = map.get(key);

			try {
				UploadFileUtils.uploadFile(uploadPath, file, sc);

				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "ok";
	}

//	@RequestMapping(value = "fuajaxtest", method = RequestMethod.POST)
//	@ResponseBody
//	public String fuAjaxTest(MultipartHttpServletRequest request) {
//		List<MultipartFile> list = request.getFiles("file");
//		
//		for(int i=0; i<list.size(); i++) {
//			MultipartFile file = list.get(i);
//			
//			try {
//				UploadFileUtils.uploadFile(uploadPath, file, sc);
//				
//				Thread.sleep(50);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return "ok";
//	}

	@RequestMapping(value = "futest", method = RequestMethod.POST)
	// MultipartFile 사용시 ㅣ문자열을 받을 수 없음
	public String fileUploadTest(MultipartHttpServletRequest request) {
		String id = request.getParameter("id");
		MultipartFile file = request.getFile("file");

		try {
			UploadFileUtils.uploadFile(uploadPath, file, sc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/board/list";
	}

	@RequestMapping(value = "futest", method = RequestMethod.GET)
	public String fileUploadTest() {
		return "/board/futest";
	}

	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.POST)
	@ResponseBody
	public Integer delete(@PathVariable Integer bno, @RequestParam Map<String, Object> map) {
		Integer result = 0;

		map.put("bno", bno);

		List<String> list = bService.getFilenames(bno);

		result = bService.delete(map);

		if (result >= 1) {
			try {
				UploadFileUtils.deleteUploadFiles(uploadPath, list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	@ResponseBody
//	public int update(@RequestParam Map<String, Object> map) {
//		int result = 0;
//
//		try {
//			result = bService.update(map);
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			String msg = "";
//			msg += "오류정보 : " + e.getMessage();
//			msg += "원인 : " + e.getCause();
//			StringWriter errors = new StringWriter();
//			msg += "상세정보 : " + errors.toString();
//
//		}
//
//		return result;
//
//	}

	@RequestMapping(value = "/update/{bno}", method = RequestMethod.POST)
	@ResponseBody
	public int update(@PathVariable("bno") Integer bno, MultipartHttpServletRequest request) {
		int result = 1;

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		String uploadedFileDeleteListStr = request.getParameter("uploadedFileDeleteList");

		String[] uploadedFileDeleteListArr = uploadedFileDeleteListStr.split(",");

		List<String> uploadedFileDeleteList = new ArrayList<String>();

		for (int i = 0; i < uploadedFileDeleteListArr.length; i++) {
			// 혹시 모를 공백을 대비하여 trim() 사용
			String filename = uploadedFileDeleteListArr[i].trim();

			if (filename.equals("")) {
				break;
			}

			uploadedFileDeleteList.add(filename);

		}

		Map<String, MultipartFile> map = request.getFileMap();
		Set<String> set = map.keySet();
		List<String> keyList = new ArrayList<String>(set);
		List<String> uploadedFileList = new ArrayList<String>();

		for (int i = 0; i < keyList.size(); i++) {
			String key = keyList.get(i);
			MultipartFile file = map.get(key);

			try {
				String uploadedFilename = UploadFileUtils.uploadFile(uploadPath, file, sc);
				uploadedFileList.add(uploadedFilename);

				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		BoardDTO dto = new BoardDTO(id, bno, pw, title, content);
		dto.setUploadedFileList(uploadedFileList);

		result = bService.update(dto, uploadedFileDeleteList);

		if (result >= 1) {
			try {
				UploadFileUtils.deleteUploadFiles(uploadPath, uploadedFileDeleteList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				UploadFileUtils.deleteUploadFiles(uploadPath, uploadedFileList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@RequestMapping(value = { "/updateui/{curpage}/{criteria}/{keyword}/{bno}",
			"/updateui/{curpage}/{bno}" }, method = RequestMethod.GET)
	public String update(@PathVariable Map<String, Object> map, Model model) {

		BoardDTO dto = bService.updateUI(map.get("bno"));

		model.addAttribute("dto", dto);
		model.addAttribute("curpage", map.get("curpage"));
		model.addAttribute("criteria", map.get("criteria"));
		model.addAttribute("keyword", map.get("keyword"));

		return "/board/update";
	}

	@RequestMapping(value = { "/read/{bno}/{curpage}/{criteria}/{keyword}", "/read/{bno}",
			"/read/{bno}/{curpage}" }, method = RequestMethod.GET)
	public String read(@PathVariable Map<String, String> map, Model model) {
		// curpage, criteria, keyword 가 null 일 수 있기 때문에
		// read() 매개변수 자료형 변경이 필요
		// Integer bno -> Map<String, String> map
		// 그에 따른 코드 변경

		String sBno = map.get("bno");
		int bno = -1;
		if (sBno != null) {
			bno = Integer.parseInt(sBno);
		}

		BoardDTO dto = bService.read(bno);

		model.addAttribute("dto", dto);

		String scurpage = map.get("curpage");
		int curpage = 1;

		if (scurpage != null) {
			curpage = Integer.parseInt(scurpage);
		}

		model.addAttribute("curpage", curpage);

		String criteria = map.get("criteria");
		String keyword = map.get("keyword");
		// criteria 와 keyword 는 int 가 아니므로 null 이여도 상관없음

		model.addAttribute("criteria", criteria);
		model.addAttribute("keyword", keyword);

		return "board/read";
	}

	@RequestMapping(value = { "/list", "/list/{curpage}", "list/{criteria}/{keyword}",
			"/list/{curpage}/{criteria}/{keyword}" }, method = RequestMethod.GET)
	public String list(Model model, @PathVariable Map<String, String> map) {
		// dispatcher 방식으로 포워딩 할 경우 void 로 받아도 됨

		PageTO<BoardDTO> pt = bService.list(map);

		model.addAttribute("list", pt.getList());
		model.addAttribute("pt", pt);

		return "/board/list";
	}

	@RequestMapping(value = "/insert2", method = RequestMethod.POST)
	public String insert(BoardDTO dto) {
		// bService.insert(dto);

		return "redirect:/board/list/";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {

	}

}
