package coachingmateanalytics.coachingmate.common.utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * FileUtils is a utility class providing file-related functionality.
 */
public final class FileUtils {

    /**
     * Downloads a file from a given URL and saves it to the specified location.
     *
     * @param urlStr the URL of the file to download
     * @param file   the destination file path
     * @throws IOException if an I/O error occurs
     */
    public static void downloadFile(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        try (BufferedInputStream bis = new BufferedInputStream(url.openStream());
             FileOutputStream fis = new FileOutputStream(file)) {

            byte[] buffer = new byte[1024];
            int count;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
        }
    }
}
