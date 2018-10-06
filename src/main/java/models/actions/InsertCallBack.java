package models.actions;

import database.connector.ConnectorDB;
import database.helpers.DatabaseHelper;
import database.helpers.QueryBuilder;
import models.helpers.Neutralizer;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertCallBack implements IInsert {
    private static final String TABLE_NAME = "call_back";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_COMMENT = "comment";

    private static final String PARAM_CALL_NAME = "callMeName";
    private static final String PARAM_CALL_PHONE = "callMePhone";
    private static final String PARAM_CALL_COMMENT = "callMeText";

    /**
     * Метод последовательно обезвреживает каждый переданный параметр от JavaScript иньекций
     * Добавляет все параметры в коллекцию
     * @param req получает параметры переданные из формы
     * @return возвращается коллекция параметров, готовая к построению запроса
     */
    @Override
    public List<String> neutralizeParams(HttpServletRequest req) {
        List<String> paramsList = new ArrayList<String>();
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CALL_NAME)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CALL_PHONE)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CALL_COMMENT)));
        return paramsList;
    }

    /**
     * Метод построения подготовленного запроса, обращается к методу buildInsertPreparedQuery
     * класса QueryBuilder
     * @param params коллекция параметров
     * @return строка готовая к передачи экземпляру класса PreparedStatement
     */
    @Override
    public String buildQuery(List<String> params) {
        return QueryBuilder.buildInsertPreparedQuery(TABLE_NAME,
                new String[] {COLUMN_NAME, COLUMN_PHONE, COLUMN_COMMENT},
                params.toArray(new String[0]));
    }

    /**
     * Метод добавления нового заказа в таблицу базы данных
     * @param query подготовленная строка запрос
     * @param params массив параметров, которые подставляются под аргументы строки-запроса
     */
    @Override
    public void insertToDatabase(String query, String[] params) {
        ConnectorDB connector = ConnectorDB.getInstance();
        DatabaseHelper helper = new DatabaseHelper();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = helper.getPreparedStatement(connector, query);
            for (int i = 1, j = 0; i <= params.length; i++, j++){
                preparedStatement.setString(i, params[j]);
            }
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            helper.closePreparedStatement(preparedStatement);
        }
    }
}
