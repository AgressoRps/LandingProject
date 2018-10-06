package models.data;

import models.actions.FillProducts;

import java.util.ArrayList;
import java.util.List;

public class ProductsBank {
    private List<Product> products = new ArrayList<Product>();

    /**
     * Конструктор создает экземпляр FillProducts, а затем передает методу getDataFromConnector
     * пустую коллекцию для заполнения ее объектами Products, на основе данных из бд
     */
    public ProductsBank(){
        FillProducts fillProducts = new FillProducts();
        fillProducts.getDataFromConnector(products);
    }

    /**
     * Метод получения коллекции products содержащей набор объектов Product
     * @return коллекция products, заполненная объектами Product
     */
    public List<Product> getProducts(){
        return products;
    }
}
