package main.java.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, String> headersMap = new LinkedHashMap<>();
    private static Map<String, String> requestBodyParametersMap = new LinkedHashMap<>();
    private static StringBuilder response = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String input = readInput();
        String[] inputTokens = input.split("\\r\\n");

        List<String> validUrls = getValidUrls(inputTokens[0]);
        List<String> requestLineTokens = getRequestLine(inputTokens[1]);

        getHeaders(inputTokens);
        buildResponse(input, validUrls, requestLineTokens);
    }

    private static void buildResponse(String inputLines, List<String> validUrls, List<String> requestLineTokens) {
        String method = requestLineTokens.get(0);
        String url = requestLineTokens.get(1);

        if (!validUrls.contains(url)) {
            response.append("HTTP/1.1 404 Not Found").append(System.lineSeparator());
            addResponseHeaders();
            response.append(System.lineSeparator());
            response.append("The requested functionality was not found.")
                    .append(System.lineSeparator());
        } else if (!headersMap.containsKey("Authorization")) {
            response.append("HTTP/1.1 401 Unauthorized").append(System.lineSeparator());
            addResponseHeaders();
            response.append(System.lineSeparator());
            response.append("You are not authorized to access the requested functionality.")
                    .append(System.lineSeparator());
        } else if ("POST".equals(method)) {
            String requestBody = getRequestBodyParameters(inputLines);

            if (requestBody == null) {
                response.append("HTTP/1.1 400 Bad Request").append(System.lineSeparator());
                addResponseHeaders();
                response.append(System.lineSeparator());
                response.append("There was an error with the requested functionality due to malformed request.")
                        .append(System.lineSeparator());
            } else {
                List<String> reqBodyParameters = new ArrayList<>();

                int index = 1;
                for (Map.Entry<String, String> currentParameter : requestBodyParametersMap.entrySet()) {
                    if (index == 1) {
                        reqBodyParameters.add(currentParameter.getValue());
                    } else {
                        reqBodyParameters.add(currentParameter.getKey() + " - " + currentParameter.getValue().trim());
                    }
                    index++;
                }

                byte[] authorizations = Base64.getDecoder().decode(headersMap.get("Authorization").split("\\s+")[1]);
                String username = new String(authorizations);

                response.append("HTTP/1.1 200 OK").append(System.lineSeparator());
                addResponseHeaders();
                response.append(System.lineSeparator());
                response.append(String.format("Greetings %s!" +
                                " You have successfully created %s with %s, %s.", username,
                        reqBodyParameters.get(0), reqBodyParameters.get(1), reqBodyParameters.get(2)))
                        .append(System.lineSeparator());
            }
        } else if ("GET".equals(method)) {
            response.append("HTTP/1.1 200 OK").append(System.lineSeparator());
            addResponseHeaders();
            response.append(System.lineSeparator());
            response.append("Greetings {username}!")
                    .append(System.lineSeparator());
        }

        System.out.print(response.toString());
    }

    private static String getRequestBodyParameters(String inputTokens) {
        String[] bodyTokens = inputTokens.split("\\r\\n\\r\\n");
        if(bodyTokens.length > 1){
            String requestBody = inputTokens.split("\\r\\n\\r\\n")[1];
            String[] requestBodyParameters = requestBody.split("&");

            for (String requestBodyParameter : requestBodyParameters) {
                String[] parameterKvp = requestBodyParameter.split("=");
                requestBodyParametersMap.putIfAbsent(parameterKvp[0], parameterKvp[1]);
            }

            return requestBody;
        }

        return null;
    }

    private static void addResponseHeaders() {
        for (Map.Entry<String, String> header : headersMap.entrySet()) {
            String headerName = header.getKey();
            if ("Date".equals(headerName) || "Host".equals(headerName) ||
                    "Content-Type".equals(headerName)) {
                response
                        .append(headerName)
                        .append(": ")
                        .append(header.getValue())
                        .append(System.lineSeparator());
            }
        }
    }

    private static void getHeaders(String[] inputTokens) {
        for (int i = 2; i < inputTokens.length; i++) {
            if ("".equals(inputTokens[i])) {
                break;
            }
            String[] headerTokens = inputTokens[i].split(":\\s");
            headersMap.putIfAbsent(headerTokens[0], headerTokens[1]);
        }
    }

    private static String readInput() throws IOException {
        StringBuilder input = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null && line.length() > 0) {
            input.append(line).append(System.lineSeparator());
        }

        input.append(System.lineSeparator());

        if ((line = reader.readLine()) != null && line.length() > 0) {
            input.append(line).append(System.lineSeparator());
        }

        return input.toString();
    }

    private static List<String> getRequestLine(String requestLine) throws IOException {
        return Arrays.asList(requestLine.split("\\s+"));

    }

    private static List<String> getValidUrls(String validUrls) throws IOException {
        return Arrays.asList(validUrls.split("\\s+"));
    }
}
