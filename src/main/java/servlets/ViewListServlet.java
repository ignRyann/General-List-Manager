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

@WebServlet("/viewList.html")
public class ViewListServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieves the model and gets the necessary parameters to view a list for 'viewList.jsp'
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String[] listItems = model.getListItems(listName);
        String[] listLinks = model.getItemLinksInList(listName);

        // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
        request.setAttribute("listName", listName);
        request.setAttribute("listItems", listItems);
        request.setAttribute("listLinks", listLinks);

        // Invokes the 'viewList' JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewList.jsp");
        dispatch.forward(request, response);
    }

}
