package coreServlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


@WebServlet("/catalogo")
public class ConsumerCatalog extends CatalogServlet {
	private static final long serialVersionUID = 1L;

	public void init(javax.servlet.ServletConfig config) throws ServletException {
		super.init(config);

		URL = "Catalogo.jsp";
		numEl = 20;
	}
}
