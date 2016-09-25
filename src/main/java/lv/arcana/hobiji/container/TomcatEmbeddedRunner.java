package lv.arcana.hobiji.container;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import java.io.File;

public class TomcatEmbeddedRunner {

    public void startServer() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        File base = new File("src/main/webapp");
//        File base = new File(System.getProperty("java.io.tmpdir"));
        Context rootCtx = tomcat.addContext("", base.getAbsolutePath());
        System.out.println(base.getAbsolutePath());

        Wrapper defaultServlet = rootCtx.createWrapper();
        defaultServlet.setName("default");
        defaultServlet.setServletClass("org.apache.catalina.servlets.DefaultServlet");
        defaultServlet.addInitParameter("debug", "0");
        defaultServlet.addInitParameter("listings", "false");
        defaultServlet.setLoadOnStartup(1);
        rootCtx.addChild(defaultServlet);
        rootCtx.addServletMapping("/", "default");
        rootCtx.addWelcomeFile("index.html");

        AnnotationConfigWebApplicationContext aactx = new AnnotationConfigWebApplicationContext();
        aactx.scan("lv/arcana/hobiji");
        DispatcherServlet dispatcher = new DispatcherServlet(aactx);
        Tomcat.addServlet(rootCtx, "SpringREST", dispatcher);
        rootCtx.addServletMapping("/rest/*", "SpringREST");




        tomcat.start();
        tomcat.getServer().await();

    }

}
