package site.chaosoft.realityswitch.integration;

import com.microsoft.azure.cognitiveservices.search.newssearch.BingNewsSearchAPI;
import com.microsoft.azure.cognitiveservices.search.newssearch.models.NewsArticle;
import com.microsoft.azure.cognitiveservices.search.newssearch.models.NewsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import site.chaosoft.realityswitch.integration.mappers.NewsArticleMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BingNewsSourceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    public BingNewsSearchAPI bingNewsSearchAPI;
    @Mock
    public NewsArticleMapper newsArticleMapper;
    public BingNewsSource bingNewsSource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bingNewsSource = new BingNewsSource(bingNewsSearchAPI, newsArticleMapper);
    }

    @Test
    public void shouldReturnNews() {
        //given
        var query = "java";
        var sources = new String[]{"source1", "source2"};
        var mkt = "en-US";
        var fullQuery = "java (site:source1 OR site:source2)";
        var newsModelResponse = mock(NewsModel.class);
        var expectedNewsArticle = new NewsArticle();
        when(newsModelResponse.value()).thenReturn(List.of(expectedNewsArticle));
        when(bingNewsSearchAPI.bingNews().search(eq(fullQuery), any())).thenReturn(newsModelResponse);
        //when
        var result = bingNewsSource.searchArticles(query, sources, mkt);
        //then
        Mockito.verify(bingNewsSearchAPI.bingNews()).search(eq(fullQuery), any());
        Mockito.verify(newsArticleMapper).toRSNewsArticle(expectedNewsArticle);
        assertEquals(1, result.size());
    }
}
