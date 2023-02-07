package kr.co.qna.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaAttachDAOImpl implements QnaAttachDAO {

	@Autowired
	private SqlSession qsession;

	private final String QNS = "kr.co.attach";

	@Override
	public void insert(Map<String, Object> map) {

		qsession.insert(QNS + ".insert", map);
	}

	@Override
	public List<String> read(int bno) {

		return qsession.selectList(QNS + ".read", bno);
	}

	@Override
	public int deleteByFilename(String uploadedFilename) {

		return qsession.delete(QNS + ".deleteByFilename", uploadedFilename);
	}

}
