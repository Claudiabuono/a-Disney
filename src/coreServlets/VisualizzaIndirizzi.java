package coreServlets;

import coreModels.model.AddressModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/visIndirizzi")
public class VisualizzaIndirizzi extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static AddressModel model;

    static {
        model = new AddressModel();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            coreModels.beans.Registered user = (coreModels.beans.Registered) request.getSession().getAttribute("user");
           // java.util.Map<Integer, coreModels.beans.Adress> ad = (java.util.Map<Integer, coreModels.beans.Adress>) request.getSession().getAttribute("addresses");

            request.getSession().setAttribute("addresses", model.doRetrieveAll(user.getLogin()));

            request.getRequestDispatcher(response.encodeURL("/protected-page.jsp")).forward(request, response);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            response.sendRedirect(response.encodeURL("error.jsp"));
        } catch (Exception e) {
            response.sendRedirect(response.encodeURL("error.jsp"));
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
