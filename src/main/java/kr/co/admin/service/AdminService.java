package kr.co.admin.service;

import java.util.List;

import kr.co.admin.domain.DMemberDTO;
import kr.co.member.domain.MemberDTO;

public interface AdminService {

	List<MemberDTO> adminList();

	int delete(List<String> checkboxArr);

	List<DMemberDTO> delete_adminList();
	
}
