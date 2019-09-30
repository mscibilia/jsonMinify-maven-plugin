package com.ms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class JsonMinifier {
    private static final String JSON_FILE_EXT = ".json";
    
    private static final Logger logger = Logger.getLogger(JsonMinifier.class.getName());

	/**
	 * @param srcPathName
	 * @throws IOException
	 */
	public static void minifyJsonFilesInDir(String srcPathName, String destDirPathName) throws IOException {
		logger.info("Minifying files in dir " + srcPathName);
		final String sanitisedDestDirPathName = sanitiseDestDirPathName(destDirPathName);
		Files.createDirectories(Paths.get(sanitisedDestDirPathName));
		try (Stream<Path> paths = Files.walk(Paths.get(srcPathName))) {
			paths.filter(p -> p.toString().endsWith(JSON_FILE_EXT)).forEach(p -> {
				try {
					logger.info("Minifying " + p.getFileName().toString());
					writeMinifiedJsonFilesToDest(sanitisedDestDirPathName.concat(p.getFileName().toString()), minifyJsonFile(p));
				} catch (IOException e) {
					logger.log(Level.SEVERE, "Could not minify the file: " + p.toString() + "\n" + e.getMessage());
				}
			});
		}
	}

	private static String sanitiseDestDirPathName(String destDirPathName) {
		String result = destDirPathName;
		if(destDirPathName.contains("/") && !destDirPathName.endsWith("/"))	{
			result = destDirPathName.concat("/");
		} else if(destDirPathName.contains("\\") && !destDirPathName.endsWith("\\"))	{
			result = destDirPathName.concat("\\");
		}
		
		return result;
	}
    
    private static String minifyJsonFile(Path jsonFilePath) throws IOException {
    	String jsonFileContent = new String(Files.readAllBytes(jsonFilePath));
		return jsonFileContent.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "").replaceAll(",\\ +", ",")
				.replaceAll(":\\ +", ":").replaceAll("\\{\\ +", "\\{").replaceAll("\\ +\\}", "\\}");
    }
    
    private static void writeMinifiedJsonFilesToDest(String destFileName, String content) throws IOException	{
    	logger.info("Writing file " + destFileName);
    	Files.write(Paths.get(destFileName), content.getBytes());
    }
}
