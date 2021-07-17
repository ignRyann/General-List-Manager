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
import java.util.HashMap;
import java.util.TreeMap;

@WebServlet("/viewAllData.html")
public class ViewAllDataServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieves the model and gets the necessary parameters to view all the data for 'ViewAllData.jsp'
        Model model = ModelFactory.getModel();
        TreeMap<String, HashMap<String, String[]>> data = model.getAllData();

        // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
        request.setAttribute("data", data);

        // Invokes the 'viewAllData' JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewAllData.jsp");
        dispatch.forward(request, response);
    }
}
