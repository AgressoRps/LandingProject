package database.helpers;

import database.connector.ConnectorDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface IHelper {

    /**
     * Метод получения подготовленного запроса к базе данных
     * @param connector экземпляр класса ConnectorDB, получает соединение с базой данных
     * @param query подготовленная строка запроса к базе данных, передается экземпляру prepareStatement
     * @return возвращается экземпляр PrepareStatement готовый к выполнению запроса
     * @throws SQLException во время выполнения метод может сгенерировать исключение при работе с бд
     */
    PreparedStatement getPreparedStatement(ConnectorDB connector, String query) throws SQLException;

    /**
     * Метод получения состояния для запроса к бд
     * @param connector экземпляр класса ConnectorDB, получает соединение с базой данных
     * @return возвращается экземпляр класса Statement, готовый для формирования запроса к бд
     * @throws SQLException во время выполнения метод может сгенерировать исключение при работе с бд
     */
    Statement getStatement(ConnectorDB connector) throws SQLException;

    /**
     * Метод получения результата выполнения запроса к бд
     * @param statement получает экземпляр Statement и использует его для выполнения запроса к бд
     * @param query строка запроса к бд
     * @return ResultSet - результат выполнения запроса
     * @throws SQLException может генерировать исключение при работе с бд
     */
    ResultSet getResultSet(Statement statement, String query) throws SQLException;

    /**
     * Метод закрытия экземпляра PreparedStatement
     * @param statement экземпляр, который необходимо закрыть
     */
    void closePreparedStatement(PreparedStatement statement);

    /**
     * Метод закрытия экземпляра Statement
     * @param statement экземпляр, который необходимо закрыть
     */
    void closeStatement(Statement statement);

    /**
     * Метод закрытия экземпляра ResultSet
     * @param resultSet экземпляр, который необходимо закрыть
     */
    void closeResultSet(ResultSet resultSet);

}
