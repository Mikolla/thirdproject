package ru.secondproject.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	private static String fileName;
	private static String useTech;

	public PropertiesReader() {
	}

	private static void setFileName() {
	    fileName = "useTech.properties";
		useTech = getProperties("useTech");
		if (useTech.equals("hibernate")) {
			fileName = "hibernate.properties";
		}
		else {
			fileName = "jdbc.properties";
		}

	}

	public static String getProperties(String name) {
		if (fileName == null) {
			setFileName();
		}

        if (useTech != null && name.equals("useTech")) {
            return useTech;
        }

		String value = null;
		Properties properties = new Properties();
		PropertiesReader reader = new PropertiesReader();
		try(InputStream input = reader.getClass().getClassLoader().getResourceAsStream(fileName)){
			properties.load(input);
			value = properties.getProperty(name);
		} catch (IOException e) {
			System.out.println("Can`t read properties file");
		}
		return value;
	}
}