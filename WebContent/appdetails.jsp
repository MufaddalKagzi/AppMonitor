<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.tcs.appmonitor.util.Constants" %>
<c:set var="SUB_TOWER_GES" scope="page" value="<%=Constants.SUB_TOWER_GES%>"/>
<c:set var="SUB_TOWER_CLEAR_CASE" scope="page" value="<%=Constants.SUB_TOWER_CLEAR_CASE%>"/>
<c:set var="SUB_TOWER_TEAMCENTER" scope="page" value="<%=Constants.SUB_TOWER_TEAMCENTER%>"/>
<c:set var="SUB_TOWER_POLARION" scope="page" value="<%=Constants.SUB_TOWER_POLARION%>"/>
<c:set var="SUB_TOWER_PLASTIC" scope="page" value="<%=Constants.SUB_TOWER_PLASTIC%>"/>
<c:set var="SUB_TOWER_CM_SYNERGY" scope="page" value="<%=Constants.SUB_TOWER_CM_SYNERGY%>"/>
<c:set var="SUB_TOWER_DOORS" scope="page" value="<%=Constants.SUB_TOWER_DOORS%>"/>
<c:set var="SUB_TOWER_JIRA" scope="page" value="<%=Constants.SUB_TOWER_JIRA%>"/>

<c:set var="STATUS_UP" scope="page" value="<%=Constants.STATUS_UP%>"/>


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
  <link rel="stylesheet" href="/AppMonitor/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
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
          Application Details  <small> <font color="red"> Data Refreshed : &nbsp; <i class="fa fa-clock-o "></i> &nbsp; <fmt:formatDate type = "both" 
				         									dateStyle = "short" timeStyle = "short" value="${dashboard.scanDate}" /> </font></small>  
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/appdetails">Application Details</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

     <div class="col-md-12">
     	 
     	 	 		<div class="nav-tabs-custom">
		            <ul class="nav nav-tabs">
		              <li class="active"><a href="#tab_1" data-toggle="tab">${entityMap[SUB_TOWER_CLEAR_CASE]}</a></li>
		              <li><a href="#tab_2" data-toggle="tab">${entityMap[SUB_TOWER_TEAMCENTER]}</a></li>
		              <li><a href="#tab_3" data-toggle="tab">${entityMap[SUB_TOWER_GES]}</a></li> 
		              <li><a href="#tab_4" data-toggle="tab">${entityMap[SUB_TOWER_POLARION]}</a></li> 
		              <li><a href="#tab_5" data-toggle="tab">${entityMap[SUB_TOWER_PLASTIC]}</a></li>  
		              <li><a href="#tab_6" data-toggle="tab">${entityMap[SUB_TOWER_DOORS]}</a></li> 
		              <li><a href="#tab_7" data-toggle="tab">${entityMap[SUB_TOWER_CM_SYNERGY]}</a></li> 
		              <li><a href="#tab_8" data-toggle="tab">${entityMap[SUB_TOWER_JIRA]}</a></li>   	            
		                     
		            </ul>
		            <div class="tab-content">
		              <div class="tab-pane active" id="tab_1">								
							<div class="box">				            
				            <div class="box-body">
				              <table id="table1" class="table table-bordered table-striped">
				                 <thead>
				                <tr>
				                  
				                  <th>Site Name </th>
				                  <th>Server  Location </th>
				                  <th>Status</th>
				                </tr>
				                 </thead>
				                 <tbody>				                
				                <c:forEach items="${masterList}" var="masterList">
				                  <c:if test="${masterList.subTowerId == SUB_TOWER_CLEAR_CASE}">
				                	<tr>	                    
					                    <td><c:out value="${masterList.serverName}" />  </td>	
					                    <td><c:out value="${masterList.serverLocation}" /> </td>
					                  
										<c:choose>
					                         <c:when test="${masterList.status==STATUS_UP}">     	         
					                    		<td><span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-up"></i>UP</span></td>
											 </c:when>   
											 <c:otherwise>
											 	<td><span class="badge bg-red"> <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span></td>
											 </c:otherwise>  
										</c:choose>										            
					                 </tr>
					                 </c:if>
				                </c:forEach>
				                </tbody>			                
				              </table>
				            </div>
						 </div>		
		                
		              </div>
		              <!-- /.tab-pane -->
		              <div class="tab-pane" id="tab_2">
		                   <div class="box"> 				             
				            <div class="box-body">
				              <table id="table2" class="table table-bordered table-striped">
				                <thead>
				                <tr>				                  
				                  <th>Site Name ( Location ) </th>
				                  <th>Server Name </th>
				                  <th>Server URL</th>
				                  <th>Abbrevation</th>
				                  <th>Status</th>
				                </tr>
				                </thead>
				                <tbody>			                
				                <c:forEach items="${masterList}" var="masterList">
				                  <c:if test="${masterList.subTowerId==SUB_TOWER_TEAMCENTER}">
				                	<tr>	                    
					                       <td><c:out value="${masterList.siteName}" /> ( <c:out value="${masterList.serverLocation}" />  ) </td>	
						                   <td><c:out value="${masterList.serverName}" /> </td>
						                   <td><c:out value="${masterList.appUrl}" /> </td>
						                   <td><c:out value="${masterList.abbreviaion}" /> </td>
											<c:choose>
						                         <c:when test="${masterList.status==STATUS_UP}">     	         
						                    		<td><span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-up"></i>UP</span></td>
												 </c:when>   
												 <c:otherwise>
												 	<td><span class="badge bg-red"> <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span></td>
												 </c:otherwise>  
											</c:choose> 										            
					                 </tr>
					                 </c:if>
				                </c:forEach>
				                </tbody>			                
				              </table>
				            </div>
		                   </div>
		              </div>
		              <!-- /.tab-pane -->
		              <div class="tab-pane" id="tab_3">	             		
		             		<div class="box">          
				            <!-- /.box-header -->				            
				            <div class="box-body">
				              <table id="table3" class="table table-bordered table-striped">
				                <thead>
				                <tr>
				                  
				                  <th>App Name</th>
				                  <th>Server URL</th>
				                  <th>Status</th>
				                </tr>
				               </thead>
				               <tbody>
				                 <c:forEach items="${masterList}" var="masterList">
				                 <c:if test="${masterList.subTowerId==SUB_TOWER_GES}">
				                	<tr>
				                	 	<td><c:out value="${masterList.appName}" /> </td>
					                   <td><c:out value="${masterList.appUrl}" /> </td>
					                   <c:choose>
					                         <c:when test="${masterList.status==STATUS_UP}">     	         
					                    		<td><span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-up"></i>UP</span></td>
											 </c:when>   
											 <c:otherwise>
											 	<td><span class="badge bg-red"> <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span></td>
											 </c:otherwise>  
										</c:choose>   
				                   </tr>
				                   </c:if>
				                </c:forEach>	                    
				                </tbody>	
				              </table>
				            </div>
				            <!-- /.box-body -->
				          </div>             				
		              </div>
		              
		              
		              <div class="tab-pane" id="tab_4">
		             		<div class="box">
		             			
		             		<div class="box-body">
				              <table id="table4" class="table table-bordered table-striped">
				                <thead>
				                <tr>				                  
				                  <th>Site Name</th>
				                  <th>Status</th>
				                </tr>
				              </thead>
				              <tbody>
				                 <c:forEach items="${masterList}" var="masterList">
				                 <c:if test="${masterList.subTowerId==SUB_TOWER_POLARION}">
				                	<tr>
				                	   <td><c:out value="${masterList.siteName}" /> </td>
					                   
					                   <c:choose>
					                         <c:when test="${masterList.status==STATUS_UP}">     	         
					                    		<td><span class="badge bg-green">  <i class="glyphicon glyphicon-arrow-up"></i>UP</span></td>
											 </c:when>   
											 <c:otherwise>
											 	<td><span class="badge bg-red">  <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span></td>
											 </c:otherwise>  
										</c:choose>   
					                   </tr>
					                   </c:if>
					                </c:forEach>	                    
					               </tbody> 	
					              </table>
					           </div>
		             		
		             		
		             		</div>
		              </div>
		              
		              
		              <div class="tab-pane" id="tab_5">
		             		<div class="box">
		             		
		             		<div class="box-body">
				              <table id="table5" class="table table-bordered table-striped">
				                <thead>
				                <tr>				                  
				                  <th>Server Name</th>
				                  <th>Status</th>
				                </tr>
				              </thead>
				              <tbody>
				                 <c:forEach items="${masterList}" var="masterList">
				                 <c:if test="${masterList.subTowerId==SUB_TOWER_PLASTIC}">
				                	<tr>
				                	   <td><c:out value="${masterList.serverName}" /> </td>
					                   
					                   <c:choose>
					                         <c:when test="${masterList.status==STATUS_UP}">     	         
					                    		<td><span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-up"></i>UP</span></td>
											 </c:when>   
											 <c:otherwise>
											 	<td><span class="badge bg-red"> <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span></td>
											 </c:otherwise>  
										</c:choose>   
					                   </tr>
					                   </c:if>
					                </c:forEach>	                    
					              </tbody>  	
					              </table>
					           </div>
		             		
		             		</div>
		              </div>
		              
		              
		              <div class="tab-pane" id="tab_6">
		             		<div class="box">
		             		
		             		<div class="box-body">
				              <table id="table6" class="table table-bordered table-striped">
				                <thead>
				                <tr>				                  
				                  	<th>App Name</th>                
				                    <th>Server Name</th>
				                    <th>App URL</th>
				                    <th>App Status</th>
				                </tr>
				              </thead>
				              <tbody>
				                 <c:forEach items="${masterList}" var="masterList">
				                 <c:if test="${masterList.subTowerId==SUB_TOWER_DOORS}">
				                	<tr>
				                	   <td><c:out value="${masterList.appName}" /></td>                   
								       <td><c:out value="${masterList.serverName}" /></td>             
								       <td><c:out value="${masterList.appUrl}" /></td>  
					                   
					                   <c:choose>
					                         <c:when test="${masterList.status==STATUS_UP}">     	         
					                    		<td><span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-up"></i>UP</span></td>
											 </c:when>   
											 <c:otherwise>
											 	<td><span class="badge bg-red"> <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span></td>
											 </c:otherwise>  
										</c:choose>   
					                   </tr>
					                   </c:if>
					                </c:forEach>	                    
					              </tbody>  	
					              </table>
					           </div>
		             		
		             		</div>
		              </div>
		              
		              <div class="tab-pane" id="tab_7">
		             		<div class="box">
		             		
		             		<div class="box-body">
				              <table id="table7" class="table table-bordered table-striped">
				                <thead>
				                <tr>
				                  <th>Site Name</th>                
							      <th>Server Name</th>
							      <th>App URL</th>
							      <th>App Status</th>
				                </tr>
				              </thead>
				              <tbody>
				                 <c:forEach items="${masterList}" var="masterList">
				                 <c:if test="${masterList.subTowerId==SUB_TOWER_CM_SYNERGY}">
				                	<tr>
				                	   			
					                   <td><c:out value="${masterList.siteName}" /></td> 	                    
								       <td><c:out value="${masterList.serverName}" /></td>             
								       <td><c:out value="${masterList.appUrl}" /></td>  
					                   <c:choose>
					                         <c:when test="${masterList.status==STATUS_UP}">     	         
					                    		<td><span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-up"></i>UP</span></td>
											 </c:when>   
											 <c:otherwise>
											 	<td><span class="badge bg-red"> <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span></td>
											 </c:otherwise>  
										</c:choose>   
					                   </tr>
					                   </c:if>
					                </c:forEach>	                    
					              </tbody>  	
					              </table>
					           </div>
		             		
		             		</div>
		              </div>         
		            
		              
		              <div class="tab-pane" id="tab_8">
		             		<div class="box">
		             		
		             		<div class="box-body">
				              <table id="table5" class="table table-bordered table-striped">
				                <thead>
				                <tr>				                  
				                  <th>Server Name</th>
				                  <th>Status</th>
				                </tr>
				              </thead>
				              <tbody>
				                 <c:forEach items="${masterList}" var="masterList">
				                 <c:if test="${masterList.subTowerId==SUB_TOWER_JIRA}">
				                	<tr>
				                	   <td><c:out value="${masterList.serverName}" /> </td>
					                   
					                   <c:choose>
					                         <c:when test="${masterList.status==STATUS_UP}">     	         
					                    		<td><span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-up"></i>UP</span></td>
											 </c:when>   
											 <c:otherwise>
											 	<td><span class="badge bg-red"> <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span></td>
											 </c:otherwise>  
										</c:choose>   
					                   </tr>
					                   </c:if>
					                </c:forEach>	                    
					              </tbody>  	
					              </table>
					           </div>
		             		
		             		</div>
		              </div>
		              
              <!-- /.tab-pane -->
            	</div>
     	 	</div>
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
<script src="/AppMonitor/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/AppMonitor/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="/AppMonitor/dist/js/adminlte.min.js"></script>


<script>
  $(function () {
	$('#table1').DataTable()
	$('#table2').DataTable()
	$('#table3').DataTable()
	$('#table4').DataTable()
	$('#table5').DataTable()
	$('#table6').DataTable()  
    $('#table7').DataTable({
      'paging'      : true,
      'lengthChange': true,
      'searching'   : true,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : true
    });
   
  });
 
</script>

</body>
</html>