package models;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IInsert {
    List<String> neutralizeParams(HttpServletRequest req);
    String buildQuery(String[] params);
    void insertToDatabase(String query);
}
