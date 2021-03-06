package com.xtrospy.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.xtrospy.core.ApplicationConfig;

import android.util.Log;

public class LoggerFile {
	protected static String _path = "";

	public static void logToFile(String level, String logEntry, String type) {
		_path = ApplicationConfig.getPackageName() + "_";
		_writeToFile(logEntry, _path + "xtrospy_verbose.log");
		_writeToFile(logEntry, _path + "xtrospy_" + type + ".log");
		if (level != "V") {
			level = (level.equals("I") ? "informational" : "warning");
			_writeToFile(logEntry, _path + "xtrospy_" + level + ".log");
		}
	}

	protected static void _writeToFile(String logEntry, String path) {
		try {
			File logFile = new File(
					ApplicationConfig.getDataDir() + "/" + path);
			if (!logFile.exists())
				logFile.createNewFile();

			//BufferedWriter for performance, true to set append to file flag
			BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
			buf.append(logEntry);
			buf.newLine();
			buf.close();
		}
		catch (Exception e) {
			Log.w("xtrospyError", "Error with file logger: " + e);
		}
	}
}
