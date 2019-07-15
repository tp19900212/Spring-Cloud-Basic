package com.quyc.learn.es.search;

import com.quyc.learn.es.EsClientUtil;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @author quyuanchao
 * @date 2019/7/2 23:46
 * <p>Title: ElasticSearch Search Scroll Api</p>
 * <p>Description: </p>
 */
public class SearchScrollApi {
    private static RestHighLevelClient client = EsClientUtil.getClient();

    public static void main(String[] args) throws IOException, InterruptedException {
        searchScroll();
    }

    public static void searchScroll() throws IOException, InterruptedException {
        // 设置时效
        Scroll scroll = new Scroll(TimeValue.timeValueSeconds(5));
        SearchRequest searchRequest = new SearchRequest("person");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(1);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(scroll);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        // 获取scrollId，后续搜索根据这个id进行搜索
        String scrollId = searchResponse.getScrollId();
        System.out.println("scrollId = " + scrollId);
        SearchHit[] hits = searchResponse.getHits().getHits();
        // 循环搜索，直到搜索结束
        while (hits != null && hits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            SearchResponse searchScrollResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchScrollResponse.getScrollId();
            System.out.println("scrollId = " + scrollId);
            hits = searchScrollResponse.getHits().getHits();
            System.out.println("hits.length = " + hits.length);
        }
        // 清理scroll上下文，若不及时清理会在时效到期自动清除
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        boolean succeeded = clearScrollResponse.isSucceeded();
        System.out.println("succeeded = " + succeeded);
    }


}
