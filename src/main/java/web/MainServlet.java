package web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.AccountDao;
import dao.AdminDao;
import dao.CostDao;
import dao.RoleModuleDao;
import entity.Account;
import entity.Admin;
import entity.Cost;
import entity.RoleModule;
import util.ImageUtil;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminDao dao;
	
	public AdminDao getDao() {
		return dao;
	}
	public void setDao(AdminDao dao) {
		this.dao = dao;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String path = req.getServletPath();
		if ("/findCost.do".equals(path)) {
			// 查询资费
			findCost(req, res);
		} else if ("/toAddCost.do".equals(path)) {
			// 打开增加资费页面
			toAddCost(req, res);
		} else if ("/addCost.do".equals(path)) {
			// 保存资费
			addCost(req, res);
		} else if ("/toUpdateCost.do".equals(path)) {
			// 打开修改资费页面
			toUpdateCost(req, res);
		} else if ("/updateCost.do".equals(path)) {
			// 修改资费
			updateCost(req, res);
		} else if ("/deleteCost.do".equals(path)) {
			// 删除资费
			deleteCost(req, res);
		} else if ("/startCost.do".equals(path)) {
			// 开始资费
			startCost(req, res);
		} else if ("/toLogin.do".equals(path)) {
			// 打开登录页面
			toLogin(req, res);
		} else if ("/toIndex.do".equals(path)) {
			// 打开首页
			toIndex(req, res);
		} else if ("/login.do".equals(path)) {
			// 登录检查
			login(req, res);
		} else if ("/logoOut.do".equals(path)) {
			// 退出登录
			logoOut(req, res);
		} else if ("/createImg.do".equals(path)) {
			// 生成验证码
			createImg(req, res);
		} else if ("/toUpdatePwd.do".equals(path)) {
			// 打开修改密码页面
			toUpdatePwd(req, res);
		} else if ("/updatePwd.do".equals(path)) {
			// 修改密码
			updatePwd(req, res);
		} else if ("/toUpdateInfo.do".equals(path)) {
			// 打开个人信息页面
			toUpdateInfo(req, res);
		} else if ("/updateInfo.do".equals(path)) {
			// 修改个人信息
			updateInfo(req, res);
		} else if ("/findAdmin.do".equals(path)) {
			// 打开管理员页面
			AbstractApplicationContext aac = new ClassPathXmlApplicationContext("applicationContext.xml");
			MainServlet ms = aac.getBean("main",MainServlet.class);
			ms.findAdmin(req, res);
			aac.close();
		} else if("/toAddAdmin.do".equals(path)) {
			//打开管理员增加页面
			toAddAdmin(req,res);
		} else if("/addAdmin.do".equals(path)) {
			//增加管理员
			addAdmin(req,res);
		} else if("/toUpdateAdmin.do".equals(path)) {
			//打开修改管理员页面
			toUpdateAdmin(req,res);
		} else if("/updateAdmin.do".equals(path)) {
			//修改管理员
			updateAdmin(req,res);
		} else if("/deleteAdmin.do".equals(path)) {
			//删除管理员
			deleteAdmin(req,res);
		} else if("/findRole.do".equals(path)) {
			//查询角色页面
			findRole(req,res);
		} else if("/toAddRole.do".equals(path)) {
			//打开增加角色页面
			toAddRole(req,res);
		} else if("/addRole.do".equals(path)) {
			//增加角色
			addRole(req,res);
		} else if("/toUpdateRole.do".equals(path)) {
			//打开修改角色页面
			toUpdateRole(req,res);
		} else if("/updateRole.do".equals(path)) {
			//修改角色
			updateRole(req,res);
		} else if("/deleteRole.do".equals(path)) {
			//删除角色
			deleteRole(req,res);
		} else if ("/findAccount.do".equals(path)) {
			//账务账号页面
			findAccount(req,res);
		} else if("/toAddAccount.do".equals(path)) {
			//前往增加账务账号页面
			toAddAccount(req,res);
		} else if("/addAccount.do".equals(path)) {
			//增加账务账号
			addAccount(req,res);
		} else {
			throw new RuntimeException("没有这个页面");
		}

	}

	private void addAccount(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//获取请求数据
		String realName = req.getParameter("realName");
		String idcardNo = req.getParameter("idcardNo");
		String loginName = req.getParameter("loginName");
		String loginPasswd = req.getParameter("loginPasswd");
		String telephone = req.getParameter("telephone");
		String recommenderId = req.getParameter("recommenderId");
		String birthdate = req.getParameter("birthdate");
		String email = req.getParameter("email");
		String occupation = req.getParameter("occupation");
		String gender = req.getParameter("gender");
		String mailaddress = req.getParameter("mailaddress");
		String zipcode = req.getParameter("zipcode");
		String qq = req.getParameter("qq");
		//处理请求数据
		Account a = new Account();
		a.setRealName(realName);
		a.setIdcardNo(idcardNo);
		a.setLoginName(loginName);
		a.setLoginPasswd(loginPasswd);
		a.setTelephone(telephone);
		a.setRecommenderId(new Integer(recommenderId));
		a.setBirthdate(Date.valueOf(birthdate));
		a.setEmail(email);
		a.setOccupation(occupation);
		a.setGender(gender);
		a.setMailaddress(mailaddress);
		a.setZipcode(zipcode);
		a.setQq(qq);
		//保存数据
		AccountDao dao = new AccountDao();
		dao.addAccount(a);
		//重定向
		res.sendRedirect("findAccount.do");
	}
	
	private void toAddAccount(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/account/addAccount.jsp").forward(req, res);
	}
	
	private void findAccount(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//查询所有的账务
		AccountDao dao = new AccountDao();
		List<Account> list = dao.findAll();
		//将查到的数据绑定到request上
		req.setAttribute("accounts", list);
		//转发到账务页面上
		req.getRequestDispatcher("WEB-INF/account/findAccount.jsp").forward(req, res);
	}

	private void deleteRole(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//获取角色id
		String roleId = req.getParameter("roleId");
		//根据id查出要删除的角色
		RoleModuleDao dao = new RoleModuleDao();
		RoleModule rm = dao.findById(new Integer(roleId));
		//删除角色
		dao.deleteRole(rm);
		//重定向到角色页面
		res.sendRedirect("findRole.do");
	}

	private void updateRole(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//获取请求数据
		String name_2 = req.getParameter("name_2");
		String roleId = req.getParameter("roleId");
		String[] name_1 = req.getParameterValues("authorization");
		String authorization = arrayString_1(name_1);
		//处理数据
		RoleModule rm = new RoleModule();
		rm.setName_2(name_2);
		rm.setRoleId(new Integer(roleId));
		rm.setAuthorization(authorization);
		//保存数据
		RoleModuleDao dao = new RoleModuleDao();
		dao.updateRole(rm);
		//重定向到角色页面
		res.sendRedirect("findRole.do");
	}

	private String arrayString_1(String[] name_1) {
		if(name_1 == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<name_1.length;i++) {
			sb.append(name_1[i]+"、");
		}
		String sub = sb.toString().substring(0, sb.toString().length()-1);
		return sub;
	}

	private void toUpdateRole(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取角色ID
		String roleId = req.getParameter("roleId");
		//查到要修改的角色
		RoleModuleDao dao = new RoleModuleDao();
		RoleModule rm = dao.findById(new Integer(roleId));
		//绑定数据到request上
		req.setAttribute("role", rm);
		//转发到修改角色页面
		req.getRequestDispatcher("WEB-INF/role/updateRole.jsp").forward(req, res);
	}

	private void addRole(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//接收请求数据
		String name = req.getParameter("name");
		String[] name_1 = req.getParameterValues("authorization");
		String authorization = arrayString_1(name_1);
		//处理数据
		RoleModule rm = new RoleModule();
		rm.setName_2(name);
		rm.setAuthorization(authorization);
		//保存数据
		RoleModuleDao dao = new RoleModuleDao();
		dao.addRole(rm);
		//重定向到角色页面
		res.sendRedirect("findRole.do");
	}

	private void toAddRole(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//转发到增加角色页面
		req.getRequestDispatcher("WEB-INF/role/addRole.jsp").forward(req, res);
	}

	private void findRole(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//查询所有的角色
		RoleModuleDao dao = new RoleModuleDao();
		List<RoleModule> list = dao.findAll();
		//将数据绑定到request上
		req.setAttribute("roleModules", list);
		//转发到角色页面
		req.getRequestDispatcher("WEB-INF/role/findRole.jsp").forward(req, res);
	}

	private void deleteAdmin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//获取管理员id
		String adminId = req.getParameter("adminId");
		System.out.println(adminId);
		//查出要删除的管理员
		AdminDao dao = new AdminDao();
		Admin a = dao.findById(new Integer(adminId));
		System.out.println(a);
		//删除数据
		dao.deleteAdmin(a);
		//重定向到管理员页面
		res.sendRedirect("findAdmin.do");
	}

	private void updateAdmin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//接收请求数据
		String name = req.getParameter("name");
		String adminCode = req.getParameter("adminCode");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String[] roles = req.getParameterValues("role");
		String role = arrayString_2(roles);
		//处理业务
		Admin a = new Admin();
		a.setName(name);
		a.setAdminCode(adminCode);
		a.setTelephone(telephone);
		a.setEmail(email);
		a.setRole(role);
		//修改这些数据
		AdminDao dao = new AdminDao();
		dao.updateAdmin(a);
		//重定向到管理员页面
		res.sendRedirect("findAdmin.do");
	}

	private String arrayString_2(String[] roles) {
		if(roles == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<roles.length;i++) {
			sb.append(roles[i]+"、");
		}
		String sub = sb.toString().substring(0, sb.toString().length()-1);
		return sub;
	}

	private void toUpdateAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取管理员账号
		String adminCode = req.getParameter("adminCode");
		//查询出要修改的管理员
		AdminDao dao = new AdminDao();
		Admin a = dao.findByCode(adminCode);
		//绑定管理员数据
		req.setAttribute("admin", a);
		//转发数据到修改管理员页面
		req.getRequestDispatcher("WEB-INF/admin/updateAdmin.jsp").forward(req, res);
	}

	private void addAdmin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//接收请求数据
		String name = req.getParameter("name");
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String[] roles = req.getParameterValues("role");
		String role = arrayString_2(roles);
		//设置管理员数据
		Admin a = new Admin();
		a.setName(name);
		a.setAdminCode(adminCode);
		a.setPassword(password);
		a.setTelephone(telephone);
		a.setEmail(email);
		a.setRole(role);
		//保存管理员数据
		AdminDao dao = new AdminDao();
		dao.add(a);
		//重定向
		res.sendRedirect("findAdmin.do");
	}

	private void toAddAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 当前：/netctoss/toAddAdmin.do
		// 目标：/netctoss/WEB-INF/admin/find.jsp
		req.getRequestDispatcher("WEB-INF/admin/addAdmin.jsp").forward(req, res);
	}

	private void findAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 查询所有的管理员
		List<Admin> list = dao.findAll();
		// 将数据绑定到request上
		req.setAttribute("admins", list);
		// 当前：/netctoss/findAdmin.do
		// 目标：/netctoss/WEB-INF/admin/find.jsp
		// 转发到管理员查询的页面
		req.getRequestDispatcher("WEB-INF/admin/find.jsp").forward(req, res);
	}

	private void toUpdateInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取管理员账号
		HttpSession session = req.getSession();
		String adminCode = (String) session.getAttribute("adminCode");
		//查到管理员数据
		AdminDao dao = new AdminDao();
		Admin admin = dao.findByCode(adminCode);
		req.setAttribute("info", admin);
		req.getRequestDispatcher("WEB-INF/user/updateInfo.jsp").forward(req, res);
	}

	private void updateInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		// 接收请求数据
		String adminCode = req.getParameter("adminCode");
		String name = req.getParameter("name");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String enrolldate = req.getParameter("enrolldate");
		// 处理请求
		Admin a = new Admin();
		a.setAdminCode(adminCode);
		a.setName(name);
		a.setTelephone(telephone);
		a.setEmail(email);
		a.setEnrolldate(Timestamp.valueOf(enrolldate));
		// 修改数据
		AdminDao dao = new AdminDao();
		dao.updateInfo(a);
		res.sendRedirect("findAdmin.do");
	}

	private void toUpdatePwd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String adminCode = (String) session.getAttribute("adminCode");
		session.setAttribute("adminCode", adminCode);
		req.getRequestDispatcher("WEB-INF/user/updatePwd.jsp").forward(req, res);
	}

	private void updatePwd(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// 获取请求数据
		String adminCode = req.getParameter("adminCode");
		String oldPwd = req.getParameter("oldPwd");
		String newPwd = req.getParameter("newPwd");
		// 处理业务
		AdminDao dao = new AdminDao();
		Admin a = new Admin();
		a.setAdminCode(adminCode);
		a.setPassword(oldPwd);
		dao.updatePwd(a, newPwd);
		res.sendRedirect("toLogin.do");
	}

	private void createImg(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 生成验证码及图片
		Object[] objs = ImageUtil.createImage();
		// 将验证码存入session
		String imgcode = (String) objs[0];
		req.getSession().setAttribute("imgcode", imgcode);
		// 将图片输出给浏览器
		BufferedImage img = (BufferedImage) objs[1];
		// 告诉浏览器输出的内容类型
		res.setContentType("image/png");
		// 通过response获取输出流，其输出的目标就是当前请求的浏览器
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
	}

	private void logoOut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// 销毁session
		req.getSession().invalidate();
		// 重定向到登录页面
		res.sendRedirect(req.getContextPath() + "/toLogin.do");
	}

	private void login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		AbstractApplicationContext aac = new ClassPathXmlApplicationContext("applicationContext2.0.xml");
		AdminDao ad = aac.getBean("ad", AdminDao.class);
		aac.close();
		req.setCharacterEncoding("utf-8");
		// 接收表单数据
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		// 从session中获取生成的验证码
		HttpSession session = req.getSession();
		String imgcode = (String) session.getAttribute("imgcode");
		// 比较验证码
		if (code == null || !code.equalsIgnoreCase(imgcode)) {
			// 验证码错误
			req.setAttribute("error", "验证码错误");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
			return;
		}
		// 检查该数据
		Admin a = ad.findByCode(adminCode);
		if (a == null) {
			// 账号不存在
			req.setAttribute("error", "账号不存在");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		} else if (!a.getPassword().equals(password)) {
			// 密码错误
			req.setAttribute("error", "密码错误");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		} else {
			// 将账号存入cookie
			Cookie c = new Cookie("adminCode", adminCode);
			res.addCookie(c);
			// 将账号存入session
			session.setAttribute("adminCode", adminCode);
			// 登录成功
			res.sendRedirect("toIndex.do");
		}
	}

	private void toIndex(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}

	private void toLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 转发到：/WEB-INF/main/login.jsp
		req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
	}

	private void startCost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String id = req.getParameter("id");
		CostDao dao = new CostDao();
		dao.start(Integer.parseInt(id));
		res.sendRedirect("findCost.do");
	}

	private void deleteCost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();
		for (Cost c : list) {
			if (new Integer(c.getStatus()) == 1) {
				dao.delete(id);
			}
		}
		res.sendRedirect("findCost.do");
	}

	private void findCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 查询所有的资费
		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();
		// 将数据绑定到request上
		req.setAttribute("costs", list);
		// 当前：/netctoss/findCost.do
		// 目标：/netctoss/WEB-INF/cost/find.jsp
		// 转发到资费查询的页面
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}

	private void toAddCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 当前：/netctoss/toAddCost.do
		// 目标：/netctoss/WEB-INF/cost/add.jsp
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}

	private void addCost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		// 接受请求数据
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		// 处理业务
		Cost c = new Cost();
		c.setName(name);
		c.setDescr(descr);
		c.setCostType(costType);
		if (baseDuration != null && !baseDuration.equals("")) {
			c.setBaseDuration(new Integer(baseDuration));
		}
		if (baseCost != null && !baseCost.equals("")) {
			c.setBaseCost(new Double(baseCost));
		}
		if (unitCost != null && !unitCost.equals("")) {
			c.setUnitCost(new Double(unitCost));
		}
		// 保存这些数据
		CostDao dao = new CostDao();
		dao.save(c);
		// 重定向到资费查询页面
		// 当前：/netctoss/addCost.do
		// 目标：/netctoss/findCost.do
		res.sendRedirect("findCost.do");
	}

	private void toUpdateCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 获取资费ID
		String id = req.getParameter("id");
		// 查询出要修改的资费
		CostDao dao = new CostDao();
		Cost c = dao.findById(new Integer(id));
		// 绑定资费数据
		req.setAttribute("cost", c);
		// 转发数据到修改页面
		// 当前：/netctoss/toUpdateCost.do
		// 目标：/WEB-INF/cost/update.jsp
		req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, res);
	}

	private void updateCost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		// 接受请求数据
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		String costId = req.getParameter("costId");
		// 处理业务
		Cost c = new Cost();
		c.setName(name);
		c.setDescr(descr);
		c.setCostType(costType);
		c.setCostId(Integer.parseInt(costId));
		if (baseDuration != null && !baseDuration.equals("")) {
			c.setBaseDuration(new Integer(baseDuration));
		}
		if (baseCost != null && !baseCost.equals("")) {
			c.setBaseCost(new Double(baseCost));
		}
		if (unitCost != null && !unitCost.equals("")) {
			c.setUnitCost(new Double(unitCost));
		}
		// 修改这些数据
		CostDao dao = new CostDao();
		dao.update(c);
		// 重定向到资费查询页面
		// 当前：/netctoss/addCost.do
		// 目标：/netctoss/findCost.do
		res.sendRedirect("findCost.do");
	}
	
}
