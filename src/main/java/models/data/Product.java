package models.data;

public class Product {
    private int id;
    private String name;
    private String imageName;
    private double weight;
    private int price;

    /**
     * Конструктор класса, заполняет переменные класса данными переданными в параметрах
     * @param id идентификтор товара, полученный из базы данных
     * @param name наименование товара
     * @param imageName наименование изображения с расширением
     * @param weight вес товара
     * @param price стоимость товара, указанная в бд
     */
    public Product(int id, String name, String imageName, double weight, int price){
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.weight = weight;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
