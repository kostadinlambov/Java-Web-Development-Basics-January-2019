package services;

import java.io.IOException;

public interface ViewsProvider {
    String view(String viewName) throws IOException;
}
