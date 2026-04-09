package utilities;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class LoadConfig {
    private String module;
    private String key;


    Map<String, Map<String, String>> readData;

    public LoadConfig(String component) {
        try {
            Yaml yaml = new Yaml();
            final String rootPath = "configurations";
            String fileName = rootPath + "/" + component.toLowerCase() + ".yml";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream == null)
                throw new RuntimeException("Yaml file not found " + fileName);

            readData = yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load an config file for component - " + component, e);
        }
    }


    public String getComponentValue(String component) {
        return String.valueOf(readData.get(component));
    }

    public String getComponentValue(String component, String key) {
        return readData.get(component).get(key);
    }


}
