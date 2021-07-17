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

@WebServlet("/viewItem.html")
public class ViewItemServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieves the model and gets the necessary parameters to view an item for 'viewListItem.jsp'
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String itemName = request.getParameter("itemName");

        // Data needed for Item Link
        String itemLink = model.getItemLink(listName, itemName);
        String[] listNames = model.getListNames();
        // Data needed for Item Text
        String itemText = model.getItemText(listName, itemName);
        // Data needed for Item URL
        String itemURL = model.getItemURL(listName, itemName);
        // Data needed for Item File
        String[] itemFile = model.getItemFile(listName, itemName);
        String itemFileName = itemFile[0];
        String itemFilePath = itemFile[1];

        // Adds the data to the request object that will be sent to the Java Server Page, so that it can access the data
        request.setAttribute("listName", listName);
        request.setAttribute("itemName", itemName);
        request.setAttribute("itemLink", itemLink);
        request.setAttribute("listNames", listNames);
        request.setAttribute("itemText", itemText);
        request.setAttribute("itemURL", itemURL);
        request.setAttribute("itemFileName", itemFileName);
        request.setAttribute("itemFilePath", itemFilePath);

        // Invokes the 'viewListItem' JSP file
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewListItem.jsp");
        dispatch.forward(request, response);
    }

    // doPost needed when uploading an image to the item
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Method POST performs same function as Method GET
        doGet(request, response);
    }

}
