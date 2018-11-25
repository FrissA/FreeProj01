<%@page import="com.pokemon.app.dto.UserMapDto"%>
<%@page import="com.pokemon.app.service.UserMapping"%>
<%@page import="java.util.*"%>
<%@page import="com.pokemon.database.*"%>
<%@page import="java.sql.*"%>
<div class="col-sm-3">
	<div class="body-left">
		<div class="list-group">
			<a class="list-group-item active"><i class="fa fa-user"></i>
				Registered Users</a>
			<div class="list-group-item" id="list-players"></div>
		</div>
	</div>
</div>