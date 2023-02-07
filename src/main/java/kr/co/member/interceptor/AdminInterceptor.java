package kr.co.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.member.domain.MemberDTO;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	// 관리자 전용 페이지 이동 시 작동
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		MemberDTO login = (MemberDTO)session.getAttribute("login");
		
		// 로그인 되어있지 않거나 grade가 A 일 경우 로그인 페이지로 이동
		if (login != null) {

			String grade = login.getGrade();
			
			if(grade.equals("A")) {
				response.sendRedirect("/member/login");
				
				return false;
			}
		}else {
			response.sendRedirect("/member/login");
			
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

}
