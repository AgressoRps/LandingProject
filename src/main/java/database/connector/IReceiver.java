package database.connector;

import java.util.Properties;

/**
 * Имеет наследника (внутренний класс, класса ConnectorDB)
 */
interface IReceiver {

    /**
     * Метод использует экземпляр Bundle для извлечения строки подключения
     * к базе данных из свойств приложения
     * @return метод возвращает строку подключения к базе данных
     */
    String getDBUrl();

    /**
     * Метод извлекает настройки и формирует объект для создания соединения с базой данных
     * @return метод возвращает настройки в экземпляре класса Properties
     */
    Properties getDBProperties();
}
