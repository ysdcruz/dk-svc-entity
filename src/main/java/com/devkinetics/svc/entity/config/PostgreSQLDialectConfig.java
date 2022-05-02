package com.devkinetics.svc.entity.config;

import org.hibernate.dialect.PostgreSQL92Dialect;

import java.sql.Types;

public class PostgreSQLDialectConfig extends PostgreSQL92Dialect {

    public PostgreSQLDialectConfig() {
        super();
        registerColumnType(Types.ARRAY, "json");
    }
}
