package database;

import java.util.Properties;

interface IReceiver {
    String getDBUrl();
    Properties getDBProperties();
}
