<nav class="navbar navbar-fixed-top">
          
          <div class="navbar-header">
           <img src="img/tcslogo.png"  height="50" width="150"  alt="Company Logo">
		    
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
              <i class="fa fa-bars"></i>
            </button>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav">
            
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu<span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="${pageContext.request.contextPath}/dashboard"><img src="img/dashboard.png"  height="25" width="25"> &nbsp;Application Dashboard</a></li>
                  <li class="divider"></li>
                  <li><a href="${pageContext.request.contextPath}/infradashboard"><img src="img/dashboard.png"  height="25" width="25"> &nbsp;Infra Dashboard</a></li>
                  <li class="divider"></li>
                  <li><a href="${pageContext.request.contextPath}/appdetails"><img src="img/appdetails.png"  height="25" width="25"> &nbsp;Application Details</a></li>                 
	              <li class="divider"></li>
	              <li><a href="${pageContext.request.contextPath}/admin"><img src="img/admin.png"  height="25" width="25"> &nbsp;Administration</a></li> 
	              <li class="divider"></li>
	              <li><a href="${pageContext.request.contextPath}/report"><img src="img/report.png"  height="25" width="25"> &nbsp;Reports</a></li>
                
                </ul>
              </li>
            </ul>
        
             <marquee class="col-md-6 hidden-sm hidden-xs"><strong>Engineering Application Monitoring System</strong></marquee>
             <ul class="nav navbar-nav navbar-right">
              <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out"></i>&nbsp;LOGOUT</a></li>
             </ul>
           
     		 <ul class="nav navbar-nav navbar-right">
              <li><a href="#"><i class="fa fa-user"></i>&nbsp;${user.userName}</a></li>
             </ul>	
          </div><!-- /.navbar-collapse -->
          <!-- /.container-fluid -->
        </nav>