<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Students <a style="float:right" href="/create-student"><button>Enroll Student</button></a></h1>
<table border="2" width="100%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Courses</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
        <td>${student[0]}</td>
        <td>${student[1]}</td>
        <td>${student[2]}</td>
        <td>${student[3]}</td>
        <td>
        <a href="/update-student/${student[0]}"><button>Update Student</button></a>
        <a href="/update-student-courses/${student[0]}"><button>Update Courses</button></a>
        <a href="/delete-student/${student[0]}"><button>Delete Student</button></a>
        </td>
        </tr>
    </c:forEach>
</table>
<br/>

