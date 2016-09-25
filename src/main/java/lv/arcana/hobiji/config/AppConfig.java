package lv.arcana.hobiji.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Import({RepositoryConfig.class})
@Configuration
@EnableWebMvc
@ComponentScan("lv.arcana.hobiji")
public class AppConfig
{

}
