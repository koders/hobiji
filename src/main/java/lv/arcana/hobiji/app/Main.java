package lv.arcana.hobiji.app;

import lv.arcana.hobiji.container.TomcatEmbeddedRunner;
import org.apache.catalina.LifecycleException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, LifecycleException {
        System.out.println("### STARTING EMBEDDED WEB CONTAINER\n");
            System.out.println("Starting Tomcat ..");
            new TomcatEmbeddedRunner().startServer();

    }

}
