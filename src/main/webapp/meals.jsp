<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<html>
<head>
    <title>Meals</title>
    <style>
        table {
            border-collapse: collapse;
        }

        td, th {
            padding: 5px;
            border: 2px solid #5f5c5c;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h4><a href="meals?action=add">Add Meal</a></h4>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm" var="cleanDate"/>
        <tr style="color:${meal.excess ? 'red' : 'green'}">
            <td>${cleanDate}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
