<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/topjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/topjava.meals.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="meal.title"/></h3>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="offset-1 col-2">
                            <dl>
                                <dt><spring:message code="meal.startDate"/>:</dt>
                                <dd><input class="form-control" type="date" name="startDate"
                                           value="${param.startDate}" autocomplete="off" id="startDate"></dd>
                            </dl>
                        </div>
                        <div class="col-2">
                            <dl>
                                <dt><spring:message code="meal.endDate"/>:</dt>
                                <dd><input class="form-control" type="date" name="endDate" value="${param.endDate}"
                                           autocomplete="off" id="endDate"></dd>
                            </dl>
                        </div>
                        <div class="offset-2 col-2">
                            <dl>
                                <dt><spring:message code="meal.startTime"/>:</dt>
                                <dd><input class="form-control" type="time" name="startTime"
                                           value="${param.startTime}" autocomplete="off" id="startTime"></dd>
                            </dl>
                        </div>
                        <div class="col-2">
                            <dl>
                                <dt><spring:message code="meal.endTime"/>:</dt>
                                <dd><input class="form-control" type="time" name="endTime" value="${param.endTime}"
                                           autocomplete="off" id="endTime"></dd>
                            </dl>
                        </div>
                    </div>
                </form>
                <div class="card-footer text-right">
                    <button class="btn btn-danger" onclick="clearFilter()">
                        <span class="fa fa-remove"></span>
                        <spring:message code="common.cancel"/>
                    </button>
                    <button class="btn btn-primary" onclick="updateFilteredTable()">
                        <span class="fa fa-filter"></span>
                        <spring:message code="meal.filter"/>
                    </button>
                </div>
            </div>
        </div>
        <hr>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="meal.add"/>
        </button>
        <hr>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="meal.dateTime"/></th>
                <th><spring:message code="meal.description"/></th>
                <th><spring:message code="meal.calories"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr id="${meal.id}" data-mealExcess="${meal.excess}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals/update?id=${meal.id}"><span class="fa fa-pencil"></span></a></td>
                    <td><a class="delete"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="meal.add"/></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="col-form-label"><spring:message code="meal.dateTime"/></label>
                        <input type="datetime-local" class="form-control" value="${meal.dateTime}" id="dateTime"
                               name="dateTime" required
                               placeholder="<spring:message code="meal.dateTime"/>">
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message
                                code="meal.description"/></label>
                        <input type="text" class="form-control" value="${meal.description}" id="description"
                               name="description" required
                               placeholder="<spring:message code="meal.description"/>">
                    </div>

                    <div class="form-group">
                        <label for="calories" class="col-form-label"><spring:message code="meal.calories"/></label>
                        <input type="number" class="form-control" value="${meal.calories}" id="calories"
                               name="calories" required
                               placeholder="<spring:message code="meal.calories"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>