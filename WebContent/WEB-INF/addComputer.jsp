<jsp:include page="include/header.jsp" />
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section id="main">

	<h1>Add Computer</h1>
	
	<form action="AddComputer?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input data-validation="required" type="text" name="name" />
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input data-validation="date" data-validation-optional="true" type="text" name="introducedDate"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input data-validation="date" data-validation-optional="true" type="text" name="discontinuedDate"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
					<option value="0"></option>
					<c:forEach items="${listCompany}" var="company">
					
						<option value="${company.id }">${company.name}</option>
						
				</c:forEach>
						
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary">
			or <a href="getComputer?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}" class="btn">Cancel</a>
		</div>
	</form>
</section>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>
<script> $.validate(); </script>

<jsp:include page="include/footer.jsp" />