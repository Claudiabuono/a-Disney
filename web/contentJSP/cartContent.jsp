<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "coreModels.beans.*, java.util.Collection" %>

<section class="ftco-section ftco-cart">
<div class="row ">
	<div class="card">
		    <div class="cart-list table-responsive">
				<table class="table">
				<thead class="thead-primary">
					<tr class="text-center">
						<th>&nbsp;</th>
						<th>&nbsp;</th>
						<th>Prodotto</th>
						<th>Prezzo</th>
						<th>Prezzo con Iva</th>
						<th>Sconto</th>
						<th>Quantita</th>
						<th>Totale</th>
					</tr>
				</thead>
				<tbody>
<!--  -->
<%
Cart cart = (Cart) session.getAttribute("cart");

if (cart == null) {
	%><tr><td style ="text-align: center"  colspan = "7">no Data</td></tr><% 
} else if (cart.size() == 0) {
	%><tr><td style ="text-align: center" colspan = "7">no Data</td></tr><% 
} else {
	Collection<Order> list = cart.getOrders();
	java.util.Iterator<Order> it = list.iterator();
	BigDecimal cartTotal = cart.getTotal();
	BigDecimal cartIva = cart.getTotalIva();
	BigDecimal cartPrice = cart.getTotalWithoutIva();

	while (it.hasNext()) {
		Order o = it.next();
		ProductBean bean = o.getProduct();
		int orderedQty = o.getQty();
		String name = bean.getName();
		String priceUnit = "" + bean.getPriceSenzaIva();
		String priceWithIva = "" + bean.getPricewithIva();
		String totalProduct = "" + o.getTotal();
		int code = bean.getCode();
		int qtyMax = bean.getQty();
		String urlImage = bean.getPhoto();
	
	%>
					<tr id="<%=code%>" class="text-center">
						<td class="product-remove"><button class="removeX" style="background-image: url('/images/x.png')"></button>
						<td class="image-prod"><div id="img" style="background-image: url('<%=urlImage%>');"></div> </td>
						<td class="product-name"><%=name%></td>
						<td><%=priceUnit%></td>
						<td><%=priceWithIva%></td>
						<td><%=bean.getDiscount()%>&#37;</td>
						<td class="quantity"><div class="input-group mb-3"><input type="number" name="quantity" class="quantity form-control input-number" value="<%=orderedQty%>" min="1" max="<%=qtyMax%>"></div></td>
						<td class="total"><%=totalProduct%></td>
					</tr>
			<%
	}
} 
%>
				</tbody>
				</table>
			</div>
	</div>
  </div>
</section>
