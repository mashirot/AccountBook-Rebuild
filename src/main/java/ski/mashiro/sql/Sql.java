package ski.mashiro.sql;

import net.mamoe.mirai.console.command.CommandSender;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ski.mashiro.AccountBookRebuild;
import ski.mashiro.dao.DealDao;
import ski.mashiro.file.Config;
import ski.mashiro.pojo.Deal;
import ski.mashiro.pojo.SelIdDeal;
import ski.mashiro.util.Utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author FeczIne
 */
public class Sql {
    private static final String TABLE_NAME = "tb_" + Config.config.getOwner();

    public static void insert(CommandSender sender) throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.getMapper(DealDao.class).createTable(TABLE_NAME);
            int failedNum = 0;
            for (Deal deal : Utils.DEAL_LIST) {
                failedNum = sqlSession.getMapper(DealDao.class).insert(TABLE_NAME, deal) == 0 ? failedNum + 1 : failedNum;
            }
            if (sender == null) {
                AccountBookRebuild.INSTANCE.getLogger().info("用户" + Config.config.getOwner() + "，已成功向数据库写入 " + (Utils.DEAL_LIST.size() - failedNum) + " 个数据， " + failedNum + " 个写入失败");
            } else {
                sender.sendMessage("已成功向数据库写入 " + (Utils.DEAL_LIST.size() - failedNum) + " 个数据， " + failedNum + " 个写入失败");
            }
            Utils.DEAL_LIST.clear();
        }
    }

    public static List<SelIdDeal> selectId(int id) throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.getMapper(DealDao.class).createTable(TABLE_NAME);
            return sqlSession.getMapper(DealDao.class).selId(TABLE_NAME, id);
        }
    }

    public static List<SelIdDeal> selectAllId() throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.getMapper(DealDao.class).createTable(TABLE_NAME);
            return sqlSession.getMapper(DealDao.class).selAllId(TABLE_NAME);
        }
    }

    public static List<Deal> selectDate(Date date) throws IOException {
        SqlSessionFactory sqlSessionFactory = Utils.getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.getMapper(DealDao.class).createTable(TABLE_NAME);
            return sqlSession.getMapper(DealDao.class).selDate(TABLE_NAME, date);
        }
    }
}
