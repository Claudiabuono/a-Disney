<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = " coreModels.beans.ProductBean, coreModels.beans.RecensioneBean"%>

<% 
	Double obj = (Double) request.getAttribute ("voto");
	java.util.List<RecensioneBean> recenzioni = (java.util.List<RecensioneBean>) request.getAttribute("recenzioni");
	RecensioneBean userCommentate = (RecensioneBean) request.getAttribute ("userComment");
	int vote = obj == null ? 0 : obj.intValue() > 5 ? 5 : obj.intValue();
	
	if ((recenzioni == null ? false : recenzioni.size() != 0) || (userCommentate == null ? false : true)) {
	
		for (int owo = 0;owo < 5; owo++) {
			if (vote > 0) {%> 
			<span class="fa fa-star checked"></span>
			<%
			vote--;
			} else {
			%> 
				<span class="fa fa-star"></span>
			<%
			}
		}
	}
%>
<hr style="border:3px solid #f1f1f1">

