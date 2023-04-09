package site.chaosoft.realityswitch.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import site.chaosoft.realityswitch.integration.NewsSource;
import site.chaosoft.realityswitch.integration.RSNewsArticle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsSearchServiceImplTest {

    @Mock
    public NewsSource newsSource;
    public NewsSearchService newsSearchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        newsSearchService = new NewsSearchServiceImpl(newsSource);
    }

    @Test
    public void shouldReturnNewsInEnglishWhenNoCyrillicSymbolsPassed() {
        //given
        var query = "java";
        var euSources = new String[]{"euSource1", "euSource2"};
        var ruSources = new String[]{"ruSource1", "ruSource2"};
        ReflectionTestUtils.setField(newsSearchService, "euSources", euSources);
        ReflectionTestUtils.setField(newsSearchService, "ruSources", ruSources);
        Mockito.when(newsSource.searchArticles(query, euSources, "en-GB")).thenReturn(List.of(createNewsArticle("euArticle")));
        Mockito.when(newsSource.searchArticles(query, ruSources, "en-GB")).thenReturn(List.of(createNewsArticle("ruArticle")));

        //when
        var result = newsSearchService.searchForNews(query);
        //then
        Mockito.verify(newsSource).searchArticles(query, euSources, "en-GB");
        Mockito.verify(newsSource).searchArticles(query, ruSources, "en-GB");
        assertEquals(result.getEuNews().get(0).getName(),"euArticle");
        assertEquals(result.getRuNews().get(0).getName(),"ruArticle");

    }

    @Test
    public void shouldReturnNewsInEnglishWhenCyrillicSymbolsPassed() {
        //given
        var query = "жаба";
        var euSources = new String[]{"euSource1", "euSource2"};
        var ruSources = new String[]{"ruSource1", "ruSource2"};
        ReflectionTestUtils.setField(newsSearchService, "euSources", euSources);
        ReflectionTestUtils.setField(newsSearchService, "ruSources", ruSources);
        Mockito.when(newsSource.searchArticles(query, euSources, "ru-RU")).thenReturn(List.of(createNewsArticle("euArticle")));
        Mockito.when(newsSource.searchArticles(query, ruSources, "ru-RU")).thenReturn(List.of(createNewsArticle("ruArticle")));

        //when
        var result = newsSearchService.searchForNews(query);
        //then
        Mockito.verify(newsSource).searchArticles(query, euSources, "ru-RU");
        Mockito.verify(newsSource).searchArticles(query, ruSources, "ru-RU");
        assertEquals(result.getEuNews().get(0).getName(),"euArticle");
        assertEquals(result.getRuNews().get(0).getName(),"ruArticle");

    }

    private RSNewsArticle createNewsArticle(String name) {
        var article = new RSNewsArticle();
        article.setName(name);
        return article;
    }

}
