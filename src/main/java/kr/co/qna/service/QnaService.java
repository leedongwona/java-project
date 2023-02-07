package kr.co.qna.service;

import java.util.List;
import java.util.Map;


import kr.co.common.domain.PageTO;
import kr.co.qna.domain.QnaDTO;

public interface QnaService {

	void insert(QnaDTO dto) throws Exception;

	List<QnaDTO> list();

	PageTO<QnaDTO> list(Map<String, String> map);

	QnaDTO read(int bno);

	int update(Map<String, Object> map);

	Integer delete(Map<String, Object> map);

	List<String> getFilenames(int bno);

	QnaDTO updateUI(Object bno);

	int update(QnaDTO dto, List<String> uploadedFileDeleteList);
}
