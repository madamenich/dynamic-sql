package com.ksga.demo.repository.mapper;

import com.ksga.demo.model.CV;
import com.ksga.demo.supports.CVDynamicSqlSupport;
import com.ksga.demo.supports.handler.JsonCustomSectionHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonSelectMapper;

import java.util.List;

@Mapper
public interface CVMapper  extends CommonSelectMapper, CommonInsertMapper<CV> {

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id ="CVResult", value = {
            @Result(column = "cv_name",property = "cvName"),
            @Result(column = "custom",property ="experiences",jdbcType = JdbcType.VARCHAR, typeHandler = JsonCustomSectionHandler.class)
    })
    public List<CV> selectMany(SelectStatementProvider statementProvider);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<CV> insertStatement);
}
