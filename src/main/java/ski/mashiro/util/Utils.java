package ski.mashiro.util;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ski.mashiro.AccountBookRebuild;
import ski.mashiro.file.Config;
import ski.mashiro.pojo.Balance;
import ski.mashiro.pojo.Deal;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

/**
 * @author FeczIne
 */
public class Utils {
    public static final List<Deal> DEAL_LIST = new Vector<>(20);
    public static Balance balance;
    public static final NumberFormat CURRENCY = NumberFormat.getCurrencyInstance(Locale.CHINA);
    public static final YAMLMapper YAML_MAPPER = new YAMLMapper();

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        Thread thread = Thread.currentThread();
        ClassLoader oc = thread.getContextClassLoader();
        try {
            thread.setContextClassLoader(AccountBookRebuild.class.getClassLoader());
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            return new SqlSessionFactoryBuilder().build(inputStream);
            // 需要切换上下文的代码
        } finally {
            thread.setContextClassLoader(oc);
        }
    }

    public static boolean isOwner(Long qq) {
        return Config.config.getOwner().equals(qq);
    }
}
