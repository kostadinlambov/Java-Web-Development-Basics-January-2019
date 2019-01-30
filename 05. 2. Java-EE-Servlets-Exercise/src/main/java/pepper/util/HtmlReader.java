package pepper.util;

import java.io.*;

public class HtmlReader {

    public String readHtmlFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

        StringBuilder fileContentSb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            fileContentSb.append(line).append(System.lineSeparator());
        }

        return fileContentSb.toString().trim();
    }
}