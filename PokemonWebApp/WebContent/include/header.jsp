<div class="header">
	<div class="container">
		<input type="hidden" id="sessionUserId"
			value="<%out.print(session.getAttribute("id"));%>" /> <span>Hi
			<%
 	out.print(session.getAttribute("username"));
 %>
		</span> <span class="seperater">/</span> <a href="Logout" title="Logout">Logout</a>
	</div>
</div>