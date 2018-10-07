package models.actions;

import database.connector.ConnectorDB;
import database.helpers.DatabaseHelper;
import database.helpers.QueryBuilder;
import models.data.Product;
import models.data.ProductsBank;
import models.helpers.Neutralizer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertOrders implements IInsert {
    private static final String LOG_MESSAGE_NEUTRALIZE = "Нейтрализация инъекций";
    private static final String LOG_MESSAGE_SEND = "Отправка письма на почту";
    private static final String LOG_MESSAGE_BUILD = "Построение строки запроса";
    private static final String LOG_MESSAGE_INSERT = "Добавление обратного звонка в базу данных";
    private static final String LOG_MESSAGE_ERROR_INSERT = "Ошибка добавления в базу данных";

    private static final String TABLE_NAME = "orders";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_PRODUCT_ID = "product_id";

    private static final String PARAM_ID = "idProduct";
    private static final String PARAM_CLIENT_NAME = "clientName";
    private static final String PARAM_CLIENT_PHONE = "clientPhoneNumber";
    private static final String PARAM_CLIENT_COMMENT = "clientMessage";

    private static final String SEND_THEME = "Клиент желает приобрести товар";
    private static final String SEND_EMAIL = "AgressoRj@gmail.com";
    private static final String SEND_ITEM = "Клиента интересует ";

    private static final Logger log = Logger.getLogger(InsertOrders.class);

    /**
     * Метод последовательно обезвреживает каждый переданный параметр от JavaScript иньекций
     * Добавляет все параметры в коллекцию
     * Вызывает метод отправки письма с данными о заказе владельцу сайта
     * @param req получает параметры переданные из формы
     * @return возвращается коллекция параметров, готовая к построению запроса
     */
    @Override
    public List<String> neutralizeParams(HttpServletRequest req) {
        log.info(LOG_MESSAGE_NEUTRALIZE);
        List<String> paramsList = new ArrayList<String>();
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_NAME)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_PHONE)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_COMMENT)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_ID)));
        sendEmail(paramsList);
        return paramsList;
    }

    /**
     * Метод отправки письма с подробной информацией о заказе
     * @param params параметры из формы
     */
    private void sendEmail(List<String> params){
        log.info(LOG_MESSAGE_SEND);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < params.size(); i++){
            if (i == params.size() - 1){
                ProductsBank productsBank = new ProductsBank();
                List<Product> products = productsBank.getProducts();
                builder.append(SEND_ITEM);
                builder.append(products.get(Integer.valueOf(params.get(i)) - 1).getName());
                builder.append(" id: ".concat(params.get(i)));
            }else {
                builder.append(params.get(i).concat("\n"));
            }
        }
        Sender sslSender = new Sender();
        sslSender.send(SEND_THEME, builder.toString(), SEND_EMAIL);
    }

    /**
     * Метод построения подготовленного запроса, обращается к методу buildInsertPreparedQuery
     * класса QueryBuilder
     * @param params коллекция параметров
     * @return строка готовая к передачи экземпляру класса PreparedStatement
     */
    @Override
    public String buildQuery(List<String> params) {
        log.info(LOG_MESSAGE_BUILD);
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
            log.info(LOG_MESSAGE_INSERT);
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
            log.error(LOG_MESSAGE_ERROR_INSERT);
            e.printStackTrace();
        }finally {
            helper.closePreparedStatement(preparedStatement);
        }
    }
}
