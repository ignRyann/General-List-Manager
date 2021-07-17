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

@WebServlet("/deleteItem.html")
public class DeleteItemServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves the model and gets the necessary parameters to delete an item
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String itemToDelete = request.getParameter("itemToDelete");
        model.deleteItemFromList(listName, itemToDelete);

        // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
        request.setAttribute("listName", listName);

        // Invokes the next page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewList.html");
        dispatch.forward(request, response);
    }
}
