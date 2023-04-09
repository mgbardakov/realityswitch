package site.chaosoft.realityswitch.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.chaosoft.realityswitch.controllers.dto.SearchResponseDTO;
import site.chaosoft.realityswitch.service.NewsSearchService;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsSearchService newsSearchService;

    @GetMapping("/news/search")
    public SearchResponseDTO getNews(@RequestParam("q") String query) {
        return this.newsSearchService.searchForNews(query);
    }
}
