package jsonReader;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static jsonReader.FileGenerator.*;
import static jsonReader.FileGenerator.getLastRegistration;
import static org.slf4j.LoggerFactory.getLogger;

public class JsonSharedExtensions {

    private static final Logger logger = getLogger(JsonSharedExtensions.class);

    /**
     * @return for the indication of the json write
     */
    public FileGenerator createNewFile(String fileName, int fileNumber, long jsonLimitSizeCheck) {
        String userDir = System.getProperty("user.dir");
        this.createDir(userDir + "/target/jsonFiles");
        return this.registerFile(fileNumber, userDir, fileName, jsonLimitSizeCheck);
    }

    /**
     * verify that the last map is not ull or empty
     * then iterate over the map if the @param file number is == to map key
     * then create check file size < 2000 kb or else create a new file
     * all ways save the last registration of the last file for create single for each file number id
     * unless if the file is bigger than 2000 km
     * @param fileId json file number id
     * @param userDir dir creation
     * @param fileName person.json etc
     * @return FileGenerator with the str and file id
     */
    private FileGenerator registerFile(int fileId, String userDir, String fileName, long jsonLimitSizeCheck) {
        long fileSize;

        if (getLastRegistration() != null && !getLastRegistration().isEmpty()) {
            HashMap<Integer, String> registration = getLastRegistration();
            for (Map.Entry<Integer, String> entry : registration.entrySet()) {
                if (this.checkCondition(entry.getKey(), fileId, entry.getValue())) {
                    fileSize = this.getFileSize(entry.getValue());
                    if (fileSize > jsonLimitSizeCheck) {
                        String setPath = this.generatePath(userDir, fileName, fileId);
                        saveLastRegistration(setPath, fileId);
                        logger.debug("find exist file: " + entry.getValue() + " create new json:" + setPath + " file is to big" + fileSize + " kb");
                        return new FileGenerator(setPath, fileId);
                    } else {
                        logger.debug("find exist file: " + entry.getValue() + " continue with the same json file is " + fileSize + " kb");
                        saveLastRegistration(entry.getValue(), entry.getKey());
                        return new FileGenerator(entry.getValue(), entry.getKey());
                    }
                }
            }
        }

        String setPath = this.generatePath(userDir, fileName, fileId);
        logger.debug("cannot find exist file: " + setPath + " create new json:" + setPath);
        saveLastRegistration(setPath, fileId);
        return new FileGenerator(setPath, fileId);
    }

    private boolean checkCondition(int getKey, int fileId, String getKeyAsString) {
        return getKey == fileId
                && System.getProperty(String.valueOf(getKey)) != null
                && System.getProperty(String.valueOf(getKey)).equals(getKeyAsString);
    }

    /**
     * @param file verify validity of the file
     */
    public void verifyIntention(FileGenerator file) {
        if (file == null || file.getFile() == null
                || file.getPath() == null || file.getPath().isEmpty()) {
            Assertions.fail("File params are missing or not valid ");
        }
    }

    public long getFileSize(String fileName) {
        try {
            return Files.size(Paths.get(fileName)) / 1024;
        } catch (IOException ioException) {
            return -1;
        }
    }

    public void delete(File file) {
        if (file.exists()) {
            String name = file.getName();
            if (file.delete()) {
                logger.info("file " + name + " has deleted");
            }
        }
    }

    public void createDir(String userDir) {
        try {
            Path dir = Paths.get(userDir);
            Files.createDirectories(dir);
        } catch (Exception exception) {
            logger.error("Exception create " + userDir + " " + exception.getMessage());
        }
    }

    public String generatePath(String userDir, String fileName, int order) {
        return userDir
                + "/target/jsonFiles/"
                + this.generateUUID()
                + "-" + order
                + "-" + fileName;
    }

    public String generateUUID() {
        return UUID.randomUUID()
                .toString()
                .substring(0,8);
    }
}
