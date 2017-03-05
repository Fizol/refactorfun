import java.io.*;

public class FileNumbersFilter {

    public void filterToFile(String inputPath, String outputPath, double threshold) throws IOException {

        try (FileInputStream fileInputStream = new FileInputStream(inputPath);
             InputStreamReader streamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(streamReader);
             FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
             OutputStreamWriter streamWriter = new OutputStreamWriter(fileOutputStream);
             Writer writer = new BufferedWriter(streamWriter)
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Double number = Double.valueOf(line);
                if (number > threshold) {
                    writer.write(number.toString());
                }
            }
        }
    }
}
