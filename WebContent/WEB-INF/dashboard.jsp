<jsp:include page="include/header.jsp" />
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="page" %>

<section id="main">
	<h1 id="homeTitle">${wrapper.getCount()}</h1>
	
	<div id="actions">
		<form action="getComputer" method="POST">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			
					<select name="filterby">

						<option value="computer">computer</option>
						<option value="company">company</option>
					</select>
		
		
	
					<select name="orderby">

						<option value="computer.id">identity</option>
						<option value="computer.name">name</option>
						<option value="computer.introduced">introduced</option>
						<option value="computer.discontinued">discontinued</option>
						<option value="computer.company_id">company</option>
					</select>
					
				<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
				
		</form>
		
		
		<a class="btn success" id="add" href="AddComputer?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}" >add</a>
		
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
   					<td>
   					<c:out value="${computerDto.getName()}"/> 
   					</td>
   					
   					<td>
   					<c:out value="${computerDto.getIntroduced()}"/> 
   					</td>
   					
   					<td>
   					<c:out value="${computerDto.getDiscontinued()}"/> 
   					</td>
   					
   					<td>
   					<c:out value="${computerDto.getCompanyName()}"/> 
   					</td>
   					
   					<td>
   					<a class="btn danger" id="delete" href="DeleteComputer?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}&id=${computerDto.getId()}">Delete </a>
   					</td>
   					
   					<td>
   					<a class="btn success" id="edit" href="EditComputer?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage()}&computerId=${computerDto.getId()}&computerName=${computerDto.getName()}&computerIntroduced=${computerDto.getIntroduced()}&computerDiscontinued=${computerDto.getDiscontinued()}&companyName=${computerDto.getCompanyName()}&computerCompany=${computerDto.getCompany()}">Edit</a>
   					</td>
   					
					</tr>
					</c:forEach>
					
			</table>		
	
		
	<page:pagination />	
</section>

<jsp:include page="include/footer.jsp" />
