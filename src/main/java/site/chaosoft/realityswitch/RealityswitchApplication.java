package site.chaosoft.realityswitch;

import com.microsoft.azure.cognitiveservices.search.newssearch.BingNewsSearchAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import site.chaosoft.realityswitch.controllers.dto.Data;

@SpringBootApplication
public class RealityswitchApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealityswitchApplication.class, args);
    }

}
