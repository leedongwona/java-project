package kr.co.qna.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.qna.domain.QnaDTO;
import kr.co.common.domain.PageTO;

@Repository
public class QnaDAOImpl implements QnaDAO {

	@Autowired
	private SqlSession qsession;

	private final String QNS = "kr.co.qna";
	// QnaMapper 의 namespace와 동일하게 작성

	@Override
	public void insert(QnaDTO dto) {

		qsession.insert(QNS + ".insert", dto);

	}

	@Override
	public List<QnaDTO> list() {

		return qsession.selectList(QNS + ".list");
	}

	@Override
	public List<QnaDTO> list(Map<String, String> map, PageTO<QnaDTO> pt) {

		RowBounds rb = new RowBounds(pt.getStartNum(), pt.getPerpage());

		return qsession.selectList(QNS + ".list2", map, rb);
	}

	@Override
	public int getAmount(Map<String, String> map) {
		Integer amount = qsession.selectOne(QNS + ".getAmount", map);
		// 게시글이 하나도 없을 경우 null 이므로 int 로 받을 수 없음.

		if (amount == null) {
			amount = 0;
		}
		return amount;
	}

	@Override
	public QnaDTO read(int bno) {
		// 두번쨰칸은 where 절에 들어갈것을 넣으면 됨
		return qsession.selectOne(QNS + ".read", bno);
	}

	@Override
	public void increaseReadcnt(int bno) {
		qsession.update(QNS + ".increaseReadcnt", bno);

	}

	@Override
	public int update(QnaDTO dto) {

		return qsession.update(QNS + ".update", dto);
	}

	@Override
	public int update(Map<String, Object> map) {

		return qsession.update(QNS + ".update", map);
	}

	@Override
	public Integer delete(Map<String, Object> map) {

		return qsession.delete(QNS + ".delete", map);
	}

}
