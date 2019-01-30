package handlers;

import http.HttpRequest;
import http.HttpResponse;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class ResponseHandler {
    private HttpResponse httpResponse;
    private HttpRequest httpRequest;

    public ResponseHandler(HttpRequest httpRequest, HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
        this.httpRequest = httpRequest;
    }

    public byte[] handleResponse() {
        String method = this.httpRequest.getMethod();
        String url = this.httpRequest.getRequestUrl();
        String responseBody = "";

        if (!this.httpRequest.getCookieMap().isEmpty()) {
            this.httpResponse.setCookies(this.httpRequest.getCookieMap());
        }

        if (!this.httpRequest.getHeaders().containsKey("Authorization")) {
            this.httpResponse.setStatusCode(401);
            addResponseHeaders();
            responseBody = "You are not authorized to access the requested functionality.";
        } else if ("POST".equals(method)) {
            Map<String, String> bodyParameters = this.httpRequest.getBodyParameters();

            if (bodyParameters.size() == 0) {
                this.httpResponse.setStatusCode(400);
                addResponseHeaders();
                responseBody = "There was an error with the requested functionality due to malformed request.";

            } else {
                List<String> reqBodyParameters = new ArrayList<>();

                int index = 1;
                for (Map.Entry<String, String> currentParameter : this.httpRequest.getBodyParameters().entrySet()) {
                    if (index == 1) {
                        reqBodyParameters.add(currentParameter.getValue());
                    } else {
                        reqBodyParameters.add(currentParameter.getKey() + " - " + currentParameter.getValue().trim());
                    }
                    index++;
                }

                this.httpResponse.setStatusCode(200);
                addResponseHeaders();
                responseBody = String.format("Greetings %s!" +
                                " You have successfully created %s with %s, %s.", this.getUsername(),
                        reqBodyParameters.get(0), reqBodyParameters.get(1), reqBodyParameters.get(2));
            }
        } else if ("GET".equals(method)) {
            this.httpResponse.setStatusCode(200);
            addResponseHeaders();
            responseBody = String.format("Greetings %s!", this.getUsername());
        }

        this.httpResponse.setContent(responseBody.getBytes());
        return this.httpResponse.getResponseBytes();
    }

    private String getUsername() {
        byte[] authorizations = Base64.getDecoder()
                .decode(this.httpRequest.getHeaders().get("Authorization").split("\\s+")[1]);
        return new String(authorizations);
    }

    private void addResponseHeaders() {
        for (Map.Entry<String, String> header : this.httpRequest.getHeaders().entrySet()) {
            this.httpResponse.addHeader(header.getKey(), header.getValue());
        }
    }
}
