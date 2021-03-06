package coreServlets;

import com.google.gson.Gson;
import coreModels.beans.Address;
import coreModels.model.AddressModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AddressOperations
 */
@WebServlet("/AddressOperations")
public class AddressOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;


	static AddressModel model;
	
	static {
		model = new AddressModel();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int op = Integer.parseInt(request.getParameter("operation"));
			coreModels.beans.Registered user = (coreModels.beans.Registered) request.getSession().getAttribute("user");
			java.util.Map<Integer, Address> ad = (java.util.Map<Integer, Address>)request.getSession().getAttribute("addresses");
			if (op == 0 && user != null) {
				//operazione di visualizzazione
				request.getSession().setAttribute("addresses", model.doRetrieveAll(user.getLogin()));
			} else if (op == 1 && user != null) {
				//operazione di inserimento
				String cap= request.getParameter("cap");
				String citta=request.getParameter("citta");
				String stato= request.getParameter("stato");
				String ncv= request.getParameter("ncv");
				String provincia= request.getParameter("provincia");
				String via= request.getParameter("via");
				if(cap.length()<5 || citta.contains("[0-9]+") || stato.contains("[0-9]+") || provincia.contains("[0-9]+") || via.contains("[0-9]+")){
					response.sendRedirect(response.encodeURL("error.jsp"));
				}

				Address bean = new Address();
				bean.setCAP(Integer.parseInt(cap));
				bean.setCitta(citta);
				bean.setNation(stato);
				bean.setnCv(Integer.parseInt(ncv));
				bean.setProvince(provincia);
				bean.setStreet(via);
				bean.setCodice(model.doSave(bean, user));

				if (ad != null) {
					ad.put(bean.getCodice(), bean);
					request.getSession().setAttribute("addresses", ad);
				}
				if (request.getHeader("x-requested-with") != null) {
					response.setContentType("application/json");
					response.getWriter().write(new Gson().toJson(bean));
				}
			} else if (op == 3 && user != null) {
				model.doDelete(Integer.parseInt(request.getParameter("code")));
			} 
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			response.sendRedirect(response.encodeURL("error.jsp"));
		}
		catch (Exception e) {
			response.sendRedirect(response.encodeURL("error.jsp"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
