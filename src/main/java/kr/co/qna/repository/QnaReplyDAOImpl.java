package kr.co.qna.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.qna.domain.QnaDTO;
import kr.co.qna.domain.QnaReplyDTO;
import kr.co.common.domain.PageTO;

@Repository
public class QnaReplyDAOImpl implements QnaReplyDAO {

	@Autowired
	private SqlSession qsession;

	private final String QNS = "kr.co.Qnareply";

	@Override
	public void insert(Map<String, Object> map) {
		qsession.insert(QNS + ".insert", map);

	}

	@Override
	public List<QnaReplyDTO> list(Integer bno) {

		return qsession.selectList(QNS + ".list", bno);
	}

	@Override
	public void update(Map<String, Object> map) {
		qsession.update(QNS + ".update", map);

	}

	@Override
	public Integer delete(Map<String, Object> map) {
		return qsession.delete(QNS + ".delete", map);
	}

	@Override
	public int getAmount(Map<String, String> map) {
		Integer amount = qsession.selectOne(QNS + ".getAmount", map);

		if (amount == null) {
			amount = 0;
		}
		return amount;
	}

	@Override
	public List<QnaReplyDTO> list(Map<String, String> map, PageTO<QnaReplyDTO> pt) {
		RowBounds rb = new RowBounds(pt.getStartNum(), pt.getPerpage());

		return qsession.selectList(QNS + ".list", map, rb);
	}

}
