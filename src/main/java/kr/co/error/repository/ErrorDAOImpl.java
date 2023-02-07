package kr.co.error.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.common.domain.PageTO;
import kr.co.error.domain.ErrorDTO;

@Repository
public class ErrorDAOImpl implements ErrorDAO {
	
	@Autowired
	private SqlSession session;
	private final String NS = "kr.co.error";
	
	
	// 에러저장
	@Override
	public void insert(String msg) {
		
		session.insert(NS+".insert", msg);
	}

	// 에러목록
	@Override
	public List<ErrorDTO> errorList() {
		
		return session.selectList(NS+".list");
	}
	
	// 에러목록(error)
	@Override
	public List<ErrorDTO> error_errorList() {
		
		return session.selectList(NS+".list_error");
	}
	
	// 에러목록(fixing)
	@Override
	public List<ErrorDTO> error_fixingList() {
		
		return session.selectList(NS+".list_fixing");
	}
	
	// 에러목록(complete)
	@Override
	public List<ErrorDTO> error_completeList() {
		
		return session.selectList(NS+".list_complete");
	}

	// 에러정보확인
	@Override
	public ErrorDTO read(int eno) {
		
		return session.selectOne(NS+".read", eno);
	}

	// 에러정보변경
	@Override
	public void update(ErrorDTO dto) {

		session.update(NS+".update", dto);
	}

	

}
