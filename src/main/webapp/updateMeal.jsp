<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<html>
<head>
    <title>Edit Meal</title>
    <style>
        form {
            display: table;
        }

        p {
            display: table-row;
        }

        label {
            display: table-cell;
        }

        input {
            display: table-cell;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
<form action="meals" method="post">
    <input type="hidden" name="id" value="${meal.id}"/>
    <p>
        <label for="dateTime">DateTime:</label>
        <input id="dateTime" type="datetime-local" name="dateTime" value="${meal.dateTime}"/>
    </p>
    <br>
    <p>
        <label for="description">Description:</label>
        <input id="description" type="text" name="description" value="${meal.description}"/>
    </p>
    <br>
    <p>
        <label for="calories">Calories:</label>
        <input id="calories" type="number" name="calories" value="${meal.calories}"/>
    </p>
    <br>
    <p>
        <input type="submit" value="Save">
        <a href="meals">
            <button type="button">Cancel</button>
        </a>
    </p>
</form>
</body>
</html>
