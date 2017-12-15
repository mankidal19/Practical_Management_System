<%-- 
    Document   : coordinatorPhoto
    Created on : Dec 14, 2017, 11:35:35 PM
    Author     : Yong Keong
--%>

<%@page import="db_conn.ConnectionUtils"%>
<%@page import="utils.MyUtils"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.OutputStream"%>
<%@page import="utils.CoordinatorFunctionUtils"%>
<%
  
  if ( request.getParameter("coID") != null )
  {
    String coordinatorId;
    coordinatorId = request.getParameter("coID") ;   
    try
    {  
       Connection conn = ConnectionUtils.getConnection();
       // get the image from the database
       byte[] imgData = CoordinatorFunctionUtils.queryCoordinatorPhoto(conn,coordinatorId); 
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