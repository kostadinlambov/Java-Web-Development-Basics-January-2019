package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Reader {
    private Reader(){}

    public static String readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder input = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null && line.length() > 0) {
            input.append(line).append(System.lineSeparator());
        }

        input.append(System.lineSeparator());

        if ((line = reader.readLine()) != null && line.length() > 0) {
            input.append(line);
        }

        return input.toString();
    }
}
