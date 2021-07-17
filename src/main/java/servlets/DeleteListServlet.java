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
import java.util.Arrays;

@WebServlet("/deleteList.html")
public class DeleteListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves the model and gets the necessary parameters to delete a list
        Model model = ModelFactory.getModel();
        String[] listNamesToDelete = request.getParameterValues("selectListsToDelete");

        // If the lists have been deleted -> Go to 'ViewAllListsServlet'
        // If any of the lists were not able to be deleted -> Go to 'errorPage'
        String nextPage;
        if(model.deleteLists(listNamesToDelete)) {
            nextPage = "/viewAllLists.html";
        }else{
            request.setAttribute("errorMessage", "Error deleting list: " + Arrays.toString(listNamesToDelete));
            nextPage = "/errorPage.jsp";
        }

        // Invokes the next page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(nextPage);
        dispatch.forward(request, response);
    }
}
