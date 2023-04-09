package site.chaosoft.realityswitch.integration.mappers;

import com.microsoft.azure.cognitiveservices.search.newssearch.models.NewsArticle;
import site.chaosoft.realityswitch.integration.RSNewsArticle;


public interface NewsArticleMapper {

    RSNewsArticle toRSNewsArticle(NewsArticle newsArticle);
}
