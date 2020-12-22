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
        <li class="active"><a href="${pageContext.request.contextPath}/access">Access Request</a></li>
       
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
    	<div class="row">
		   <div class="col-md-3"></div>
    
    	<div class="col-md-6">
          <!-- Horizontal Form -->
          <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Access Request</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
           
            <form class="form-horizontal" name="aceessform" method="post" action="${pageContext.request.contextPath}/adduser" id="aceessform" >
              <div class="box-body">
                <div class="form-group">
                   <input type= "hidden" name="page" id="page" value="access">
                  <label  class="col-sm-4 control-label">User's Net Id</label>
                  <div class="col-sm-8">
                    <input type="text" id="netId" name="netId" value="${user.netId}" readonly="readonly" class="form-control" >
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-4 control-label">User's Name</label>

                  <div class="col-sm-8">
                    <input type="text" id="netId" name="netId" value="${user.userName}" readonly="readonly" class="form-control" >
                  </div>
                </div>
                <div class="form-group">
                 	
                  <label class="col-sm-4 control-label">Select Tower</label>

                  <div class="col-sm-8">
                   <select id="towerid" name= "towerid" class="form-control" required="required">	
                    <option value="" >[ Select One ]</option>      
                    <c:forEach items="${entityList}" var="entity">	
                    	<c:if test="${entity.entityValue == 1}">	                    
                    		<option value=" <c:out value="${entity.entityId}" />">  <c:out value="${entity.entityName}" /> </option>
                    	</c:if>	
                     </c:forEach>                      
                  </select>
                  </div>
                 
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer">               
                <button type="submit" id="btnSubmit" class="btn btn-info pull-right">Submit</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
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

<script>
  $(function () {	  
	  
	  $("#btnSubmit").click(function () {
	         if ($("#towerid").val() == "") {
	           	 alert("Please select a Tower!");
	            return false;
	         }
	        return true;
	    });
	  
	  
	  });
	  
	 
	</script>
</body>
</html>