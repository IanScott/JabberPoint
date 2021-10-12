package nl.ou.jp.logging;

public class LoggerManager {
	
	private LoggerManager() {
		//singleton
	}
	
	public static Logger getLogger() {
		return new Logger() {

			@Override
			public void logDebug(String message) {
				System.out.println("DEBUG: " + message);
			}

			@Override
			public void logInfo(String message) {
				System.out.println("INFO: " + message);
			}

			@Override
			public void logError(String message) {
				System.err.println("ERROR: " + message);
			}
			
		};
	}
}
