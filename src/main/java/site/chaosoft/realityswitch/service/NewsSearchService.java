package site.chaosoft.realityswitch.service;

import site.chaosoft.realityswitch.controllers.dto.SearchResponseDTO;

public interface NewsSearchService {

    SearchResponseDTO searchForNews(String query);
}
