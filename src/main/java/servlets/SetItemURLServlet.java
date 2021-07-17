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

@WebServlet("/setItemURL.html")
public class SetItemURLServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieves the model and gets the necessary parameters to change an item' url
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String itemName = request.getParameter("itemName");
        String itemURL = request.getParameter("itemURL");
        model.setItemURL(listName, itemName, itemURL);

        // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
        request.setAttribute("listName", listName);
        request.setAttribute("itemName", itemName);

        // Invokes the 'viewItem' servlet
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewItem.html");
        dispatch.forward(request, response);
    }
}
