package jsonReader;

import java.io.File;
import java.util.HashMap;

public class FileGenerator {

    private int fileNumber;
    private File file;
    private String path;

    protected static final HashMap<Integer,String> lastRegistration = new HashMap<>();

    protected static void saveLastRegistration(String fileName, int fileNumber) {
        if (!fileName.isEmpty() && fileNumber != 0) {
            System.setProperty(""+fileNumber+"", fileName);
            lastRegistration.put(fileNumber, fileName);
        }
    }

    protected static HashMap<Integer, String> getLastRegistration() {
        return lastRegistration;
    }

    public FileGenerator() { }
    public FileGenerator(String path, int fileNumber) {
        this.path = path;
        this.file = new File(this.path);
        this.fileNumber = fileNumber;
    }

    public File getFile() { return file; }
    public String getPath() { return path; }
    public int getFileNumber() { return fileNumber; }
}
