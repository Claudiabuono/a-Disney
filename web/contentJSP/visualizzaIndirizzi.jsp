<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%

    java.util.Map<Integer, coreModels.beans.Adress> map = (java.util.Map<Integer, coreModels.beans.Adress>) session.getAttribute("addresses");
    java.util.List <coreModels.beans.Adress> addresses = map == null ? new java.util.ArrayList<coreModels.beans.Adress>() : new java.util.ArrayList (map.values());


%>

<div id="visualizzaIndirizzi">
                <table id="myIndirizzi">
                    <thead class="thead-primary">
                    <tr class="text-center">
                        <th style="padding-left: 5px;padding-top: 10px;padding-bottom: 10px;text-align: left;background-color: #1E90FF; color: white;"> </th>
                        <th style="padding-left: 5px;padding-top: 10px;padding-bottom: 10px;text-align: left;background-color: #1E90FF; color: white;">Indirizzi</th>
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
                    <tr id=<%=address.getCodice()%>>
                        <td class="product-remove"><button class="removeIndirizzoX" ><span style="color: red;" class="glyphicon glyphicon-trash"></span></button> </td>
                        <td><%=address%></td>
                    </tr>
                    <%}
                    }%>

                    </tbody>
                </table>
</div><br>

<script>
    $(".removeIndirizzoX").click (() => {removeIndirizzo()});

    function removeIndirizzo () {
        var row = $(this).parents().filter("tr");
        var code = $(row).attr("id");
        console.log(row);
        $.post("AddressOperations", {operation: "3", id : code})
            .done(function () {
                $(row).remove();
            })
            .fail(function () {
                alert("Non e' stato possibile rimuovere l'indirizzo");
            })
    }
</script>


