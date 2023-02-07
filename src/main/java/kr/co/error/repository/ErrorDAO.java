package kr.co.error.repository;

import java.util.List;
import java.util.Map;

import kr.co.common.domain.PageTO;
import kr.co.error.domain.ErrorDTO;

public interface ErrorDAO {

	void insert(String msg);

	List<ErrorDTO> errorList();

	List<ErrorDTO> error_errorList();

	List<ErrorDTO> error_fixingList();

	List<ErrorDTO> error_completeList();

	ErrorDTO read(int eno);

	void update(ErrorDTO dto);

}
