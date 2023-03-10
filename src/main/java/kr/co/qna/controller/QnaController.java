package kr.co.qna.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.websocket.Session;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

import kr.co.qna.domain.QnaDTO;
import kr.co.qna.service.QnaService;
import kr.co.common.domain.PageTO;
import kr.co.common.utils.UploadFileUtils;

@Controller
@RequestMapping("/qna")
public class QnaController {

	@Inject
	private QnaService qService;

	@Inject
	private ServletContext qsc;

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
		return qService.getFilenames(bno);
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
				String uploadedFilename = UploadFileUtils.uploadFile(uploadPath, file, qsc);

				uploadedFileList.add(uploadedFilename);

				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		QnaDTO dto = new QnaDTO(id, pw, title, content);
		dto.setUploadedFileList(uploadedFileList);

		int result = 1;

		try {
			qService.insert(dto);
		} catch (Exception e1) {
			e1.printStackTrace();

			result = 0;
		}

		if (result == 0) {
			System.out.println("????????? ??? ????????? ????????????.");

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
				UploadFileUtils.uploadFile(uploadPath, file, qsc);

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
//				UploadFileUtils.uploadFile(uploadPath, file, qsc);
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
	// MultipartFile ????????? ??????????????? ?????? ??? ??????
	public String fileUploadTest(MultipartHttpServletRequest request) {
		String id = request.getParameter("id");
		MultipartFile file = request.getFile("file");

		try {
			UploadFileUtils.uploadFile(uploadPath, file, qsc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/qna/list";
	}

	@RequestMapping(value = "futest", method = RequestMethod.GET)
	public String fileUploadTest() {
		return "/qna/futest";
	}

	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.POST)
	@ResponseBody
	public Integer delete(@PathVariable Integer bno, @RequestParam Map<String, Object> map) {
		Integer result = 0;

		map.put("bno", bno);

		List<String> list = qService.getFilenames(bno);

		result = qService.delete(map);

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
//			msg += "???????????? : " + e.getMessage();
//			msg += "?????? : " + e.getCause();
//			StringWriter errors = new StringWriter();
//			msg += "???????????? : " + errors.toString();
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
			// ?????? ?????? ????????? ???????????? trim() ??????
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
				String uploadedFilename = UploadFileUtils.uploadFile(uploadPath, file, qsc);
				uploadedFileList.add(uploadedFilename);

				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		QnaDTO dto = new QnaDTO(id, bno, pw, title, content);
		dto.setUploadedFileList(uploadedFileList);

		result = qService.update(dto, uploadedFileDeleteList);

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

		QnaDTO dto = qService.updateUI(map.get("bno"));

		model.addAttribute("dto", dto);
		model.addAttribute("curpage", map.get("curpage"));
		model.addAttribute("criteria", map.get("criteria"));
		model.addAttribute("keyword", map.get("keyword"));

		return "/qna/update";
	}

	@RequestMapping(value = { "/read/{bno}/{curpage}/{criteria}/{keyword}", "/read/{bno}",
			"/read/{bno}/{curpage}" }, method = RequestMethod.GET)
	public String read(@PathVariable Map<String, String> map, Model model) {
		// curpage, criteria, keyword ??? null ??? ??? ?????? ?????????
		// read() ???????????? ????????? ????????? ??????
		// Integer bno -> Map<String, String> map
		// ?????? ?????? ?????? ??????

		String sBno = map.get("bno");
		int bno = -1;
		if (sBno != null) {
			bno = Integer.parseInt(sBno);
		}

		QnaDTO dto = qService.read(bno);

		model.addAttribute("dto", dto);

		String scurpage = map.get("curpage");
		int curpage = 1;

		if (scurpage != null) {
			curpage = Integer.parseInt(scurpage);
		}

		model.addAttribute("curpage", curpage);

		String criteria = map.get("criteria");
		String keyword = map.get("keyword");
		// criteria ??? keyword ??? int ??? ???????????? null ????????? ????????????

		model.addAttribute("criteria", criteria);
		model.addAttribute("keyword", keyword);

		return "qna/read";
	}

	@RequestMapping(value = { "/list", "/list/{curpage}", "list/{criteria}/{keyword}",
			"/list/{curpage}/{criteria}/{keyword}" }, method = RequestMethod.GET)
	public String list(Model model, @PathVariable Map<String, String> map) {
		// dispatcher ???????????? ????????? ??? ?????? void ??? ????????? ???

		PageTO<QnaDTO> pt = qService.list(map);

		model.addAttribute("list", pt.getList());
		model.addAttribute("pt", pt);

		return "/qna/list";
	}

	@RequestMapping(value = "/insert2", method = RequestMethod.POST)
	public String insert(QnaDTO dto) {
		// bService.insert(dto);

		return "redirect:/qna/list/";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {

	}

}
