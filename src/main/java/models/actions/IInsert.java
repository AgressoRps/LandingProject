package models.actions;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IInsert {

    /**
     * Метод последовательно обезвреживает каждый переданный параметр от JavaScript иньекций
     * Добавляет все параметры в коллекцию
     * @param req получает параметры переданные из формы
     * @return возвращается коллекция параметров, готовая к построению запроса
     */
    List<String> neutralizeParams(HttpServletRequest req);

    /**
     * Метод построения подготовленного запроса, обращается к методу buildInsertPreparedQuery
     * класса QueryBuilder
     * @param params коллекция параметров
     * @return строка готовая к передачи экземпляру класса PreparedStatement
     */
    String buildQuery(List<String> params);

    /**
     * Метод добавления нового заказа в таблицу базы данных
     * @param query подготовленная строка запрос
     * @param params массив параметров, которые подставляются под аргументы строки-запроса
     */
    void insertToDatabase(String query, String[] params);
}
