package com.quyc.learn.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Created by quyuanchao on 2019/6/30 15:08.
 * <p>Title: ElasticSearch Config</p>
 * <p>Description: </p>
 */
//@Configuration
public class EsClientUtil {

    private static class RestHighLevelClientHolder {
        private static final RestHighLevelClient ES_CLIENT = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.175.128", 9200, "http"))
        );
    }

    public static final RestHighLevelClient getClient() {
        return RestHighLevelClientHolder.ES_CLIENT;
    }

}
