package kr.co.board.repository;

import java.util.List;
import java.util.Map;

import kr.co.board.domain.BoardDTO;
import kr.co.common.domain.PageTO;

public interface BoardDAO {

	void insert(BoardDTO dto);

	List<BoardDTO> list();

	List<BoardDTO> list(Map<String, String> map, PageTO<BoardDTO> pt);

	int getAmount(Map<String, String> map);

	BoardDTO read(int bno);

	void increaseReadcnt(int bno);

	int update(Map<String, Object> map);

	Integer delete(Map<String, Object> map);

	int update(BoardDTO dto);

}
