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

@WebServlet("/changeItemName.html")
public class ChangeItemNameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves the model and gets the necessary parameters to change an item's name
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String oldItemName = request.getParameter("itemName");
        String newItemName = request.getParameter("newItemName");

        // If item name has been changed -> Go to 'ViewListServlet' and set the necessary attributes for it
        // If item name has not been changed -> Go to 'errorPage'
        String nextPage;
        if (model.changeItemNameInList(listName, oldItemName, newItemName)){
            // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
            request.setAttribute("listName", listName);
            nextPage ="/viewList.html";
        }else{
            nextPage ="/errorPage.jsp";
        }

        // Invokes the next page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(nextPage);
        dispatch.forward(request, response);
    }
}