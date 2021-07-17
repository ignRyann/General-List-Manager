<%
    String[] listNames = (String[]) request.getAttribute("listNames");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/meta.jsp"/>
</head>

<body>
    <jsp:include page="/header.jsp"/>

    <!-- Search Section -->
    <section class="align-items-center justify-content-center bg-dark" style="color: white">
        <div class="container">
            <form action="searchItem.html" method="get">
                <!-- Input for Search Query --->
                <div class="input-group">
                    <input type="text" class="form-control"  name="searchQuery" placeholder="Item Name" required>
                    <button type="submit" class="input-group-text">Submit</button>
                </div>
                <small class="form-text">Searching for an item is case-insensitive</small>
                <hr class="mb-3">

                <!-- Input to filter table with 'searchKey' -->
                <div class="container text-center mb-4">
                    <input type="text" id="searchKey" class="form-control" onkeyup="filterTable(1,2)" placeholder="Search Table">
                </div>

                <!-- 'Selected Lists to Search' Table -->
                <div class="table-scrollable-wrapper table-scrollbar table-fixed-header">
                    <table class="table table-striped table-dark table-bordered text-center" id="dataTable">
                        <thead>
                            <tr>
                                <th style="max-width: 20px">Checkbox</th>
                                <th>List Name</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            for (String listName : listNames){
                        %>
                        <tr>
                            <td><input type="checkbox" name="listNames" value="<%=listName%>" id="<%=listName%>" checked></td>
                            <td><label for="<%=listName%>"><%=listName%></label></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </section>

    <jsp:include page="/footer.jsp"/>
</body>

</html>