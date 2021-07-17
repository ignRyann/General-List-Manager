<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String listName = (String) request.getAttribute("listName");
    String[] listItems = (String[]) request.getAttribute("listItems");
    String[] listLinks = (String[]) request.getAttribute("listLinks");
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="meta.jsp" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <!-- View List Section -->
    <section class="bg-dark">
        <div class="container">
            <!-- Add Item to List Division -->
            <div class="row text-center" style="color: white">
                <div class="col-sm-4 offset-4">
                    <!-- Form to Add Item -->
                    <form action="addItem.html" method="get">
                        <label for="addItemForm2">Add Item</label>
                        <input type="hidden" name="listName" value="<%=listName%>">
                        <div class="form-group">
                            <input type="text" name ="itemName" class="form-control" id="addItemForm2" pattern="^[A-Za-z0-9]*[A-Za-z0-9][A-Za-z0-9 _]*$" placeholder="Enter Item Name" required>
                            <small class="form-text">Letters and Numbers only</small>
                        </div>
                        <button type="submit" class="btn btn-success">Add</button>
                    </form>
                </div>
            </div>

            <br>

            <!-- Display List Items Division-->
            <div class="row">
                <!-- Input to filter table with 'searchKey' -->
                <div class="container text-center mb-2">
                    <input type="text" id="searchKey" class="form-control" onkeyup="filterTable(1,2)" placeholder="Search Bar">
                </div>
                <!-- All Items Table -->
                <table class="table table-dark text-center" id="dataTable">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th><%=listName%> Items</th>
                            <th>View Item</th>
                            <th>Remove Item</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < listItems.length; i++) {
                        %>
                        <tr>
                            <!-- Item Name and the link it is linked to -->
                            <td><%=i + 1%></td>
                            <%
                                if (listLinks[i].equals("")){
                            %>
                            <td><%=listItems[i]%></td>
                            <%
                                }else{
                            %>
                            <td>
                                <a href="<%="viewList.html?listName=" + listLinks[i]%>"><%=listItems[i]%></a>
                            </td>
                            <% } %>

                            <!-- View Item Button -->
                            <td>
                                <form action="viewItem.html" method="post">
                                    <input type="hidden" name="listName" value="<%=listName%>">
                                    <button type="submit" class="btn btn-light" name="itemName" value="<%=listItems[i]%>">View</button>
                                </form>
                            </td>

                            <!--Delete Item Button -->
                            <td>
                                <form action="deleteItem.html" method="get">
                                    <input type="hidden" name="listName" value="<%=listName%>">
                                    <button type="submit" class="btn btn-danger" name="itemToDelete" value="<%=listItems[i]%>">Delete</button>
                                </form>
                            </td>

                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp" />
</body>

</html>
