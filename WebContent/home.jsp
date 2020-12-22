<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1"> 
  <title>Application Monitoring Tool</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="icon" href="img/loader.gif">
  <link rel="stylesheet" href="/AppMonitor/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="/AppMonitor/bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="/AppMonitor/bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/skins/skin-blue.min.css">

 <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> 
</head>

<body class="hold-transition skin-blue layout-top-nav ">
<div class="wrapper">

  <header class="main-header">
     <%@ include file = "header.jsp" %>    
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  

  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      	<h1>       	
		
      </h1>
      <ol class="breadcrumb">
        <li class="active"><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
       
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
		
		<div class="row">
		   <div class="col-md-3"></div>       
       	   <div class="col-md-6">
				 <span> <img src="img/apm.jpg"   class="img-fluid" alt="Company Logo"> </span> 
				 <br>Application Monitor Tool scan the applications from below Sub-Towers :  
      			 <br><br><b> GES </b> 
      			 <br><b> ClearCase </b> 
      			 <br><b> TeamCenter </b> 
      			 <br><b> Polarion </b> 
      			 <br><b> Plastic SCM </b>
      			 <br><b> Doors </b> &
      			 <br><b> CM Synergy </b>
      			 <br><br>      
     			 	<p>It scans application with an interval of <b>5</b> minutes and refreshed data on Dashboard. 
     			 	<br> Navigate application from Menu Option. <a href="${pageContext.request.contextPath}/access"> Click Here </a> to get application access .</p>
				    <span> <font color="red" size="5">${message}</font></span>	
			</div>
			<div class="col-md-3"></div> 
		</div>
      	
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <%@ include file = "footer.jsp" %>    
  </footer>

 
  <div class="control-sidebar-bg"></div>
</div>

<script src="/AppMonitor/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/AppMonitor/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/AppMonitor/dist/js/adminlte.min.js"></script>



</body>
</html>