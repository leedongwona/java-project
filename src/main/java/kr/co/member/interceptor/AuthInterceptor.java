package kr.co.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	// 회원 전용 페이지 이동 시 작동
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		Object login = session.getAttribute("login");
		
		// 로그인 되어있지 않으면 로그인 페이지로 이동
		if (login == null) {

			String uri = request.getRequestURI();
			String querry = request.getQueryString();

			String dest = querry == null ? uri : uri + "?" + querry;

			session.setAttribute("dest", dest);

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
