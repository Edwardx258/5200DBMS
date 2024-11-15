<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sponsorship Analysis</title>
</head>
<body>
    <h1>Frequent Foreign Worker Sponsors Analysis</h1>
    <span id="successMessage"><b>${messages.success}</b></span>
    <br/>
    <br/>
    <h2>Companies with 5+ Sponsorship Applications</h2>
    <table border="1">
        <tr>
            <th>Company Name</th>
            <th>Location</th>
            <th>Total Sponsorships</th>
            <th>Success Rate</th>
            <th>Sponsorship Types</th>
            <th>Job Titles</th>
        </tr>
        <c:forEach items="${sponsorshipSummaries}" var="summary">
            <tr>
                <td><c:out value="${summary.companyName}" /></td>
                <td><c:out value="${summary.location}" /></td>
                <td><c:out value="${summary.totalApplications}" /></td>
                <td>
                    <fmt:formatNumber value="${summary.successRate}" pattern="#.#"/>%
                </td>
                <td>
                    <c:forEach items="${summary.sponsorshipTypes}" var="type">
                        <c:out value="${type.key}"/>: <c:out value="${type.value}"/><br/>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${summary.jobRoles}" var="role">
                        <c:out value="${role.key}"/>: <c:out value="${role.value}"/><br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <br/>
    <p>Note: Only showing companies with 5 or more sponsorship applications. Success rate is calculated based on approved applications.</p>
    <p>Sponsorship Types include: H1B, PERM, and Labor Certification.</p>
</body>
</html>