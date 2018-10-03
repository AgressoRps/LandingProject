package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface IHelper {
    PreparedStatement getPreparedStatement(ConnectorDB connection, String query) throws SQLException;
    Statement getStatement(ConnectorDB connection) throws SQLException;
    ResultSet getResultSet(Statement statement, String query) throws SQLException;
    void closePreparedStatement(PreparedStatement statement) throws SQLException;
    void closeStatement(Statement statement) throws SQLException;
    void closeResultSet(ResultSet resultSet) throws SQLException;

}
