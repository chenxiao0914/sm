<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	$("a").click(function() {
		$("img").attr("src","valiCode?date="+new Date());
		return false;
	})
</script>
</head>
<body>
${error}
<form action="login" method="post">
	用户名：<input type="text" name="username"><br/>
	密码：<input type="password" name="password"><br/>
	验证码：<input type="text" name="valiCode"><img alt="" src="valiCode"><a href="">看不清，换一张</a><br/>
	<input type="submit" value="提交"><input type="reset" value="重置">
</form>
</body>
</html>