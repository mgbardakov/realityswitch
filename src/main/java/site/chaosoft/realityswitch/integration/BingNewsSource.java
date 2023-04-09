package site.chaosoft.realityswitch.integration;

import com.microsoft.azure.cognitiveservices.search.newssearch.BingNewsSearchAPI;
import com.microsoft.azure.cognitiveservices.search.newssearch.models.Freshness;
import com.microsoft.azure.cognitiveservices.search.newssearch.models.SearchOptionalParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.chaosoft.realityswitch.integration.mappers.NewsArticleMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BingNewsSource implements NewsSource {
    private final BingNewsSearchAPI bingNewsSearchAPI;
    private final NewsArticleMapper mapper;

    @Override
    public List<RSNewsArticle> searchArticles(String query, String[] newsSources, String mkt) {
        var sources = Arrays.stream(newsSources)
                .map("site:%s"::formatted)
                .collect(Collectors.joining(" OR "));
        var fullQuery = "%s (%s)".formatted(query, sources);
        var options = new SearchOptionalParameter().withFreshness(Freshness.WEEK).withMarket(mkt);
        var searchResults = bingNewsSearchAPI.bingNews().search(fullQuery, options);
        return Optional.ofNullable(searchResults.value()).stream()
                .flatMap(List::stream)
                .map(mapper::toRSNewsArticle)
                .toList();
    }
}
