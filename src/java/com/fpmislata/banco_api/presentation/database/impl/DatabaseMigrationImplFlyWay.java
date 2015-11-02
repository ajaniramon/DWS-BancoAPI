package com.fpmislata.banco_api.presentation.database.impl;

import com.fpmislata.banco.persistence.jdbc.DataSourceFactory;
import com.fpmislata.banco_api.presentation.database.DatabaseMigration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseMigrationImplFlyWay implements DatabaseMigration {

    @Autowired
    DataSourceFactory dataSourceFactory;

    @Override
    public void migrate() {
        
        Flyway flyway = new Flyway();
        DataSource dataSource = dataSourceFactory.getDataSource();
        flyway.setDataSource(dataSource);
        flyway.setLocations("com.fpmislata.banco_api.presentation.database.scripts");
        flyway.setEncoding("UTF-8");
        
    }
}
