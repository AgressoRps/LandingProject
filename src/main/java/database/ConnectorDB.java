package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;


public class ConnectorDB implements IConnector {
    private static volatile IConnector instance;
    private IReceiver receiver;

    private ConnectorDB(){
        receiver = new ResourceReceiver();
    }

    /**
     * @return получаем соедниение с базой данных
     * @throws SQLException метод может генерировать исключение при подключении к бд
     */
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(receiver.getDBUrl(), receiver.getDBProperties());
    }

    /**
     * Реализация шаблона Singleton (Double Checked Locking & volatile)
     * @return метод возвращает экземпляр класса в единичном экземпляре
     */
    public static IConnector getInstance() {
        IConnector localInstance = instance;
        if (localInstance == null){
            synchronized (ConnectorDB.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new ConnectorDB();
                }
            }
        }
        return localInstance;
    }

    private class ResourceReceiver implements IReceiver{
        private ResourceBundle bundle;
        private Properties properties;

        public ResourceReceiver(){
            bundle = ResourceBundle.getBundle("database");
            properties = new Properties();
        }

        /**
         * Метод использует экземпляр Bundle для извлечения строки подключения
         * к базе данных из свойств приложения
         * @return метод возвращает строку подключения к базе данных
         */
        @Override
        public String getDBUrl() {
            return bundle.getString("db.host");
        }

        /**
         * Метод извлекает настройки и формирует объект для создания соединения с базой данных
         * @return метод возвращает настройки в едином объекте
         */
        @Override
        public Properties getDBProperties() {
            properties.put("user", bundle.getString("db.login"));
            properties.put("password", bundle.getString("db.password"));
            properties.put("autoReconnect", bundle.getString("db.autoReconnect"));
            properties.put("serverTimezone", bundle.getString("db.serverTimezone"));
            properties.put("characterEncoding", bundle.getString("db.characterEncoding"));
            properties.put("useUnicode", bundle.getString("db.useUnicode"));
            properties.put("useSSL", bundle.getString("db.useSSL"));
            return properties;
        }
    }
}
