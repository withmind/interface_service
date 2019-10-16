package com.bootdo.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.bootdo.common.db.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author DGD
 * @date 2018/2/6.
 */

//@MapperScan({"com.warm.system.mapper*"})
@Configuration
@MapperScan("com.bootdo.*.dao")
public class MybatisPlusConfig {

    @Value("${spring.datasource.druid.use}")
    private String usedb;

    @Autowired
    private Environment env;

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
        return paginationInterceptor;
    }

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

   /* @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1" )
    public DataSource db1 () {
        return DruidDataSourceBuilder.create().build();
    }*/

  /*  @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2" )
    public DataSource db2 () {
        return DruidDataSourceBuilder.create().build();
    }*/
    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource ( ) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map< Object, Object > targetDataSources = new HashMap<>();
        String[] dbCodes = usedb.split(",");

        for(String dbCode:dbCodes){
            DruidDataSource tempDataSource = DruidDataSourceBuilder.create().build(env,"spring.datasource.druid."+dbCode+"");

            String dbUrl = env.getProperty("spring.datasource.druid."+dbCode+".url");
            String username = env.getProperty("spring.datasource.druid."+dbCode+".username");
            String password = env.getProperty("spring.datasource.druid."+dbCode+".password");
            String driverClassName = env.getProperty("spring.datasource.druid."+dbCode+".driver-class-name");

            int initialSize = Integer.parseInt(env.getProperty("spring.datasource.druid."+dbCode+".initialSize"));
            int minIdle = Integer.parseInt(env.getProperty("spring.datasource.druid."+dbCode+".minIdle"));
            int maxActive = Integer.parseInt(env.getProperty("spring.datasource.druid."+dbCode+".maxActive"));


            boolean removeAbandoned = Boolean.parseBoolean(env.getProperty("spring.datasource.druid."+dbCode+".removeAbandoned"));
            int removeAbandonedTimeout = Integer.parseInt(env.getProperty("spring.datasource.druid."+dbCode+".removeAbandonedTimeout"));
            boolean logAbandoned = Boolean.parseBoolean(env.getProperty("spring.datasource.druid."+dbCode+".logAbandoned"));

            //int maxWait = env.getProperty("spring.datasource.druid."+dbCode+".initialSize");

            tempDataSource.setUrl(dbUrl);
            tempDataSource.setUsername(username);
            tempDataSource.setPassword(password);
            tempDataSource.setDriverClassName(driverClassName);

            //configuration
            tempDataSource.setInitialSize(initialSize);
            tempDataSource.setMinIdle(minIdle);
            tempDataSource.setMaxActive(maxActive);

            tempDataSource.setRemoveAbandoned(removeAbandoned);
            tempDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
            tempDataSource.setLogAbandoned(logAbandoned);
            /*tempDataSource.setMaxWait(maxWait);
            tempDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            tempDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            tempDataSource.setValidationQuery(validationQuery);
            tempDataSource.setTestWhileIdle(testWhileIdle);
            tempDataSource.setTestOnBorrow(testOnBorrow);
            tempDataSource.setTestOnReturn(testOnReturn);
            tempDataSource.setPoolPreparedStatements(poolPreparedStatements);
            tempDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);*/

            targetDataSources.put(dbCode, tempDataSource );
            if("master".equals(dbCode)){
                dynamicDataSource.setDefaultTargetDataSource(tempDataSource);
            }
        }
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource());

        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/**/*Mapper.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }

   @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(0);
        conf.setMetaObjectHandler(new MyMetaObjectHandler());
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        return conf;
    }



}
