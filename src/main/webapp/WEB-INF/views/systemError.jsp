<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<style type="text/css">
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<title>SystemError</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span12">
				<p class="alert alert-error">
					<c:choose>
						<c:when test="${not empty exceptionCode}">[${f:h(exceptionCode)}]</c:when>
						<c:when test="${not empty param.exceptionCode}">[${f:h(param.exceptionCode)}]</c:when>
						<c:otherwise>[e.tm.9999]</c:otherwise>
					</c:choose>
					System Error...<br /> <a href="${pageContext.request.contextPath}"
						class="btn">Go to TOP</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
