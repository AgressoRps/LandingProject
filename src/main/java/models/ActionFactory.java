package models;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ActionFactory implements IActionFactory {
    private static final String PARAM_ID = "idProduct";
    private static final String PARAM_CLIENT_NAME = "clientName";
    private static final String PARAM_CLIENT_PHONE = "clientPhoneNumber";
    private static final String PARAM_CLIENT_MESSAGE = "clientMessage";
    private static final String PARAM_CALL_NAME = "callMeName";
    private static final String PARAM_CALL_PHONE = "callMePhone";
    private static final String PARAM_CALL_MESSAGE = "callMeText";
    private String commandOne;
    private String commandTwo;

    @Override
    public boolean checkCommand(HttpServletRequest req) {
        boolean isCommand = false;
        commandOne = req.getParameter("idProduct");
        commandTwo = req.getParameter("callMeName");
        if (commandOne != null){
            isCommand = true;
        }
        if (commandTwo != null){
            isCommand = true;
        }
        return isCommand;
    }

    @Override
    public List<String> neutralizeParams(HttpServletRequest req) {
        List<String> paramsList = new ArrayList<String>();
        if (commandOne == null || commandOne.isEmpty()){
            paramsList.add(neutralize(req.getParameter(PARAM_CALL_NAME)));
            paramsList.add(neutralize(req.getParameter(PARAM_CALL_PHONE)));
            paramsList.add(neutralize(req.getParameter(PARAM_CALL_MESSAGE)));
        }else {
            paramsList.add(neutralize(req.getParameter(PARAM_ID)));
            paramsList.add(neutralize(req.getParameter(PARAM_CLIENT_NAME)));
            paramsList.add(neutralize(req.getParameter(PARAM_CLIENT_PHONE)));
            paramsList.add(neutralize(req.getParameter(PARAM_CLIENT_MESSAGE)));
        }
        return paramsList;
    }
    private String neutralize(String param){
        return param == null ? "" : param.replaceAll("<", "&lt")
                .replaceAll(">", "&gt");
    }

    @Override
    public String buildQuery(List<String> params) {
        return null;
    }

    @Override
    public void insertToDatabase(String query) {

    }
}
