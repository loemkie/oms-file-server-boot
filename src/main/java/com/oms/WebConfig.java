package com.oms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.oms.servlet.PhotoDownloadServlet;
import com.oms.servlet.RecDownloadServlet;  
   

@Configuration  
public class WebConfig extends WebMvcConfigurerAdapter{  
	@Bean
	public FilterRegistrationBean corsFilter() {
	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	  CorsConfiguration config = new CorsConfiguration();
	  config.setAllowCredentials(true);
	  config.addAllowedOrigin("*");
	  config.addAllowedHeader("*");
	  config.addAllowedMethod("*");
	  source.registerCorsConfiguration("/**", config);
	  FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	  bean.setOrder(0);
	  return bean;
	}
   /* @Bean  
    public FilterRegistrationBean getCrossOriginControlFilter(){  
    	CrossOriginControl crossOriginControl = new CrossOriginControl();  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        registrationBean.setFilter(crossOriginControl);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/*");//拦截路径，可以添加多个  
        registrationBean.setUrlPatterns(urlPatterns);  
        registrationBean.setOrder(1);  
        return registrationBean;  
    }  */
    @Bean  
    public ServletRegistrationBean getPhotoDownloadServlet(){  
    	PhotoDownloadServlet downloadServlet = new PhotoDownloadServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(downloadServlet);  
        List<String> urlMappings=new ArrayList<String>();  
        urlMappings.add("/photo/*");////访问，可以添加多个  
        registrationBean.setUrlMappings(urlMappings);  
        registrationBean.setLoadOnStartup(1);  
        return registrationBean;  
    }
    @Bean  
    public ServletRegistrationBean getRecDownloadServlet(){  
    	RecDownloadServlet downloadServlet = new RecDownloadServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(downloadServlet);  
        List<String> urlMappings=new ArrayList<String>();  
        urlMappings.add("/rec/*");////访问，可以添加多个  
        registrationBean.setUrlMappings(urlMappings);  
        registrationBean.setLoadOnStartup(1);  
        return registrationBean;
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20000000000KB");
        factory.setMaxRequestSize("200000KB");
        return factory.createMultipartConfig();
    }
    
    /*@Bean  
    public ServletListenerRegistrationBean<EventListener> getDemoListener(){  
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();  
        registrationBean.setListener(new DemoListener());  
        //registrationBean.setOrder(1);  
        return registrationBean;  
    }  */
}