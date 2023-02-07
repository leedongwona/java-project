package kr.co.error.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.board.domain.BoardDTO;
import kr.co.common.domain.PageTO;
import kr.co.error.domain.ErrorDTO;
import kr.co.error.repository.ErrorDAO;
import kr.co.member.repository.MemberDAO;

@Service
public class ErrorServiceImpl implements ErrorService {

	@Autowired
	private ErrorDAO eDAO;

	// 에러저장
	@Override
	public void insert(String msg) {
		
		eDAO.insert(msg);
	}
	
	// 에러목록
	@Override
	public List<ErrorDTO> errorList() {
		
		return eDAO.errorList();
	}
	
	// 에러목록(error)
	@Override
	public List<ErrorDTO> error_errorList() {
		
		return eDAO.error_errorList();
	}
	
	// 에러목록(fixing)
	@Override
	public List<ErrorDTO> error_fixingList() {
		
		return eDAO.error_fixingList();
	}
	
	// 에러목록(complete)
	@Override
	public List<ErrorDTO> error_completeList() {
		
		return eDAO.error_completeList();
	}

	// 에러정보확인
	@Override
	public ErrorDTO read(int eno) {
		ErrorDTO dto = eDAO.read(eno);
		
		return dto;
	}

	// 에러정보변경 화면이동
	@Override
	public ErrorDTO updateui(int eno) {
		ErrorDTO dto = eDAO.read(eno);
		return dto;
	}

	// 에러정보변경
	@Override
	public ErrorDTO update(ErrorDTO dto) {
		eDAO.update(dto);
		dto = eDAO.read(dto.getEno());
		return dto;
	}

	
	
}
