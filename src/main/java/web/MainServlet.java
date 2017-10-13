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
			// ��ѯ�ʷ�
			findCost(req, res);
		} else if ("/toAddCost.do".equals(path)) {
			// �������ʷ�ҳ��
			toAddCost(req, res);
		} else if ("/addCost.do".equals(path)) {
			// �����ʷ�
			addCost(req, res);
		} else if ("/toUpdateCost.do".equals(path)) {
			// ���޸��ʷ�ҳ��
			toUpdateCost(req, res);
		} else if ("/updateCost.do".equals(path)) {
			// �޸��ʷ�
			updateCost(req, res);
		} else if ("/deleteCost.do".equals(path)) {
			// ɾ���ʷ�
			deleteCost(req, res);
		} else if ("/startCost.do".equals(path)) {
			// ��ʼ�ʷ�
			startCost(req, res);
		} else if ("/toLogin.do".equals(path)) {
			// �򿪵�¼ҳ��
			toLogin(req, res);
		} else if ("/toIndex.do".equals(path)) {
			// ����ҳ
			toIndex(req, res);
		} else if ("/login.do".equals(path)) {
			// ��¼���
			login(req, res);
		} else if ("/logoOut.do".equals(path)) {
			// �˳���¼
			logoOut(req, res);
		} else if ("/createImg.do".equals(path)) {
			// ������֤��
			createImg(req, res);
		} else if ("/toUpdatePwd.do".equals(path)) {
			// ���޸�����ҳ��
			toUpdatePwd(req, res);
		} else if ("/updatePwd.do".equals(path)) {
			// �޸�����
			updatePwd(req, res);
		} else if ("/toUpdateInfo.do".equals(path)) {
			// �򿪸�����Ϣҳ��
			toUpdateInfo(req, res);
		} else if ("/updateInfo.do".equals(path)) {
			// �޸ĸ�����Ϣ
			updateInfo(req, res);
		} else if ("/findAdmin.do".equals(path)) {
			// �򿪹���Աҳ��
			AbstractApplicationContext aac = new ClassPathXmlApplicationContext("applicationContext.xml");
			MainServlet ms = aac.getBean("main",MainServlet.class);
			ms.findAdmin(req, res);
			aac.close();
		} else if("/toAddAdmin.do".equals(path)) {
			//�򿪹���Ա����ҳ��
			toAddAdmin(req,res);
		} else if("/addAdmin.do".equals(path)) {
			//���ӹ���Ա
			addAdmin(req,res);
		} else if("/toUpdateAdmin.do".equals(path)) {
			//���޸Ĺ���Աҳ��
			toUpdateAdmin(req,res);
		} else if("/updateAdmin.do".equals(path)) {
			//�޸Ĺ���Ա
			updateAdmin(req,res);
		} else if("/deleteAdmin.do".equals(path)) {
			//ɾ������Ա
			deleteAdmin(req,res);
		} else if("/findRole.do".equals(path)) {
			//��ѯ��ɫҳ��
			findRole(req,res);
		} else if("/toAddRole.do".equals(path)) {
			//�����ӽ�ɫҳ��
			toAddRole(req,res);
		} else if("/addRole.do".equals(path)) {
			//���ӽ�ɫ
			addRole(req,res);
		} else if("/toUpdateRole.do".equals(path)) {
			//���޸Ľ�ɫҳ��
			toUpdateRole(req,res);
		} else if("/updateRole.do".equals(path)) {
			//�޸Ľ�ɫ
			updateRole(req,res);
		} else if("/deleteRole.do".equals(path)) {
			//ɾ����ɫ
			deleteRole(req,res);
		} else if ("/findAccount.do".equals(path)) {
			//�����˺�ҳ��
			findAccount(req,res);
		} else if("/toAddAccount.do".equals(path)) {
			//ǰ�����������˺�ҳ��
			toAddAccount(req,res);
		} else if("/addAccount.do".equals(path)) {
			//���������˺�
			addAccount(req,res);
		} else {
			throw new RuntimeException("û�����ҳ��");
		}

	}

	private void addAccount(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//��ȡ��������
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
		//������������
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
		//��������
		AccountDao dao = new AccountDao();
		dao.addAccount(a);
		//�ض���
		res.sendRedirect("findAccount.do");
	}
	
	private void toAddAccount(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/account/addAccount.jsp").forward(req, res);
	}
	
	private void findAccount(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ѯ���е�����
		AccountDao dao = new AccountDao();
		List<Account> list = dao.findAll();
		//���鵽�����ݰ󶨵�request��
		req.setAttribute("accounts", list);
		//ת��������ҳ����
		req.getRequestDispatcher("WEB-INF/account/findAccount.jsp").forward(req, res);
	}

	private void deleteRole(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//��ȡ��ɫid
		String roleId = req.getParameter("roleId");
		//����id���Ҫɾ���Ľ�ɫ
		RoleModuleDao dao = new RoleModuleDao();
		RoleModule rm = dao.findById(new Integer(roleId));
		//ɾ����ɫ
		dao.deleteRole(rm);
		//�ض��򵽽�ɫҳ��
		res.sendRedirect("findRole.do");
	}

	private void updateRole(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//��ȡ��������
		String name_2 = req.getParameter("name_2");
		String roleId = req.getParameter("roleId");
		String[] name_1 = req.getParameterValues("authorization");
		String authorization = arrayString_1(name_1);
		//��������
		RoleModule rm = new RoleModule();
		rm.setName_2(name_2);
		rm.setRoleId(new Integer(roleId));
		rm.setAuthorization(authorization);
		//��������
		RoleModuleDao dao = new RoleModuleDao();
		dao.updateRole(rm);
		//�ض��򵽽�ɫҳ��
		res.sendRedirect("findRole.do");
	}

	private String arrayString_1(String[] name_1) {
		if(name_1 == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<name_1.length;i++) {
			sb.append(name_1[i]+"��");
		}
		String sub = sb.toString().substring(0, sb.toString().length()-1);
		return sub;
	}

	private void toUpdateRole(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ȡ��ɫID
		String roleId = req.getParameter("roleId");
		//�鵽Ҫ�޸ĵĽ�ɫ
		RoleModuleDao dao = new RoleModuleDao();
		RoleModule rm = dao.findById(new Integer(roleId));
		//�����ݵ�request��
		req.setAttribute("role", rm);
		//ת�����޸Ľ�ɫҳ��
		req.getRequestDispatcher("WEB-INF/role/updateRole.jsp").forward(req, res);
	}

	private void addRole(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//������������
		String name = req.getParameter("name");
		String[] name_1 = req.getParameterValues("authorization");
		String authorization = arrayString_1(name_1);
		//��������
		RoleModule rm = new RoleModule();
		rm.setName_2(name);
		rm.setAuthorization(authorization);
		//��������
		RoleModuleDao dao = new RoleModuleDao();
		dao.addRole(rm);
		//�ض��򵽽�ɫҳ��
		res.sendRedirect("findRole.do");
	}

	private void toAddRole(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//ת�������ӽ�ɫҳ��
		req.getRequestDispatcher("WEB-INF/role/addRole.jsp").forward(req, res);
	}

	private void findRole(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ѯ���еĽ�ɫ
		RoleModuleDao dao = new RoleModuleDao();
		List<RoleModule> list = dao.findAll();
		//�����ݰ󶨵�request��
		req.setAttribute("roleModules", list);
		//ת������ɫҳ��
		req.getRequestDispatcher("WEB-INF/role/findRole.jsp").forward(req, res);
	}

	private void deleteAdmin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//��ȡ����Աid
		String adminId = req.getParameter("adminId");
		System.out.println(adminId);
		//���Ҫɾ���Ĺ���Ա
		AdminDao dao = new AdminDao();
		Admin a = dao.findById(new Integer(adminId));
		System.out.println(a);
		//ɾ������
		dao.deleteAdmin(a);
		//�ض��򵽹���Աҳ��
		res.sendRedirect("findAdmin.do");
	}

	private void updateAdmin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//������������
		String name = req.getParameter("name");
		String adminCode = req.getParameter("adminCode");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String[] roles = req.getParameterValues("role");
		String role = arrayString_2(roles);
		//����ҵ��
		Admin a = new Admin();
		a.setName(name);
		a.setAdminCode(adminCode);
		a.setTelephone(telephone);
		a.setEmail(email);
		a.setRole(role);
		//�޸���Щ����
		AdminDao dao = new AdminDao();
		dao.updateAdmin(a);
		//�ض��򵽹���Աҳ��
		res.sendRedirect("findAdmin.do");
	}

	private String arrayString_2(String[] roles) {
		if(roles == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<roles.length;i++) {
			sb.append(roles[i]+"��");
		}
		String sub = sb.toString().substring(0, sb.toString().length()-1);
		return sub;
	}

	private void toUpdateAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ȡ����Ա�˺�
		String adminCode = req.getParameter("adminCode");
		//��ѯ��Ҫ�޸ĵĹ���Ա
		AdminDao dao = new AdminDao();
		Admin a = dao.findByCode(adminCode);
		//�󶨹���Ա����
		req.setAttribute("admin", a);
		//ת�����ݵ��޸Ĺ���Աҳ��
		req.getRequestDispatcher("WEB-INF/admin/updateAdmin.jsp").forward(req, res);
	}

	private void addAdmin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		//������������
		String name = req.getParameter("name");
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String[] roles = req.getParameterValues("role");
		String role = arrayString_2(roles);
		//���ù���Ա����
		Admin a = new Admin();
		a.setName(name);
		a.setAdminCode(adminCode);
		a.setPassword(password);
		a.setTelephone(telephone);
		a.setEmail(email);
		a.setRole(role);
		//�������Ա����
		AdminDao dao = new AdminDao();
		dao.add(a);
		//�ض���
		res.sendRedirect("findAdmin.do");
	}

	private void toAddAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ��ǰ��/netctoss/toAddAdmin.do
		// Ŀ�꣺/netctoss/WEB-INF/admin/find.jsp
		req.getRequestDispatcher("WEB-INF/admin/addAdmin.jsp").forward(req, res);
	}

	private void findAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ��ѯ���еĹ���Ա
		List<Admin> list = dao.findAll();
		// �����ݰ󶨵�request��
		req.setAttribute("admins", list);
		// ��ǰ��/netctoss/findAdmin.do
		// Ŀ�꣺/netctoss/WEB-INF/admin/find.jsp
		// ת��������Ա��ѯ��ҳ��
		req.getRequestDispatcher("WEB-INF/admin/find.jsp").forward(req, res);
	}

	private void toUpdateInfo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ȡ����Ա�˺�
		HttpSession session = req.getSession();
		String adminCode = (String) session.getAttribute("adminCode");
		//�鵽����Ա����
		AdminDao dao = new AdminDao();
		Admin admin = dao.findByCode(adminCode);
		req.setAttribute("info", admin);
		req.getRequestDispatcher("WEB-INF/user/updateInfo.jsp").forward(req, res);
	}

	private void updateInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		// ������������
		String adminCode = req.getParameter("adminCode");
		String name = req.getParameter("name");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String enrolldate = req.getParameter("enrolldate");
		// ��������
		Admin a = new Admin();
		a.setAdminCode(adminCode);
		a.setName(name);
		a.setTelephone(telephone);
		a.setEmail(email);
		a.setEnrolldate(Timestamp.valueOf(enrolldate));
		// �޸�����
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
		// ��ȡ��������
		String adminCode = req.getParameter("adminCode");
		String oldPwd = req.getParameter("oldPwd");
		String newPwd = req.getParameter("newPwd");
		// ����ҵ��
		AdminDao dao = new AdminDao();
		Admin a = new Admin();
		a.setAdminCode(adminCode);
		a.setPassword(oldPwd);
		dao.updatePwd(a, newPwd);
		res.sendRedirect("toLogin.do");
	}

	private void createImg(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// ������֤�뼰ͼƬ
		Object[] objs = ImageUtil.createImage();
		// ����֤�����session
		String imgcode = (String) objs[0];
		req.getSession().setAttribute("imgcode", imgcode);
		// ��ͼƬ����������
		BufferedImage img = (BufferedImage) objs[1];
		// ����������������������
		res.setContentType("image/png");
		// ͨ��response��ȡ��������������Ŀ����ǵ�ǰ����������
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
	}

	private void logoOut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// ����session
		req.getSession().invalidate();
		// �ض��򵽵�¼ҳ��
		res.sendRedirect(req.getContextPath() + "/toLogin.do");
	}

	private void login(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		AbstractApplicationContext aac = new ClassPathXmlApplicationContext("applicationContext2.0.xml");
		AdminDao ad = aac.getBean("ad", AdminDao.class);
		aac.close();
		req.setCharacterEncoding("utf-8");
		// ���ձ�����
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String code = req.getParameter("code");
		// ��session�л�ȡ���ɵ���֤��
		HttpSession session = req.getSession();
		String imgcode = (String) session.getAttribute("imgcode");
		// �Ƚ���֤��
		if (code == null || !code.equalsIgnoreCase(imgcode)) {
			// ��֤�����
			req.setAttribute("error", "��֤�����");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
			return;
		}
		// ��������
		Admin a = ad.findByCode(adminCode);
		if (a == null) {
			// �˺Ų�����
			req.setAttribute("error", "�˺Ų�����");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		} else if (!a.getPassword().equals(password)) {
			// �������
			req.setAttribute("error", "�������");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		} else {
			// ���˺Ŵ���cookie
			Cookie c = new Cookie("adminCode", adminCode);
			res.addCookie(c);
			// ���˺Ŵ���session
			session.setAttribute("adminCode", adminCode);
			// ��¼�ɹ�
			res.sendRedirect("toIndex.do");
		}
	}

	private void toIndex(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}

	private void toLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ת������/WEB-INF/main/login.jsp
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
		// ��ѯ���е��ʷ�
		CostDao dao = new CostDao();
		List<Cost> list = dao.findAll();
		// �����ݰ󶨵�request��
		req.setAttribute("costs", list);
		// ��ǰ��/netctoss/findCost.do
		// Ŀ�꣺/netctoss/WEB-INF/cost/find.jsp
		// ת�����ʷѲ�ѯ��ҳ��
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
	}

	private void toAddCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ��ǰ��/netctoss/toAddCost.do
		// Ŀ�꣺/netctoss/WEB-INF/cost/add.jsp
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}

	private void addCost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		// ������������
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		// ����ҵ��
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
		// ������Щ����
		CostDao dao = new CostDao();
		dao.save(c);
		// �ض����ʷѲ�ѯҳ��
		// ��ǰ��/netctoss/addCost.do
		// Ŀ�꣺/netctoss/findCost.do
		res.sendRedirect("findCost.do");
	}

	private void toUpdateCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// ��ȡ�ʷ�ID
		String id = req.getParameter("id");
		// ��ѯ��Ҫ�޸ĵ��ʷ�
		CostDao dao = new CostDao();
		Cost c = dao.findById(new Integer(id));
		// ���ʷ�����
		req.setAttribute("cost", c);
		// ת�����ݵ��޸�ҳ��
		// ��ǰ��/netctoss/toUpdateCost.do
		// Ŀ�꣺/WEB-INF/cost/update.jsp
		req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, res);
	}

	private void updateCost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("utf-8");
		// ������������
		String name = req.getParameter("name");
		String costType = req.getParameter("costType");
		String baseDuration = req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		String descr = req.getParameter("descr");
		String costId = req.getParameter("costId");
		// ����ҵ��
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
		// �޸���Щ����
		CostDao dao = new CostDao();
		dao.update(c);
		// �ض����ʷѲ�ѯҳ��
		// ��ǰ��/netctoss/addCost.do
		// Ŀ�꣺/netctoss/findCost.do
		res.sendRedirect("findCost.do");
	}
	
}
