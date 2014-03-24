<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
			
