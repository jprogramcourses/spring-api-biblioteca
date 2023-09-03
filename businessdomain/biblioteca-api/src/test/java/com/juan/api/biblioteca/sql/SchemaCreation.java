package com.juan.api.biblioteca.sql;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Action;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

public class SchemaCreation {
	
	@Test
	public void createBibliotecaSchema() {
		
		File f = new File("C:/programacion/hibernate");
		
		if(f.exists() || f.mkdir()) {
			Map<String, String> settings = new HashMap<>();
			settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
			settings.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();
			MetadataSources metadataSources = new MetadataSources(serviceRegistry);
			
			new LocalSessionFactoryBuilder(null, new PathMatchingResourcePatternResolver(), metadataSources).scanPackages("com.juan.api.biblioteca.entities");
			
			SchemaExport schemaExport = new SchemaExport();
			
			schemaExport.setOutputFile("C:/programacion/hibernate/" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_schema");
			schemaExport.setDelimiter(";");
			schemaExport.setFormat(true);
			
			schemaExport.execute(EnumSet.of(org.hibernate.tool.schema.TargetType.SCRIPT), Action.CREATE, metadataSources.buildMetadata());
		}
		
	}
	

}
