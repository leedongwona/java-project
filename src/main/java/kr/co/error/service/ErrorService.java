package kr.co.error.service;

import java.util.List;
import java.util.Map;

import kr.co.common.domain.PageTO;
import kr.co.error.domain.ErrorDTO;

public interface ErrorService {

	void insert(String msg);

	List<ErrorDTO> errorList();
	
	List<ErrorDTO> error_errorList();

	List<ErrorDTO> error_fixingList();

	List<ErrorDTO> error_completeList();

	ErrorDTO read(int eno);

	ErrorDTO updateui(int eno);

	ErrorDTO update(ErrorDTO dto);

}
