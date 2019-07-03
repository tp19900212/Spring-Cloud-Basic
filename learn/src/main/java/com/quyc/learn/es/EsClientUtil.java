package com.quyc.learn.es;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Created by quyuanchao on 2019/6/30 15:08.
 * <p>Title: ElasticSearch Config</p>
 * <p>Description: </p>
 */
//@Configuration
public class EsClientUtil {

    private static CredentialsProvider credentialsProvider;

    static {
        credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "Es!@#$%^"));
    }

    private static class RestHighLevelClientHolder {
        private static final RestHighLevelClient ES_CLIENT = new RestHighLevelClient(
                RestClient.builder(new HttpHost("es-cn-45916s405000dd54p.public.elasticsearch.aliyuncs.com",
                        9200, "http"))
                        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                            @Override
                            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            }
                        })
        );
    }

    private static class RestLowLevelClientHolder {
        private static final RestClient ES_LOW_LEVEL_CLIENT = RestClient.builder(
                new HttpHost("es-cn-45916s405000dd54p.public.elasticsearch.aliyuncs.com", 9200, "http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                        return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();
    }

    public static final RestHighLevelClient getClient() {
        return RestHighLevelClientHolder.ES_CLIENT;
    }

    public static final RestClient getLowLevelClient() {
        return RestLowLevelClientHolder.ES_LOW_LEVEL_CLIENT;
    }
}
