package kr.co.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.member.domain.MemberDTO;
import kr.co.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO mDao;

	// 회원관리 관련코드
	// 회원가입
	@Override
	public void insert(MemberDTO dto) {

		mDao.insert(dto);
	}

	// 아이디 중복확인
	@Override
	public MemberDTO checkId(Map<String, Object> map) {
		String id = (String) map.get("id");
		MemberDTO dto = mDao.read(id);
		return dto;
	}

	// 회원정보확인
	@Override
	public MemberDTO read(String id) {
		MemberDTO dto = mDao.read(id);

		return dto;
	}

	// 회원정보변경 화면이동
	@Override
	public MemberDTO updateui(String id) {
		MemberDTO dto = mDao.read(id);
		return dto;
	}

	// 회원정보변경
	@Override
	public MemberDTO update(MemberDTO dto) {
		mDao.update(dto);
		dto = mDao.read(dto.getId());
		return dto;
	}

	// 비밀번호변경
	@Override
	public int changePw(Map<String, Object> map) {
		int result = mDao.changePw(map);

		return result;
	}

	// 회원탈퇴
	@Override
	public Integer delete(Map<String, Object> map) {
		Integer result = 0;
		result = mDao.delete(map);

		return result;
	}

	// 로그인 관련코드
	// 로그인
	@Override
	public MemberDTO login(MemberDTO login) {
		login = mDao.login(login);

		return login;
	}

}
