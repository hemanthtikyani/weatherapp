<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="data" value="${weatherdata}" scope="request"></c:set>
<c:set var="history" value="${userhistory}" scope="session"></c:set>
<c:set var="username" value="${username}" scope="session"></c:set>

<fmt:setBundle basename="com.skycast.nl.resources.Skycast"/>

<html>
	
	<head>
    	<title>
        	Weather App
    	</title>
    	
    	<meta name="viewport" content="width=device-width, initial-scale=1">
  		<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
  		
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    	<!-- Bootstrap Core CSS -->
    	<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->

    	<!-- Custom CSS -->
    	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet">
    	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    	<link href="${pageContext.request.contextPath}/css/weather.css" rel="stylesheet">
    	
    	<link href="${pageContext.request.contextPath}/css/weather-icons.css" rel="stylesheet">
    	<%-- <link href="${pageContext.request.contextPath}/css/weather-icons.min.css" rel="stylesheet"> --%>
    	<%-- <link href="${pageContext.request.contextPath}/css/weather-icons-wind.css" rel="stylesheet">
    	<link href="${pageContext.request.contextPath}/css/weather-icons-wind.min.css" rel="stylesheet">
    	 --%>
    	<!-- <script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js" ></script> -->

	</head>

	<body>
		<form action="${pageContext.request.contextPath}/WeatherController" method="post" id="weather">
		
		<input type="hidden" id="latlong" name="latlong" />
		
		<h1 align="center">
        	<fmt:message key="weather.heading"/>
      	</h1>
      	<center>(You are logged in as <b><c:out value="${username }"></c:out></b>)</center><br>
      	<div class="row">
  			<div class="col-lg-6 col-lg-offset-3" >
    			<div class="input-group">
      				<input type="text" id="address" name="address" class="form-control" value="${data.formattedAddress }" placeholder="Address...">
     	 			<span class="input-group-btn">
        				<button class="btn btn-default" type="button" onclick="submitForm();">Go!</button>
        				<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" style="margin-left: 10px">User History
  						<span class="caret"></span></button>
  							<ul class="dropdown-menu">
  								<c:if test="${not empty history}">
  									<c:forEach var="history" items="${history}">
  										<li class="userhistory"><a href="javascript:submitSearch('${history }');"><c:out value="${history }"></c:out></a></li>
  									</c:forEach>
  									
  								</c:if>
  							</ul>
  						<button class="btn btn-default" id="gps" type="button" onclick="getCurrentLocation();"></button>
  					</span>
    			</div>
    			
  			</div>
		</div>
		
		<c:if test="${not empty data}">
			<c:if test="${data.status == 'error' }">
				<br>
				<center><div style="color: #FF0000;font-size: 30px;">Invalid Address</div></center>
			</c:if>
			<c:if test="${data.status != 'error' }">
			<center><br>
				<h4>
				<fmt:message key="weather.label.location"/> <c:out value="${data.formattedAddress }"></c:out> 
				<br><br>
				<fmt:message key="weather.label.currenttemperature"/> <b><c:out value="${data.forecastData.currently.temperature }"></c:out>&deg;F</b> 
				<c:out value="${data.forecastData.currently.summary }"></c:out>
				&nbsp;<i class="wi wi-forecast-io-${data.forecastData.currently.icon }"></i>
				</h4>
				<h5><fmt:message key="weather.label.feelslike"/> <c:out value="${data.forecastData.currently.apparentTemperature }"></c:out>&deg;F </h5>
			</center>	
			</c:if>

		<br>
		<c:if test="${not empty data.forecastData}">
		<div class="container">
  			<table class="table">
  				<h4>
  					<fmt:message key="weather.lable.hourly"/>
  					${data.forecastData.hourly.summary }
  				</h4>	
    			<thead>
      				<tr>
      					<c:forEach var="hourly" items="${data.forecastData.hourly.data }" varStatus="loop">
      						<c:if test="${loop.count<16 }">
      							<th><c:out value="${hourly.currentTime }"></c:out></th>
      						</c:if>
      					</c:forEach>
      	 		 	</tr>	
      	 		 	<tr>
      					<c:forEach var="hourly" items="${data.forecastData.hourly.data }" varStatus="loop">
      						<c:if test="${loop.count<16 }">
      							<th><c:out value="${hourly.temperature }"></c:out>&deg;F</th>
      						</c:if>
      					</c:forEach>
      	 		 	</tr>	
      	 		 	<tr>
      					<c:forEach var="hourly" items="${data.forecastData.hourly.data }" varStatus="loop">
      						<c:if test="${loop.count<16 }">
      							<th><i class="wi wi-forecast-io-${hourly.icon }"></i></th>
      							<%-- <th><c:out value="${hourly.icon }"></c:out></th> --%>
      						</c:if>
      					</c:forEach>
      	 		 	</tr>	
    			</thead>
    		</table>
    	</div>		
			
		<div class="container">
  			<table class="table table-bordered">
  			<h4>
	  			<fmt:message key="weather.label.daily"/>
	  			${data.forecastData.daily.summary }
	  		</h4>	
    			<thead>
      				<tr>
      	 		 		<th><fmt:message key="weather.table.heading.day"/></th>
      	 		 		<th><fmt:message key="weather.table.heading.icon"/></th>
        				<th><fmt:message key="weather.table.heading.mintemp"/></th>
        				<th><fmt:message key="weather.table.heading.maxtemp"/></th>
        				<th><fmt:message key="weather.table.heading.summary"/></th>
        				<th></th>
      				</tr>
    			</thead>
    			<tbody>
    				<c:forEach var="daily" items="${data.forecastData.daily.data }" varStatus="loop">
						<tr>
        					<td class="col-md-1"><c:out value="${daily.day }"></c:out></td>
        					<td class="col-md-1"><i class="wi wi-forecast-io-${daily.icon }"></i></td>
        					<td class="col-md-1"><c:out value="${daily.temperatureMin }"></c:out>&deg;F</td>
        					<td class="col-md-1"><c:out value="${daily.temperatureMax }"></c:out>&deg;F</td>
        					<td class="col-md-2"><c:out value="${daily.summary }"></c:out></td>
        					<td>
        						<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#${loop.count }">+</button>
        						<div id="${loop.count }" class="collapse">
        							<div>
  										<table class="subtable">
    										<thead>
      											<tr>
      												<th><fmt:message key="weather.table.heading.sunrise"/></th>
      												<th><fmt:message key="weather.table.heading.sunset"/></th>
      	 		 									<th><fmt:message key="weather.table.heading.humidity"/></th>
        											<th><fmt:message key="weather.table.heading.wind"/></th>
        											<th><fmt:message key="weather.table.heading.dewpoint"/></th>
        											<th><fmt:message key="weather.table.heading.pressure"/></th>
      											</tr>
    										</thead>
    										<tbody>
    											<tr>
    												<td class="col-md-1"><c:out value="${daily.sunriseTime }"></c:out></td>
    												<td class="col-md-1"><c:out value="${daily.sunsetTime }"></c:out></td>
    												<td class="col-md-1"><c:out value="${daily.humidity}"></c:out>%</td>
    												<td class="col-md-1"><c:out value="${daily.windSpeed }"></c:out>mph</td>
    												<td class="col-md-1"><c:out value="${daily.dewPoint }"></c:out></td>
    												<td class="col-md-1"><c:out value="${daily.pressure }"></c:out>mb</td>
    											</tr>
    										</tbody>
    									</table>
    								</div>
								</div>
        					</td>
     					</tr>
					</c:forEach>
    			</tbody>
  			</table>
		</div>
		</c:if>
		<c:if test="${not empty data.graphData}">
		<div class="container">
			<div id="linechart" style="width: 100%; height: 200px;margin-bottom: 3%;"></div>
		</div>
		</c:if>
		</c:if>
		</form>
	</body>
	
		

	<script type="text/javascript">
	
	function getCurrentLocation(){
		if (navigator.geolocation) {
	        navigator.geolocation.getCurrentPosition(showPosition);
	    } else{
	    	alert("Geolocation is not supported by this browser")
	    }
	}
	
	
	
	function showPosition(position) {
	    
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;
		
		var url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+latitude+','+longitude+'&sensor=true?';
		
		 $.ajax({
            url: url,
            type: 'GET',
            success: function(resultData) {
            	if(null!=resultData.results[0].formatted_address){
            		var address = document.getElementById("address");
            		address.value = resultData.results[0].formatted_address;
            		document.getElementById("latlong").value = 'true';
            		submitForm();
		 		}
            },
            error : function(jqXHR, textStatus, errorThrown) {
            },
        });
	}    
	
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	
		function submitForm(){
			var address = document.getElementById("address");
			if(""!=address.value.trim()){
				document.getElementById("weather").submit();
			}else{
				alert("Please enter an address");
			}
		}
		
		function submitSearch(searchvalue){
			document.getElementById("address").value = searchvalue;
			document.getElementById("weather").submit();
		}
		
		if(navigator.geolocation) {
        	navigator.geolocation.getCurrentPosition(locationSuccess, locationError, options);
    	}
    	else{
        	alert("Your browser does not support Geolocation!");
    	}
		function drawChart(){
			
			var input = "<c:out value='${data.graphData }'/>";
			
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Date');
			data.addColumn('number', 'Temperature');
			var arr = input.substring(0,input.length).split(",");
			data.addRows(arr.length);
			
	        for(var i=0;i<arr.length;i++){
	        	for(var j=0;j<2;j++){
	        		data.setCell(i, j, (arr[i].split(":")[j]));
	        		if(j==1){
	        			data.setCell(i, j, parseInt(arr[i].split(":")[j]));
	        		}
	        	}
	        }
	        var options = {
	          title: 'Temperature in the last 6 days',
	          curveType: 'function',
	          legend: { position: 'bottom' }
	        };

	        var chart = new google.visualization.LineChart(document.getElementById('linechart'));

	        chart.draw(data, options);
	        
		}
		
		
	</script>

</html>	
	