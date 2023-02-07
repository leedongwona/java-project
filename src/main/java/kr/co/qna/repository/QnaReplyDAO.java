package kr.co.qna.repository;

import java.util.List;
import java.util.Map;

import kr.co.qna.domain.QnaDTO;
import kr.co.qna.domain.QnaReplyDTO;
import kr.co.common.domain.PageTO;

public interface QnaReplyDAO {

	void insert(Map<String, Object> map);

	List<QnaReplyDTO> list(Integer bno);

	void update(Map<String, Object> map);

	Integer delete(Map<String, Object> map);

	int getAmount(Map<String, String> map);

	List<QnaReplyDTO> list(Map<String, String> map, PageTO<QnaReplyDTO> pt);

}
