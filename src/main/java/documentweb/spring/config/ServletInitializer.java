package documentweb.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import documentweb.servlet.config.RootContext;
import documentweb.servlet.config.ServletContext;

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		//root-context.xml
		return new Class<?>[]{RootContext.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//myservlet-context.xml
		return new Class<?>[]{ServletContext.class};
	}

	@Override
	protected String[] getServletMappings() {
		//root url
		return new String[]{"/"};
	}

}
