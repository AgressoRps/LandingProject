package models.helpers;

public class Neutralizer {
    /**
     * Метод обезвреживает JavaScript инъекции пользователей
     * @param param передается параметр, полученный экземпляром HttpServletRequest
     * @return возвращается параметр, готовый к формированию запроса PreparedStatement
     */
    public static String neutralize(String param){
        return param == null ? "" : param.replaceAll("<", "&lt")
                .replaceAll(">", "&gt");
    }
}
