package app.domain.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DocumentCreateBindingModel {
    private String title;
    private String content;

    public DocumentCreateBindingModel() {
    }

    @NotNull
    @Size(min = 1)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @Size(min = 1)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
