package com.ppcredit.bamboo.backend.config;

import java.util.Arrays;
import java.util.EnumSet;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlet.InstrumentedFilter;
import com.codahale.metrics.servlets.MetricsServlet;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class WebConfigurer implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Inject
    private Environment env;

    @Inject
    private PPCreditProperties ppcProperties;

    @Autowired(required = false)
    private MetricRegistry metricRegistry;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (env.getActiveProfiles().length != 0) {
            log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
        }
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);
        initMetrics(servletContext, disps);
        initDruid(servletContext, disps);
        log.info("Web application fully configured");
    }

    /**
     * Customize the Servlet engine: Mime types, the document root, the cache.
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("html", "text/html;charset=utf-8");
        mappings.add("json", "text/html;charset=utf-8");
        container.setMimeMappings(mappings);
    }

    /**
     * Initializes Metrics.
     */
    private void initMetrics(ServletContext servletContext, EnumSet<DispatcherType> disps) {
        log.debug("Initializing Metrics registries");
        servletContext.setAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE,
            metricRegistry);
        servletContext.setAttribute(MetricsServlet.METRICS_REGISTRY,
            metricRegistry);

        log.debug("Registering Metrics Filter");
        FilterRegistration.Dynamic metricsFilter = servletContext.addFilter("webappMetricsFilter",
            new InstrumentedFilter());

        metricsFilter.addMappingForUrlPatterns(disps, true, "/*");
        metricsFilter.setAsyncSupported(true);

        log.debug("Registering Metrics Servlet");
        ServletRegistration.Dynamic metricsAdminServlet =
            servletContext.addServlet("metricsServlet", new MetricsServlet());

        metricsAdminServlet.addMapping("/management/ppc/metrics/*");
        metricsAdminServlet.setAsyncSupported(true);
        metricsAdminServlet.setLoadOnStartup(2);
    }

    @Bean
    @ConditionalOnProperty(name = "ppc.cors.allowed-origins")
    public CorsFilter corsFilter() {
        log.debug("Registering CORS filter");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = ppcProperties.getCors();
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/v2/api-docs", config);
        source.registerCorsConfiguration("/oauth/**", config);
        return new CorsFilter(source);
    }
    
    /**
     * Initializes Druid stat view.
     * other initialize method
     * 
     * @WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
	 *	    initParams={
	 *	        @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),
	 *			@WebInitParam(name="principalSessionName",value="_dest_login_")
	 *	})
	 *	public class DruidStatFilter extends WebStatFilter {
	 *		//empty method
	 *	}
	 *	@WebServlet(urlPatterns = "/druid/*", 
	 *	    initParams={
	 *	            @WebInitParam(name="allow",value="192.168.29.176,127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
	 *	            @WebInitParam(name="deny",value="192.168.29.100"),// IP黑名单 (存在共同时，deny优先于allow)
	 *	            @WebInitParam(name="loginUsername",value="hayden"),// 用户名
	 *	            @WebInitParam(name="loginPassword",value="ppcredit"),// 密码
	 *	            @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
	 *	    })
	 *	public class DruidStatViewServlet extends StatViewServlet {
	 *		//empty method
	 *	}
     */
    private void initDruid(ServletContext servletContext, EnumSet<DispatcherType> disps) {
        log.debug("Initializing druid registries");
//        servletContext.setAttribute("contextConfigLocation", "classpath:root-context.xml");
        log.debug("Registering Web Stat Filter");
        FilterRegistration.Dynamic webStatFilter = servletContext.addFilter("DruidWebStatFilter",
            new WebStatFilter());
        webStatFilter.setInitParameter(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/dbstat/*");
        webStatFilter.setInitParameter(WebStatFilter.PARAM_NAME_PRINCIPAL_SESSION_NAME, "_dest_login_");
        webStatFilter.addMappingForUrlPatterns(disps, true, "/*");
        webStatFilter.setAsyncSupported(true);
        
        log.debug("Registering druid stat Servlet");
        ServletRegistration.Dynamic statAdminServlet =
            servletContext.addServlet("DruidStatView", new StatViewServlet());
//        statAdminServlet.setInitParameter(StatViewServlet.PARAM_NAME_ALLOW, "192.168.29.176,127.0.0.1");
//        statAdminServlet.setInitParameter(StatViewServlet.PARAM_NAME_DENY, "192.168.29.100");
        statAdminServlet.setInitParameter(StatViewServlet.PARAM_NAME_USERNAME, "hayden");
        statAdminServlet.setInitParameter(StatViewServlet.PARAM_NAME_PASSWORD, "ppcredit.com");
        statAdminServlet.setInitParameter(StatViewServlet.PARAM_NAME_RESET_ENABLE, "false");
        statAdminServlet.addMapping("/dbstat/*");
        statAdminServlet.setAsyncSupported(true);
        statAdminServlet.setLoadOnStartup(0);
    }
}
