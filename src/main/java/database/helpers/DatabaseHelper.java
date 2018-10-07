package database.helpers;

import database.connector.ConnectorDB;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper implements IHelper {
    private static final String LOG_MESSAGE_PREPARED_STATEMENT = "Передача экземпляра PreparedStatement";
    private static final String LOG_MESSAGE_STATEMENT = "Передача экземпляра Statement";
    private static final String LOG_MESSAGE_RESULT_SET = "Передача экземпляра ResultSet";
    private static final String LOG_MESSAGE_CLOSED_PREPARED = "Закрытие PreparedStatement";
    private static final String LOG_MESSAGE_CLOSED_STATEMENT = "Закрытие Statement";
    private static final String LOG_MESSAGE_CLOSED_RESULT = "Закрытие ResultSet";
    private static final String LOG_MESSAGE_ERROR_CLOSED = "Ошибка закрытия";

    private static final Logger log = Logger.getLogger(DatabaseHelper.class);

    /**
     * Метод получения подготовленного запроса к базе данных
     * @param connector экземпляр класса ConnectorDB, получает соединение с базой данных
     * @param query подготовленная строка запроса к базе данных, передается экземпляру prepareStatement
     * @return возвращается экземпляр PrepareStatement готовый к выполнению запроса
     * @throws SQLException во время выполнения метод может сгенерировать исключение при работе с бд
     */
    @Override
    public PreparedStatement getPreparedStatement(ConnectorDB connector, String query) throws SQLException {
        log.info(LOG_MESSAGE_PREPARED_STATEMENT);
        return connector.getConnection().prepareStatement(query);
    }

    /**
     * Метод получения состояния для запроса к бд
     * @param connector экземпляр класса ConnectorDB, получает соединение с базой данных
     * @return возвращается экземпляр класса Statement, готовый для формирования запроса к бд
     * @throws SQLException во время выполнения метод может сгенерировать исключение при работе с бд
     */
    @Override
    public Statement getStatement(ConnectorDB connector) throws SQLException {
        log.info(LOG_MESSAGE_STATEMENT);
        return connector.getConnection().createStatement();
    }

    /**
     * Метод получения результата выполнения запроса к бд
     * @param statement получает экземпляр Statement и использует его для выполнения запроса к бд
     * @param query строка запроса к бд
     * @return ResultSet - результат выполнения запроса
     * @throws SQLException может генерировать исключение при работе с бд
     */
    @Override
    public ResultSet getResultSet(Statement statement, String query) throws SQLException {
        log.info(LOG_MESSAGE_RESULT_SET);
        return statement.executeQuery(query);
    }

    /**
     * Метод закрытия экземпляра PreparedStatement
     * @param preparedStatement экземпляр, который необходимо закрыть
     */
    @Override
    public void closePreparedStatement(PreparedStatement preparedStatement){
        log.info(LOG_MESSAGE_CLOSED_PREPARED);
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод закрытия экземпляра Statement
     * @param statement экземпляр, который необходимо закрыть
     */
    @Override
    public void closeStatement(Statement statement){
        log.info(LOG_MESSAGE_CLOSED_STATEMENT);
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                log.error(LOG_MESSAGE_ERROR_CLOSED);
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод закрытия экземпляра ResultSet
     * @param resultSet экземпляр, который необходимо закрыть
     */
    @Override
    public void closeResultSet(ResultSet resultSet){
        log.info(LOG_MESSAGE_CLOSED_RESULT);
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException ex) {
                log.error(LOG_MESSAGE_ERROR_CLOSED);
                ex.printStackTrace();
            }
        }
    }
}
