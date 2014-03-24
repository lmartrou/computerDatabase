<jsp:include page="include/header.jsp" />
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section id="main">
	<h1 id="homeTitle">${count}</h1>
	
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
		
		
		<form action="AddComputer?search=${name}&filterby=${filtrerpar}&orderby=${rangerpar}&page=${page}" method="GET">
			<input type="submit" id="addsubmit" name="addComputer"
				value="addComputer"
				class="btn primary"/>
				</form>
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
   					<a class="btn danger" id="delete" href="DeleteComputer?search=${name}&filterby=${filtrerpar}&orderby=${rangerpar}&page=${page}&id=${computerDto.getId()}">Delete </a>
   					</td>
   					
   					<td>
   					<a class="btn success" id="edit" href="EditComputer?search=${name}&filterby=${filtrerpar}&orderby=${rangerpar}&page=${page}&id=${computerDto.getId()}">Edit</a>
   					</td>
   					
					</tr>
					</c:forEach>
					
			</table>		
	<%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="getComputer?search=${name}&filterby=${filtrerpar}&orderby=${rangerpar}&page=${currentPage - 1}">Previous</a></td>
    </c:if>
 
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPage}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="getComputer?search=${name}&filterby=${filtrerpar}&orderby=${rangerpar}&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
 
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPage}">
        <td><a href="getComputer?search=${name}&filterby=${filtrerpar}&orderby=${rangerpar}&page=${currentPage + 1}">Next</a></td>
    </c:if>
			

		
		
</section>

<jsp:include page="include/footer.jsp" />
