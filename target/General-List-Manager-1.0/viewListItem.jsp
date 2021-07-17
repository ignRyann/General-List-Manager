<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String listName = (String) request.getAttribute("listName");
    String itemName = (String) request.getAttribute("itemName");
    String itemLink = (String) request.getAttribute("itemLink");
    String itemText = (String) request.getAttribute("itemText");
    String itemFileName = (String) request.getAttribute("itemFileName");
    String itemFilePath = (String) request.getAttribute("itemFilePath");
    String itemURL = (String) request.getAttribute("itemURL");
    String[] listNames = (String[]) request.getAttribute("listNames");
%>
<html>
<head>
    <jsp:include page="meta.jsp" />
</head>
<body>
<jsp:include page="header.jsp" />

    <!-- View Item Section -->
    <section class="bg-dark">

        <!-- Go back to item's list button -->
        <div class="row text-center justify-content-center mb-5">
            <form action="viewList.html" method="get">
                <input type="hidden" name="listName" value="<%=listName%>">
                <button type="submit" class="btn btn-light">Go Back To List: <%=listName%></button>
            </form>
        </div>

        <!-- Table to display item's data -->
        <div class="row table-responsive">
            <div class="col-sm-10 offset-1">
                <table class="table table-dark text-center" id="itemTable">
                    <thead>
                        <tr>
                            <td style="min-width: 120px;">Type</td>
                            <td style="min-width: 300px;">Data</td>
                            <td style="min-width: 500px;">Edit</td>
                            <td style="min-width: 120px;">Remove Item</td>
                        </tr>
                    </thead>

                    <tbody>
                        <!-- Item Name -->
                        <tr>
                            <td>Item Name</td>
                            <td><%=itemName%></td>
                            <!-- Change Item Name -->
                            <td>
                                <div class="col-sm-10 offset-1">
                                    <form action="changeItemName.html" method="post">
                                        <input type="hidden" name="listName" value="<%=listName%>">
                                        <div class="input-group">
                                            <input type="text" class="form-control" name ="newItemName" pattern="^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$" placeholder="New Item Name" required>
                                            <button type="submit" class="input-group-text" name="itemName" value="<%=itemName%>">Submit</button>
                                        </div>
                                    </form>
                                </div>
                            </td>
                            <!-- Delete Item -->
                            <td>
                                <form action="deleteItem.html" method="get">
                                    <input type="hidden" name="listName" value="<%=listName%>">
                                    <button type="submit" class="btn btn-danger" name="itemToDelete" value="<%=itemName%>">Delete</button>
                                </form>
                            </td>
                        </tr>

                        <!-- Item Link -->
                        <tr>
                            <td>Item Link</td>
                            <%
                                if (itemLink.equals(listName)){
                            %>
                            <td></td>
                            <%
                                }else{
                            %>
                            <td><a href="viewList.html?listName=<%=itemLink%>"><%=itemLink%></a></td>
                            <%
                                }
                            %>
                            <!-- Link Item to another List -->
                            <td>
                                <div class="col-sm-10 offset-1">
                                    <form action="setItemLink.html" method="post">
                                        <input type="hidden" name="listName" value="<%=listName%>">
                                        <input type="hidden" name="itemName" value="<%=itemName%>">
                                        <div class="input-group">
                                            <select name="itemLinkedList" class="form-control"  required>
                                                <%
                                                    for (String currentListName : listNames){
                                                        if (!currentListName.equals(listName)){
                                                %>
                                                <option value="<%=currentListName%>"><%=currentListName%></option>
                                                <%
                                                        }
                                                    }
                                                %>>
                                            </select>
                                            <button type="submit" class="input-group-text" name="itemName" value="<%=itemName%>">Submit</button>
                                        </div>
                                    </form>
                                </div>
                            </td>
                            <!-- Link item back to its own list -->
                            <td>
                                <form action="deleteItemObject.html" method="post">
                                    <input type="hidden" name="listName" value="<%=listName%>">
                                    <input type="hidden" name="itemName" value="<%=itemName%>">
                                    <button type="submit" class="btn btn-danger" name="itemObjectType" value="itemLink">Reset</button>
                                </form>
                            </td>
                        </tr>

                        <!-- Item Text -->
                        <tr>
                            <td>Item Text</td>
                            <td><%=itemText%></td>
                            <!-- Set Item Text -->
                            <td>
                                <div class="col-sm-10 offset-1">
                                    <form action="setItemText.html" method="post">
                                        <input type="hidden" name="listName" value="<%=listName%>">
                                        <div class="input-group">
                                            <textarea class="form-control" name ="itemText" placeholder="Add text.." maxlength="1000" required></textarea>
                                            <button type="submit" class="input-group-text" name="itemName" value="<%=itemName%>">Submit</button>
                                        </div>
                                        <small class="form-text d-flex">1000 Characters maximum.</small>
                                    </form>
                                </div>
                            </td>
                            <!-- Clear Item Text -->
                            <td>
                                <form action="deleteItemObject.html" method="post">
                                    <input type="hidden" name="listName" value="<%=listName%>">
                                    <input type="hidden" name="itemName" value="<%=itemName%>">
                                    <button type="submit" class="btn btn-danger" name="itemObjectType" value="itemText">Clear</button>
                                </form>
                            </td>
                        </tr>

                        <!-- Item URL -->
                        <tr>
                            <td>Item URL</td>
                            <td><a href="<%=itemURL%>" target="_blank"><%=itemURL%></a></td>
                            <!-- Set Item URL -->
                            <td>
                                <div class="col-sm-10 offset-1">
                                    <form action="setItemURL.html" method="post">
                                        <input type="hidden" name="listName" value="<%=listName%>">
                                        <div class="input-group">
                                            <input type="url" class="form-control" name ="itemURL" placeholder="https://" required>
                                            <button type="submit" class="input-group-text" name="itemName" value="<%=itemName%>">Submit</button>
                                        </div>
                                    </form>
                                </div>
                            </td>
                            <!-- Clear Item URL -->
                            <td>
                                <form action="deleteItemObject.html" method="post">
                                    <input type="hidden" name="listName" value="<%=listName%>">
                                    <input type="hidden" name="itemName" value="<%=itemName%>">
                                    <button type="submit" class="btn btn-danger" name="itemObjectType" value="itemURL">Clear</button>
                                </form>
                            </td>
                        </tr>

                        <!-- Item File -->
                        <tr>
                            <td>Item File</td>
                            <td><a href="<%=itemFilePath%>" target="_blank"><%=itemFileName%></a></td>
                            <!-- Set Item File -->
                            <td>
                                <div class="col-sm-10 offset-1">
                                    <form action="setItemFile.html" method="post" enctype="multipart/form-data">
                                        <div class="input-group">
                                            <input type="hidden" name="listName" value="<%=listName%>">
                                            <input type="file" class="form-control" name="file" required>
                                            <button type="submit" class="input-group-text" name="itemName" value="<%=itemName%>">Submit</button>
                                        </div>
                                    </form>
                                </div>
                            </td>
                            <!-- Delete Item File -->
                            <td>
                                <form action="deleteItemObject.html" method="post">
                                    <input type="hidden" name="listName" value="<%=listName%>">
                                    <input type="hidden" name="itemName" value="<%=itemName%>">
                                    <button type="submit" class="btn btn-danger" name="itemObjectType" value="itemFile">Clear</button>
                                </form>
                            </td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>

    </section>

<jsp:include page="footer.jsp" />
</body>


</html>
