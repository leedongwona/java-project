package kr.co.member.service;

import java.util.List;
import java.util.Map;

import kr.co.member.domain.MemberDTO;

public interface MemberService {

	void insert(MemberDTO dto);
	
	MemberDTO checkId(Map<String, Object> map);

	MemberDTO read(String id);

	MemberDTO updateui(String id);

	MemberDTO update(MemberDTO dto);

	int changePw(Map<String, Object> map);

	Integer delete(Map<String, Object> map);

	MemberDTO login(MemberDTO login);

}
