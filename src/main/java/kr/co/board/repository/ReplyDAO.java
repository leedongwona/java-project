package kr.co.board.repository;

import java.util.List;
import java.util.Map;

import kr.co.board.domain.BoardDTO;
import kr.co.board.domain.ReplyDTO;
import kr.co.common.domain.PageTO;

public interface ReplyDAO {

	void insert(Map<String, Object> map);

	List<ReplyDTO> list(Integer bno);

	void update(Map<String, Object> map);

	Integer delete(Map<String, Object> map);

	int getAmount(Map<String, String> map);

	List<ReplyDTO> list(Map<String, String> map, PageTO<ReplyDTO> pt);

}
