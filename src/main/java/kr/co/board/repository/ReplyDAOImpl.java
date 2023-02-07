package kr.co.board.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.board.domain.BoardDTO;
import kr.co.board.domain.ReplyDTO;
import kr.co.common.domain.PageTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession session;

	private final String NS = "kr.co.reply";

	@Override
	public void insert(Map<String, Object> map) {
		session.insert(NS + ".insert", map);

	}

	@Override
	public List<ReplyDTO> list(Integer bno) {

		return session.selectList(NS + ".list", bno);
	}

	@Override
	public void update(Map<String, Object> map) {
		session.update(NS + ".update", map);

	}

	@Override
	public Integer delete(Map<String, Object> map) {
		return session.delete(NS + ".delete", map);
	}

	@Override
	public int getAmount(Map<String, String> map) {
		Integer amount = session.selectOne(NS + ".getAmount", map);

		if (amount == null) {
			amount = 0;
		}
		return amount;
	}

	@Override
	public List<ReplyDTO> list(Map<String, String> map, PageTO<ReplyDTO> pt) {
		RowBounds rb = new RowBounds(pt.getStartNum(), pt.getPerpage());

		return session.selectList(NS + ".list", map, rb);
	}

}
