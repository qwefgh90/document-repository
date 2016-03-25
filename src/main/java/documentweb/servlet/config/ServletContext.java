package documentweb.servlet.config;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"documentweb.controller", "documentweb.repository", "documentweb.service"})
public class ServletContext extends WebMvcConfigurerAdapter{

	@Value("${mysql.driverClassName}")
	String driverClassName;
	@Value("${sphinx.url}")
	String sphinxUrl;
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
}
