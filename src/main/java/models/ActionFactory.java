package models;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ActionFactory implements IActionFactory {
    @Override
    public boolean checkCommand(HttpServletRequest req) {
        boolean isCommand = false;
        String commandOne = req.getParameter("idProduct");
        String commandTwo = req.getParameter("callMeName");
        if (commandOne != null){
            isCommand = true;
        }
        if (commandTwo != null){
            isCommand = true;
        }
        return isCommand;
    }

    @Override
    public List<String> neutralizeString(HttpServletRequest req) {
        return null;
    }

    @Override
    public String buildQuery(List<String> params) {
        return null;
    }

    @Override
    public void insertToDatabase(String query) {

    }
}
