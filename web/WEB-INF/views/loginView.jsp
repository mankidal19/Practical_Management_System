<%-- 
    Document   : loginView
    Created on : Nov 14, 2017, 12:10:09 PM
    Author     : NURUL AIMAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>UTM Practical Management System | Log in</title>
        <jsp:include page="_bootstrapHead.jsp"></jsp:include>

            <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
            <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
            <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
            <![endif]-->

            <!-- Google Font -->
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        </head>
        <body class="hold-transition login-page">
            <div class="login-box">
                <div class="login-logo">
                    <img src="${pageContext.request.contextPath}/Source_Files/images/UTM-LOGO-FULL.png" width="50%;"><br>
                <a href="${pageContext.request.contextPath}/"><b>Practical Management System</a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">
                <p class="login-box-msg">Sign in to start your session</p>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" placeholder="Username" name="username">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" class="form-control" placeholder="Password" name="password">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback" style='display: none;'>
                        <label for="usertype">Login as:</label><br>
                        <input type="radio" name="usertype" value="student"> Student<br>
                        <input type="radio" name="usertype" value="coordinator"> Coordinator<br>
                        <input type="radio" name="usertype" value="admin"> Admin<br>
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                            <div class="checkbox icheck">
                                <label>
                                    <input type="checkbox" name="rememberMe" value="Y"> Remember Me
                                </label>
                            </div>
                        </div>
                        <!-- /.col -->
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>



                <a href="#">I forgot my password</a><br>

            </div>
            <!-- /.login-box-body -->
        </div>
        <!-- /.login-box -->

        <jsp:include page="_bootstrapEnd.jsp"></jsp:include>
    </body>
</html>

