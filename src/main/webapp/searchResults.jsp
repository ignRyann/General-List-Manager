<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String[][] searchResults = (String[][]) request.getAttribute("searchResults");
    String searchQuery = (String) request.getAttribute("searchQuery");
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="meta.jsp" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <!-- Search Results Section -->
    <section class="bg-dark justify-content-center text-center">
        <!-- Search Information Division -->
        <div class="container card mb-4 mt-1" style="width: 40rem;">
            <div class="card-body">
                <h4 class="card-title">Search Results</h4>
                <h6 class="card-subtitle mb-2 text-muted">Search Query: <%=searchQuery%></h6>
                <p class="card-text"><%=searchResults.length%> items were found that contained the search query</p>
            </div>
        </div>

        <!-- Input to filter table with searchKey -->
        <div class="container mb-4">
            <input type="text" id="searchKey" class="form-control" onkeyup="filterTable(0,2)" placeholder="Search Table">
        </div>
        <!-- Search Results Table -->
        <div class="col-sm-10 offset-1 table-responsive table-scrollable-wrapper table-scrollbar table-fixed-header">
            <table class="table table-striped table-dark table-bordered text-center" id="dataTable">
                <thead>
                    <tr>
                        <th style="min-width: 100px;">List Name</th>
                        <th style="min-width: 100px;">Item Name</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        for (String[] searchResult : searchResults) {
                    %>
                    <tr>
                        <td>
                            <a href="<%="viewList.html?listName=" + searchResult[0]%>"><%=searchResult[0]%></a></td>
                        <td>
                            <a href="<%="viewItem.html?listName=" + searchResult[0] + "&itemName=" + searchResult[1]%>"><%=searchResult[1]%></a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </section>

    <jsp:include page="footer.jsp" />
</body>

</html>
