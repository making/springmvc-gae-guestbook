<!DOCTYPE html>
<html>
<body>
	<form:form action="${pageContext.request.contextPath}/greeting"
		modelAttribute="greetingForm" method="post">
		<form:textarea path="content" rows="3" cols="60" />
		<form:hidden path="guestbookName" />
		<br>
		<input type="submit" value="Post greeting">
	</form:form>
	<form action="${pageContext.request.contextPath}/greeting" method="get">
		<div>
			<input type="text" name="guestbookName"
				value="${f:h(greetingForm.guestbookName)}" />
		</div>
		<div>
			<input type="submit" value="Switch Guestbook" />
		</div>
	</form>

	<ul>
		<c:forEach items="${greetings}" var="greeting">
			<li>${f:h(greeting.content)}</li>
		</c:forEach>
	</ul>
</body>
</html>