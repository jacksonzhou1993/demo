package com.jackson.springboot.demo.config;



import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;


import java.util.ArrayList;
import java.util.List;


@Slf4j
public class TableShardingConfig {


    public static List<TableRuleConfiguration> getTableRules() {
        List<TableRuleConfiguration> rules = new ArrayList<>();
        rules.add(getOrderRule());
        return rules;
    }

    /**
     * t_order表分表规则
     * @return
     */
    private static TableRuleConfiguration getOrderRule() {
        //逻辑表t_order
        TableRuleConfiguration demoConfig = new TableRuleConfiguration("t_order", "db${0..1}.t_order_${0..1}");
        //分库规则：task_id 取模
        demoConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("task_id", "db${task_id % 2}"));
        //分表规则：id 取模
        demoConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id","t_order_${id % 2}"));
        return demoConfig;
    }
}


