package kr.co.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.admin.domain.DMemberDTO;
import kr.co.admin.repository.AdminDAO;
import kr.co.member.domain.MemberDTO;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDAO aDao;
	
	//회원목록
	@Override
	public List<MemberDTO> adminList() {
		
		return aDao.adminList();
	}
	
	//회원삭제
	@Override
	@Transactional
	public int delete(List<String> checkboxArr) {
		int result = 1;
		
		for(int i=0; i<checkboxArr.size();i++) {
			String id = checkboxArr.get(i);
			
			result = aDao.delete(id);
			
			if(result == 0) {
				break;
			}
		}
		return result;
	}
	
	// 탈퇴회원목록
	@Override
	public List<DMemberDTO> delete_adminList() {
		
		return aDao.delete_adminList();
	}
	
	
}	
