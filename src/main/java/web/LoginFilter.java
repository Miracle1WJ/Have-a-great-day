package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	
	private String[] paths;
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//Tomcat���ô˷���ʱ�������Http��ͷ�Ķ����ǵ�ǰ�����������͡�
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//�ų�������Ҫ���˵�����
		//����web.xmlԤ�����ݴ���д��������
		//String[] paths = new String[]{
		//		"/toLogin.do",
		//		"/login.do",
		//		"/createImg.do"
		//};
		for(String path : paths) {
			if(path.equals(request.getServletPath())) {
				//����ǰ·������ĳ�ų�·�����򱾴�������Ҫ���ˣ��������ִ��
				chain.doFilter(request, response);
				return;
			}
		}
		//��session�л�ȡ�˺�
		HttpSession session = request.getSession();
		String adminCode = (String)session.getAttribute("adminCode");
		//�жϸ��˺��Ƿ�Ϊ��
		if(adminCode == null) {
			//�˺�Ϊ�գ�δ��¼���ض��򵽵�¼ҳ��
			response.sendRedirect(request.getContextPath()+"/toLogin.do");
		} else {
			//�ǿգ��ѵ�¼���������ִ��
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig cfg) throws ServletException {
		//��ȡweb.xml����Ԥ�õ�·���ַ�������
		String ipath = cfg.getInitParameter("ignorePath");
		//���ַ������ݶ��ŷֿ�������ȫ���������paths��17�У�
		paths = ipath.split(",");
	}

}
