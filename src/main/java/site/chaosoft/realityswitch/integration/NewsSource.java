package site.chaosoft.realityswitch.integration;

import org.springframework.stereotype.Service;

import java.util.List;

public interface NewsSource {

    List<RSNewsArticle> searchArticles(String query, String[] newsSources, String mkt);

}
