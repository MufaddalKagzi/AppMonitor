<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<c:set var="STATUS_DOWN" scope="page" value="<%=Constants.STATUS_DOWN%>"/>

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
  <link rel="stylesheet" href="/AppMonitor/bower_components/morris.js/morris.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/AppMonitor/dist/css/skins/skin-blue.min.css">

 <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> 
    <script>
   	function timedRefresh(timeoutPeriod) {
		setTimeout("location.reload(true);",timeoutPeriod);
	}window.onload = timedRefresh(500000);   
</script>
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
        Dashboard
        <small>Version 1.0</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">
			
	   <div class="row">
      	 <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-yellow"><i class="fa fa-clock-o"></i></span>
            <div class="info-box-content">
              <span class="info-box-text">Last Scanned  </span>
              <span class="info-box-number">  <fmt:formatDate type = "both" 
         									dateStyle = "short" timeStyle = "short" value="${dashboard.scanDate}" />  (EST)  </span>
            </div>
          </div>
        </div>
      	
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="glyphicon glyphicon-search"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Application Scanned</span>
              <span class="info-box-number">${dashboard.scanCount}</span>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="glyphicon glyphicon-arrow-down"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Application Down</span>
              <span class="info-box-number">${dashboard.scanDownCount}</span>
              <a href="#" class="info-box-text" data-toggle="modal" data-target="#modal-down" >More info <i class="fa fa-arrow-circle-right"></i></a>
            </div>
          </div>
        </div>

        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="glyphicon glyphicon-arrow-up"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Application Running</span>
              <span class="info-box-number">${dashboard.scanUpCount}</span>
              <a href="#" class="info-box-text"  data-toggle="modal" data-target="#modal-up" >More info <i class="fa fa-arrow-circle-right"></i></a>
            </div>
          </div>
        </div>
       </div>
	   
	   
	   
	   <div class="row">       
        	<div class="col-md-6">
          <div class="box box-info">
          <div class="box-header with-border">
              <h3 class="box-title ">Status By SubTower</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>  </button>
              </div>
            </div>           
            		
            		<div class="box-body">
							<div class="box-group" id="accordion">
									<div class="panel box box-info">
					                  <div class="box-header with-border">
					                    <h4 class="box-title">
					                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseges">
					                         ${entityMap[SUB_TOWER_GES]}
					                      </a>
					                    </h4>
					                  </div>
					                  <div id="collapseges" class="panel-collapse collapse in">
					                    <div class="box-body">
					                      	<div class="table-responsive">
								                <table class="table no-margin">
								                  <thead>
								                  <tr>                   
								                    <th>App/Site Name</th>
								                    <th>App Status</th>
								                   
								                  </tr>
								                  </thead>
								                  <tbody>
								                   <c:forEach items="${masterList}" var="masterList">
									                  <c:if test="${masterList.subTowerId == SUB_TOWER_GES}">
										                  <tr>	                    
										                    <td><c:out value="${masterList.appName}" /></td>             
										                   
										                    <c:choose>
										                         <c:when test="${masterList.status==STATUS_UP}">     	         
										                    		<td><span class="label label-success">Running</span></td>
																 </c:when>   
																 <c:otherwise>
																 	<td><span class="label label-danger">Down</span></td>
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
					                </div>
					                
					                
					                
					                <div class="panel box box-info">
					                  <div class="box-header with-border">
					                    <h4 class="box-title">
					                      <a data-toggle="collapse" data-parent="#accordion" href="#collapsetc">
					                        ${entityMap[SUB_TOWER_TEAMCENTER]}
					                      </a>
					                    </h4>
					                  </div>
					                  <div id="collapsetc" class="panel-collapse collapse">
					                    <div class="box-body">
					                     	<div class="table-responsive">
								                <table class="table no-margin">
								                  <thead>
								                  <tr>
								                    <th>App/Site Name</th>
								                    <th>App Status</th>
								                   
								                  </tr>
								                  </thead>
								                  <tbody>
									                  <c:forEach items="${masterList}" var="masterList">
									                   <c:if test="${masterList.subTowerId ==SUB_TOWER_TEAMCENTER}">
									                  <tr>	                    
									                    <td><c:out value="${masterList.siteName}" /> ( <c:out value="${masterList.serverLocation}" />  ) </td>	
									                    <c:choose>
									                         <c:when test="${masterList.status==STATUS_UP}">     	         
									                    		<td><span class="label label-success">Running</span></td>
															 </c:when>   
															 <c:otherwise>
															 	<td><span class="label label-danger">Down</span></td>
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
					                </div>
					                
					                
					                 <div class="panel box box-info">
					                  <div class="box-header with-border">
					                    <h4 class="box-title">
					                      <a data-toggle="collapse" data-parent="#accordion" href="#collapsecc">
					                        ${entityMap[SUB_TOWER_CLEAR_CASE]}
					                      </a>
					                    </h4>
					                  </div>
					                  <div id="collapsecc" class="panel-collapse collapse">
					                    <div class="box-body">
					                     	<div class="table-responsive">
							                <table class="table no-margin">
							                  <thead>
							                  <tr>                   
							                    <th>App/Site Name</th>
							                    <th>App Status</th>
							                   
							                  </tr>
							                  </thead>
							                  <tbody>
							                    <c:forEach items="${masterList}" var="masterList">
							                     <c:if test="${masterList.subTowerId==SUB_TOWER_CLEAR_CASE}">
								                  <tr>	                    
								                    <td><c:out value="${masterList.serverName}" /> ( <c:out value="${masterList.serverLocation}" />  )</td>             
								                   
								                    <c:choose>
								                         <c:when test="${masterList.status== STATUS_UP}">     	         
								                    		<td><span class="label label-success">Running</span></td>
														 </c:when>   
														 <c:otherwise>
														 	<td><span class="label label-danger">Down</span></td>
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
					                </div>
					                
					                
					                <div class="panel box box-info">
					                  <div class="box-header with-border">
					                    <h4 class="box-title">
					                      <a data-toggle="collapse" data-parent="#accordion" href="#collapsepol">
					                        ${entityMap[SUB_TOWER_POLARION]}
					                      </a>
					                    </h4>
					                  </div>
					                  <div id="collapsepol" class="panel-collapse collapse">
					                    <div class="box-body">
					                     	<div class="table-responsive">
							                <table class="table no-margin">
							                  <thead>
							                  <tr>                   
							                    <th>App/Site Name</th>
							                    <th>App Status</th>
							                   
							                  </tr>
							                  </thead>
							                  <tbody>
							                    <c:forEach items="${masterList}" var="masterList">
							                     <c:if test="${masterList.subTowerId==SUB_TOWER_POLARION}">
								                  <tr>	                    
								                    <td><c:out value="${masterList.siteName}" /></td>             
								                   
								                    <c:choose>
								                         <c:when test="${masterList.status==STATUS_UP}">     	         
								                    		<td><span class="label label-success">Running</span></td>
														 </c:when>   
														 <c:otherwise>
														 	<td><span class="label label-danger">Down</span></td>
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
					                </div>
          
          
          							
          							<div class="panel box box-info">
					                  <div class="box-header with-border">
					                    <h4 class="box-title">
					                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseplastic">
					                        ${entityMap[SUB_TOWER_PLASTIC]}
					                      </a>
					                    </h4>
					                  </div>
					                  <div id="collapseplastic" class="panel-collapse collapse">
					                    <div class="box-body">
					                     	<div class="table-responsive">
							                <table class="table no-margin">
							                  <thead>
							                  <tr>                   
							                    <th>Server Name</th>
							                    <th>App Status</th>
							                   
							                  </tr>
							                  </thead>
							                  <tbody>
							                    <c:forEach items="${masterList}" var="masterList">
							                     <c:if test="${masterList.subTowerId==SUB_TOWER_PLASTIC}">
								                  <tr>	                    
								                    <td><c:out value="${masterList.serverName}" /></td>             
								                   
								                    <c:choose>
								                         <c:when test="${masterList.status==STATUS_UP}">     	         
								                    		<td><span class="label label-success">Running</span></td>
														 </c:when>   
														 <c:otherwise>
														 	<td><span class="label label-danger">Down</span></td>
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
					                </div>
					                
					                <div class="panel box box-info">
					                  <div class="box-header with-border">
					                    <h4 class="box-title">
					                      <a data-toggle="collapse" data-parent="#accordion" href="#collapsedoors">
					                        ${entityMap[SUB_TOWER_DOORS]}
					                      </a>
					                    </h4>
					                  </div>
					                  <div id="collapsedoors" class="panel-collapse collapse">
					                    <div class="box-body">
					                     	<div class="table-responsive">
							                <table class="table no-margin">
							                  <thead>
							                  <tr>   
							                                
							                    <th>Server Name</th>
							                    
							                    <th>App Status</th>
							                   
							                  </tr>
							                  </thead>
							                  <tbody>
							                    <c:forEach items="${masterList}" var="masterList">
							                     <c:if test="${masterList.subTowerId==SUB_TOWER_DOORS}">
								                  <tr>	   
								                                 
								                    <td><c:out value="${masterList.serverName}" /></td>             
								                   
								                    <c:choose>
								                         <c:when test="${masterList.status==STATUS_UP}">     	         
								                    		<td><span class="label label-success">Running</span></td>
														 </c:when>   
														 <c:otherwise>
														 	<td><span class="label label-danger">Down</span></td>
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
					                </div>
					                
					                <div class="panel box box-info">
					                  <div class="box-header with-border">
					                    <h4 class="box-title">
					                      <a data-toggle="collapse" data-parent="#accordion" href="#collapsesynergy">
					                        ${entityMap[SUB_TOWER_CM_SYNERGY]}
					                      </a>
					                    </h4>
					                  </div>
					                  <div id="collapsesynergy" class="panel-collapse collapse">
					                    <div class="box-body">
					                     	<div class="table-responsive">
							                <table class="table no-margin">
							                  <thead>
							                  <tr>  
							                                   
							                    <th>Server Name</th>							                    
							                    <th>App Status</th>
							                   
							                  </tr>
							                  </thead>
							                  <tbody>
							                    <c:forEach items="${masterList}" var="masterList">
							                     <c:if test="${masterList.subTowerId==SUB_TOWER_CM_SYNERGY}">
								                  <tr>
								                  	                    
								                   <td><c:out value="${masterList.serverName}" /></td>             
								                  
								                    <c:choose>
								                         <c:when test="${masterList.status==STATUS_UP}">     	         
								                    		<td><span class="label label-success">Running</span></td>
														 </c:when>   
														 <c:otherwise>
														 	<td><span class="label label-danger">Down</span></td>
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
					                </div>
					                
					                
					                <div class="panel box box-info">
					                  <div class="box-header with-border">
					                    <h4 class="box-title">
					                      <a data-toggle="collapse" data-parent="#accordion" href="#collapsejira">
					                        ${entityMap[SUB_TOWER_JIRA]}
					                      </a>
					                    </h4>
					                  </div>
					                  <div id="collapsejira" class="panel-collapse collapse">
					                    <div class="box-body">
					                     	<div class="table-responsive">
							                <table class="table no-margin">
							                  <thead>
							                  <tr>  
							                                   
							                    <th>Server Name</th>							                    
							                    <th>App Status</th>
							                   
							                  </tr>
							                  </thead>
							                  <tbody>
							                    <c:forEach items="${masterList}" var="masterList">
							                     <c:if test="${masterList.subTowerId==SUB_TOWER_JIRA}">
								                  <tr>
								                  	                    
								                   <td><c:out value="${masterList.serverName}" /></td>             
								                  
								                    <c:choose>
								                         <c:when test="${masterList.status==STATUS_UP}">     	         
								                    		<td><span class="label label-success">Running</span></td>
														 </c:when>   
														 <c:otherwise>
														 	<td><span class="label label-danger">Down</span></td>
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
					                </div>
          							
          							
          
         					</div>
            		</div>
            
            
          </div>		
        </div>
        

        <div class="col-md-6">
		 <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title ">Last (10) Down Status of Application </h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="box-body chart-responsive">
              <div class="chart" id="bar-chart-hstry" style="height: 300px;"></div>
            </div>
            
            
            <div class="box-header with-border">
              <h3 class="box-title "> Status - Group By Application </h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
             <div class="box-body chart-responsive">
              <div class="chart" id="bar-chart" style="height: 300px;"></div>
            </div> 
         </div> 
        
        </div>
      </div> 
      
      <div class="row">
      	<div class = col-md-12 >
      			
      			
      	<div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title ">Last Scan Logs</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <div class="box-body">
            		<c:forEach items="${timelineMap}" var="timelineMap">
	        		<ul class="timeline">
			            	 <li class="time-label">
			                  <span class="bg-red">
			                  	<span class="time"><i class="fa fa-clock-o"></i> </span> <c:out value="${timelineMap.key}" />		                    	
			                  </span>
			                 </li>		               		
			               	<li>
						        <i class="fa fa-commenting bg-blue"></i>
						        <div class="timeline-item">						            				
						                <div class="timeline-body">					              
							                <c:out value="${timelineMap.value}" /> 
							            </div>	          
						        </div>
						    </li>			  
		            </ul>     
		     		</c:forEach>            		
            </div>
        </div>
      		
      		
      	
      	</div>      
      </div>  
      
      
      
       <div class="modal modal-info fade" id="modal-down">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Down Application's Information</h4>
              </div>
              <div class="modal-body">
                <p>
                	<c:forEach items="${masterList}" var="masterList">   
                			<c:if test="${masterList.status==STATUS_DOWN && masterList.subTowerId==SUB_TOWER_GES}">
                				<i class="fa fa-circle text-red"></i>&nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.appName}"/><br></span>                				
                			</c:if>  
                			<c:if test="${masterList.status==STATUS_DOWN && ( masterList.subTowerId==SUB_TOWER_TEAMCENTER || masterList.subTowerId==SUB_TOWER_POLARION )}">
                				<i class="fa fa-circle text-red"></i> &nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.siteName}" /><br></span>                				
                			</c:if>  
                			<c:if test="${masterList.status==STATUS_DOWN && ( masterList.subTowerId==SUB_TOWER_CLEAR_CASE || masterList.subTowerId==SUB_TOWER_PLASTIC || masterList.subTowerId==SUB_TOWER_CM_SYNERGY || masterList.subTowerId==SUB_TOWER_DOORS || masterList.subTowerId==SUB_TOWER_JIRA )}">
                				<i class="fa fa-circle text-red"></i> &nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.serverName}" /><br></span>                				
                			</c:if>               			
                	</c:forEach>             	
                </p>
              </div>
            </div>
          </div>
        </div>
        
        
        <div class="modal modal-info fade" id="modal-up">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Running Application's Information</h4>
              </div>
              <div class="modal-body">
                <p>
                	<c:forEach items="${masterList}" var="masterList">
                			<c:if test="${masterList.status==STATUS_UP && masterList.subTowerId==SUB_TOWER_GES}">
                				<i class="fa fa-circle text-green"></i> &nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.appName}" /><br></span>                				
                			</c:if>  
                			<c:if test="${masterList.status==STATUS_UP && ( masterList.subTowerId==SUB_TOWER_TEAMCENTER || masterList.subTowerId==SUB_TOWER_POLARION )}">
                				<i class="fa fa-circle text-green"></i>&nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.siteName}" /><br></span>                				
                			</c:if>  
                			<c:if test="${masterList.status==STATUS_UP && ( masterList.subTowerId==SUB_TOWER_CLEAR_CASE || masterList.subTowerId==SUB_TOWER_PLASTIC || masterList.subTowerId==SUB_TOWER_CM_SYNERGY || masterList.subTowerId==SUB_TOWER_DOORS || masterList.subTowerId==SUB_TOWER_JIRA)}">
                				<i class="fa fa-circle text-green"></i> &nbsp;<span><c:out value="${entityMap[masterList.subTowerId]}"/> - <c:out value="${masterList.serverName}" /><br></span>                				
                			</c:if>               			
                	</c:forEach>  
                </p>
              </div>
              
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

<script src="/AppMonitor/bower_components/raphael/raphael.min.js"></script>
<script src="/AppMonitor/bower_components/morris.js/morris.min.js"></script>
<script src="/AppMonitor/bower_components/fastclick/lib/fastclick.js"></script>
<script src="/AppMonitor/dist/js/adminlte.min.js"></script>

<script>
  $(function () {
	  "use strict";
	//BAR CHART
		 var bar = new Morris.Bar({
	      element: 'bar-chart',
	      resize: true,	      
	      data:${graphData},
	      barColors: ['#0080ff', '#00a65a' ,'#f56954'],
	      xkey: 'subTower',
	      ykeys: ['a', 'b' ,'c' ],	
	      labels: ['TotalApps', 'Running' , 'Down' ],
	      hideHover: 'auto'
	    }); 
	
		var bar1 = new Morris.Bar({
		      element: 'bar-chart-hstry',
		      resize: true,	      
		      data:${hstryGraphData},
		      barColors: ['#0080ff', '#00a65a' ,'#f56954'],
		      xkey: 'scanDate',
		      ykeys: ['a', 'b' ,'c' ],	
		      labels: ['TotalApps', 'Running' , 'Down' ],
		      hideHover: 'auto'
		    });
	
		
  });
  
  function onload(scanid){	  
	  window.open("${pageContext.request.contextPath}/scannedapp?id="+scanid, "Application Details", "width=500,height=500,toolbar=0,location=0, directories=0, status=0, menubar=0");
  }
</script>

</body>
</html>