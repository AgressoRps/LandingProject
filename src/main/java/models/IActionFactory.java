package models;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IActionFactory {
    boolean checkCommand(HttpServletRequest req);
    List<String> neutralizeString(HttpServletRequest req);
    String buildQuery(List<String> params);
    void insertToDatabase(String query);
}
