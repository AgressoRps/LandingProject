package models;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IActionFactory {
    IInsert getCommand(HttpServletRequest req);
}
