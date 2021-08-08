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
                        <td class="product-remove"><button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" data-product="<%=address.getCodice()%>"><span style="color: red;" class="glyphicon glyphicon-trash"></span></button> </td>
                        <td><%=address%></td>
                    </tr>
                    <%}
                    }%>

                    </tbody>
                </table>
</div><br>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Attenzione!</h4>
            </div>
            <div class="modal-body">
                <p>Sei sicuro di voler eliminare l'indirizzo?</p>
            </div>
            <div class="modal-footer">
                <form id="ciao">
                    <input id="id" type="hidden"/>
                    <button id = "save"  form="ciao" type="submit" class="btn btn-primary">Si, elimina indirizzo</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
                </form>
            </div>
        </div>

    </div>
</div>
<script>
    $(document).ready(() => {
        $('#myModal').on('show.bs.modal', function (event) {
            let button = $(event.relatedTarget) // Bottone che triggera la modal
            let prod = button.data('product') // Estrai info dal data-product
            let modal = $(this)

            modal.find(".modal-footer form #id").val(prod)
        })

        $('#myModal .modal-footer form').submit((e) => {
            e.preventDefault()
            let id= $('#id').val()
            let row= $('tbody #' +id)
            $.post("AddressOperations", {operation: "3", code : id})
                .done(function () {
                    row.remove();
                })
                .fail(function () {
                    alert("Non e' stato possibile rimuovere l'indirizzo");
                })
            $('#myModal').modal('hide')
        })

        //scratch this shit let's go with form and use input type hidden solution
        $("#myModal").on("hidden.bs.modal", () => {
            let modal = $(this)
            let btn = modal.find(".modal-footer .btn-primary")
            modal.find("#myModal #id").val('')
        })
    })
</script>

