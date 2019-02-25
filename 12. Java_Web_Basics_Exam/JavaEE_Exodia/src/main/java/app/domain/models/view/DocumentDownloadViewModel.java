package app.domain.models.view;

public class DocumentDownloadViewModel {
    private String id;
    private String title;
    private String content;

    public DocumentDownloadViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.formatTitle();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.formatContent();
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String formatTitle(){
        return String.format("<div class=\"text-center\">%s</div>", this.title);

    }

    private String formatContent(){
        StringBuilder sbContent = new StringBuilder();
        String[] splitContent = this.content.split("\r\n");

        for (String s : splitContent) {
            if(s.startsWith("#")){
                sbContent.append(String.format("<h1>%s</h1>", s)).append(System.lineSeparator());
            }else if(s.startsWith("##")){
                sbContent.append(String.format("<h2>%s</h2>", s)).append(System.lineSeparator());
            }else if(s.startsWith("###")){
                sbContent.append(String.format("<h3>%s</h3>", s)).append(System.lineSeparator());
            }
            else if(s.startsWith("####")){
                sbContent.append(String.format("<h4>%s</h4>", s)).append(System.lineSeparator());
            }else if(s.startsWith("#####")){
                sbContent.append(String.format("<h5>%s</h5>", s)).append(System.lineSeparator());
            }else if(s.startsWith("######")){
                sbContent.append(String.format("<h6>%s</h6>", s)).append(System.lineSeparator());
            }else if(s.startsWith("*")){
                sbContent.append(String.format("<ul><li>%s</ul></li>", s)).append(System.lineSeparator());
            }
            String s1 = s.replaceAll("\\*\\*.*?\\*\\*", String.format("<p style=\"font-weight: bold;\">%s</p>", s.substring(s.indexOf("**") + 1, s.lastIndexOf("**"))));

            sbContent.append(s1).append(System.lineSeparator());
        }

        return sbContent.toString();
    }

    @Override
    public String toString() {
       return this.title.toString() + this.content.toString();
    }
}
