package kr.co.board.service;

import java.util.List;
import java.util.Map;

import kr.co.board.domain.ReplyDTO;
import kr.co.common.domain.PageTO;

public interface ReplyService {

	void insert(Map<String, Object> map);

	List<ReplyDTO> list(Integer bno);

	void update(Map<String, Object> map);

	Integer delete(Map<String, Object> map);

	PageTO<ReplyDTO> list(Map<String, String> map);

}
