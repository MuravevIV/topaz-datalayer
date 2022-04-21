package com.ilyamur.topaz.datalayer.generator;

import com.ilyamur.topaz.datalayer.core.entity.Role;
import com.ilyamur.topaz.datalayer.core.entity.User;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Ignore;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Helper class to create SQL schema that corresponds to the dialect.
 */
public class SchemaGenerator {

    private static final String SCHEMA_OUTPUT_PATH = "target/schemas/schema.sql";

    @Test
    @Ignore
    public void generate() {

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.hbm2ddl.auto", "create")
                .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect")
                .applySetting("hibernate.id.new_generator_mappings", "true")
                .build();

        MetadataSources metadataSources = new MetadataSources(standardRegistry);
        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(Role.class);
        MetadataImplementor metadata = (MetadataImplementor) metadataSources
                .getMetadataBuilder()
                .build();

        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setFormat(true);
        schemaExport.setOutputFile(SCHEMA_OUTPUT_PATH);
        schemaExport.createOnly(EnumSet.of(TargetType.SCRIPT), metadata);
    }
}
