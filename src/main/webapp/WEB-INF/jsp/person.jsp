<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Add a new Person:</p>
<form:form method="post" modelAttribute="person">

	<form:input path="id" type="hidden" required="required" />
	<form:errors path="id" />

	<form:label path="firstName">firstName:</form:label>
	<form:input path="firstName" type="text" required="required" />
	<form:errors path="firstName" />
	<br><br>

	<form:label path="lastName">lastName:</form:label>
	<form:input path="lastName" type="text" required="required" />
	<form:errors path="lastName" />
	<br><br>

	<form:label path="phoneNumber">phoneNumber:</form:label>
	<form:input path="phoneNumber" type="text" required="required" />
	<form:errors path="phoneNumber" />
	<br><br>

	<form:label path="emailAddress">emailAddress:</form:label>
	<form:input path="emailAddress" type="text" required="required" />
	<form:errors path="emailAddress" />
	<br><br>

	<form:label path="password">Password:</form:label>
	<form:input path="password" type="text" required="required" />
	<form:errors path="password" />
	<br><br>

	<button type="submit">OK</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>