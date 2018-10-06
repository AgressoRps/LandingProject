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

public class InsertOrders implements IInsert {
    private static final String TABLE_NAME = "orders";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_PRODUCT_ID = "product_id";

    private static final String PARAM_ID = "idProduct";
    private static final String PARAM_CLIENT_NAME = "clientName";
    private static final String PARAM_CLIENT_PHONE = "clientPhoneNumber";
    private static final String PARAM_CLIENT_COMMENT = "clientMessage";

    /**
     * Метод последовательно обезвреживает каждый переданный параметр от JavaScript иньекций
     * Добавляет все параметры в коллекцию
     * @param req получает параметры переданные из формы
     * @return возвращается коллекция параметров, готовая к построению запроса
     */
    @Override
    public List<String> neutralizeParams(HttpServletRequest req) {
        List<String> paramsList = new ArrayList<String>();
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_NAME)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_PHONE)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_COMMENT)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_ID)));
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
                new String[]{COLUMN_NAME, COLUMN_PHONE, COLUMN_COMMENT, COLUMN_PRODUCT_ID},
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
                if (i == params.length){
                    preparedStatement.setInt(i, Integer.valueOf(params[j]));
                    continue;
                }
                preparedStatement.setString(i, params[j]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            helper.closePreparedStatement(preparedStatement);
        }
    }
}
