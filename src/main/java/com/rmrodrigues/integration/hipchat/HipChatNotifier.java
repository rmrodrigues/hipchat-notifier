package com.rmrodrigues.integration.hipchat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.rmrodrigues.integration.hipchat.exceptions.UnableToSendHipChatMsgException;

/**
 * The Class HipchatNotifier.
 */
public class HipChatNotifier {

    /** The default url. */
    private static String DEFAULT_URL = "https://api.hipchat.com";

    /** The uri palceholder. */
    private static String URI_PALCEHOLDER = "v2/room/{0}/notification";

    /** The base url. */
    private String baseUrl;

    /**
     * Instantiates a new hipchat notifier.
     */
    public HipChatNotifier() {
        baseUrl = DEFAULT_URL;
    }

    /**
     * Instantiates a new hipchat notifier.
     *
     * @param baseUrl the base url
     */
    public HipChatNotifier(String baseUrl) {
        super();
        this.baseUrl = baseUrl;
    }

    /**
     * Send message to Hipchat Room.
     *
     * @param idOrRoomID the id or room id
     * @param authToken the auth token
     * @param hipchatMessage the hipchat message
     * @return true, if successful
     * @throws UnableToSendHipChatMsgException the unable to send hip chat msg exception
     */
    public boolean send(String idOrRoomID, String authToken, HipChatMessage hipchatMessage)
        throws UnableToSendHipChatMsgException {

        // Validations
        if (idOrRoomID == null || "".equals(idOrRoomID.trim()) || idOrRoomID.length() < 1 || idOrRoomID.length() > 100) {
            throw new IllegalArgumentException("Attribute idOrRoomID is required.");
        }

        if (authToken == null || "".equals(authToken.trim())) {
            throw new IllegalArgumentException("Attribute authToken is required.");
        }

        try {
            // Build the url
            StringBuffer urlBuilder = new StringBuffer();
            urlBuilder.append(this.baseUrl);
            if (baseUrl != null && !baseUrl.endsWith("/")) {
                urlBuilder.append("/");
            }
            // Replace the path parameter
            urlBuilder.append(MessageFormat.format(URI_PALCEHOLDER, idOrRoomID));

            // Create URL object
            URL obj = new URL(urlBuilder.toString());
            // Get the connection
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            // Set HTTP Headers
            con.setRequestProperty("content-type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + authToken);

            // Set the request type
            con.setRequestMethod("POST");

            // Send POST data
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            // Map HipchatMessage to JSON format data
            final Gson gson = new Gson();
            final String jsonString = gson.toJson(hipchatMessage);
            wr.write(jsonString.getBytes("utf-8"), 0, jsonString.getBytes("UTF-8").length);
            wr.flush();
            wr.close();

            // Get the HTTP Response Status Code
            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return responseCode == 204;
        } catch (Exception e) {
            throw new UnableToSendHipChatMsgException(e.getLocalizedMessage());
        }

    }
}
