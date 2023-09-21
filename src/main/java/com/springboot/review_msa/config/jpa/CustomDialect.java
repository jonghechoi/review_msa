package com.springboot.review_msa.config.jpa;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;


public class CustomDialect extends Oracle10gDialect {
    public CustomDialect() {
        super();
        this.registerFunction("ROWNUM", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "ROWNUM"));
    }
}
