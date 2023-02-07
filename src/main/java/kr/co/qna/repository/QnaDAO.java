package kr.co.qna.repository;

import java.util.List;
import java.util.Map;


import kr.co.common.domain.PageTO;
import kr.co.qna.domain.QnaDTO;

public interface QnaDAO {

	void insert(QnaDTO dto);

	List<QnaDTO> list();

	List<QnaDTO> list(Map<String, String> map, PageTO<QnaDTO> pt);

	int getAmount(Map<String, String> map);

	QnaDTO read(int bno);

	void increaseReadcnt(int bno);

	int update(Map<String, Object> map);

	Integer delete(Map<String, Object> map);

	int update(QnaDTO dto);

}
