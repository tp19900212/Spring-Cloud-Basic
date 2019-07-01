package com.quyc.learn.es;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.concurrent.TimeUnit;

/**
 * @author: andy
 * @create: 2019/7/1 14:55
 * @description: ElasticSearch BulkProcessorApi
 */
@Slf4j
public class BulkProcessorApi {

    private static RestHighLevelClient client = EsClientUtil.getClient();

    public static void main(String[] args) throws InterruptedException {
        bulkProcessor();
    }

    public static void bulkProcessor() throws InterruptedException {
        // 每一个BulkRequest执行时都会触发该Listener
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                int numberOfActions = request.numberOfActions();
                log.debug("Executing bulk [{}] with {} requests",
                        executionId, numberOfActions);
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  BulkResponse response) {
                if (response.hasFailures()) {
                    log.warn("Bulk [{}] executed with failures", executionId);
                } else {
                    log.info("Bulk [{}] completed in {} milliseconds",
                            executionId, response.getTook().getMillis());
                }
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request,
                                  Throwable failure) {
                log.error("Failed to execute bulk", failure);
            }
        };

        // 构建BulkProcessor
        BulkProcessor.Builder builder = BulkProcessor.builder((bulkRequest, bulkResponseActionListener)
                -> client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, bulkResponseActionListener), listener);
        // 多少个操作之后刷新一个新的BulkRequest，默认1000，-1：disable
        builder.setBulkActions(500);
        // 多大数据量之后刷新一个新的BulkRequest，默认5M，-1：disable
        builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
        // 并行Request数量，默认1，0：只允许一个Request
        builder.setConcurrentRequests(0);
        // 隔多少时间刷新一个新的BulkRequest，默认无
        builder.setFlushInterval(TimeValue.timeValueSeconds(10L));
        // 补偿策略，当一个BulkRequest被拒绝时，1s后重试3次，初始化为 BackoffPolicy.exponentialBackoff()
        builder.setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(1L), 3));
        BulkProcessor bulkProcessor = builder.build();
        // 准备创建index数据
        IndexRequest one = new IndexRequest("posts", "doc", "8").
                source(XContentType.JSON, "title",
                        "In which order are my Elasticsearch queries executed?").opType(DocWriteRequest.OpType.CREATE);
        IndexRequest two = new IndexRequest("posts", "doc", "9")
                .source(XContentType.JSON, "title",
                        "Current status and upcoming changes in Elasticsearch");
        IndexRequest three = new IndexRequest("posts", "doc", "10")
                .source(XContentType.JSON, "title",
                        "The Future of Federated Search in Elasticsearch");
        bulkProcessor.add(one).add(two).add(three);
        // 直接关闭
//        bulkProcessor.close();
        // 定时关闭，true：即是结束所有BulkRequest发送完毕，false：相反
        boolean terminated = bulkProcessor.awaitClose(30L, TimeUnit.SECONDS);

    }

}
