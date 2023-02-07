package kr.co.member.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	// 로그인 시 session 에서 로그인 관련 정보 획득
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession(true);

		Map<String, Object> map = modelAndView.getModel();

		Object login = map.get("login");
		if (login != null) {
			session.setAttribute("login", login);

			String dest = (String) session.getAttribute("dest");

			if (dest == null) {
				response.sendRedirect("/");
			} else {
				response.sendRedirect(dest);
			}

		} else {
			response.sendRedirect("/member/login");
		}

	}

}
