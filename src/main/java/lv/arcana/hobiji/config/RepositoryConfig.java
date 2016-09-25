package lv.arcana.hobiji.config;

import lv.arcana.hobiji.dao.BaseDAOImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class RepositoryConfig
{
    private String driverClassName = "org.hsqldb.jdbcDriver";
    private String url = "jdbc:hsqldb:file:db/test;shutdown=true";
    private String username = "sa";
    private String password = "";

    private String hibernateDialect = "org.hibernate.dialect.HSQLDialect";
    private String hibernateShowSql = "true";
    private String hibernateHbm2ddlAuto = "update";

    @Bean()
    public DataSource dataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);

        return properties;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
        localSessionFactoryBean.setPackagesToScan(new String[]{"lv.arcana.hobiji"});
        return localSessionFactoryBean;
//        AnnotationSessionFactoryBean lsfb = new AnnotationSessionFactoryBean();
//        lsfb.setPackagesToScan(new String[]{"lv.arcana.hobiji"});
//        lsfb.setDataSource(dataSource());
//        lsfb.setHibernateProperties(getHibernateProperties());
//        return lsfb;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return sessionFactoryBean().getObject();
    }

    @Bean
    public BaseDAOImpl dao() {
        BaseDAOImpl dao = new BaseDAOImpl();
        dao.setSessionFactory(sessionFactory());
        return dao;
    }

}
