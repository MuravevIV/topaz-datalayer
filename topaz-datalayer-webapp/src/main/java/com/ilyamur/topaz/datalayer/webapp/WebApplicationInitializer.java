package com.ilyamur.topaz.datalayer.webapp;

import com.ilyamur.topaz.datalayer.core.ApplicationProfile;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebappConfiguration.class};
    }

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        super.onStartup(context);
        context.setInitParameter("spring.profiles.active", ApplicationProfile.DEVELOP);
    }
}
