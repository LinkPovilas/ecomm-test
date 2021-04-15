package util;

import java.util.Properties;

public class Config {

    private static final Properties properties = new PropertyFileReader().loadPropertyFile("config.properties");

    public static final String COOKIE_CONSENT = properties.getProperty("COOKIE_CONSENT");

    public static final String WEB_DRIVER_PATH = properties.getProperty("WEB_DRIVER_PATH");

    public static final String WEB_DRIVER = properties.getProperty("WEB_DRIVER");

    public static final String BROWSER_NAME = properties.getProperty("BROWSER_NAME");

    public static final boolean HEADLESS = Boolean.parseBoolean(properties.getProperty("HEADLESS"));

    public static final boolean MAXIMIZED = Boolean.parseBoolean(properties.getProperty("MAXIMIZED"));
}
