package com.fizz.jdbc;

import com.fizz.jdbc.core.JdbcFactory;
import com.fizz.jdbc.core.SimpleJdbc;
import com.fizz.jdbc.entity.User;
import com.fizz.jdbc.mapper.UserMapper;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws SQLException {
//		SimpleDataSource dataSource = new SimpleDataSource();
        InputStream in = Test.class.getClassLoader().getResourceAsStream("jdbc.properties");
        SimpleJdbc jdbc = JdbcFactory.getInstance(in);
        String sql;
        /**查询list Start*/
        sql = "select * from user";
        /**方式一*/
        /*List<User> list1 = (List<User>) jdbc.queryForBean(sql, new UserMapper());
        System.out.println("方式一结果--------------------");
        for (User user : list1) {
            System.out.println(user.getId() + "---" + user.getUserName());
        }*/

        for (int i=0; i<20; i++){
            /**方式二*/
            List<User> list2 = (List<User>) jdbc.queryForEntity(sql, User.class);
            System.out.println("方式二结果--------------------");
            for (User user : list2) {
                System.out.println(user.getId() + "---" + user.getUserName());
            }
        }

        /**方式三*/
        /*List<Map<String, Object>> list3 = jdbc.queryForMap(sql);
        System.out.println("方式三结果--------------------");
        for (Map<String, Object> map : list3) {
            for (String key : map.keySet()) {
                System.out.println(key + "---" + map.get(key));
            }
        }*/

        /**方式四*/
        /*UserDaoImpl userDaoImpl = new UserDaoImpl();
        List<User> list4 = userDaoImpl.queryForEntity(User.class, sql);
        System.out.println("方式四结果--------------------");
        for (User user : list4) {
            System.out.println(user.getId() + "---" + user.getUserName());
        }*/
        /**查询list End*/

        /**插入 Start*/
        /*sql = "insert into user(username) values (?)";
        Object[] values = new Object[1];
        values[0] = "LocationTest";
        int id = jdbc.sava(sql, values);
        System.out.println("key:" + id);*/
        /**插入 End*/
    }

}
