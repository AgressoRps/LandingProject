package database;

import java.sql.Connection;
import java.sql.SQLException;

interface IConnector {
    Connection getConnection() throws SQLException;
}
