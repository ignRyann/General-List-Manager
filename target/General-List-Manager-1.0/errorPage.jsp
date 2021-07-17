<!DOCTYPE html>
<html lang="en">

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<head>
    <jsp:include page="/meta.jsp"/>
</head>

<body>
<jsp:include page="/header.jsp"/>
<section class="bg-dark">
    <div class="container card mb-4 mt-1" style="width: 70vw;">
        <div class="card-body text-center">
            <h4 class="card-title" style="color: #f10000;">ERROR</h4>
            <h6 class="card-subtitle mb-2 text-muted">An error has occurred.</h6>
            <p class="card-text"><%=errorMessage%></p>
        </div>
    </div>

</section><!-- End Hero -->
<jsp:include page="/footer.jsp"/>
</body>
</html>