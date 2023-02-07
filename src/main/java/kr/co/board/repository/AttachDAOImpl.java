package kr.co.board.repository;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttachDAOImpl implements AttachDAO {

	@Autowired
	private SqlSession session;

	private final String NS = "kr.co.attach";

	@Override
	public void insert(Map<String, Object> map) {

		session.insert(NS + ".insert", map);
	}

	@Override
	public List<String> read(int bno) {

		return session.selectList(NS + ".read", bno);
	}

	@Override
	public int deleteByFilename(String uploadedFilename) {

		return session.delete(NS + ".deleteByFilename", uploadedFilename);
	}

}
