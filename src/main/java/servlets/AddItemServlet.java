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

@WebServlet("/addItem.html")
public class AddItemServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves the model and gets the necessary parameters to add an item to the list
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String itemName = request.getParameter("itemName");

        // If item has been added -> Go to 'ViewListServlet' and set the necessary attributes for it
        // If item has not been added -> Go to 'errorPage'
        String nextPage;
        if(model.addItemToList(listName, itemName)) {
            request.setAttribute("listName", listName);
            nextPage = "/viewList.html";
        }else{
            request.setAttribute("errorMessage", "An error has occurred when adding an item");
            nextPage = "/errorPage.jsp";
        }

        // Invokes the next page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(nextPage);
        dispatch.forward(request, response);
    }
}