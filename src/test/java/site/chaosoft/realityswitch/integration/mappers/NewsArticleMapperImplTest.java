package site.chaosoft.realityswitch.integration.mappers;

import com.microsoft.azure.cognitiveservices.search.newssearch.models.NewsArticle;
import org.junit.jupiter.api.Test;
import site.chaosoft.realityswitch.integration.RSNewsArticle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class NewsArticleMapperImplTest {

    private final NewsArticleMapperImpl newsArticleMapper = new NewsArticleMapperImpl();

    @Test
    public void testToRSNewsArticle() {
       var name = "name";
       var url = "url";
       var datePublished = "datePublished";
       var description = "description";
       var newsArticle = createNewsArticleMock(name, url, datePublished, description);
       var expectedRsNewsArticle = createRsNewsArticleAndFillWithValues(name, url, datePublished, description);
       assertEquals(expectedRsNewsArticle, newsArticleMapper.toRSNewsArticle(newsArticle));
    }

    @Test
    public void testToRSNewsArticleWithNull() {
        assertNull(newsArticleMapper.toRSNewsArticle(null));
    }

    private NewsArticle createNewsArticleMock(String name, String url, String datePublished, String description) {
        NewsArticle newsArticle = mock(NewsArticle.class);
        when(newsArticle.name()).thenReturn(name);
        when(newsArticle.url()).thenReturn(url);
        when(newsArticle.datePublished()).thenReturn(datePublished);
        when(newsArticle.description()).thenReturn(description);
        return newsArticle;
    }

    private RSNewsArticle createRsNewsArticleAndFillWithValues(String name, String url, String datePublished, String description) {
        RSNewsArticle rsNewsArticle = new RSNewsArticle();
        rsNewsArticle.setName(name);
        rsNewsArticle.setUrl(url);
        rsNewsArticle.setDatePublished(datePublished);
        rsNewsArticle.setDescription(description);
        return rsNewsArticle;
    }

}
