package com.springboot.review_msa.config;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

import static com.querydsl.core.types.Ops.StringOps.LPAD;

public class CustomDialect extends Oracle10gDialect {
    public CustomDialect() {
        super();
        this.registerFunction("ROWNUM", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "ROWNUM"));
    }
}
