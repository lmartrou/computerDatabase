<jsp:include page="include/header.jsp" />
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="page"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section id="main">
Language : <a href="?lang=en">English</a>|<a href="?lang=fr">Français</a>

	<h1 id="homeTitle">${wrapper.getCount()} <spring:message code="page.title.dashboard" text="default text" /></h1>

	<div id="actions">
		<form action="dashboard" method="GET">
			<input type="search" id="searchbox" name="search"
				value="${wrapper.getFilter()}" placeholder="Search name">
				 <select
				name="filterby">

				<option value="${wrapper.getFilterby()}">${wrapper.getFilterby()}</option>
				<option value="computer">computer</option>
				<option value="company">company</option>

			</select> <select name="orderby">
			
 				<option value="${wrapper.getOrder()}">${wrapper.getOrder()}</option>
				<option value="name"><spring:message code="label.name" text="default text" /></option>
				<option value="introduced"><spring:message code="label.introduced" text="default text" /></option>
				<option value="discontinued"><spring:message code="label.discontinued" text="default text" /></option>
				<option value="company"><spring:message code="label.company" text="default text" /></option>
			</select> <input type="submit" id="searchsubmit" value="<spring:message code="button.filter.title" text="default text" />"
				class="btn primary">

		</form>


		<a class="btn success" id="add"
			href="getCompany?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}"><spring:message code="button.add.title" text="default text" /></a>

	</div>

	<table border="1" cellpadding="5" cellspacing="5">

		<tr>
			<!-- Variable declarations for passing labels as parameters -->
			<!-- Table header for Computer Name -->
			<th>Computer Name</th>
			<th>Introduced Date</th>
			<!-- Table header for Discontinued Date -->
			<th>Discontinued Date</th>
			<!-- Table header for Company -->
			<th>Company</th>
			<!-- Table header for Delete -->
			<th>Delete</th>
			<!-- Table header for Edit -->
			<th>Edit</th>
		</tr>


		<c:forEach items="${listComputer}" var="computerDto">
			<tr>
				<td><c:out value="${computerDto.getName()}" /></td>

				<td><c:out value="${computerDto.getIntroduced()}" /></td>

				<td><c:out value="${computerDto.getDiscontinued()}" /></td>

				<td><c:out value="${computerDto.getCompanyName()}" /></td>

				<td><a class="btn danger" id="delete"
					href="deleteComputer?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}&id=${computerDto.getId()}"><spring:message code="button.delete.title" text="default text" />
				</a></td>

				<td><a class="btn success" id="edit"
					href="getCompany?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}&id=${computerDto.getId()}&computerName=${computerDto.getName()}&computerIntroduced=${computerDto.getIntroduced()}&computerDiscontinued=${computerDto.getDiscontinued()}&companyName=${computerDto.getCompanyName()}&computerCompany=${computerDto.getCompany()}"><spring:message code="button.edit.title" text="default text" /></a>
				</td>

			</tr>
		</c:forEach>

	</table>


	<page:pagination />
</section>

<jsp:include page="include/footer.jsp" />
