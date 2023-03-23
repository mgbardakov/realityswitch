package site.chaosoft.realityswitch.integration;

import java.util.List;

public interface NewsSource {

    List<RSNewsArticle> searchArticles(String query, String category, List<String> newsSources);

}
