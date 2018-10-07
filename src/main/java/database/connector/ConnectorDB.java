package database.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;


public class ConnectorDB implements IConnector {
    private static final String LOG_MESSAGE_CREATE = "Создан экземпляр класса";
    private static final String LOG_MESSAGE_INIT_CONNECTION = "Получено соединение с базой данных";
    private static final String LOG_MESSAGE_ERROR_CREATE_CONNECTION = "Ошибка получения соединения с базой данных";
    private static final String LOG_MESSAGE_SEND_CONNECTION = "Соединение отправлено";
    private static final String LOG_MESSAGE_DRIVER_NOT_FOUND = "Драйвер не найден";
    private static final Logger log = Logger.getLogger(ConnectorDB.class);

    private static volatile ConnectorDB instance;
    private IReceiver receiver;
    private Connection connection = null;

    /**
     * Конструктор класса инициализирует переменные receiver и connection
     */
    private ConnectorDB(){
        receiver = new ResourceReceiver();
        connection = initConnection();
    }

    /**
     * Метод регистрирует драйвер и получает соединение с базой данных
     * @return возвращает перменной экземпляра класса соединение с бд
     */
    private Connection initConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(receiver.getDBUrl(), receiver.getDBProperties());
            log.info(LOG_MESSAGE_INIT_CONNECTION);
        } catch (ClassNotFoundException e) {
            log.error(LOG_MESSAGE_DRIVER_NOT_FOUND);
            e.printStackTrace();
        } catch (SQLException ex){
            log.info(LOG_MESSAGE_ERROR_CREATE_CONNECTION);
            ex.printStackTrace();
        }
        return connection;
    }
    /**
     * Метод получения синхронизированного соединения с БД
     * @return передается соединение с базой данных
     */
    @Override
    public synchronized Connection getConnection(){
        log.info(LOG_MESSAGE_SEND_CONNECTION);
        return connection;
    }

    /**
     * Реализация шаблона Singleton (Double Checked Locking & volatile)
     * @return метод возвращает ссылку на объект ConnectorDB
     */
    public static ConnectorDB getInstance(){
        ConnectorDB localInstance = instance;
        if (localInstance == null){
            synchronized (ConnectorDB.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new ConnectorDB();
                    log.info(LOG_MESSAGE_CREATE);
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
         * @return метод возвращает настройки в экземпляре класса Properties
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
