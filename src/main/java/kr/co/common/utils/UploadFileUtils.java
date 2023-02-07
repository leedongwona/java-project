package kr.co.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.aspectj.runtime.internal.AroundClosure;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtils {

	// 파일 업로드 코드
	public static String uploadFile(String uploadPath, MultipartFile file, ServletContext sc) throws Exception {

		String originalFilename = file.getOriginalFilename();

		String filename = makeFilename(originalFilename);

		byte[] arr = file.getBytes();

		// 프로젝트 시 사용
		// uploadPath = changeToAbsolutePath(uploadPath, sc);
		String datePath = makePath(uploadPath);
		// 현재의 날짜로 폴더경로 지정 -> /2023/01/05

		File target = new File(uploadPath + datePath, filename);
		// D:/upload/2023/01/05
		// 파일명 수정해서 저장

		FileCopyUtils.copy(arr, target);

		// 이미지 파일인지 아닌지 구분
		String formatName = getFormatNames(filename);
		MediaType mType = getMediaType(formatName);

		String uploadedFilename = null;

		if (mType == null) {
			uploadedFilename = makeIcon(datePath, filename);
			// /2023/01/05/uuid_입력한 파일명.xlx , txt ... 이미지 파일이 아닌 확장자
		} else {
			uploadedFilename = makeThumbnail(datePath, filename, uploadPath);
			// 썸네일 생성
			// 썸네일의 파일명이 반환됨
			// /2023/01/05/s_uuid_입력한 파일명.png , img ... 확장자가 이미지 파일
		}

		return uploadedFilename;

	}
	
	// 썸네일 아래 이름 생성 코드
	private static String makeIcon(String datePath, String filename) {

		String uploadedfilename = datePath + File.separator + filename;
		// \2023\01\06\aaa_a.txt : 역슬래쉬로 잡혀있음
		// /2023/01/06/aaa_a.txt : 브라우저의 경우 슬래쉬이므로 바꿔줘야함

		uploadedfilename = uploadedfilename.replace(File.separatorChar, '/');

		return uploadedfilename;
	}
	
	// 썸네일 파일이름 및 썸네일 생성코드 
	private static String makeThumbnail(String datePath, String filename, String uploadPath) throws Exception {
		
		String thumbnailName = "s_" + filename;
		File tFile = new File(uploadPath + datePath, thumbnailName);

		String formatName = getFormatNames(filename);

		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + datePath, filename));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, 100);

		ImageIO.write(destImg, formatName, tFile);

		String uploadedFilename = datePath + File.separator + thumbnailName;
		// 역슬래쉬를 슬래쉬로 바꾸기

		uploadedFilename = uploadedFilename.replace(File.separatorChar, '/');

		return uploadedFilename;
	}

	// 매개변수의 filename 은 시스템에서 관리하는 filename
	public static String getOriginalFilename(String filename) {
		int idx = filename.indexOf("_") + 1;
		String originalFilename = filename.substring(idx);

		return originalFilename;
	}
	
	// 디렉토리에 저장될 파일 이름 생성
	public static String makeFilename(String filename) {
		UUID uid = UUID.randomUUID();
		String saveName = uid.toString() + "_" + filename;

		return saveName;
	}

	public static String changeToAbsolutePath(String uploadPath, ServletContext sc) {

		return sc.getRealPath(uploadPath);
	}

	// 미디어 파일 인지 확인하는 코드
	// getmediatype 은 org.springframework.http 사용
	public static MediaType getMediaType(String formatName) {
		formatName = formatName.toLowerCase();
		// 소문자로 만드는 코드

		Map<String, MediaType> map = new HashMap<String, MediaType>();

		map.put("png", MediaType.IMAGE_PNG);
		map.put("gif", MediaType.IMAGE_GIF);
		map.put("jpeg", MediaType.IMAGE_JPEG);
		map.put("jpg", MediaType.IMAGE_JPEG);

		// formatname이 넷 중 하나면 mediatype 이 나오고 아니면 null 반환
		return map.get(formatName);
	}
	
	// 파일의 형식 이름 가져오는 코드
	public static String getFormatNames(String filename) {

		int idx = filename.lastIndexOf(".") + 1;
		String formatName = filename.substring(idx);
		return formatName;
	}
	
	// 파일 생성될 경로 만드는 코드
	public static String makePath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);

		int month = cal.get(Calendar.MONTH) + 1;
		String smonth = month < 10 ? "0" + month : month + "";

		int date = cal.get(Calendar.DATE);
		String sdate = date < 10 ? "0" + date : date + "";

		String monthPath = yearPath + File.separator + smonth;
		String datePath = monthPath + File.separator + sdate;

		makeDir(yearPath, monthPath, datePath, uploadPath);
		
		// 파일경로 : 현재 년도 / 현재 월 / 현재 일 
		return datePath;
	}
	
	// 파일 생성경로가 있는지 확인 없다면 생성
	public static void makeDir(String yearPath, String monthPath, String datePath, String uploadPath) {
		
		// 파일생성 경로가 없을 시 작동
		File fy = new File(uploadPath, yearPath);
		if (!fy.exists()) {
			fy.mkdir();
		}

		File fm = new File(uploadPath, monthPath);
		if (!fm.exists()) {
			fm.mkdir();
		}

		File fd = new File(uploadPath, datePath);
		if (!fd.exists()) {
			fd.mkdir();
		}

	}
	
	// 업로드 파일 삭제
	public static void deleteUploadFiles(String uploadPath, List<String> uploadedFileList) throws Exception {

		for (int i = 0; i < uploadedFileList.size(); i++) {
			// 썸네일 파일 삭제
			String uploadedFilename = uploadedFileList.get(i);
			File deleteFile = new File(uploadPath, uploadedFilename);

			if (deleteFile.exists()) {
				deleteFile.delete();
			}

			// 오리지널 파일 삭제
			int idx = uploadedFilename.indexOf("s_");

			// 미디어 파일과 일반 파일 구분
			// 일반파일의 경우 idx = -1 출력
			if (idx != -1) {

				String prefix = uploadedFilename.substring(0, idx);
				String suffix = uploadedFilename.substring(idx + 2);

				String filename = prefix + suffix;

				deleteFile = new File(uploadPath, filename);

				if (deleteFile.exists()) {
					deleteFile.delete();
				}

			}
			// 너무 빠른 진행을 막기위한 딜레이 
			Thread.sleep(50);
		}
	}
	
	// 파일의 화면 출력 및 다운로드
	public static ResponseEntity<byte[]> showOrDownload(String uploadPath, String filename) {
		ResponseEntity<byte[]> entity = null;

		String formatName = getFormatNames(filename);
		MediaType mType = getMediaType(formatName);

		// 이미지 파일과 일반 파일의 contentType 구분을 위해 사용
		HttpHeaders headers = new HttpHeaders();

		InputStream in = null;

		try {
			
			// 일반 파일
			if (mType != null) {
				String prefix = filename.substring(0, 12);
				String suffix = filename.substring(14);

				filename = prefix + suffix;

				in = new FileInputStream(uploadPath + filename);

				headers.setContentType(mType);
			
			// 미디어 파일
			} else {

				in = new FileInputStream(uploadPath + filename);

				// 파일을 다운로드 하게 해주는 미디어 파일
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

				int idx = filename.indexOf("_") + 1;
				String originalFilename = filename.substring(idx);
				originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");

				headers.add("Content-Disposition", "attachment;filename=\"" + originalFilename + "\"");
			}

			byte[] arr = IOUtils.toByteArray(in);

			entity = new ResponseEntity<byte[]>(arr, headers, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
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

}
