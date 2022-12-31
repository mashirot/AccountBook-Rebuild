package ski.mashiro.file;

import org.apache.commons.io.FileUtils;
import ski.mashiro.AccountBookRebuild;
import ski.mashiro.util.Utils;

import java.io.File;

/**
 * @author FeczIne
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Config {
    public static ski.mashiro.pojo.Config config;
    private static final File CONFIG_FILE = new File(AccountBookRebuild.INSTANCE.getConfigFolder(), "config.yml");

    public static void loadConfig() {
        try {
            if (!CONFIG_FILE.exists()) {
                CONFIG_FILE.createNewFile();
                config = new ski.mashiro.pojo.Config();
                FileUtils.writeStringToFile(CONFIG_FILE, Utils.YAML_MAPPER.writeValueAsString(config), "utf-8");
                return;
            }
            config = Utils.YAML_MAPPER.readValue(FileUtils.readFileToString(CONFIG_FILE, "utf-8"), ski.mashiro.pojo.Config.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        try {
            if (!CONFIG_FILE.exists()) {
                CONFIG_FILE.createNewFile();
                FileUtils.writeStringToFile(CONFIG_FILE, Utils.YAML_MAPPER.writeValueAsString(new ski.mashiro.pojo.Config()), "utf-8");
                return;
            }
            FileUtils.writeStringToFile(CONFIG_FILE, Utils.YAML_MAPPER.writeValueAsString(config), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
