package com.jackson.springboot.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @author zhoujunhui
 * @description: 数据库的配置文件
 * @date 2021/5/10 16:52
 */
@Configuration
@Slf4j
@MapperScan(basePackages ="com.jackson.springboot.demo.mapper" , sqlSessionFactoryRef = "sqlSessionFactory")
@PropertySource("classpath:config/jdbc.properties")
@Data
public class DBConfig {


    @Value("${db.url1}")
    private String url1;
    @Value("${db.username1}")
    private String username1;
    @Value("${db.password1}")
    private String password1;
    @Value("${db.driverName1}")
    private String driverName1;

    @Value("${db.url2}")
    private String url2;
    @Value("${db.username2}")
    private String username2;
    @Value("${db.password2}")
    private String password2;
    @Value("${db.driverName2}")
    private String driverName2;

    @Bean("dataSource")
    public DataSource dataSource() {
        DataSource dataSource = null;
        try{
            Map<String, DataSource> dataSourceMap = new HashMap<>();

            DruidDataSource source1 = new DruidDataSource();
            source1.setUrl(url1);
            source1.setUsername(username1);
            source1.setPassword(password1);
            source1.setDriverClassName(driverName1);
            dataSourceMap.put("db0",source1);

            DruidDataSource source2 = new DruidDataSource();
            source2.setUrl(url2);
            source2.setUsername(username2);
            source2.setPassword(password2);
            source2.setDriverClassName(driverName2);
            dataSourceMap.put("db1",source2);

            ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
            shardingRuleConfig.setTableRuleConfigs(TableShardingConfig.getTableRules());

            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
        }catch (Exception e){
            log.error("配置shard-jdbc失败", e);
        }
        return dataSource;
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier(value = "dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        //打印sql
        config.setLogImpl(StdOutImpl.class);
        sessionFactoryBean.setConfiguration(config);
        return sessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }
}
