<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%--For displaying Previous link except for the 1st page --%>
    <c:if test="${wrapper.getPage() != 1}">
        <td><a href="DashboardServlet?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage() - 1}">Previous</a></td>
    </c:if>
 
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${wrapper.getNoOfPage()}" var="i">
                <c:choose>
                    <c:when test="${wrapper.getPage() eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="DashboardServlet?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
 
    <%--For displaying Next link --%>
    <c:if test="${wrapper.getPage() lt wrapper.getNoOfPage()}">
        <td><a href="DashboardServlet?search=${wrapper.getFilter()}&filterby=${wrapper.getFilterby()}&orderby=${wrapper.getOrder()}&page=${wrapper.getPage() + 1}">Next</a></td>
    </c:if>
			
