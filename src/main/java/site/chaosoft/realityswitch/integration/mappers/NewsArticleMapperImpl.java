package site.chaosoft.realityswitch.integration.mappers;

import com.microsoft.azure.cognitiveservices.search.newssearch.models.NewsArticle;
import org.springframework.stereotype.Service;
import site.chaosoft.realityswitch.integration.RSNewsArticle;

@Service
public class NewsArticleMapperImpl implements NewsArticleMapper {
    @Override
    public RSNewsArticle toRSNewsArticle(NewsArticle newsArticle) {
        if ( newsArticle == null ) {
            return null;
        }

        RSNewsArticle rSNewsArticle = new RSNewsArticle();
        rSNewsArticle.setName(newsArticle.name());
        rSNewsArticle.setUrl(newsArticle.url());
        if(newsArticle.image() != null && newsArticle.image().thumbnail() != null) {
            rSNewsArticle.setThumbnailUrl(newsArticle.image().thumbnail().contentUrl());
        }
        rSNewsArticle.setDatePublished(newsArticle.datePublished());
        rSNewsArticle.setDescription(newsArticle.description());

        return rSNewsArticle;
    }
}
