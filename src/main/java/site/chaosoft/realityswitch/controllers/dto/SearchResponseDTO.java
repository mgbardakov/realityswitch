package site.chaosoft.realityswitch.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import site.chaosoft.realityswitch.integration.RSNewsArticle;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchResponseDTO {
    private List<RSNewsArticle> euNews;
    private List<RSNewsArticle> ruNews;
}
