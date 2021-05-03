package marcosoft;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Configuration {
    
    InputStream inputStream;
    public String getProperty(String property){
        String result = "";


        //getClass().getResourceAsStream

        try (InputStream input = new FileInputStream("my-app/src/main/java/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            result = prop.getProperty(property);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
