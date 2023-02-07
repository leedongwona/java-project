package kr.co.admin.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.admin.domain.DMemberDTO;
import kr.co.member.domain.MemberDTO;

@Repository
public class AdminDAOImpl implements AdminDAO{
	
	@Autowired
	private SqlSession session;
	private final String NS = "kr.co.admin";
	
		// 회원목록
		@Override
		public List<MemberDTO> adminList() {
			
			return session.selectList(NS+".list");
		}

		// 회원삭제
		@Override
		public int delete(String id) {
			
			return session.delete(NS + ".delete", id);
		}

		// 탈퇴회원목록
		@Override
		public List<DMemberDTO> delete_adminList() {
			
			return session.selectList(NS+".delete_list");
		}

	}