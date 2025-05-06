<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Update Student</h1>
<form:form method="post" action="/update-student/${id}">
    <table>
        <tr>
            <td>Name: </td>
            <td><form:input path="name" required='true'/></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><form:input path="email" required='true'/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update" /></td>
        </tr>
    </table>
</form:form>
