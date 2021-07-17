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

@WebServlet("/changeListName.html")
public class ChangeListNameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves the model and gets the necessary parameters to change a list's name
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String newListName = request.getParameter("newListName");

        // If list name has been changed -> Go to 'ViewAllListsServlet'
        // If list name has not been changed -> Go to 'errorPage'
        String nextPage;
        if (model.changeListName(listName, newListName)){
            nextPage = "/viewAllLists.jsp";
        }else{
            request.setAttribute("errorMessage", "There was an error when changing " + listName + " to " + newListName);
            nextPage = "/errorPage.jsp";
        }

        // Invokes the next page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(nextPage);
        dispatch.forward(request, response);
    }
}