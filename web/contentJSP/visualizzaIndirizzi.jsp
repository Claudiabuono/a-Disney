<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%
    java.util.List<Adress> list = (List<Adress>) request.getAttribute("list");

    java.util.Map<Integer, coreModels.beans.Adress> map = (java.util.Map<Integer, coreModels.beans.Adress>) session.getAttribute("addresses");
    java.util.List <coreModels.beans.Adress> addresses = map == null ? new java.util.ArrayList<coreModels.beans.Adress>() : new java.util.ArrayList (map.values());


%>

<div id="visualizzaIndirizzi">
                <table class="table">
                    <thead class="thead-primary">
                    <tr class="text-center">

                        <th> </th>
                        <th>Via</th>

                    </tr>
                    </thead>
                    <tbody>
                    <%	if (map == null) {
                    %>
                    <tr>
                        <td colspan = "8" style = "text-align: center">non ci sono indirizzi</td>
                    </tr>
                    <%
                    } else {
                        for(Adress address : addresses){%>

                        <td> </td>
                        <td><%=address%></td>
                    <%}
                    }%>
                    </tbody>
                </table>
</div><br>


