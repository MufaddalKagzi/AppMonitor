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
    <link rel="stylesheet" href="/AppMonitor/bower_components/morris.js/morris.css">
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
          Application Availability Report  
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/report">Report</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
     					
     <div class="col-md-12">
     
     		 <a href="${pageContext.request.contextPath}/report">
     			<span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-left"></i>&nbsp;&nbsp;Back&nbsp;</span></a>  
   				  			     
     	 		<div class="nav-tabs-custom">
		            <ul class="nav nav-tabs">
		              <li class="active"><a href="#tab_1" data-toggle="tab">Availability Graph</a></li>		    
		              <li><a href="#tab_2" data-toggle="tab">Down Application's History</a></li>	
		             <!--  <li><a href="#tab_3" data-toggle="tab">Application History Graph</a></li>    -->         	            
		                     
		            </ul>
		            <div class="tab-content">
		              <div class="tab-pane active" id="tab_1">								
							<div class="box">				            
				            <div class="box-body">
				              	
				              	      <div class="col-md-6">
				              	      
				              	      		<table  class="table table-bordered table-striped">
								                <tbody>		       
								                  <tr> <td> <b>Application Tower</b> </td> <td>${entityMap[tower]} </td></tr>
								                  <tr> <td><b>Application Sub-Tower</b> </td> <td> ${entityMap[stower]}</td></tr>
								                  <tr> <td><b>Application Name</b> </td> <td> ${appName}</td></tr>
								                  <tr> <td><b>Date Range</b> </td> <td>${daterange} </td></tr>
								                </tbody>			                
								              </table>
								              	      
						              </div>
						              
						              
						              <div class="col-md-6">
						               <div id="donut-chart" style="height: 300px;"></div>
						               </div>
						            <!-- /.box-body-->
						        
										              
				              
				            </div>
						 </div>		
		                
		              </div>
		              
		              
		              <div class="tab-pane" id="tab_2">
		                   <div class="box"> 				             
				            <div class="box-body">
				              
				                <table id="table1" class="table table-bordered table-striped">
				                <thead>
				                <tr>	
				                  <!-- <th> <span class="custom-checkbox"> <input type="checkbox" id="master"></span></th>			  -->                 
				                  <th>Sub Tower</th>
				                  <th>Application/Site/Server Name </th>
				                  <th>Status</th>
				                  <th>Down Date/Time</th>
				                </tr>
				                </thead>
				                <tbody>	
				                		
				                  	<c:forEach items="${hstryList}" var="hstryList">
				                  
				                  	<tr>
				                  		<%-- <td><input type="checkbox" class="sub_chk" data-id="${hstryList.id}"></td> --%>
				                    	<td><c:out value="${entityMap[hstryList.subTowerId]}"/> </td>
				                       
				                        <td>			                        	
				                        	
				                        	<c:if test="${hstryList.subTowerId == SUB_TOWER_GES}">
		                						<span><c:out value="${hstryList.appName}" /><br></span>                				
		                					</c:if>
		                					<c:if test="${ hstryList.subTowerId == SUB_TOWER_TEAMCENTER || hstryList.subTowerId == SUB_TOWER_POLARION }">
		                						<span><c:out value="${hstryList.siteName}" /><br></span>                				
		                					</c:if>
		                					<c:if test="${hstryList.subTowerId==SUB_TOWER_CLEAR_CASE || hstryList.subTowerId==SUB_TOWER_PLASTIC || hstryList.subTowerId==SUB_TOWER_CM_SYNERGY || hstryList.subTowerId==SUB_TOWER_DOORS}">
		                						<span><c:out value="${hstryList.serverName}" /><br></span>                				
		                					</c:if>		
				                        	
				                        	                					
				                        </td>
				                        <td>
				                          <c:choose>
					                         <c:when test="${hstryList.status==STATUS_UP}">     	         
					                    		<span class="badge bg-green"> <i class="glyphicon glyphicon-arrow-up"></i>UP</span>
											 </c:when>   
											 <c:otherwise>
											 	<span class="badge bg-red"> <i class="glyphicon glyphicon-arrow-down"></i>DOWN</span>
											 </c:otherwise>  
										  </c:choose>		
				                        </td>
				                        <td> <c:out value="${hstryList.scanDate}" /> </td>			                        
				                    </tr>        
				                
				                  </c:forEach>
				                	 
				                </tbody>			                
				              </table>
				                
				               
				            </div>
		                   </div>
		              </div>
		              
		              <!-- /.tab-pane -->
		              <div class="tab-pane" id="tab_3">
		                   <div class="box"> 				             
				            <div class="box-body">
				              
				                <div class="box-body chart-responsive">
					              <div class="chart" id="bar-chart-hstry" style="height: 300px;"></div>
					            </div>
				                
				               
				            </div>
		                   </div>
		              </div>
		              
		              
		              
		              
		              <!-- /.tab-pane -->
		             
		              
		                      
		            
		              
		              
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

<script src="/AppMonitor/bower_components/Flot/jquery.flot.js"></script>
<script src="/AppMonitor/bower_components/Flot/jquery.flot.resize.js"></script>
<script src="/AppMonitor/bower_components/Flot/jquery.flot.pie.js"></script>
<script src="/AppMonitor/bower_components/Flot/jquery.flot.categories.js"></script>

<script src="/AppMonitor/bower_components/raphael/raphael.min.js"></script>
<script src="/AppMonitor/bower_components/morris.js/morris.min.js"></script>




<script>
  $(function () {
	$('#table1').DataTable({
      'paging'      : true,
      'lengthChange': true,
      'searching'   : true,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : true
    });
	
	

/* 	  var bar1 = new Morris.Bar({
	      element: 'bar-chart-hstry',
	      resize: true,
	      data: [
	        {y: '2006', a: 100},
	        {y: '2007', a: 75}
	       
	      ],
	      barColors: ['#00a65a'],
	      xkey: 'y',
	      ykeys: ['a'],
	      labels: ['CPU'],
	      hideHover: 'auto'
	    });   */
	
	
	  var total = ${totalScanCount};	 
      var browsersChart = Morris.Donut({
    	  element: 'donut-chart',
    	  data:  ${avlbleGraphData},
    	  formatter: function (value, data) {    		 
    	  	return Math.abs(value/total*100).toFixed(2)+ '%'; 
    	  }
    	});
    
      /*   var bar = new Morris.Bar({
	      element: 'bar-chart-hstry',
	      data: ${avlbleGraphData} ,
	      barColors: function (row, series, type) {
				if (row.y > 0)
					return "#00a65a";
				return "#f56954";
		
	      xkey: 'scanDate',
	      ykeys: ['a'],	
	      labels: ['Status'],
	      barSize : 5	      
	    });   */
  

    
	 
  });
    	 
</script>

</body>
</html>