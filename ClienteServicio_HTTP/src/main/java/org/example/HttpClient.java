package org.example;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpClient {
    public static void downloadFilesOfType(String websiteUrl, String fileType, String saveDir) {

        String pageContent = downloadPageContent(websiteUrl);

        if (pageContent != null) {
            downloadOfType(pageContent, fileType, saveDir);
        } else {
            System.err.println("Error al descargar la pagina.");
        }

    }

    private static String downloadPageContent(String websiteUrl) {

        try {
            URL url = new URL(websiteUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
            return content.toString();

        } catch (IOException e) {
            System.err.println("Error al cargar la pagina: " + e.getMessage());
            return null;
        }
    }

    private static void downloadOfType(String pageContent, String fileType, String saveDir) {

        Pattern pattern = Pattern.compile("https?://\\S+\\." + fileType);
        Matcher matcher = pattern.matcher(pageContent);
        int count = 0;

        while (matcher.find()) {
            String fileUrl = matcher.group();

            try {
                downloadFile(fileUrl, saveDir + "/" + count + "." + fileType);
                count++;
            } catch (IOException e) {
                System.out.println("Error al descargar ficheros: " + e.getMessage());
            }

        }

        System.out.println("Descargados " + count + " archivos " + fileType);
    }

    private static void downloadFile(String fileUrl, String savePath) throws IOException {

        URL url = new URL(fileUrl);

        try (InputStream in = url.openStream();

             FileOutputStream out = new FileOutputStream(savePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
