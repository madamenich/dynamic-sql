package com.ksga.demo.supports;

import com.ksga.demo.supports.handler.JsonCustomSectionHandler;
import com.ksga.demo.supports.handler.JsonRenderingStrategies;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;

import java.sql.JDBCType;

public class CVDynamicSqlSupport {



    public static final CV cv = new CV();
    public static final SqlColumn<String> uid = cv.uid;
    public static final SqlColumn<String>  cvName = cv.cvName;
    public static final SqlColumn<String> experiences = cv.experiences;
    public static final class  CV extends SqlTable{
        private final SqlColumn<String>cvName = column("cv_name",JDBCType.VARCHAR);
        private final SqlColumn<String> uid =  column("uid", JDBCType.INTEGER);
        private final SqlColumn<String> experiences = column("custom",JDBCType.VARCHAR).withRenderingStrategy(new JsonRenderingStrategies());
        public CV(){
            super("mn_cv");
        }
    }
}
