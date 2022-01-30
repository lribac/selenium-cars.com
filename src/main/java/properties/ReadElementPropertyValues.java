package properties;

import java.io.InputStream;
import java.util.Properties;

public class ReadElementPropertyValues {

    /**
     * Reading element's location from .properties file
     */
        private Properties properties = new Properties();

        public ReadElementPropertyValues() {
            InputStream input = getClass().getClassLoader().getResourceAsStream("data.properties");
            try {
                properties.load(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String getProperty(String element)  {
            return properties.getProperty(element);
        }
    }
