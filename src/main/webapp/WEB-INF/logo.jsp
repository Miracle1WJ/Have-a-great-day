<%@page pageEncoding="utf-8" %>
<span>${cookie.adminCode.value }</span>
<%-- 
	user -> request.getAtttibute("user");
	user.name -> request.getAtttibute("user").getName();
	contextPath -> request.getAtttibute("contextPath");
	pageContext.request.contextPath -> pageContext.getRequest().getContextPath()
 --%>
<a href="${pageContext.request.contextPath }/logoOut.do">[退出]</a>
