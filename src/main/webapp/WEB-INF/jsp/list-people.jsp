<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <H1>Person list:</H1>

    <table border="1">
        <thead>
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Phone number</th>
            <th>EmailAddress</th>
            <th>Password</th>
            <th>Delete</th>
            <th>Add new</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${people}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.firstName}</td>
                <td>${p.lastName}</td>
                <td>${p.phoneNumber}</td>
                <td>${p.emailAddress}</td>
                <td>${p.password}</td>
                <td><a type="button" href="/update-person/${p.id}">UPDATE</a></td>
                <td><a type="button" href="/delete-person/${p.id}">DELETE</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <br>
    <div>
        <a href="add-person">[ADD a new Person]</a>
    </div>
</div>
<%@ include file="common/footer.jspf" %>