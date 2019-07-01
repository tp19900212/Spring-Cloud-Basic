package com.quyc.learn.es.search;

import com.quyc.learn.es.EsClientUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: andy
 * @create: 2019/7/1 19:35
 * @description: ElasticSearch Search Api
 */
public class SearchApi {

    private static RestHighLevelClient client = EsClientUtil.getClient();

    public static void main(String[] args) throws IOException {
//        search();
        searchQuery();
    }

    /**
     * 简单搜索
     *
     * @throws IOException
     */
    public static void search() throws IOException {
        // 创建一个空的SearchRequest，则对所有index进行搜索，传入index可对该index进行搜索
        SearchRequest searchRequest = new SearchRequest("posts");
        // 可通过 SearchSourceBuilder 设置任意查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // A query that matches on all documents.
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // Controls how to deal with unavailable concrete indices (closed or missing)
        // 忽略不存在的indices，允许出现没有indices
        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        // 设置搜索偏好，默认随机选择shard进行搜索，可设置：_local,_primary
        // or a custom value, which guarantees that the same order will be used across different requests.
        searchRequest.preference("_local");
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("searchResponse = " + searchResponse);
    }

    /**
     * 通过 SearchSourceBuilder 条件化搜索
     *
     * @throws IOException the io exception
     */
    public static void searchQuery() throws IOException {
        // 创建一个空的SearchRequest，则对所有index进行搜索，传入index可对该index进行搜索
        SearchRequest searchRequest = new SearchRequest("posts");
        // 可通过 SearchSourceBuilder 设置任意查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 可设置任意查询类型
//        searchSourceBuilder.query(QueryBuilders.termQuery("message","trying out Elasticsearch"));
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("message", "trying");
        // 模糊搜索
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        // todo 还需研究 设置模糊匹配的前缀长度
        matchQueryBuilder.prefixLength(3);
        // todo 还需研究 最大匹配词条数
        matchQueryBuilder.maxExpansions(10);
        // 使用QueryBuilders实现同样的设置
        QueryBuilders.matchQuery("title", "Current status and upcoming")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
        searchSourceBuilder.query(matchQueryBuilder);
        // 包含哪些field
        String[] includeFields = {"user", "title", "*date"};
        // 不包含
        String[] excludeFields = {"message"};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);
        // 根据评分倒序排列
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.ASC));
        // todo 还需研究 根据user这个字段进行正序排列
        searchSourceBuilder.sort(new FieldSortBuilder("user").order(SortOrder.ASC));
        // 设置偏移量
        searchSourceBuilder.from(0);
        // 设置步长
//        searchSourceBuilder.size(2);
        // 设置超时时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("searchResponse = " + searchResponse);
    }

    /**
     * 通过 SearchSourceBuilder 条件化搜索
     *
     * @throws IOException the io exception
     */
    public static void searchQueryHighLight() throws IOException {
        // 创建一个空的SearchRequest，则对所有index进行搜索，传入index可对该index进行搜索
        SearchRequest searchRequest = new SearchRequest("posts");
        // 可通过 SearchSourceBuilder 设置任意查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highLightTitle = new HighlightBuilder.Field("title");
        highLightTitle.highlighterType("unified");
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("searchResponse = " + searchResponse);
    }


}
