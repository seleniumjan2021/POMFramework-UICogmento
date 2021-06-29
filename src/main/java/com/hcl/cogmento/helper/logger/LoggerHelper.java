package com.hcl.cogmento.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hcl.cogmento.helper.resources.ResourceHelper;


public class LoggerHelper {

	private static boolean root = false;

	/**This method is responsible for writing logs in file.
	 * @param <T>
	 * @param cls
	 * @return Logger
	 */
	public static <T> Logger getLogger(Class<T> cls) {
		if (root) {
			return Logger.getLogger(cls);
		}
		// Configuring the path of log4jproperties file
		PropertyConfigurator.configure(ResourceHelper.getResourcePath()+"/propertiesFiles/log4jConfig.properties");
		root = true;
		return Logger.getLogger(cls);
	}

//	public static void main(String[] args) {
//		Logger log = LoggerHelper.getLogger(LoggerHelper.class);
//		log.info("Logger is configured");
//		log.info("logger is testing");
//	}
}
