package cn.com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.mapper.UserMapper;
import cn.com.pojo.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private UserMapper userMapper;
	@Override
	public void init() throws ServletException {
		WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		userMapper = ac.getBean("userMapper", UserMapper.class);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String valiCode = req.getParameter("valiCode");
		String codeSession = req.getSession().getAttribute("valiCode").toString();
		if(valiCode.equals(codeSession)) {
			User user = new User();
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			user.setUsername(username);
			user.setPassword(password);
			User selOne = userMapper.selOne(user);
			if(selOne != null) {
				resp.sendRedirect("main.jsp");
				return ;
//	由于response多次提交或者是由于有页面显示后仍然含请求转向产生的，
//	就是说程序在return之前就已经执行了跳转或者执行过response，之后遇到return的话,
//  程序想再次执行跳转，也就是重定向，这时功能也许会实现了，但是控制台会报错，
//	所以控制好跳转是很重要的，解决办法是加return null,或者return;
			}else {}
			req.setAttribute("error", "用户名密码不正确");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			return ;
		}else {
			req.setAttribute("error", "验证码不正确");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
