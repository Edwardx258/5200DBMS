<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Foreign Worker Sponsorship Analysis</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #f4f4f4; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        #successMessage { color: green; margin-bottom: 10px; display: block; }
        .center { text-align: center; }
        .subtext { font-size: 0.9em; color: #555; }
    </style>
</head>
<body>
    <h1>Top Companies Sponsoring Foreign Workers</h1>
    <span id="successMessage"><b>${messages.success}</b></span>

    <c:choose>
        <c:when test="${empty sponsorshipSummaries}">
            <p>No sponsorship data available.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Company Name</th>
                    <th>Location</th>
                    <th>Total Applications</th>
                    <th>Success Rate</th>
                    <th>Visa Types Distribution</th>
                    <th>Top Job Roles</th>
                </tr>
                <c:forEach items="${sponsorshipSummaries}" var="summary">
                    <tr>
                        <td><c:out value="${summary.companyName}" default="N/A" /></td>
                        <td><c:out value="${summary.location}" default="N/A" /></td>
                        <td class="center"><c:out value="${summary.totalApplications}" default="0" /></td>
                        <td class="center"><fmt:formatNumber value="${summary.successRate}" pattern="#.#"/>%</td>
                        <td>
                            <c:if test="${not empty summary.sponsorshipTypes}">
                                <c:forEach items="${summary.sponsorshipTypes}" var="type">
                                    <span class="subtext"><c:out value="${type.key}"/>: <c:out value="${type.value}"/></span><br/>
                                </c:forEach>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${not empty summary.jobRoles}">
                                <c:forEach items="${summary.jobRoles}" var="role">
                                    <span class="subtext"><c:out value="${role.key}"/>: <c:out value="${role.value}"/></span><br/>
                                </c:forEach>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
