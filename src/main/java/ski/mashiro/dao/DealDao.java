package ski.mashiro.dao;

import org.apache.ibatis.annotations.Param;
import ski.mashiro.pojo.Deal;
import ski.mashiro.pojo.SelIdDeal;

import java.util.Date;
import java.util.List;

/**
 * @author FeczIne
 */
public interface DealDao {
    /**
     * 插入交易记录
     * @param tableName 表名
     * @param deal 交易对象
     * @return 影响的行数
     */
    int insert(@Param("tableName") String tableName, @Param("deal") Deal deal);

    /**
     * 查询指定id之后的交易记录
     * @param tableName 表名
     * @param id id
     * @return 影响的行数
     */
    List<SelIdDeal> selId(@Param("tableName") String tableName, @Param("id") int id);

    /**
     * 查询所有交易记录
     * @param tableName 表名
     * @return 所有交易金额的和和最后的id
     */
    List<SelIdDeal> selAllId(@Param("tableName") String tableName);

    /**
     * 查询指定日期之后的交易记录
     * @param tableName 表名
     * @param date 指定日期
     * @return 指定日期之后的交易记录
     */
    List<Deal> selDate(@Param("tableName") String tableName, @Param("date") Date date);

    /**
     * 创建表
     * @param tableName 表名
     */
    void createTable(@Param("tableName") String tableName);
}
