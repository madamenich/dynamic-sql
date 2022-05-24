package com.ksga.demo.supports.handler;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.MyBatis3RenderingStrategy;

public class JsonRenderingStrategies extends MyBatis3RenderingStrategy {

    @Override
    public String getFormattedJdbcPlaceholder(BindableColumn<?> column, String prefix, String parameterName) {
        return super.getFormattedJdbcPlaceholder(column, prefix, parameterName)+"::json";
    }
}
