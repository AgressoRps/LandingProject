package models;

import database.ConnectorDB;
import database.DatabaseHelper;
import database.QueryBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;

public class FillProducts {
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE = "image_name";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_PRICE = "price";

    public FillProducts(){
    }

    /**
     * Метод вызывает построение строки query, а затем выполняет запрос к базе данных и получает
     * все товары содержащиеся в таблице product, затем вызывает метод initProductsBank, передает
     * ему результат запроса (ResultSet) и пустую коллекцию для заполнения объектами Products
     * @param products пустая коллекция переданная из ProductsBank для заполнения данными из бд
     */
    public void getDataFromConnector(List<Product> products){
        String query = QueryBuilder.buildSelectQuery(TABLE_NAME);
        DatabaseHelper databaseHelper = new DatabaseHelper();
        Statement statement = null;
        ResultSet resultSet = null;
        ConnectorDB connectorDB;
        try {
            connectorDB = ConnectorDB.getInstance();
            statement = databaseHelper.getStatement(connectorDB);
            resultSet = statement.executeQuery(query);
            initProductsBank(products, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closedAllConnections(databaseHelper, statement, resultSet);
        }
    }

    /**
     * Метод заполнения коллекции products объектами product, создаваемыми на основе данных из БД
     * @param products пустая коллекция, которую необходимо заполнить данными
     * @param resultSet содержит значения полученные в результате запроса к базе данных
     * @throws SQLException метод может генерировать исключение при работе с бд
     */
    private void initProductsBank(List<Product> products, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            int id = resultSet.getInt(COLUMN_ID);
            String name = resultSet.getString(COLUMN_NAME);
            String image = resultSet.getString(COLUMN_IMAGE);
            double weight = resultSet.getDouble(COLUMN_WEIGHT);
            int price = resultSet.getInt(COLUMN_PRICE);
            products.add(new Product(id, name, image, weight, price));
        }
    }

    /**
     * Метод освобождения ресурсов по завершении инициализации коллекции
     * @param helper экзмепляр класса DatabaseHelper, вызывает методы закрытия
     * @param statement освобождаемая переменная экземпляра Statement
     * @param resultSet освобождаемая переменная экземпляра resultState, результат выполнения запроса к бд
     */
    private void closedAllConnections(DatabaseHelper helper, Statement statement, ResultSet resultSet){
        helper.closeStatement(statement);
        helper.closeResultSet(resultSet);
    }
}
