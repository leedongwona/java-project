package kr.co.qna.service;

import java.util.List;
import java.util.Map;

import kr.co.qna.domain.QnaReplyDTO;
import kr.co.common.domain.PageTO;

public interface QnaReplyService {

	void insert(Map<String, Object> map);

	List<QnaReplyDTO> list(Integer bno);

	void update(Map<String, Object> map);

	Integer delete(Map<String, Object> map);

	PageTO<QnaReplyDTO> list(Map<String, String> map);

}
