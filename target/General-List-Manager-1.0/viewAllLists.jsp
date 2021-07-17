<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="meta.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<section class="bg-dark">
    <div class="container">
        <!-- View All Data Page Button -->
        <div class="row mb-3">
            <div class="col-sm-2 offset-5 ">
                <form action="viewAllData.html">
                    <button type="submit" class="btn btn-light">View All Data</button>
                </form>
            </div>
        </div>

        <div class="row">
            <!-- All Lists Table Division -->
            <div class="col-sm-6">
                <!-- Input to filter table with 'searchKey' -->
                <div class="container mb-4">
                    <input type="text" id="searchKey" class="form-control" onkeyup="filterTable(1,2)" placeholder="Search Bar">
                </div>
                <!-- All Lists Table -->
                <table class="table table-dark text-center" id="dataTable">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>List Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        String[] listNames = (String[]) request.getAttribute("listNames");
                        for (int i = 0; i < listNames.length; i++) {
                    %>
                    <tr>
                        <td><%=i + 1%></td>
                        <td><a href="<%="viewList.html?listName=" + listNames[i]%>"><%=listNames[i]%></a></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <!-- List Editor Division -->
            <div class="col-sm-6 text-center" style="color: white">
                <h2>List Editor</h2>
                <div class="col-sm-10 offset-1">

                    <!-- Form to Add List -->
                    <form action="addList.html" method="get">
                        <label for="addListForm">Add List</label>
                        <input name ="listNameToAdd" type="text" class="form-control" id="addListForm" pattern="^[A-Za-z0-9]*[A-Za-z0-9][A-Za-z0-9 _]*$" placeholder="Enter List Name" required>
                        <small class="form-text">Letters and Numbers Only</small>
                        <button type="submit" class="btn btn-success mt-3">Add</button>
                    </form>
                    <br>
                    <!-- Form to Delete List -->
                    <form action="deleteList.html" method="get">
                        <label for="deleteListForm">Delete List</label>
                        <select name="selectListsToDelete" class="form-control select" id="deleteListForm" multiple required>
                            <%
                                for (String listName : listNames){
                            %>
                            <option value="<%=listName%>"><%=listName%></option>
                            <%
                                }
                            %>>
                        </select>
                        <button type="submit" class="btn btn-danger mt-3">Delete</button>
                    </form>
                    <br>
                    <!-- Form to Change List Name -->
                    <form action="changeListName.html" method="get">
                        <label for="selectedOriginalName">Change List Name</label>
                        <select name="listName" class="form-control" id="selectedOriginalName" required>
                            <%
                                for (String listName : listNames){
                            %>
                            <option value="<%=listName%>"><%=listName%></option>
                            <%
                                }
                            %>>
                        </select>
                        <br>
                        <input name ="newListName" type="text" class="form-control" id="selectedNewListName" pattern="^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$" placeholder="Change List Name To.." required>
                        <small class="form-text">Letters and Numbers only</small>
                        <button type="submit" class="btn btn-primary mt-3">Change</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>
