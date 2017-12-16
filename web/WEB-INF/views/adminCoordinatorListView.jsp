<%-- 
    Document   : adminCoordinatorListView
    Created on : Nov 23, 2017, 9:39:31 AM
    Author     : NURUL AIMAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <jsp:include page="_bootstrapHead.jsp"></jsp:include>
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
                                    <span class="hidden-xs">Alexander Pierce</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                        <p>
                                            Alexander Pierce - Web Developer
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
                                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="#" class="btn btn-default btn-flat">Sign out</a>
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
                            <p>Alexander Pierce</p>
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
                                <span> Manage Students</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href=${pageContext.request.contextPath}/createStudent><i class="fa fa-circle-o"></i> Add New Student</a></li>
                                <li><a href= ${pageContext.request.contextPath}/studentList><i class="fa fa-circle-o"></i> View Students List</a></li>
                            </ul>
                        </li>

                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-files-o"></i>
                                <span> Manage Coordinators</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href=${pageContext.request.contextPath}/createCoordinator><i class="fa fa-circle-o"></i> Add New Coordinator</a></li>
                                <li class="active"><a href=${pageContext.request.contextPath}/coordinatorList><i class="fa fa-circle-o"></i> View Coordinators List</a></li>
                            </ul>
                        </li>

                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-address-card"></i>
                                <span> Manage Companies</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href= ${pageContext.request.contextPath}/createCompany><i class="fa fa-circle-o"></i> Add New Company</a></li>
                                <li><a href= ${pageContext.request.contextPath}/companyList><i class="fa fa-circle-o"></i> View Companies List</a></li>
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
                        Manage Coordinator
                        <small>Manage coordinator of UTM Practical Management System</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Examples</a></li>
                        <li class="active">Blank page</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    
<!--edit coordinator modal-->   
     <div class="modal modal-info fade" id="modal-edit">
          <div class="modal-dialog">
              
            <div class="modal-content">
              
              <c:if test="${not empty coordinator}">

                                <form method="POST" action="${pageContext.request.contextPath}/editCoordinator">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">×</span></button>
                <h4 class="modal-title">Edit Coordinator Details</h4>
              </div>
              <div class="modal-body">
                <!-- form start -->
              <div class="box-body">
                  
                                    
                                    <table border="0">
                                        <tr>
                                            <td>Coordinator's ID</td>
                                            <td>
                                                <input type="text" style="color:red;" readonly name="co_id" value="${coordinator.coordinatorId}">
                                            </td>
                                        </tr>
                                        
                                        <tr style="display: none;">
                                            <td>Coordinator's Password</td>
                                            <td> 
                                        <input type="text" style="color:red;" readonly name="password" value="${coordinator.coordinatorPassword}">
                                            </td>
                                        </tr>
                                        <div class="form-group" style="visibility: hidden;">
                                            
                                                <input type="number" name="level" value="${coordinator.coordinatorLevel}" value="2"/>
                                            
                                        </div>
                                        <tr>
                                            <td>Name</td>
                                            <td><input type="text" name="name" value="${coordinator.coordinatorName}" /></td>
                                        </tr>
                                        <tr>
                                            <td>Department</td>
                                            <td><input type="text" name="department" value="${coordinator.coordinatorDepartment}" /></td>
                                        </tr>
                                        
                                        <tr>
                                            <td>Position</td>
                                            <td><input type="text" name="position" value="${coordinator.coordinatorPosition}" /></td>
                                        </tr>
                                        
                                        <tr>
                                            <td colspan = "2">
                                                <input type="submit" value="Submit" />
                                                <a href="${pageContext.request.contextPath}/coordinatorList">Cancel</a>
                                            </td>
                                        </tr>
                                    </table>
                                
                
              </div>
              <!-- /.box-body -->

              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-outline">Update</button>
              </div>
            </form>
                            </c:if>  
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
                    <!-- Default box -->
                    <div class="box">
                        
                        
            <div class="box-header">
              <h3 class="box-title">COORDINATORS LIST</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body container-fluid">
                 <div id="coordinatorList" class="dataTables_wrapper form-inline dt-bootstrap col-lg-12">
                  <div class="row"><div class="row">
                              <div class="col-sm-12">
                                  <table id="example1" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">
                <thead>
                <tr role="row"><th class="sorting_asc text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Car ID: activate to sort column descending" style="width: 10%;">Coordinator ID</th>
                    <th>Photo</th>
                    <th class="sorting text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Car Model: activate to sort column ascending" style="width: 25%;">Coordinator's Name</th>
                    <th class="sorting text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Max Passengers: activate to sort column ascending" style="width: 20%;">Department</th>
                    <th class="sorting text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Max Passengers: activate to sort column ascending" style="width: 15%;">Position</th>
                    <th class=" text-center" tabindex="0" aria-controls="example1" rowspan="1" aria-label="Action" style="width: 10%;">Action</th></tr>
                </thead>
                <tbody>
              
          <c:forEach items="${coordinatorList}" var="coordinator">
           <tr role="row" class="odd">
                  <td class="sorting_1">${coordinator.coordinatorId}</td>
                  <td></td>
                  <td>${coordinator.coordinatorName}</td>
                  <td>${coordinator.coordinatorDepartment}</td>
                  <td>${coordinator.coordinatorPosition}</td>
                  <td style="width: 5;">
                      <a role="button" class="btn btn-block btn-primary btn-xs" href="editCoordinator?id=${coordinator.coordinatorId}"><span class="glyphicon glyphicon-pencil"></span></a>
                      <a role="button" class="btn btn-block btn-danger btn-xs"  onclick="confirm_decision_co('${coordinator.coordinatorId}')"><span class="glyphicon glyphicon-trash"></span></a>                      
                      </td>
                </tr>
       </c:forEach>
               
                
                <tfoot>
                <tr role="row"><th class="sorting_asc text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Car ID: activate to sort column descending" style="width: 10%;">Coordinator ID</th>
                    <th>Photo</th>
                     
                    <th class="sorting text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Car Model: activate to sort column ascending" style="width: 25%;">Coordinator's Name</th>
                    <th class="sorting text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Max Passengers: activate to sort column ascending" style="width: 20%;">Department</th>
                    
                    <th class="sorting text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Max Passengers: activate to sort column ascending" style="width: 15%;">Position</th>
                    <th class=" text-center" tabindex="0" aria-controls="example1" rowspan="1" colspan="2" aria-label="Action" style="width: 25%;">Action</th></tr>
                </tfoot>
              </table></div></div>
                  </div>
            <!-- /.box-body -->
          
                        </div>
            </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            
                        </div>
                        <!-- /.box-footer-->
                    
                   
                        
                        
                       
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


            <jsp:include page="_bootstrapEnd.jsp"></jsp:include>
           
    </body>
</html>
