package site.chaosoft.realityswitch.integration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RSNewsArticle {
    private String name;
    private String url;
    private String thumbnailUrl;
    private String datePublished;
    private String description;
}
