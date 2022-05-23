package com.ksga.demo.supports;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public class CVDynamicSqlSupport {



    public static final CV cv = new CV();
    public static final SqlColumn<String> uid = cv.uid;
    public static final SqlColumn<String>  cvName = cv.cvName;
    public static final SqlColumn<String> experiences = cv.experiences;
    public static final class  CV extends SqlTable{
        private final SqlColumn<String>cvName = column("cv_name",JDBCType.VARCHAR);
        private final SqlColumn<String> uid =  column("uid", JDBCType.VARCHAR);
        private final SqlColumn<String> experiences = column("custom",JDBCType.VARCHAR);

        public CV(){
            super("mn_cv");
        }
    }
}
