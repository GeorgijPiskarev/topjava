<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1px">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm" var="cleanDate"/>
        <tr style="color:${meal.excess ? 'green' : 'red'}">
            <td>${cleanDate}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
