<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<title>Home</title>
</head>
<body>
 <div id="container">
                <%--부트스트랩 회원가입 form--%>
                    <div id="row">

                    </div>
                        <%--회원가입 폼--%>
                    <div id="row">
                        <div class="col-md-4">

                        </div>
                        <div class="col-md-4">
                                <%--부트스트랩 회원가입 form--%>
                            <form class="form-horizontal" method="post" name="signForm" id="signForm" action="/login">
                                <div class="form-group">
                                    <label for="inputEmail3" class="col-sm-2 control-label">ID</label>
                                    <div class="col-sm-10">
                                        <input type="id" class="form-control" id="id" name="id" placeholder="아이디"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="패스워드"/>
                                    </div>
                                </div>
                                <div class ="col-sm-5"></div>
                                    <%--로그인 버튼--%>
                                <input type="submit" class="btn btn-info active img-rounded" value="로그인"/>
                            </form>
                        </div>
                        <div class="col-md-4">

                        </div>
                    </div>
        <div id="footer">

        </div>
    </div>
</body>
</html>
