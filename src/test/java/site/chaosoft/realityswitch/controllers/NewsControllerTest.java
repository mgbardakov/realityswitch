package site.chaosoft.realityswitch.controllers;

import com.microsoft.azure.cognitiveservices.search.newssearch.BingNewsSearchAPI;
import com.microsoft.azure.cognitiveservices.search.newssearch.models.NewsArticle;
import com.microsoft.azure.cognitiveservices.search.newssearch.models.NewsModel;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import site.chaosoft.realityswitch.controllers.dto.SearchResponseDTO;
import site.chaosoft.realityswitch.integration.RSNewsArticle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NewsControllerTest {

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private BingNewsSearchAPI bingNewsSearchAPI;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void shouldReturnNews() throws Exception {
        //given
        var name = "Article 1";
        var url = "http://article1.com";
        var thumbnailUrl = "http://article1.com/image";
        var date = "2020-01-01";
        var description = "Description";
        var euNews = createResponseArticleList(name, url, thumbnailUrl, date, description);
        var ruNews = createResponseArticleList(name, url, thumbnailUrl, date, description);
        var searchResponseDTO = new SearchResponseDTO(euNews, ruNews);
        var stubNewsArticles = createStubNewsArticles(name, url, thumbnailUrl, date, description);
        when(bingNewsSearchAPI.bingNews().search(anyString(), any())).thenReturn(stubNewsArticles);

        //when
        var response = restTemplate.getForEntity("/news/search?q=java", SearchResponseDTO.class);

        //then
        assert response.getStatusCode().is2xxSuccessful();
        assertEquals(response.getBody(), searchResponseDTO);
    }

    private static NewsModel createStubNewsArticles(String name, String url, String thumbnailUrl, String date, String description) {
        var newsModel = mock(NewsModel.class, Answers.RETURNS_DEEP_STUBS);
        var newsArticles = List.of(createMockNewsArticle(name, url, thumbnailUrl, date, description));
        when(newsModel.value()).thenReturn(newsArticles);
        return newsModel;
    }

    private static NewsArticle createMockNewsArticle(String name, String url, String thumbnailUrl, String date, String description) {
        var newArticle = mock(NewsArticle.class, Answers.RETURNS_DEEP_STUBS);
        when(newArticle.name()).thenReturn(name);
        when(newArticle.url()).thenReturn(url);
        when(newArticle.image().thumbnail().contentUrl()).thenReturn(thumbnailUrl);
        when(newArticle.datePublished()).thenReturn(date);
        when(newArticle.description()).thenReturn(description);
        return newArticle;
    }

    public List<RSNewsArticle> createResponseArticleList(String name, String url, String thumbnailUrl, String date, String description) {
        var newsArticle = new RSNewsArticle();
        newsArticle.setName(name);
        newsArticle.setUrl(url);
        newsArticle.setThumbnailUrl(thumbnailUrl);
        newsArticle.setDatePublished(date);
        newsArticle.setDescription(description);
        return List.of(newsArticle);
    }

}
