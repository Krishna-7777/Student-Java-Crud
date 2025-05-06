<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Update Student Courses</title>
</head>
<body>
    <h2>Update Courses for Student: ${student.name}</h2>
    <form action="/update-student-courses/${student.id}" method="post">
        <label>Select Courses:</label><br/>
        <c:forEach var="course" items="${allCourses}">
            <input type="checkbox" name="courseIds"
                   value="${course.id}"
                   <c:if test="${fn:contains(enrolledCourseIds, course.id)}">checked</c:if> />
            ${course.name}<br/>
        </c:forEach>
        <br/>
        <input type="submit" value="Update Courses"/>
    </form>
</body>
</html>
