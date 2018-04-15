package cn.smbms.configuration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
@Configuration
public class SsmViewResolver implements ViewResolver {
	private Logger logger = Logger.getLogger(SsmViewResolver.class);
	@Override
	@Bean
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		   for(Map.Entry<Set<String>, ViewResolver> map : viewResolverMap.entrySet()){  
	           Set<String> suffixs = map.getKey();  
	           for(String suffix : suffixs){  
	               System.out.println(suffix+" == "+viewName);  
	               if (viewName.endsWith(suffix)){  
	                   ViewResolver viewResolver = map.getValue();  
	                   if(null != viewResolver){  
	                       if (logger.isDebugEnabled()) {  
	                            logger.debug("found viewResolver '" + viewResolver + "' for viewName '" + viewName+ "'");  
	                        }  
	                       return viewResolver.resolveViewName(viewName, locale);  
	                   }  
	               }  
	  
	           }  
	       }  
	       if(defaultViewResolver != null){  
	           return defaultViewResolver.resolveViewName(viewName+".jsp", locale);  
	       }  
	       // to allow for ViewResolver chaining  
	       return null;  
	    }  
	    private Map<Set<String>,ViewResolver> viewResolverMap = new HashMap<Set<String>,ViewResolver>();  
	    private ViewResolver defaultViewResolver = null;  
	    public Map<Set<String>, ViewResolver> getViewResolverMap() {  
	        return viewResolverMap;  
	    }  
	    public void setViewResolverMap(Map<Set<String>, ViewResolver> viewResolverMap) {  
	        this.viewResolverMap = viewResolverMap;  
	    }  
	    public ViewResolver getDefaultViewResolver() {  
	        return defaultViewResolver;  
	    }  
	    public void setDefaultViewResolver(ViewResolver defaultViewResolver) {  
	        this.defaultViewResolver = defaultViewResolver;  
	    } 
		
	}


