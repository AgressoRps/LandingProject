package models;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory implements IActionFactory {
    private static final String PARAM_ONE = "clientName";
    private static final String PARAM_TWO = "callMeName";

    @Override
    public IInsert getCommand(HttpServletRequest req){
        IInsert insertAction = null;
        String commandOne = req.getParameter(PARAM_ONE);
        String commandTwo = req.getParameter(PARAM_TWO);
        if (commandOne != null){
            insertAction = new InsertOrders();
        }
        if (commandTwo != null){
            insertAction = new InsertCallBack();
        }
        return insertAction;
    }
}
