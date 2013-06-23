<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">

</script>

<div style="clear: both"></div>
<!--float clear -->
<div class="right">
	<div id="dark_box"></div>
	<div id="light_box_login">

		<form:form id="form2" name="form2" method="post" action="j_spring_security_check">
			<table width="458px" align="center" cellpadding="0" cellspacing="0" style="margin-top: 15px;">

				<tr>
					<td align="right" valign="middle">&nbsp;</td>
					<td align="left" valign="middle">&nbsp;</td>
				</tr>
				<tr>
					<td width="160px" height="35" align="right" valign="middle"><span class="login_header_text" style="margin-right: 10px;">User Name: </span></td>
					<td align="left" valign="middle"><label> <input type="text" class="login_text_box" id="j_username" name="j_username" /> </label>
					</td>
				</tr>
				<tr>
					<td align="right" valign="middle">&nbsp;</td>
					<td align="left" valign="middle">&nbsp;</td>
				</tr>
				<tr>
					<td align="right" valign="middle"><span class="login_header_text" style="margin-right: 10px;">Password: </span></td>
					<td align="left" valign="middle"><input type="password" name="j_password" class="login_text_box" id="j_password" /></td>
				</tr>
				<tr>
					<td align="right" valign="middle">&nbsp;</td>
					<td align="left" valign="middle">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" valign="top" align="right">
					<input name="button2" type="submit" class="login_btn" id="button2" style="font-size: 18px;" value="Login" /></td>
				</tr>
			</table>
		</form:form>
		<c:if test="${not empty param.login_error}">
			<div style="margin-top: 30px;" class="error_message">INVALID USERNAME OR PASSWORD.</div>
		</c:if>
	</div>
</div>
