package kr.co.board.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.board.domain.BoardDTO;
import kr.co.common.domain.PageTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession session;

	private final String NS = "kr.co.board";
	// boardMapper 의 namespace와 동일하게 작성

	@Override
	public void insert(BoardDTO dto) {

		session.insert(NS + ".insert", dto);

	}

	@Override
	public List<BoardDTO> list() {

		return session.selectList(NS + ".list");
	}

	@Override
	public List<BoardDTO> list(Map<String, String> map, PageTO<BoardDTO> pt) {

		RowBounds rb = new RowBounds(pt.getStartNum(), pt.getPerpage());

		return session.selectList(NS + ".list2", map, rb);
	}

	@Override
	public int getAmount(Map<String, String> map) {
		Integer amount = session.selectOne(NS + ".getAmount", map);
		// 게시글이 하나도 없을 경우 null 이므로 int 로 받을 수 없음.

		if (amount == null) {
			amount = 0;
		}
		return amount;
	}

	@Override
	public BoardDTO read(int bno) {
		// 두번쨰칸은 where 절에 들어갈것을 넣으면 됨
		return session.selectOne(NS + ".read", bno);
	}

	@Override
	public void increaseReadcnt(int bno) {
		session.update(NS + ".increaseReadcnt", bno);

	}

	@Override
	public int update(BoardDTO dto) {

		return session.update(NS + ".update", dto);
	}

	@Override
	public int update(Map<String, Object> map) {

		return session.update(NS + ".update", map);
	}

	@Override
	public Integer delete(Map<String, Object> map) {

		return session.delete(NS + ".delete", map);
	}

}
