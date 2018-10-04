package database;

public class QueryBuilder {
    private static final String INSERT = "INSERT INTO ";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";
    private static final String SELECT = "SELECT ";
    private static final String FROM = " FROM ";
    private static final String VALUES = " VALUES";
    private static final String COMMA = ", ";
    private static final String QUESTION_MARK = "?";
    private static final String ALL = "*";

    /**
     * Метод построения строки запроса для экземпляра PreparedStatement
     * @param tableName имя таблицы, в которую будет осуществляться добавление
     * @param columnsTable массив наименований колонок таблицы, которые необходимо заполнить
     * @param values значения, которые будут вставлены в таблицу
     * @return возвращается готовая строка запрос, можно передавать экземпляру PreparedStatement
     */
    public static synchronized String buildInsertPreparedQuery(String tableName, String[] columnsTable, String[] values) {
        String initBuilder = INSERT.concat(tableName).concat(LEFT_BRACKET);
        StringBuilder stringBuilder = new StringBuilder(initBuilder);
        for (int i = 0; i < columnsTable.length; i++){
            if (i != columnsTable.length-1){
                stringBuilder.append(columnsTable[i].concat(COMMA));
            }else {
                stringBuilder.append(columnsTable[i]);
            }
        }
        stringBuilder.append(RIGHT_BRACKET.concat(VALUES).concat(LEFT_BRACKET));
        for (int i = 0; i < values.length; i++){
            if (i != values.length-1){
                stringBuilder.append(QUESTION_MARK.concat(COMMA));
            }else {
                stringBuilder.append(QUESTION_MARK.concat(RIGHT_BRACKET));
            }

        }
        return stringBuilder.toString();
    }

    /**
     * Метод построения строки запроса для извлечения из указанной таблицы данных определенных колонок
     * @param tableName наименование таблицы, для которой будет осуществлятся запрос
     * @param columnsTable массив наименований колонок, данные которых необходимо получить
     * @return возвращается готовая строка запроса для передачи экзмепляру Statement
     */
    public static synchronized String buildSelectQuery(String tableName, String[] columnsTable) {
        StringBuilder stringBuilder = new StringBuilder(SELECT);
        for (int i = 0; i < columnsTable.length; i++){
            if (i != columnsTable.length-1){
                stringBuilder.append(columnsTable[i].concat(COMMA));
            }else {
                stringBuilder.append(columnsTable[i].concat(FROM).concat(tableName));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Метод получения всех данных из указанной таблицы
     * @param tableName наименования таблицы из которой необходимо получить данные
     * @return возвращается готовая строка запроса для передачи экземпляру Statement
     */
    public static synchronized String buildSelectQuery(String tableName) {
        String result = SELECT.concat(ALL).concat(FROM).concat(tableName);
        return result;
    }
}
