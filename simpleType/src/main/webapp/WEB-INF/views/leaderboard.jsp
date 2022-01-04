<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SimpleType</title>
<!--  
This can be added back if angularjs is necessary, currently removed for bundle size
<script src="/resources/angular.min.js"></script>
-->
<link rel="stylesheet" href="/resources/style.css">
</head>
<body>
	<jsp:include page="../fragments/header.jsp"></jsp:include>

	<h1>LeaderBoard</h1>

	<h3>Your place</h3>
	<table>
		<thead>
			<tr>
				<th>Place</th>
				<th>Name</th>
				<th>Speed</th>
				<th>Accuracy</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${placement}" var="spot">
				<tr>
					<td>${spot.place}</td>
					<td>${spot.name}</td>
					<td><fmt:formatNumber value="${spot.speed}" type="number" maxFractionDigits="1"/> wpm</td>
					<td><fmt:formatNumber value="${spot.accuracy}" type="number" maxFractionDigits="1"/>%</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h3>Top Scores</h3>

	<table>
		<thead>
			<tr>
				<th>Place</th>
				<th>Name</th>
				<th>Speed</th>
				<th>Accuracy</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${leaderboard}" var="spot">
				<tr>
					<td>${spot.place}</td>
					<td>${spot.name}</td>
					<td><fmt:formatNumber value="${spot.speed}" type="number" maxFractionDigits="1"/> wpm</td>
					<td><fmt:formatNumber value="${spot.accuracy}" type="number" maxFractionDigits="1"/>%</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h4>
		<a href="/home">Take another test</a>
	</h4>
	
	<script>
		document.addEventListener('keydown', (e) => {
			console.log(e.code);
			if(e.shiftKey && e.code === "Tab") window.location.href = "/home";
		});
	</script>
</body>
</html>