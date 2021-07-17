package servlets;

import model.Model;
import model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/searchItem.html")
public class SearchItemServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieves the model and gets the necessary parameters to search for an item
        Model model = ModelFactory.getModel();
        String[] listNames = request.getParameterValues("listNames");
        String searchQuery = Objects.requireNonNullElse(request.getParameter("searchQuery"), "");
        String[][] searchResults = model.searchFor(searchQuery, listNames);

        // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
        request.setAttribute("searchResults", searchResults);
        request.setAttribute("searchQuery", searchQuery);

        // Invokes the 'searchResult' JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/searchResults.jsp");
        dispatch.forward(request, response);
    }
}
