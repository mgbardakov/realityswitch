package site.chaosoft.realityswitch.integration;

import com.microsoft.azure.cognitiveservices.search.newssearch.BingNewsSearchAPI;
import com.microsoft.azure.cognitiveservices.search.newssearch.implementation.BingNewsSearchAPIImpl;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BingSearchConfiguration {

    private static final String AZURE_SUB_KEY_HEADER = "Ocp-Apim-Subscription-Key";

    @Bean
    public static BingNewsSearchAPI bingNewsSearchAPI(final ServiceClientCredentials serviceClientCredentials, @Value("${integration.azure.search.url}") String searchUrl) {
        return new BingNewsSearchAPIImpl(searchUrl,
                serviceClientCredentials);
    }

    @Bean
    public static ServiceClientCredentials serviceClientCredentials(@Value("${integration.azure.search.news.subscription-key}") String subscriptionKey) {
        return builder -> builder.addNetworkInterceptor(
                chain -> {
                    Request request;
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader(AZURE_SUB_KEY_HEADER, subscriptionKey);
                    request = requestBuilder.build();
                    return chain.proceed(request);
                });
    }
}
