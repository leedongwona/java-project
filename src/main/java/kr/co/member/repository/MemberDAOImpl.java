package kr.co.member.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.member.domain.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession session;
	private final String NS = "kr.co.member";

	// 회원관리 관련코드
	// 회원가입
	@Override
	public void insert(MemberDTO dto) {

		session.insert(NS + ".insert", dto);
	}

	// 회원정보확인
	@Override
	public MemberDTO read(String id) {

		return session.selectOne(NS + ".read", id);
	}

	// 회원정보변경
	@Override
	public void update(MemberDTO dto) {

		session.update(NS + ".update", dto);
	}

	// 비밀번호변경
	@Override
	public int changePw(Map<String, Object> map) {

		return session.update(NS + ".changePw", map);
	}

	// 회원탈퇴
	@Override
	public Integer delete(Map<String, Object> map) {

		return session.delete(NS + ".delete", map);
	}

	// 로그인 관련코드
	// 로그인
	@Override
	public MemberDTO login(MemberDTO login) {

		return session.selectOne(NS + ".login", login);
	}

}
