package database.connector;

import java.sql.Connection;
import java.sql.SQLException;

interface IConnector {

    /**
     * Метод получения синхронизированного соединения с БД
     * @return передается соединение с базой данных
     */
    Connection getConnection() throws SQLException;
}
