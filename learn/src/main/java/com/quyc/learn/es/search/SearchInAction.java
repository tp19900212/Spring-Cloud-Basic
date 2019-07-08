package com.quyc.learn.es.search;

import com.google.common.collect.Lists;
import com.quyc.learn.es.BuyerPortraitQuery;
import com.quyc.learn.es.EsClientUtil;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.HasChildQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: andy
 * @create: 2019/7/8 19:22
 * @description:
 */
public class SearchInAction {

    private static RestHighLevelClient restHighLevelClient = EsClientUtil.getClient();

    public static void main(String[] args) throws IOException, ParseException {
        BuyerPortraitQuery query = new BuyerPortraitQuery();
        query.setShopId("SHP005405234151200242AC110002640");
//        query.setBuyerId("大好青年梁二蛋");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2019-04-02 11:56:21");
        Date minDate = DateUtils.addDays(date, -1);
        Date maxDate = DateUtils.addDays(date, 1);
//        query.setStartPayedTime(minDate);
//        query.setEndPayedTime(maxDate);
        query.setMatchRule(1);
//        query.setSex(1);
        query.setSexs(Lists.newArrayList(2));
        countSingleLabelBuyerPortrait(query);
//        test(query);
    }

    public static void countSingleLabelBuyerPortrait(BuyerPortraitQuery query) throws IOException {
        SearchRequest searchRequest = new SearchRequest("buyer_portrait_calculate");
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        // 查询父节点
        HasChildQueryBuilder hasChildQueryBuilder =
                new HasChildQueryBuilder("product", QueryBuilders.matchAllQuery(), ScoreMode.None);
        builder.must(hasChildQueryBuilder);
        builder.must(QueryBuilders.termsQuery("shopId.keyword", query.getShopId()));
        if (query.getMatchRule() != null && query.getMatchRule() == 1) {
            if (query.getStartPayedTime() != null) {
                builder.must(QueryBuilders.rangeQuery("gmtLastPayed").gte(query.getStartPayedTime().getTime()));
            }
            if (query.getEndPayedTime() != null) {
                builder.must(QueryBuilders.rangeQuery("gmtLastPayed").lte(query.getEndPayedTime().getTime()));
            }
            if (query.getStartConsulTime() != null) {
                builder.must(QueryBuilders.rangeQuery("gmtLastConsultation").gte(query.getStartConsulTime().getTime()));
            }
            if (query.getEndConsulTime() != null) {
                builder.must(QueryBuilders.rangeQuery("gmtLastConsultation").lte(query.getEndConsulTime().getTime()));
            }
            if (!CollectionUtils.isEmpty(query.getSexs())) {
                builder.must(QueryBuilders.termsQuery("sex", query.getSexs()));
            }
            if (!CollectionUtils.isEmpty(query.getProvinces())) {
                builder.must(QueryBuilders.termsQuery("province.keyword", query.getProvinces()));
            }
            if (!CollectionUtils.isEmpty(query.getCitys())) {
                builder.must(QueryBuilders.termsQuery("city.keyword", query.getCitys()));
            }
            if (!CollectionUtils.isEmpty(query.getAreas())) {
                builder.must(QueryBuilders.termsQuery("area.keyword", query.getAreas()));
            }
            if (query.getMinOrderPayed() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed").gte(query.getMinOrderPayed()));
            }
            if (query.getMaxOrderPayed() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed").lte(query.getMaxOrderPayed()));
            }
            if (query.getMinOrderPayed30() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed30").gte(query.getMinOrderPayed30()));
            }
            if (query.getMaxOrderPayed30() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed30").lte(query.getMaxOrderPayed30()));
            }
            if (query.getMinOrderPayed60() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed60").gte(query.getMinOrderPayed60()));
            }
            if (query.getMaxOrderPayed60() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed60").lte(query.getMaxOrderPayed60()));
            }
            if (query.getMinOrderPayed90() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed90").gte(query.getMinOrderPayed90()));
            }
            if (query.getMaxOrderPayed90() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed90").lte(query.getMaxOrderPayed90()));
            }
            if (query.getMinOrderPayed180() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed180").gte(query.getMinOrderPayed180()));
            }
            if (query.getMaxOrderPayed180() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed180").lte(query.getMaxOrderPayed180()));
            }
            if (query.getMinOrderCount() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount").gte(query.getMinOrderCount()));
            }
            if (query.getMaxOrderCount() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount").lte(query.getMaxOrderCount()));
            }
            if (query.getMinOrderCount30() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount30").gte(query.getMinOrderCount30()));
            }
            if (query.getMaxOrderCount30() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount30").lte(query.getMaxOrderCount30()));
            }
            if (query.getMinOrderCount60() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount60").gte(query.getMinOrderCount60()));
            }
            if (query.getMaxOrderCount60() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount60").lte(query.getMaxOrderCount60()));
            }
            if (query.getMinOrderCount90() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount90").gte(query.getMinOrderCount90()));
            }
            if (query.getMaxOrderCount90() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount90").lte(query.getMaxOrderCount90()));
            }
            if (query.getMinOrderCount180() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount180").gte(query.getMinOrderCount180()));
            }
            if (query.getMaxOrderCount180() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount180").lte(query.getMaxOrderCount180()));
            }
            if (query.getMinAverageProduct() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct").gte(query.getMinAverageProduct()));
            }
            if (query.getMaxAverageProduct() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct").lte(query.getMaxAverageProduct()));
            }
            if (query.getMinAverageProduct30() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct30").gte(query.getMinAverageProduct30()));
            }
            if (query.getMaxAverageProduct30() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct30").lte(query.getMaxAverageProduct30()));
            }
            if (query.getMinAverageProduct60() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct60").gte(query.getMinAverageProduct60()));
            }
            if (query.getMaxAverageProduct60() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct60").lte(query.getMaxAverageProduct60()));
            }
            if (query.getMinAverageProduct90() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct90").gte(query.getMinAverageProduct90()));
            }
            if (query.getMaxAverageProduct90() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct90").lte(query.getMaxAverageProduct90()));
            }
            if (query.getMinAverageProduct180() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct180").gte(query.getMinAverageProduct180()));
            }
            if (query.getMaxAverageProduct180() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct180").lte(query.getMaxAverageProduct180()));
            }
            if (query.getMinProductCount() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount").gte(query.getMinProductCount()));
            }
            if (query.getMaxProductCount() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount").lte(query.getMaxProductCount()));
            }
            if (query.getMinProductCount30() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount30").gte(query.getMinProductCount30()));
            }
            if (query.getMaxProductCount30() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount30").lte(query.getMaxProductCount30()));
            }
            if (query.getMinProductCount60() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount60").gte(query.getMinProductCount60()));
            }
            if (query.getMaxProductCount60() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount60").lte(query.getMaxProductCount60()));
            }
            if (query.getMinProductCount90() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount90").gte(query.getMinProductCount90()));
            }
            if (query.getMaxProductCount90() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount90").lte(query.getMaxProductCount90()));
            }
            if (query.getMinProductCount180() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount180").gte(query.getMinProductCount180()));
            }
            if (query.getMaxProductCount180() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount180").lte(query.getMaxProductCount180()));
            }
            if (query.getOrderConsultTag() != null && query.getNonOrderConsultTag() == null) {
                // todo andy ElasticSearch 与运算
            }
            if (query.getOrderConsultTag() == null && query.getNonOrderConsultTag() != null) {

            }
            if (query.getOrderConsultTag() != null && query.getNonOrderConsultTag() != null) {

            }
        } else {
            if (query.getStartPayedTime() != null && query.getEndPayedTime() != null) {
                builder.should(QueryBuilders.rangeQuery("gmtLastPayed")
                        .gte(query.getStartPayedTime().getTime())
                        .lte(query.getEndPayedTime().getTime()));
            }
            if (query.getStartPayedTime() != null && query.getEndPayedTime() == null) {
                builder.should(QueryBuilders.rangeQuery("gmtLastPayed").gte(query.getStartPayedTime().getTime()));
            }
            if (query.getStartPayedTime() == null && query.getEndPayedTime() != null) {
                builder.should(QueryBuilders.rangeQuery("gmtLastPayed").gte(query.getEndPayedTime().getTime()));
            }
            if (query.getStartConsulTime() != null && query.getEndConsulTime() != null) {
                builder.should(QueryBuilders.rangeQuery("gmtLastConsultation")
                        .gte(query.getStartConsulTime().getTime())
                        .lte(query.getEndConsulTime().getTime()));
            }
            if (query.getStartConsulTime() != null && query.getEndConsulTime() == null) {
                builder.should(QueryBuilders.rangeQuery("gmtLastConsultation").gte(query.getStartConsulTime().getTime()));
            }
            if (query.getStartConsulTime() == null && query.getEndConsulTime() != null) {
                builder.should(QueryBuilders.rangeQuery("gmtLastConsultation").gte(query.getEndConsulTime().getTime()));
            }
            if (!CollectionUtils.isEmpty(query.getSexs())) {
                builder.should(QueryBuilders.termsQuery("sex.keyword", query.getSexs()));
            }
            if (!CollectionUtils.isEmpty(query.getProvinces())) {
                builder.should(QueryBuilders.termsQuery("province.keyword", query.getProvinces()));
            }
            if (!CollectionUtils.isEmpty(query.getCitys())) {
                builder.should(QueryBuilders.termsQuery("city.keyword", query.getCitys()));
            }
            if (!CollectionUtils.isEmpty(query.getAreas())) {
                builder.should(QueryBuilders.termsQuery("area.keyword", query.getAreas()));
            }
            if (query.getMinOrderPayed() != null && query.getMaxOrderPayed() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed")
                        .gte(query.getMinOrderPayed())
                        .lte(query.getMaxOrderPayed()));
            }
            if (query.getMinOrderPayed() != null && query.getMaxOrderPayed() == null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed").gte(query.getMinOrderPayed()));
            }
            if (query.getMinOrderPayed() == null && query.getMaxOrderPayed() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed").lte(query.getMaxOrderPayed()));
            }
            if (query.getMinOrderPayed30() != null && query.getMaxOrderPayed30() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed30")
                        .gte(query.getMinOrderPayed30())
                        .lte(query.getMaxOrderPayed30()));
            }
            if (query.getMinOrderPayed30() != null && query.getMaxOrderPayed30() == null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed30").gte(query.getMinOrderPayed30()));
            }
            if (query.getMinOrderPayed30() == null && query.getMaxOrderPayed30() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed30").lte(query.getMaxOrderPayed30()));
            }
            if (query.getMinOrderPayed60() != null && query.getMaxOrderPayed60() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed60")
                        .gte(query.getMinOrderPayed60())
                        .lte(query.getMaxOrderPayed60()));
            }
            if (query.getMinOrderPayed60() != null && query.getMaxOrderPayed60() == null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed60").gte(query.getMinOrderPayed60()));
            }
            if (query.getMinOrderPayed60() == null && query.getMaxOrderPayed60() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed60").lte(query.getMaxOrderPayed60()));
            }
            if (query.getMinOrderPayed90() != null && query.getMaxOrderPayed90() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed90")
                        .gte(query.getMinOrderPayed90()).lte(query.getMaxOrderPayed90()));
            }
            if (query.getMinOrderPayed90() != null && query.getMaxOrderPayed90() == null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed90").gte(query.getMinOrderPayed90()));
            }
            if (query.getMinOrderPayed90() == null && query.getMaxOrderPayed90() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed90").lte(query.getMaxOrderPayed90()));
            }
            if (query.getMinOrderPayed180() != null && query.getMaxOrderPayed180() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed180_").gte(query.getMinOrderPayed180())
                        .lte(query.getMaxOrderPayed180()));
            }
            if (query.getMinOrderPayed180() != null && query.getMaxOrderPayed180() == null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed180").gte(query.getMinOrderPayed180()));
            }
            if (query.getMinOrderPayed180() == null && query.getMaxOrderPayed180() != null) {
                builder.should(QueryBuilders.rangeQuery("orderPayed180").lte(query.getMaxOrderPayed180()));
            }
            if (query.getMaxOrderCount() != null && query.getMinOrderCount() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount")
                        .gte(query.getMaxOrderCount())
                        .lte(query.getMinOrderCount()));
            }
            if (query.getMaxOrderCount() != null && query.getMinOrderCount() == null) {
                builder.should(QueryBuilders.rangeQuery("orderCount").gte(query.getMaxOrderCount()));
            }
            if (query.getMaxOrderCount() == null && query.getMinOrderCount() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount").lte(query.getMinOrderCount()));
            }
            if (query.getMaxOrderCount30() != null && query.getMinOrderCount30() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount30")
                        .gte(query.getMaxOrderCount30())
                        .lte(query.getMinOrderCount30()));
            }
            if (query.getMaxOrderCount30() != null && query.getMinOrderCount30() == null) {
                builder.should(QueryBuilders.rangeQuery("orderCount30").gte(query.getMaxOrderCount30()));
            }
            if (query.getMaxOrderCount30() == null && query.getMinOrderCount30() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount30").lte(query.getMinOrderCount30()));
            }
            if (query.getMaxOrderCount60() != null && query.getMinOrderCount60() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount60")
                        .gte(query.getMaxOrderCount60())
                        .lte(query.getMinOrderCount60()));
            }
            if (query.getMaxOrderCount60() != null && query.getMinOrderCount60() == null) {
                builder.should(QueryBuilders.rangeQuery("orderCount60").gte(query.getMaxOrderCount60()));
            }
            if (query.getMaxOrderCount60() == null && query.getMinOrderCount60() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount60").lte(query.getMinOrderCount60()));
            }
            if (query.getMaxOrderCount90() != null && query.getMinOrderCount90() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount90")
                        .gte(query.getMaxOrderCount90())
                        .lte(query.getMinOrderCount90()));
            }
            if (query.getMaxOrderCount90() != null && query.getMinOrderCount90() == null) {
                builder.should(QueryBuilders.rangeQuery("orderCount90").gte(query.getMaxOrderCount90()));
            }
            if (query.getMaxOrderCount90() == null && query.getMinOrderCount90() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount90").lte(query.getMinOrderCount90()));
            }
            if (query.getMaxOrderCount180() != null && query.getMinOrderCount180() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount180_")
                        .gte(query.getMaxOrderCount180())
                        .lte(query.getMinOrderCount180()));
            }
            if (query.getMaxOrderCount180() != null && query.getMinOrderCount180() == null) {
                builder.should(QueryBuilders.rangeQuery("orderCount180").gte(query.getMaxOrderCount180()));
            }
            if (query.getMaxOrderCount180() == null && query.getMinOrderCount180() != null) {
                builder.should(QueryBuilders.rangeQuery("orderCount180").lte(query.getMinOrderCount180()));
            }
            if (query.getMaxAverageProduct() != null && query.getMinAverageProduct() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct")
                        .gte(query.getMaxAverageProduct())
                        .lte(query.getMinAverageProduct()));
            }
            if (query.getMaxAverageProduct() != null && query.getMinAverageProduct() == null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct").gte(query.getMaxAverageProduct()));
            }
            if (query.getMaxAverageProduct() == null && query.getMinAverageProduct() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct").lte(query.getMinAverageProduct()));
            }
            if (query.getMaxAverageProduct30() != null && query.getMinAverageProduct30() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct30")
                        .gte(query.getMaxAverageProduct30())
                        .lte(query.getMinAverageProduct30()));
            }
            if (query.getMaxAverageProduct30() != null && query.getMinAverageProduct30() == null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct30").gte(query.getMaxAverageProduct30()));
            }
            if (query.getMaxAverageProduct30() == null && query.getMinAverageProduct30() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct30").lte(query.getMinAverageProduct30()));
            }
            if (query.getMaxAverageProduct60() != null && query.getMinAverageProduct60() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct60")
                        .gte(query.getMaxAverageProduct60())
                        .lte(query.getMinAverageProduct60()));
            }
            if (query.getMaxAverageProduct60() != null && query.getMinAverageProduct60() == null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct60").gte(query.getMaxAverageProduct60()));
            }
            if (query.getMaxAverageProduct60() == null && query.getMinAverageProduct60() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct60").lte(query.getMinAverageProduct60()));
            }
            if (query.getMaxAverageProduct90() != null && query.getMinAverageProduct90() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct90")
                        .gte(query.getMaxAverageProduct90())
                        .lte(query.getMinAverageProduct90()));
            }
            if (query.getMaxAverageProduct90() != null && query.getMinAverageProduct90() == null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct90").gte(query.getMaxAverageProduct90()));
            }
            if (query.getMaxAverageProduct90() == null && query.getMinAverageProduct90() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct90").lte(query.getMinAverageProduct90()));
            }
            if (query.getMaxAverageProduct180() != null && query.getMinAverageProduct180() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct180_")
                        .gte(query.getMaxAverageProduct180())
                        .lte(query.getMinAverageProduct180()));
            }
            if (query.getMaxAverageProduct180() != null && query.getMinAverageProduct180() == null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct180").gte(query.getMaxAverageProduct180()));
            }
            if (query.getMaxAverageProduct180() == null && query.getMinAverageProduct180() != null) {
                builder.should(QueryBuilders.rangeQuery("averageProduct180").lte(query.getMinAverageProduct180()));
            }
            if (query.getMaxProductCount() != null && query.getMinProductCount() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount")
                        .gte(query.getMaxProductCount())
                        .lte(query.getMinProductCount()));
            }
            if (query.getMaxProductCount() != null && query.getMinProductCount() == null) {
                builder.should(QueryBuilders.rangeQuery("productCount").gte(query.getMaxProductCount()));
            }
            if (query.getMaxProductCount() == null && query.getMinProductCount() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount").lte(query.getMinProductCount()));
            }
            if (query.getMaxProductCount30() != null && query.getMinProductCount30() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount30")
                        .gte(query.getMaxProductCount30())
                        .lte(query.getMinProductCount30()));
            }
            if (query.getMaxProductCount30() != null && query.getMinProductCount30() == null) {
                builder.should(QueryBuilders.rangeQuery("productCount30").gte(query.getMaxProductCount30()));
            }
            if (query.getMaxProductCount30() == null && query.getMinProductCount30() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount30").lte(query.getMinProductCount30()));
            }
            if (query.getMaxProductCount60() != null && query.getMinProductCount60() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount60")
                        .gte(query.getMaxProductCount60())
                        .lte(query.getMinProductCount60()));
            }
            if (query.getMaxProductCount60() != null && query.getMinProductCount60() == null) {
                builder.should(QueryBuilders.rangeQuery("productCount60").gte(query.getMaxProductCount60()));
            }
            if (query.getMaxProductCount60() == null && query.getMinProductCount60() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount60").lte(query.getMinProductCount60()));
            }
            if (query.getMaxProductCount90() != null && query.getMinProductCount90() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount90")
                        .gte(query.getMaxProductCount90())
                        .lte(query.getMinProductCount90()));
            }
            if (query.getMaxProductCount90() != null && query.getMinProductCount90() == null) {
                builder.should(QueryBuilders.rangeQuery("productCount90").gte(query.getMaxProductCount90()));
            }
            if (query.getMaxProductCount90() == null && query.getMinProductCount90() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount90").lte(query.getMinProductCount90()));
            }
            if (query.getMaxProductCount180() != null && query.getMinProductCount180() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount180_")
                        .gte(query.getMaxProductCount180())
                        .lte(query.getMinProductCount180()));
            }
            if (query.getMaxProductCount180() != null && query.getMinProductCount180() == null) {
                builder.should(QueryBuilders.rangeQuery("productCount180").gte(query.getMaxProductCount180()));
            }
            if (query.getMaxProductCount180() == null && query.getMinProductCount180() != null) {
                builder.should(QueryBuilders.rangeQuery("productCount180").lte(query.getMinProductCount180()));
            }
            if (query.getOrderConsultTag() != null) {
                // todo andy 与运算
            }
            if (query.getNonOrderConsultTag() != null) {
                // todo andy 与运算
            }
        }
        if (query.getSex() != null) {
            builder.must(QueryBuilders.termQuery("sex", query.getSex()));
        }
        if (StringUtils.isNotBlank(query.getProvince())) {
            builder.must(QueryBuilders.termQuery("province.keyword", query.getProvince()));
        }
        if (StringUtils.isNotBlank(query.getCity())) {
            builder.must(QueryBuilders.termQuery("city.keyword", query.getCity()));
        }
        if (query.getBlackEnable() != null && query.getBlackEnable() != 0L) {
            // todo andy 与运算
        }
        if (query.getBlackEnable() != null && query.getBlackEnable() == 0L) {
            // todo andy 与运算
        }
        if (query.getLabelTag() != null) {
            // todo andy 与运算
        }
        BuyerPortraitQuery.OriginalCondition originalCondition = query.getOriginalCondition();
        if (originalCondition != null) {
            if (originalCondition.getStartPayedTime() != null) {
                builder.must(QueryBuilders.rangeQuery("gmtLastPayed").gte(originalCondition.getStartPayedTime().getTime()));
            }
            if (originalCondition.getEndPayedTime() != null) {
                builder.must(QueryBuilders.rangeQuery("gmtLastPayed").lte(originalCondition.getEndPayedTime().getTime()));
            }
            if (originalCondition.getStartConsulTime() != null) {
                builder.must(QueryBuilders.rangeQuery("gmtLastConsultation").gte(originalCondition.getStartConsulTime().getTime()));
            }
            if (originalCondition.getEndConsulTime() != null) {
                builder.must(QueryBuilders.rangeQuery("gmtLastConsultation").lte(originalCondition.getEndConsulTime().getTime()));
            }
            if (originalCondition.getMinOrderPayed() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed").gte(originalCondition.getMinOrderPayed()));
            }
            if (originalCondition.getMaxOrderPayed() != null) {
                builder.must(QueryBuilders.rangeQuery("orderPayed").lte(originalCondition.getMaxOrderPayed()));
            }
            if (originalCondition.getMinOrderCount() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount").gte(originalCondition.getMinOrderCount()));
            }
            if (originalCondition.getMaxOrderCount() != null) {
                builder.must(QueryBuilders.rangeQuery("orderCount").lte(originalCondition.getMaxOrderCount()));
            }
            if (originalCondition.getMinAverageProduct() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct").gte(originalCondition.getMinAverageProduct()));
            }
            if (originalCondition.getMaxAverageProduct() != null) {
                builder.must(QueryBuilders.rangeQuery("averageProduct").lte(originalCondition.getMaxAverageProduct()));
            }
            if (originalCondition.getMinProductCount() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount").gte(originalCondition.getMinProductCount()));
            }
            if (originalCondition.getMaxProductCount() != null) {
                builder.must(QueryBuilders.rangeQuery("productCount").lte(originalCondition.getMaxProductCount()));
            }
        }
        if (StringUtils.isNotBlank(query.getBuyerId())) {
            builder.must(QueryBuilders.prefixQuery("buyerId.keyword", query.getBuyerId()));
        }
        if (query.getSmsStatus() != null) {
            builder.mustNot(QueryBuilders.termQuery("phone.keyword", ""));
        }
        // 若没有 .should()条件配置，则下面的条件将永远不能实现
//        builder.minimumShouldMatch(1);
        searchRequest.source(new SearchSourceBuilder().query(builder));
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("searchResponse = " + searchResponse);
    }
}
