package site.chaosoft.realityswitch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.chaosoft.realityswitch.controllers.dto.SearchResponseDTO;
import site.chaosoft.realityswitch.integration.NewsSource;

@Service
@RequiredArgsConstructor
public class NewsSearchServiceImpl implements NewsSearchService {

    private static final String EU_MKT = "en-GB";
    private static final String RU_MKT = "ru-RU";
    @Value("${news-sources.eu}")
    private String[] euSources;
    @Value("${news-sources.ru}")
    private String[] ruSources;
    private final NewsSource newsSource;

    @Override
    public SearchResponseDTO searchForNews(String query) {
        var mkt = this.containsCyrillic(query) ? RU_MKT : EU_MKT;
        var euNews = newsSource.searchArticles(query, euSources, mkt);
        var ruNews = newsSource.searchArticles(query, ruSources, mkt);
        return new SearchResponseDTO(euNews, ruNews);
    }

    private boolean containsCyrillic(String query) {
        return query.chars()
                .mapToObj(Character.UnicodeBlock::of)
                .anyMatch(Character.UnicodeBlock.CYRILLIC::equals);
    }
}
