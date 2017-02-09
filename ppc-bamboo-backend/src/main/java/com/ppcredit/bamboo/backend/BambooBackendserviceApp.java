package com.ppcredit.bamboo.backend;

import com.ppcredit.bamboo.backend.config.Constants;
import com.ppcredit.bamboo.backend.config.DefaultProfileUtil;
import com.ppcredit.bamboo.backend.config.PPCreditProperties;
import com.ppcredit.bamboo.backend.web.rest.admin.login.handler.SSOAuthInteceptor;
import com.ppcredit.bamboo.backend.web.rest.admin.login.handler.TreeViewInteceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
@SpringBootApplication(scanBasePackages={"com.ppcredit.bamboo.backend"}, exclude = { MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class })
@EnableConfigurationProperties({ PPCreditProperties.class })
//@EnableDiscoveryClient
public class BambooBackendserviceApp extends WebMvcConfigurerAdapter{

    private static final Logger log = LoggerFactory.getLogger(BambooBackendserviceApp.class);

    @Inject
    private Environment env; 

    @Inject
    private SSOAuthInteceptor mySSOAuthInteceptor;
    
    @Inject
    private TreeViewInteceptor myTreeViewInteceptor;
    
    /**
     * Initializes bookservice.
     * <p>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     */
    @PostConstruct
    public void initApplication() {
        log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
    }

    /*@Bean
    public ServletRegistrationBean servletRegistrationBean() {
      return new ServletRegistrationBean(new SinoCaptchaServlet(), "/default.gif");
    }*/
    
    /** 
     * 配置拦截器 
     * @author lance 
     * @param registry 
     */  
    @Override
    public void addInterceptors(InterceptorRegistry registry) {  
        registry.addInterceptor(mySSOAuthInteceptor).addPathPatterns("/admin/**");  
        registry.addInterceptor(myTreeViewInteceptor).addPathPatterns("/admin/**");  
        super.addInterceptors(registry);
    }
    
    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(BambooBackendserviceApp.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:{}\n\t" +
                "External: \thttp://{}:{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"));

        String configServerStatus = env.getProperty("configserver.status");
        log.info("\n----------------------------------------------------------\n\t" +
        "Config Server: \t{}\n----------------------------------------------------------",
            configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
    }
}
