package com.ppcredit.bamboo.backend.web.rest.admin.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class GlobalConfig {
	private static Log log = LogFactory.getLog(GlobalConfig.class);
	public static final String SYSTEM_PROPERTIES = "/system.properties";
	private static String propertiesStorePath;
	private static Map propertieMap = new HashMap();

	private static Map propertieFileMap = new HashMap();

	static {
		Properties properties = init("/system.properties");
		Iterator it = properties.keySet().iterator();
		propertiesStorePath = properties.getProperty("path");

		while (it.hasNext()) {
			String name = (String) it.next();
			String file = properties.getProperty(name);

			file = file.trim();

			propertieFileMap.put(name, file);
			Properties p = init("/" + file);
			propertieMap.put(name, p);
		}
	}

	private static Properties init(String propertyFile) {
		Properties p = new Properties();
		try {
			log.info("Start Loading property file \t" + propertyFile);
			p.load(GlobalConfig.class.getResourceAsStream(propertyFile));
			log.info("Load property file success!\t" + propertyFile);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not load property file." + propertyFile, e);
		}

		return p;
	}

	public static String getProperty(String cls, String name) {
		Properties p = (Properties) propertieMap.get(cls);
		if (p != null) {
			return p.getProperty(name);
		}

		return null;
	}

	public static boolean getBooleanProperty(String cls, String name) {
		String p = getProperty(cls, name);
		return "true".equals(p);
	}

	public static Integer getIntegerProperty(String cls, String name) {
		String p = getProperty(cls, name);
		if (p == null) {
			return null;
		}
		return Integer.valueOf(p);
	}

	public static Long getLongProperty(String cls, String name) {
		String p = getProperty(cls, name);
		if (p == null) {
			return null;
		}
		return Long.valueOf(p);
	}

	public static Double getDoubleProperty(String cls, String name) {
		String p = getProperty(cls, name);
		if (p == null) {
			return null;
		}
		return Double.valueOf(p);
	}

	public static void store() {
	}

	public static void store(String cls) {
		Properties p = (Properties) propertieMap.get(cls);
		try {
			FileOutputStream fi = new FileOutputStream(new File(
					(String) propertieFileMap.get(cls)));
			p.store(fi, "Modified time: " + Calendar.getInstance().getTime());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			FileOutputStream fi;
			e.printStackTrace();
		}
	}
}