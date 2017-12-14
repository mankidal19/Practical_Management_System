<%-- 
    Document   : applyApplication
    Created on : Nov 25, 2017, 8:33:24 PM
    Author     : Nurfarahin Nadhirah
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <title>Apply New Application</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="Source_Files/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="Source_Files/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="Source_Files/bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="Source_Files/dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="Source_Files/dist/css/skins/_all-skins.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="Source_Files/plugins/iCheck/square/blue.css">
        <script type="text/javascript">
function validateForm()
{
var n=document.forms["myForm"]["name"].value;
if (n==null || n=="")
{
alert("Company name must be filled out!");
document.myForm.name.focus();
return false;
}
var a=document.forms["myForm"]["adress"].value;
if (a==null || a=="")
{
alert("Company address must be filled out!");
document.myForm.address.focus();
return false;
}

var p=document.forms["myForm"]["person"].value;
if (p==null || p=="")
{
alert("Company contact person must be filled out!");
document.myForm.person.focus();
return false;
}
var o=document.forms["myForm"]["phone"].value;
if (o==null || o=="")
{
alert("Company contact phone number must be filled out!");
document.myForm.phone.focus();
return false;
}

var e=document.forms["myForm"]["email"].value;
var atpos=e.indexOf("@");
var dotpos=e.lastIndexOf(".");

if (atpos<1 || dotpos<atpos+2 || dotpos+2>=e.length)
{
alert("Enter a valid e-mail address!");
document.myForm.email.focus();
return false;
}

var v=document.forms["myForm"]["vacancy"].value;
if (v==null || v=="")
{
document.myForm.vacancy.focus();
return false;
}
var t=document.forms["myForm"]["jobtitle"].value;
if (t==null || t=="")
{
alert("Job title must be filled out");
document.myForm.jobtitle.focus();
return false;
}
}
</script>
        <style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 50%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
    </head>
    <body class="hold-transition skin-purple sidebar-mini">
        <!-- Site wrapper -->
        <div class="wrapper">

            <header class="main-header">
                <!-- Logo -->
                <a href="${pageContext.request.contextPath}/" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>UTM</b>P</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>UTM</b>Practical</span>
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">

                            <!-- Notifications: style can be found in dropdown.less -->
                            <li class="dropdown notifications-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-bell-o"></i>
                                    <span class="label label-warning">10</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 10 notifications</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu">
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">View all</a></li>
                                </ul>
                            </li>
                            <!-- Tasks: style can be found in dropdown.less -->
                            <li class="dropdown tasks-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-flag-o"></i>
                                    <span class="label label-danger">9</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">You have 9 tasks</li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu">
                                            <li><!-- Task item -->
                                                <a href="#">
                                                    <h3>
                                                        Design some buttons
                                                        <small class="pull-right">20%</small>
                                                    </h3>
                                                    <div class="progress xs">
                                                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"
                                                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                            <span class="sr-only">20% Complete</span>
                                                        </div>
                                                    </div>
                                                </a>
                                            </li>
                                            <!-- end task item -->
                                        </ul>
                                    </li>
                                    <li class="footer">
                                        <a href="#">View all tasks</a>
                                    </li>
                                </ul>
                            </li>
                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="../../dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">Student</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                        <p>
                                            Name - Software Engineering
                                            <small>Member since Nov. 2012</small>
                                        </p>
                                    </li>
                                    <!-- Menu Body -->
                                    <li class="user-body">
                                        <div class="row">
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Followers</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Sales</a>
                                            </div>
                                            <div class="col-xs-4 text-center">
                                                <a href="#">Friends</a>
                                            </div>
                                        </div>
                                        <!-- /.row -->
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <form action="${pageContext.request.contextPath}/studentProfile" method="post">
                                                <button name="studentProfile" class="btn btn-default btn-flat">Profile</button></form>
                                        </div>
                                        <div class="pull-right">
                                            <form action="${pageContext.request.contextPath}/LogoutServlet" method="get">
                                                <button name="logout" class="btn btn-default btn-flat">Sign Out</button></form>
                                        </div>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                </nav>
            </header>

            <!-- =============================================== -->

            <!-- Left side column. contains the sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>Student</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>
                    <!-- /.search form -->
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <li class="header">MAIN NAVIGATION</li>
                        <li>
                            <a href="#">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
                        </li>

                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-user-circle"></i>
                                <span> Practical Training </span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li class="active"><a href="${pageContext.request.contextPath}/applyApplication"><i class="fa fa-circle-o"></i> Apply New Application</a></li>
                                <li><a href=""><i class="fa fa-circle-o"></i> View Application Status</a></li>
                                <li><a href=""><i class="fa fa-circle-o"></i> View Application History</a></li>
                            </ul>
                        </li>

                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-files-o"></i>
                                <span> Log Book</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="${pageContext.request.contextPath}/studentAddLogBook"><i class="fa fa-circle-o"></i> Add New Log Book</a></li>
                                <li><a href="${pageContext.request.contextPath}/studentViewLogBookList"><i class="fa fa-circle-o"></i> View Log Book List</a></li>
                            </ul>
                        </li>

                        <li class="header">LABELS</li>
                        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
                        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
                        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- =============================================== -->

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Application Form
                        <small>Universiti Teknologi Malaysia</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}/studentMain"><i class="fa fa-dashboard"></i> Home </a></li>
                        <li><a href="#">Practical Training</a></li>
                        <li class="active">Apply New Application</a></li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">

                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Application Form</h3>

                            <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                                        title="Collapse">
                                    <i class="fa fa-minus"></i></button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
                                    <i class="fa fa-times"></i></button>
                            </div>
                        </div>
                        <div class="box-body">                   
                            <form name="myForm" onsubmit="return validateForm()" action="./applyApplication" method="post">
                                <fieldset>
                                    
                                <table cellpadding="5">
                                
                                    <tr>
                                    <th>Company Name</th>
                                    
                                    <td><select id="name">
                                       <c:forEach items="${companyDisplay}" var="company">    
                                            <option value="${company.applicationCompany}"><c:out value="${company.applicationCompany}" /></option>
                                       </c:forEach>      
                                    </select></td>
                                </tr>
                                <tr>
                                    <th>Company Address</th>
                                 <td><select id="address">    
                                        <c:forEach items="${companyDisplay}" var="company">   
                                            <option value="${company.applicationAddress}"><c:out value="${company.applicationAddress}" /></option>
                                    </c:forEach> 
                                     </select></td>
                                </tr>
                                <tr>
                                    <th>Company Contact Person</th>
                                 <td><select id="person">    
                                        <c:forEach items="${companyDisplay}" var="company">   
                                            <option value="${company.applicationName}"><c:out value="${company.applicationName}" /></option>
                                    </c:forEach> 
                                     </select></td>
                                </tr>
                                <tr>
                                    <th>Company Contact Phone Number</th>
                                 <td><select id="phone">    
                                        <c:forEach items="${companyDisplay}" var="company">   
                                            <option value="${company.appplicationNumber}"><c:out value="${company.appplicationNumber}" /></option>
                                    </c:forEach> 
                                     </select></td>
                                </tr>
                                <tr>
                                    <th>Company Contact Email</th>
                                 <td><select id="email">    
                                        <c:forEach items="${companyDisplay}" var="company">   
                                            <option value="${company.appplicationEmail}"><c:out value="${company.appplicationEmail}" /></option>
                                    </c:forEach> 
                                     </select></td>
                                </tr>
                                <tr>
                                    <th>Job Vacancy</th>
                                 <td><select id="vacancy">    
                                        <c:forEach items="${companyDisplay}" var="company">   
                                            <option value="${company.appplicationJob}"><c:out value="${company.appplicationJob}" /></option>
                                    </c:forEach> 
                                     </select></td>
                                </tr>
                                <tr>
                                    <th>Job Title</th>
                                 <td><select id="title">    
                                        <c:forEach items="${companyDisplay}" var="company">   
                                            <option value="${company.appplicationJobTitle}"><c:out value="${company.applicationJobTitle}" /></option>
                                    </c:forEach> 
                                     </select></td>
                                </tr>
                                <tr>
                                    <td rowspan="2"></td>
                                    <td><input type="submit" name="submit" /></td>
                                </tr>
                                </fieldset>
                                            
                                </table></form>
                        </div>
                        <!-- /.box-body -->

                        <!-- /.box-footer-->
                    </div>
                    <!-- /.box -->

                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.4.0
                </div>
                <strong>Copyright &copy; 2017 <a href="https://adminlte.io">ASTF Dev.</a>.</strong> All rights
                reserved.
            </footer>



            <!-- ./wrapper -->

            <!-- jQuery 3 -->
            <script src="Source_Files/bower_components/jquery/dist/jquery.min.js"></script>
            <!-- Bootstrap 3.3.7 -->
            <script src="Source_Files/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
            <!-- iCheck -->
            <script src="Source_Files/plugins/iCheck/icheck.min.js"></script>

            <!-- DataTables -->
            <script src="Source_Files/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
            <script src="Source_Files/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
            <!-- SlimScroll -->
            <script src="Source_Files/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
            <!-- FastClick -->
            <script src="Source_Files/bower_components/fastclick/lib/fastclick.js"></script>
            <!-- AdminLTE App -->
            <script src="Source_Files/dist/js/adminlte.min.js"></script>

            <script>
                $(document).ready(function(){
                    $('.sidebar-menu').tree();
                });
            </script>
    </body>
</html>