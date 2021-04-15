package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {

    private final Properties properties = new Properties();

    public Properties loadPropertyFile(String property) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(property);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
