package com.quyc.servicetwo.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * @author quyuanchao
 * @date 2019/7/30 23:41
 * <p>Title: </p>
 * <p>Description: 分片策略</p>
 */
public class DemoTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each : collection) {
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}
