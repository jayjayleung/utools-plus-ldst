package com.jayjay.ldst.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jayjay
 * @date 2020/11/1
 * @Description: mybastis-plus 分页配置，由于内存分页，不用了
 */
@Deprecated
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MyBatusPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
