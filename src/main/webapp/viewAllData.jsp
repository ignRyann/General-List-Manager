<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.TreeMap" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    TreeMap<String, HashMap<String, String[]>> data = (TreeMap<String, HashMap<String, String[]>>) request.getAttribute("data");
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="meta.jsp"/>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <!-- Display All Data Section -->
    <section class="bg-dark">

        <!-- All Data Division -->
        <div class="container">
            <%
                for (String listName : data.keySet()){
            %>

            <!-- 'listName' Table Division -->
            <div id="<%=listName%>">
                <hr style="color: white;">
                <table class="table table-dark table-bordered table-striped text-center" style="margin-top: 80px;">
                    <thead style="background-color: black;">
                        <tr>
                            <th colspan="5" style="min-width: 200px;"><a href="viewList.html?listName=<%=listName%>"><%=listName%></a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>Item Name</th>
                            <th>Item Link</th>
                            <th>Item Text</th>
                            <th>Item URL</th>
                            <th>Item File</th>
                        </tr>
                        <%
                            for (String itemName : data.get(listName).keySet()){
                        %>
                        <tr>
                            <td><%=itemName%></td>
                            <td><a href="#<%=data.get(listName).get(itemName)[0]%>"><%=data.get(listName).get(itemName)[0]%></a></td>
                            <td><%=data.get(listName).get(itemName)[1]%></td>
                            <td><%=data.get(listName).get(itemName)[2]%></td>
                            <td><%=data.get(listName).get(itemName)[3]%></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <%
                }
            %>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>
</body>
</html>
