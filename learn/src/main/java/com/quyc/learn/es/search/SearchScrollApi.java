package com.quyc.learn.es.search;

import com.google.common.collect.Maps;
import com.quyc.learn.es.EsClientUtil;
import com.quyc.learn.es.entity.Person;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author quyuanchao
 * @date 2019/7/2 23:46
 * <p>Title: ElasticSearch Search Scroll Api</p>
 * <p>Description: </p>
 */
public class SearchScrollApi {
    private static RestHighLevelClient client = EsClientUtil.getClient();

    public static void main(String[] args) throws IOException {
        searchScroll();
//        test1();
    }

    public static void searchScroll() throws IOException {
        // 设置时效
        Scroll scroll = new Scroll(TimeValue.timeValueSeconds(5));
        String scrollId;
        SearchRequest searchRequest = new SearchRequest("person");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        HashMap<String, Object> params = Maps.newHashMap();
        for (int i = 0; i < 3; i++) {
            params.put("multiplier", i);
            searchSourceBuilder.query(QueryBuilders.scriptQuery(
                    new Script(ScriptType.INLINE, "painless", "def multiplier = params.multiplier;\n" +
                            "      long value = doc['age'].value % 3;\n" +
                            "      return value == multiplier;", params)));
            searchSourceBuilder.size(3);
            searchSourceBuilder.sort("salary", SortOrder.DESC);
            searchRequest.source(searchSourceBuilder);
            searchRequest.scroll(scroll);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            // 获取scrollId，后续搜索根据这个id进行搜索
            scrollId = searchResponse.getScrollId();
            System.out.println("scrollId = " + scrollId);
            SearchHit[] hits = searchResponse.getHits().getHits();
            // 循环搜索，直到搜索结束
            while (hits != null && hits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                SearchResponse searchScrollResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
                scrollId = searchScrollResponse.getScrollId();
                hits = searchScrollResponse.getHits().getHits();
                List<Person> searchResult = EsClientUtil.getSearchResult(searchScrollResponse, Person.class);
                System.out.println("searchResult = " + searchResult);
            }
            // 清理scroll上下文，若不及时清理会在时效到期自动清除
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.addScrollId(scrollId);
            ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
            boolean succeeded = clearScrollResponse.isSucceeded();
            System.out.println("succeeded = " + succeeded);
        }
    }

    public static void test1() throws IOException {
        SearchRequest searchRequest = new SearchRequest("buyer_portrait_calculate");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(100001);
        searchSourceBuilder.size(10);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest);
        System.out.println("search = " + search);
    }

}
