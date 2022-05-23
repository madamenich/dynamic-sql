package com.ksga.demo.service;

import com.ksga.demo.model.CV;
import com.ksga.demo.repository.mapper.CVMapper;
import com.ksga.demo.supports.CVDynamicSqlSupport;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.ksga.demo.supports.CVDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.insert;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

@Service
public class CVServiceImp {
    @Autowired
    private CVMapper mapper;
    @Autowired
    SqlSessionFactory sqlSession;
    BasicColumn[]  columns = BasicColumn.columnList(cv.allColumns());


    public List<CV> findAll(){
        SelectStatementProvider statement = select(columns).from(cv).build().render(RenderingStrategies.MYBATIS3);
        return mapper.selectMany(statement);
    }

   public int add(CV record) throws JSONException {
       JSONObject jsonObject = new JSONObject(Arrays.stream(record.getExperiences().toArray()).toArray().toString());

        InsertStatementProvider<CV> insertStatementProvider = insert(record)
                .into(cv)
                .map(cvName).toProperty("cvName")
                .map(experiences).toStringConstant(jsonObject.toString()).build()
                .render(RenderingStrategies.MYBATIS3);

        return mapper.insert(insertStatementProvider);
   }
}
