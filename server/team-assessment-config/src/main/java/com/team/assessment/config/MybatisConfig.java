package com.team.assessment.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 MyBatis Plus 自动填充功能
 */
@Configuration
public class MybatisConfig {
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                long current = System.currentTimeMillis();
                this.setFieldValByName("create_time", current, metaObject);
                this.setFieldValByName("update_time", current, metaObject);
//                if(metaObject.hasSetter("creatorId")) {
//                    Class clazz = metaObject.getSetterType("creatorId");
//                    if (clazz == String.class) {
//                        this.setFieldValByName("creatorId", "", metaObject);
//                    } else {
//                        this.setFieldValByName("creatorId", UserUtils.getUid(), metaObject);
//                    }
//                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
            }
        };
    }
}
