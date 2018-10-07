package models.actions;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory implements IActionFactory {
    private static final String LOG_MESSAGE_GET_COMMAND = "Создание экземпляра IInsert";
    private static final String PARAM_ONE = "clientName";
    private static final String PARAM_TWO = "callMeName";

    private static final Logger log = Logger.getLogger(ActionFactory.class);
    /**
     * Метод определяет из какой формы были переданы параметры, создает новый экземпляр
     * необходимого действия (класса) и возвращает его
     * @param req экземпляр запроса, содержащий переданные параметры
     * @return экземпляр одного из дочерних классов интерфейса IInsert
     */
    @Override
    public IInsert getCommand(HttpServletRequest req){
        IInsert insertAction = null;
        String commandOne = req.getParameter(PARAM_ONE);
        String commandTwo = req.getParameter(PARAM_TWO);
        if (commandOne != null){
            log.info(LOG_MESSAGE_GET_COMMAND);
            insertAction = new InsertOrders();
        }
        if (commandTwo != null){
            log.info(LOG_MESSAGE_GET_COMMAND);
            insertAction = new InsertCallBack();
        }
        return insertAction;
    }
}
