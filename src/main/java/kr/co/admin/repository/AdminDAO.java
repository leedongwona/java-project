package kr.co.admin.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.admin.domain.DMemberDTO;
import kr.co.member.domain.MemberDTO;

@Repository
public interface AdminDAO {

	List<MemberDTO> adminList();

	int delete(String id);

	List<DMemberDTO> delete_adminList();
	
}
