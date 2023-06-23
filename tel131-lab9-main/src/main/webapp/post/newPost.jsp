<%@page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.tel131lab9.bean.Post" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="userSession" scope="session" type="pe.edu.pucp.tel131lab9.dto.EmployeeDto"
             class="pe.edu.pucp.tel131lab9.dto.EmployeeDto"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lista empleados</title>
    <jsp:include page="../includes/headCss.jsp"></jsp:include>
</head>
<body>
<div class='container'>
    <jsp:include page="../includes/navbar.jsp">
        <jsp:param name="currentPage" value="newPost"/>
    </jsp:include>

    <form method="POST" action="<%=request.getContextPath()%>/PostServlet?action=new">

        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" class="form-control" name="title">
        </div>
        <div class="mb-3">
            <label class="form-label">Comment</label>
            <input type="text" class="form-control" name="content">
        </div>
        <input type="hidden" class="form-control" name="employeeid" value="<%=userSession.getEmployeeId()%>">

        <a href="<%= request.getContextPath()%>/JobServlet" class="btn btn-danger">Cancelar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>


    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
