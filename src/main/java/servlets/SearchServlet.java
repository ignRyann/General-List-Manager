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

@WebServlet("/search.html")
public class SearchServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieves the model and gets the necessary parameters to set up the 'search.jsp' page
        Model model = ModelFactory.getModel();
        String[] listNames = model.getListNames();

        // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
        request.setAttribute("listNames", listNames);

        // Invokes the 'search' JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/search.jsp");
        dispatch.forward(request, response);
    }
}