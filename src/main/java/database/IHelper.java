package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IHelper {
    PreparedStatement getPreparedStatement();
    ResultSet getResultSet();
    void closePreparedStatement();
    void closeResultSet();

}
