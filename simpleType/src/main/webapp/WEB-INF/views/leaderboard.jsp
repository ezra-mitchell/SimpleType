<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<style>
h1, h2, h3, h4, h5 {
	color: var(--c-pink-dark);
}

.container {
	display: flex;
	flex-direction: column;
	align-items: center;
}

th {
	color: var(--c-tan-light);
}

td {
	color: var(--c-pink-dark);
}

th, td {
	padding: 0.2em
}

a {
	color: var(--c-blue);
	font-size: 0.7em;
}

table {
	border-collapse: collapse;
	border-radius: 5px;
    margin: 25px 0;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    padding: 0.2em;
    overflow: hidden;
}

thead {
	text-align: left;
	background-color: var(--c-blue);
	border-radius: 5px;
}


tbody tr{
	border-bottom: 1px solid #dddddd;	
}

tbody tr:nth-of-type(even) {
	background-color: var(--c-tan);
}

tbody tr:last-of-type {
	border-bottop: 2px solid var(--c-blue)
}

</style>

</head>
<body>
	<jsp:include page="../fragments/header.jsp"></jsp:include>
	<div class="container">
		<div>
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
							<td><fmt:formatNumber value="${spot.speed}" type="number"
									maxFractionDigits="1" /> wpm</td>
							<td><fmt:formatNumber value="${spot.accuracy}" type="number"
									maxFractionDigits="1" />%</td>
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
							<td><fmt:formatNumber value="${spot.speed}" type="number"
									maxFractionDigits="1" /> wpm</td>
							<td><fmt:formatNumber value="${spot.accuracy}" type="number"
									maxFractionDigits="1" />%</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<h4>
				<a href="/home">Take another test (Shift + Tab)</a>
			</h4>
		</div>
	</div>
	<script>
		document.addEventListener('keydown', (e) => {
			console.log(e.code);
			if(e.shiftKey && e.code === "Tab") window.location.href = "/home";
		});
	</script>
</body>
</html>