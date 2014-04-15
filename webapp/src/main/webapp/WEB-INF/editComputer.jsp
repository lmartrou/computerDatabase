<jsp:include page="include/header.jsp" />
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section id="main">

	<h1>Edit Computer</h1>

	<form:form action="editComputer" modelAttribute="computerDto"
		method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<form:input value="${computerDto.getName()}" type="text"
						path="name" />
					<form:errors path="name" cssStyle="color: red;" />
					<span class="help-inline">Required</span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<form:input value="${computerDto.getIntroduced()}" type="text"
						path="introduced" />
						<form:errors path="introduced" cssStyle="color: red;"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<form:input value="${computerDto.getDiscontinued()}" type="text"
						path="discontinued" />
						<form:errors path="discontinued" cssStyle="color: red;"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<form:select path="company">

						<form:option value="0" label="--Please Select" />
						<form:options items="${listCompany}" itemValue="id"
							itemLabel="name" />
					</form:select>
				</div>
			</div>
			<form:input type="hidden" value="${computerDto.getId()}" path="id"></form:input>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Edit" class="btn primary"> or <a
				href="dashboard?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}"
				class="btn">Cancel</a>
		</div>
	</form:form>
</section>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>
<script>
	$.validate();
</script>

<jsp:include page="include/footer.jsp" />