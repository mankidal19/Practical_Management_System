<%-- 
    Document   : studentPhoto
    Created on : Dec 15, 2017, 3:16:38 PM
    Author     : Nurfarahin Nadhirah
--%>

<%@page import="db_conn.ConnectionUtils"%>
<%@page import="utils.MyUtils"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.OutputStream"%>
<%@page import="utils.StudentFunctionsUtils"%>
<%
  
  if ( request.getParameter("stdID") != null )
  {
    String stdID;
    stdID = request.getParameter("stdID") ;   
    try
    {  
       Connection conn = ConnectionUtils.getConnection();
       // get the image from the database
       byte[] imgData = StudentFunctionsUtils.queryStudentPhoto(conn,stdID); 
       // display the image
       response.setContentType("image/jpg");
       OutputStream o = response.getOutputStream();
       o.write(imgData);
       o.flush(); 
       o.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    } 
  }
%>

