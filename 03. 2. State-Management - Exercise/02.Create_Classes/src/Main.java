import handlers.ResponseHandler;
import http.HttpRequest;
import http.HttpRequestImpl;
import http.HttpResponse;
import http.HttpResponseImpl;
import io.Reader;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String input = Reader.readInput();

        HttpRequest httpRequest = new HttpRequestImpl(input);
        HttpResponse httpResponse = new HttpResponseImpl();

        ResponseHandler responseHandler = new ResponseHandler(httpRequest, httpResponse);
        byte[] responseBytes = responseHandler.handleResponse();

        if (!httpResponse.getCookies().isEmpty()) {
            StringBuilder cookiesSb = new StringBuilder();
            cookiesSb.append("###Cookie attributes: ").append(System.lineSeparator());
            httpResponse.getCookies()
                    .forEach((key, value) ->
                            cookiesSb
                                    .append(String.format("%s <-> %s",
                                            value.getKey(),
                                            value.getValue()))
                                    .append(System.lineSeparator())
                    );
            System.out.println(cookiesSb.toString());
        } else {
            System.out.println("Cookie header is missing!\r\n");
        }


        String responseString = new String(responseBytes);
        System.out.println("###HTTP Response: ");
        System.out.println(responseString);

        System.out.println("\r\n###Response bytes: ");
        System.out.println(Arrays.toString(responseBytes));
    }
}
