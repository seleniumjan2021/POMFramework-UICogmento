package com.hcl.cogmento.helper.resources;

import org.apache.log4j.Logger;

import com.hcl.cogmento.helper.logger.LoggerHelper;

public class ResourceHelper {

	private static Logger log = LoggerHelper.getLogger(ResourceHelper.class);
	/**
	 * This method will return the project path 
	 * @return String
	 */
	public static String getProjectPath() {
		String basePath = System.getProperty("user.dir")+"/";
		return basePath;	
	}
	
	/**This method will return the project resource path 
	 * @return String
	 */
	public static String getResourcePath() {
		String path = "src/main/resources/";
		return getProjectPath() + path; 
	}
	
	/**This method will return the project resource path 
	 * @return String
	 */
	public static String getResourcePath(String FilePath) {
		String basePath = System.getProperty("user.dir") + "/";
		String path = "src/main/resources/";
		return basePath + path + FilePath; 
	}
	/**This method will return the driver folder path 
	 * @return String
	 */
	public static String getDriverPath() {
		return getResourcePath()+"driver";
		
	}
	/**This method will return the driver(chrome/ff/ie) path 
	 * @return String
	 */
	public static String getBrowserDriverPath(String fileName) {
		return getDriverPath()+"/"+fileName;
	}
	
	/**This method will return the driver path 
	 * @return String
	 */
	public static String getReportPath(String subFolderName , String fileName) {
			return getProjectPath()+"AutomationReport/"+ subFolderName+"/"+fileName;
	}
	
	public static String getReportPath() {
		return getProjectPath()+"AutomationReport/";
	}
	
	public static String getReportPath(String subFolderName ) {
		return getProjectPath()+"AutomationReport/"+ subFolderName+"/";
}
	public static void main(String[] args) {
		System.out.println(getResourcePath());
		System.out.println(getBrowserDriverPath("chromedriver"));
		System.out.println(getBrowserDriverPath("firefox"));
	}
	
}
