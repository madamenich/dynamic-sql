package com.ksga.demo.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.ksga.demo.model.CV;
import com.ksga.demo.model.CustomSection;
import com.ksga.demo.repository.mapper.CVMapper;
import com.ksga.demo.supports.CVDynamicSqlSupport;
import com.ksga.demo.supports.handler.JsonCustomSectionHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.util.json.JSONParser;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.ksga.demo.supports.CVDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

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

   public int add(CV record) throws JSONException, JsonProcessingException {
       ObjectMapper objectMapper = new ObjectMapper();
       JsonCustomSectionHandler handler = new JsonCustomSectionHandler();
       String jsonExperience = objectMapper.writeValueAsString(record.getExperiences());
        InsertStatementProvider<CV> insertStatementProvider = insert(record)
                .into(cv)
                .map(cvName).toProperty("cvName")
                .map(experiences).toStringConstant(jsonExperience)
                .build()
                .render(RenderingStrategies.MYBATIS3);


//       GeneralInsertStatementProvider insertStatementProvider = insertInto(cv)
//               .set(cvName).toValue(record.getCvName())
//               .set(experiences).toValue("{\"title\":\"hha\",\"body\":\"hashdhsad\"}")
//               .build()
//               .render(RenderingStrategies.MYBATIS3);
//       System.out.println(insertStatementProvider.getInsertStatement());
       return mapper.insert(insertStatementProvider);


   }
}
