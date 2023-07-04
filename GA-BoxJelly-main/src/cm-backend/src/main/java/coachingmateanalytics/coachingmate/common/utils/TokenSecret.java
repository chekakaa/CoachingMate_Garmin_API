package coachingmateanalytics.coachingmate.common.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * A utility class for handling token secrets.
 */
@Component
public class TokenSecret {

    /**
     * Retrieves the token secret from an HttpURLConnection's input stream.
     *
     * @param connection The HttpURLConnection containing the token secret.
     * @return A String containing the token secret.
     * @throws IOException If an error occurs while reading the input stream.
     */
    public String getTokenSecretFromStream(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        in.close();
        
        return response.toString();
    }
}
