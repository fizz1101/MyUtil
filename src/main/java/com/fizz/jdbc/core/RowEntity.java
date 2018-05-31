package com.fizz.jdbc.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RowEntity {

    public static Map<String, Object> objMap = new HashMap<>();
    public static Map<String, Method> methodMap = new HashMap<>();
    public static Map<String, Map<String, Class<?>>> fieldMap = new HashMap<>();

    public static Object transToEntity(ResultSet rs, Class<?> clazz) throws SQLException {
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Map<String, Class<?>> map_field = initEntityField(clazz);
            ResultSetMetaData rsd = rs.getMetaData();
            int columnCount = rsd.getColumnCount();
            for (int i=1; i<=columnCount; i++) {
                String columnName = rsd.getColumnName(i);
                Object columnValue = rs.getObject(columnName);
                columnName = transToHump(columnName);
                if (map_field.containsKey(columnName)) {
                    Method method = getMethod(clazz, columnName, "set");
                    method.invoke(obj, columnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 通过缓存或反射获取实体类(有问题)
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Deprecated
    private static Object getObject(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        Object obj;
        String objKey = clazz.getName();
        if (objMap.containsKey(objKey)) {
            obj = objMap.get(objKey);
        } else {
            obj = clazz.newInstance();
            objMap.put(objKey, obj);
        }
        return obj;
    }

    /**
     * 获取Method
     * @param clazz
     * @param column    字段
     * @param type  方式(get/set)
     * @return
     */
    private static Method getMethod(Class<?> clazz, String column, String type) throws NoSuchMethodException {
        Method method;
        Map<String, Class<?>> map_field = initEntityField(clazz);
        String methodName = type + column.substring(0,1).toUpperCase() + column.substring(1);
        String methodKey = clazz.getName() + "_" + methodName;
        if (methodMap.containsKey(methodKey)) {
            method = methodMap.get(methodKey);
        } else {
            method = clazz.getMethod(methodName, map_field.get(column));
            methodMap.put(methodKey, method);
        }
        return method;
    }

    /**
     * 初始化实体类字段
     * @param clazz
     * @return
     */
    private static Map<String, Class<?>> initEntityField(Class<?> clazz) {
        Map<String, Class<?>> map = new HashMap<>();
        String className = clazz.getName();
        if (fieldMap.containsKey(className)) {
            map = fieldMap.get(className);
        } else {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                map.put(field.getName(), field.getType());
            }
            fieldMap.put(className, map);
        }
        return map;
    }

    /**
     * 将字段转为驼峰式
     * @param column
     * @return
     */
    private static String transToHump(String column) {
        String[] arr = column.split("_");
        String columnHump = arr[0];
        for (int i=1; i<arr.length; i++) {
            columnHump += arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
        }
        return columnHump;
    }

}
