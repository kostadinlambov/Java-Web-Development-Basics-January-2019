package services.implementations;

import services.ViewsProvider;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.stream.Collectors;

public class ViewsProviderImpl implements ViewsProvider {
    @Override
    public String view(String viewName) throws IOException {
        var viewPath = MessageFormat.format("views/{0}.html", viewName);

        URL url = this.getClass().getClassLoader().getResource(viewPath);

        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        return Files.lines(Paths.get(file.getAbsolutePath()))
                .collect(Collectors.joining("\n"));
    }
}
