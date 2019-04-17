/**
 *
 */
package com.cn.platform.managecenter.config;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by chenzhiheng on 15-3-23.<br>
 * Desc:JDBC基础操作接口定义;<br>
 * common开头的方法，是以占位符SQL语法实现的数据操作方法，其余方法为命名SQL语法实现的数据操作方法；<br>
 * <具体使用方法参见各方法注释>
 */
public interface IJdbcOperations {

    /**
     * 更新数据操作，适用于INSERT、UPDATE、DELETE
     *
     * @param nameSql 命名语法SQL;例如:(UPDATE users SET name = :userName)
     * @param bean    Bean类型的属性名，替换命名语法参数
     * @return 更新数据条数
     */
    public int update(String nameSql, Object bean) throws DataAccessException;

    /**
     * 更新数据操作，适用于INSERT、UPDATE、DELETE
     *
     * @param nameSql  命名语法SQL;例如:(UPDATE users SET name = :userName)
     * @param paramMap Map类型的key，替换命名语法参数
     * @return 更新数据条数
     */
    public int update(String nameSql, Map<String, ?> paramMap) throws DataAccessException;

    /**
     * 更新数据操作，适用于INSERT、UPDATE、DELETE
     *
     * @param sql  占位符语法SQL;例如:(UPDATE users SET name = ?)
     * @param args 数组类型，替换占位符参数
     * @return 更新数据条数
     */
    public int commonUpdate(String sql, Object... args) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param nameSql      命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param paramMap     Map类型的key，替换命名参数
     * @param requiredType 返回的Bean类型
     * @return 唯一的结果，Bean类型为requiredType
     */
    public <T> T selectObject(String nameSql, Map<String, ?> paramMap, Class<T> requiredType) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param nameSql      命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param bean         Bean类型的属性名，替换命名参数
     * @param requiredType 返回的Bean类型
     * @return 唯一的结果，Bean类型为requiredType
     */
    public <T> T selectObject(String nameSql, Object bean, Class<T> requiredType) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param nameSql  命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param paramMap Map类型的key，替换命名参数
     * @return 唯一的Map类型结果
     */
    public Map<String, Object> selectMap(String nameSql, Map<String, ?> paramMap) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param nameSql 命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param bean    Bean类型的属性名，替换命名参数
     * @return 唯一的Map类型结果
     */
    public Map<String, Object> selectMap(String nameSql, Object bean) throws DataAccessException;

    /**
     * 查询匹配条件的所有数据
     *
     * @param nameSql  命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param paramMap Map类型的key，替换命名参数
     * @return 包含Map的List结果列表
     */
    public List<Map<String, Object>> select(String nameSql, Map<String, ?> paramMap) throws DataAccessException;

    /**
     * 查询匹配条件的所有数据
     *
     * @param nameSql 命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param bean    Bean类型的属性名，替换命名参数
     * @return 包含Map的List结果列表
     */
    public List<Map<String, Object>> select(String nameSql, Object bean) throws DataAccessException;

    /**
     * 查询所有数据
     *
     * @param nameSql     命名语法SQL;例如:(SELECT * FROM users)
     * @param mappedClass 返回的class类型
     * @return mappedClass类型的List结果列表
     */
    public <T> List<T> select(String nameSql, Class<T> mappedClass) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param sql          占位符语法SQL;例如:(SELECT * FROM users WHERE name = ?, age = ?)
     * @param requiredType 返回的Bean类型
     * @param args         数组类型，替换占位符参数
     * @return 唯一的结果，Bean类型为requiredType
     */
    public <T> T commonSelectObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param sql  占位符语法SQL;例如:(SELECT * FROM users WHERE name = ?, age = ?)
     * @param args 数组类型，替换占位符参数
     * @return 唯一的Map类型结果
     */
    public Map<String, Object> commonSelectMap(String sql, Object... args) throws DataAccessException;

    /**
     * 查询匹配条件的所有数据
     *
     * @param sql  占位符语法SQL;例如:(SELECT * FROM users WHERE name = ?, age = ?)
     * @param args 数组类型，替换占位符参数
     * @return 包含Map的List结果列表
     */
    public List<Map<String, Object>> commonSelect(String sql, Object... args) throws DataAccessException;

    /**
     * 查询所有数据
     *
     * @param sql         占位符语法SQL;例如:(SELECT * FROM users)
     * @param mappedClass 返回的class类型
     * @return mappedClass类型的List结果列表
     */
    public <T> List<T> commonSelect(String sql, Class<T> mappedClass) throws DataAccessException;

    /**
     *
     * @param sql 占位符语法SQL；例如：(SELECT * FROM users WHERE username = ?)
     * @param rowMapper 数据库字段和BEAN字段不一致时的映射
     * @param args 条件参数
     * @param <T>
     * @return <T> List<T> 返回list结果集
     * @throws org.springframework.dao.DataAccessException
     */
    public <T> List<T> commonSelect(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException;

    /**
     *
     * @param sql 占位符语法SQL；例如：（SELECT COUNT(*) FROM users  WHERE = ?）
     * @param requiredType 返回值类型
     * @param args 条件参数
     * @param <T>
     * @return <T>T 返回值
     * @throws org.springframework.dao.DataAccessException
     */
    public <T> T selectObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException;

    /**
     *
     * @param sql 占位sql语句 目前只支持oracle分页
     * @param args 参数
     * @param argTypes 参数类型
     * @param currPageNum   当前页数
     * @param pageSize  页记录大小 <=0时 表示不分页
     * @return
     * @throws DataAccessException
     */
    public List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes, int currPageNum, int pageSize) throws DataAccessException;
}
