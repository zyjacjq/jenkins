package chapter.forezp.config;

import org.springframework.stereotype.Component;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JF on 2019/11/18.
 */
@Component
public class TestClickHouse {



/**
 * @Description:
 * @Date 2018/11/12
 */

    private static String clickhouseAddress="jdbc:clickhouse://192.168.140.1:8123";

    private static String clickhouseUsername="root";

    private static String clickhousePassword="123456";

    private static String clickhouseDB="shop";

    private static Integer clickhouseSocketTimeout=6000;



    public static Connection getConn() {

        ClickHouseConnection conn = null;
        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setUser(clickhouseUsername);
        properties.setPassword(clickhousePassword);
        properties.setDatabase(clickhouseDB);
        properties.setSocketTimeout(clickhouseSocketTimeout);
        ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(clickhouseAddress,properties);
        try {
            conn = clickHouseDataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static List<Map> exeSql(String sql){
        System.out.println("cliockhouse 执行sql：" + sql);
        Connection connection = getConn();
        if (connection!=null){


        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map> list = new ArrayList();
            while(results.next()){
                Map row = new HashMap();
                for(int i = 1;i<=rsmd.getColumnCount();i++){
                    row.put(rsmd.getColumnName(i),results.getString(rsmd.getColumnName(i)));
                }
                list.add(row);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        return null;
    }

}
