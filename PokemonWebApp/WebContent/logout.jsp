<%@page import="com.pokemon.app.service.UserMapping"%>
<%
	if (session.getAttribute("username") != null || session.getAttribute("username") != "") {
		int userId = Integer.parseInt(session.getAttribute("id").toString());
		if (UserMapping.getInstance().getMap().containsKey(userId)) {
			UserMapping.getInstance().getMap().remove(userId);
		}
		session.invalidate();
		response.sendRedirect("login.jsp");
	}
%>