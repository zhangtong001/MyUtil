package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test01 {

	public static void main(String[] args) throws Exception {

		Properties properties = new Properties();

		InputStream resourceAsStream = Test01.class.getClassLoader().getResourceAsStream("properties/test.properties");

		properties.load(resourceAsStream);

		String property = properties.getProperty("url");

		System.out.println(property);

	}

}
