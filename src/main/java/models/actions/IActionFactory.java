package models.actions;

import models.actions.IInsert;

import javax.servlet.http.HttpServletRequest;

public interface IActionFactory {
    /**
     * Метод определяет из какой формы были переданы параметры, создает новый экземпляр
     * необходимого действия (класса) и возвращает его
     * @param req экземпляр запроса, содержащий переданные параметры
     * @return экземпляр одного из дочерних классов интерфейса IInsert
     */
    IInsert getCommand(HttpServletRequest req);
}
