<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/header/header.jsp" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="mobile-web-app-capable" content="yes"/>
    <title>글쓰기 확인</title>
    <link rel="stylesheet" href="/resources/app/stat/writing.css"/>
    <link rel="stylesheet" href="/resources/app/stat/layout.css"/>
    <title>Sign-Up/Login Form</title>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"/>
    <link rel="stylesheet" href="/resources/sign-up-login-form/css/style.css"/>
    <script src="/resources/app/javascript/write.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<div>
    <div class="form">

        <ul class="tab-group">
            <li class="tab active"><a href="#signup">Sign Up</a></li>
            <li class="tab"><a href="#login">Log In</a></li>
        </ul>

        <div class="tab-content">
            <div id="signup">
                <h1>Sign Up for Free</h1>

                <form action="/sign_up" method="post">


                        <div class="field-wrap">
                            <label>
                               ID<span class="req">*</span>
                            </label>
                            <input id="id" name = "id"  type="id" requiredautocomplete="off"/>
                        </div>


                    <div class="field-wrap">
                        <label>
                            Nickname<span class="req">*</span>
                        </label>
                        <input id="nickname" name = "nickname" requiredautocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Set A Password<span class="req">*</span>
                        </label>
                        <input id="password" name = "password" type="password" requiredautocomplete="off"/>
                    </div>

                    <button type="submit" class="button button-block">Get Started</button>

            </form>

        </div>

        <div id="login">
            <h1>Welcome Back!</h1>

            <form action="/login" method="post">

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="id" id="id"  name = "id" requiredautocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" id="password" name = "password" requiredautocomplete="off"/>
                </div>


                <button class="button button-block">Log In</button>
        </form>

    </div>

    </div><!-- tab-content -->
</div> <!-- /form -->
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="/resources/sign-up-login-form/js/index.js"></script>
</body>
</html>
