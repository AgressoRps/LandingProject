package controllers;

import models.ActionFactory;
import models.IInsert;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/controller")
public class MainController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        ActionFactory action = new ActionFactory();
        IInsert insertCommand = action.getCommand(req);
        if (insertCommand != null){
            List<String> params = insertCommand.neutralizeParams(req);
            String query = insertCommand.buildQuery(params);
            insertCommand.insertToDatabase(query, params.toArray(new String[params.size()]));
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
