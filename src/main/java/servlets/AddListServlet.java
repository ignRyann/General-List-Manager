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

@WebServlet("/addList.html")
public class AddListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves the model and gets the necessary parameters to add a list
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listNameToAdd");

        // If list has been added -> Go to 'ViewAllListServlet' and set the necessary attributes for it
        // If list has not been added -> Go to 'errorPage'
        String nextPage;
        if (model.addList(listName)) {
            nextPage = "/viewAllLists.html";
        }else{
            nextPage = "/errorPage.jsp";
            request.setAttribute("errorMessage", "An error has occurred when adding the list: " + listName);
        }

        // Invokes the next page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(nextPage);
        dispatch.forward(request, response);
    }
}