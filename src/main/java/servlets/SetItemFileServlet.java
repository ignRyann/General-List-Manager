package servlets;

import model.Model;
import model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

@WebServlet("/setItemFile.html")
@MultipartConfig
public class SetItemFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieves the model and gets the necessary parameters to set an item's file
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String itemName = request.getParameter("itemName");
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();


        // If item file has been set -> Go to 'ViewItemServlet'
        // If an error has occurred -> Go to 'errorPage'
        String nextPage;
        if (model.setItemFile(listName, itemName, fileName, filePart)) {
            // Redirects to the ViewListServlet
            request.setAttribute("listName", listName);
            request.setAttribute("itemName", itemName);
            nextPage = "/viewItem.html";
        } else {
            nextPage = "/errorPage.jsp";
        }

        // Invokes the next page
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(nextPage);
        dispatch.forward(request, response);
    }
}