package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper implements IHelper {

    /**
     * Метод получения подготовленного запроса к базе данных
     * @param connector экземпляр класса ConnectorDB, получает соединение с базой данных
     * @param query подготовленная строка запроса к базе данных, передается экземпляру prepareStatement
     * @return возвращается экземпляр PrepareStatement готовый к выполнению запроса
     * @throws SQLException во время выполнения метод может сгенерировать исключение при работе с бд
     */
    @Override
    public PreparedStatement getPreparedStatement(ConnectorDB connector, String query) throws SQLException {
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
        return statement.executeQuery(query);
    }

    /**
     * Метод закрытия экземпляра PreparedStatement
     * @param statement экземпляр, который необходимо закрыть
     * @throws SQLException метод может сгенирировать исключение при освобождении ресурсов
     */
    @Override
    public void closePreparedStatement(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    /**
     * Метод закрытия экземпляра Statement
     * @param statement экземпляр, который необходимо закрыть
     * @throws SQLException метод может сгенирировать исключение при освобождении ресурсов
     */
    @Override
    public void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }

    /**
     * Метод закрытия экземпляра ResultSet
     * @param resultSet экземпляр, который необходимо закрыть
     * @throws SQLException метод может сгенирировать исключение при освобождении ресурсов
     */
    @Override
    public void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }
}
