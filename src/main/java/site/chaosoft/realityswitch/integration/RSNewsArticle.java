package site.chaosoft.realityswitch.integration;


import lombok.Data;

@Data
public class RSNewsArticle {
    private String name;
    private String url;
    private String thumbnailUrl;
    private String datePublished;
    private String description;
}
