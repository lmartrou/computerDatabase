<jsp:include page="include/header.jsp" />
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section id="main">

	<h1>Edit Computer</h1>
	
	<form action="EditComputer?search=${search}&filterby=${filterby}&orderby=${orderby}&page=${page}&id=${computer.getId()}" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input value="${computer.getName()}" data-validation="required" type="text" name="name" />
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input value="${computer.getIntroduced()}" data-validation="date" data-validation-optional="true" type="text" name="introducedDate" />
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input value="${computer.getDiscontinued()}" data-validation="date" data-validation-optional="true" type="text" name="discontinuedDate"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
					
					<option value="${computer.getCompany()}">${computer.getCompanyName()}</option>
					<c:forEach items="${listCompany}" var="company">
					<c:if test="${company.id != computer.getCompany()}">
						<option value="${company.id }">${company.name}</option>
						</c:if>
				</c:forEach>
						
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Edit" class="btn primary">
			or <a href="getComputer?search=${search}&filterby=${filterby}&orderby=${orderby}&page=${page}" class="btn">Cancel</a>
		</div>
	</form>
</section>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>
<script> $.validate(); </script>

<jsp:include page="include/footer.jsp" />