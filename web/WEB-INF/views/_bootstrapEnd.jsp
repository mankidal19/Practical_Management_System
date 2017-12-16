<%-- 
    Document   : _bootstrapEnd
    Created on : Nov 14, 2017, 12:00:29 PM
    Author     : NURUL AIMAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/Source_Files/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/Source_Files/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="${pageContext.request.contextPath}/Source_Files/plugins/iCheck/icheck.min.js"></script>

<!-- DataTables -->
<script src="${pageContext.request.contextPath}/Source_Files/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/Source_Files/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/Source_Files/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/Source_Files/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/Source_Files/dist/js/adminlte.min.js"></script>


<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });

  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  });
  
  $(document).ready(function() {

  if(window.location.href.indexOf('#modal-edit') != -1) {
    $('#modal-edit').modal('show');
  }

});

function confirm_decision_co(user_id){
    if(confirm("Confirm delete the coordinator with ID " + user_id +"?")) // this will pop up confirmation box and if yes is clicked it call servlet else return to page
     {
       window.location="deleteCoordinator?id="+user_id; 
       
     }else{
       return false;
    }
   return true;
 }
 
 function confirm_decision_stu(user_id){
    if(confirm("Confirm delete the student with ID " + user_id +"?")) // this will pop up confirmation box and if yes is clicked it call servlet else return to page
     {
       window.location="deleteStudent?id="+user_id; 
       
     }else{
       return false;
    }
   return true;
 }
</script>
