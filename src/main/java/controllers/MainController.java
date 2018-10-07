package controllers;

import models.actions.ActionFactory;
import models.actions.IInsert;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

@WebServlet("/controller")
public class MainController extends HttpServlet {
    private static final String LOG_MESSAGE_POST = "Получен post-запрос";
    private static final String LOG_MESSAGE_POST_SUCCESS = "Post-запрос успешно обработан";
    private static final Logger log = Logger.getLogger(MainController.class);

    /**
     * Метод обрабатывает post-запросы страницы index.jsp
     * @param req входящие параметры
     * @param resp исходящие параметры
     * @throws ServletException может генерировать необрабатываемое исключение
     * @throws IOException может генерировать необрабатываемое исключение
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(LOG_MESSAGE_POST);
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        ActionFactory action = new ActionFactory();
        IInsert insertCommand = action.getCommand(req);
        if (insertCommand != null){
            List<String> params = insertCommand.neutralizeParams(req);
            String query = insertCommand.buildQuery(params);
            insertCommand.insertToDatabase(query, params.toArray(new String[0]));
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
        log.info(LOG_MESSAGE_POST_SUCCESS);
    }
}
