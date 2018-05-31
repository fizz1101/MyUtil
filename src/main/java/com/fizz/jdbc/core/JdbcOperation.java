package com.fizz.jdbc.core;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public interface JdbcOperation {

    /**
     * save功能
     * @param sql
     * @param values
     * @return  自增主键id
     * @throws SQLException
     */
    int sava(String sql, Object[] values) throws SQLException;

    /**
     * save或update或delete功能
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    boolean execute(String sql, Object[] params) throws SQLException;

    /**
     * save或update或delete功能
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    boolean execute(String sql) throws SQLException;

    /**
     * 批处理save或update或delete功能
     *
     * @param sql
     * @param params
     * @return 变更记录数
     * @throws SQLException
     */
    int executeBatch(String sql, List<Object[]> params) throws SQLException;

    /**
     * 批处理save或update或delete功能
     *
     * @param sql
     * @return 变更记录数
     * @throws SQLException
     */
    int executeBatch(String sql) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @param params
     * @return 原生ResultSet数据集合
     * @throws SQLException
     */
    ResultSet queryForResultSet(String sql, Object[] params) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @return 原生ResultSet数据集合
     * @throws SQLException
     */
    ResultSet queryForResultSet(String sql) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @return 统计单列记录数
     * @throws SQLException
     */
    int queryForInt(String sql, Object[] params) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @return 统计单列记录数
     * @throws SQLException
     */
    int queryForInt(String sql) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @return 返回sql执行结果
     * @throws SQLException
     */
    String queryForString(String sql, Object[] params) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @return 返回sql执行结果
     * @throws SQLException
     */
    String queryForString(String sql) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @param params
     * @param clazz
     * @return List<?>数据集合
     * @throws SQLException
     */
    List<?> queryForEntity(String sql, Object[] params, Class<?> clazz) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @param clazz
     * @return List<?>数据集合
     * @throws SQLException
     */
    List<?> queryForEntity(String sql, Class<?> clazz) throws SQLException;

    /**
     * select功能:分页查询(查询页数越大，性能越差)
     *
     * @param sql
     * @param params
     * @param curPage 当前页
     * @param pageSize 每页记录数
     * @param clazz
     * @return List<?>数据集合
     * @throws SQLException
     */
    List<?> queryForEntityPage(String sql, Object[] params, int curPage, int pageSize, Class<?> clazz) throws SQLException;

    /**
     * select功能:分页查询(查询页数越大，性能越差)
     *
     * @param sql
     * @param curPage 当前页
     * @param pageSize 每页记录数
     * @param clazz
     * @return List<?>数据集合
     * @throws SQLException
     */
    List<?> queryForEntityPage(String sql, int curPage, int pageSize, Class<?> clazz) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @param params
     * @param mapper 自定义mapper
     * @return List<?>数据集合
     * @throws SQLException
     */
    List<?> queryForMapper(String sql, Object[] params, RowMapper<?> mapper) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @param mapper 自定义mapper
     * @return List<?>数据集合
     * @throws SQLException
     */
    List<?> queryForMapper(String sql, RowMapper<?> mapper) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @param params
     * @return List<Map<String, Object>>数据集合
     * @throws SQLException
     */
    List<Map<String, Object>> queryForMap(String sql, Object[] params) throws SQLException;

    /**
     * select功能
     *
     * @param sql
     * @return List<Map<String, Object>>数据集合
     * @throws SQLException
     */
    List<Map<String, Object>> queryForMap(String sql) throws SQLException;

}
