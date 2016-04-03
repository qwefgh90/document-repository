package documentweb.servlet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"documentweb.repository", "documentweb.service", "documentweb.controller"})
@EnableJpaRepositories(basePackages = {"documentweb.repository"})
public class ServletContext extends WebMvcConfigurerAdapter{

	@Value("${sphinx.url}")
	String sphinxUrl;

	@Value("${mysql.driverClassName}")
	String driverClassName;
	@Value("${mysql.url}")
	String url;
	@Value("${mysql.databaseName}")
	String dbname;
	@Value("${mysql.username}")
	String username;
	@Value("${mysql.password}")
	String password;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/staticfiles/**").addResourceLocations("/staticfiles/");
	}

	@Bean
	public DataSource sphinxDataSource() throws SQLException {
		BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(sphinxUrl);
		ds.setUsername("");
		ds.setPassword("");
		return ds;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		Resource[] locations = new Resource[]{new ClassPathResource("mysql.properties")};
		configurer.setLocations(locations);
		return configurer;
	}

	@Override
	public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
		converters.add(converter());
	}

	@Bean
	MappingJackson2HttpMessageConverter converter() {
		return new MappingJackson2HttpMessageConverter();
	}


	@Bean
	public DataSource dataSource() throws SQLException {
		Connection conn = null;
		Statement s = null;
		try{
			conn = DriverManager.getConnection(
					url +"?user="+username+"&password="+password+""); 
			s=conn.createStatement();
			s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbname);
		}finally{
			if(s!=null)
				s.close();
			if(conn!=null)
				conn.close();
		}
		
		BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url + dbname);
		ds.setUsername(username);
		ds.setPassword(password);

		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setPackagesToScan("documentweb.repository");

		// persistence 설정 jpaProperties
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");

		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setJpaProperties(jpaProperties);
		return lef;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		hibernateJpaVendorAdapter.setShowSql(true);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
}
