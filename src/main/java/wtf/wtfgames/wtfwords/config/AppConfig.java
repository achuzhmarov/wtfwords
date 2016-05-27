package wtf.wtfgames.wtfwords.config;

import com.googlecode.genericdao.search.MetadataUtil;
import com.googlecode.genericdao.search.hibernate.HibernateMetadataUtil;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManagerFactory;

@Configuration
@PropertySource(value = { "application.properties","application-db.properties" })
public class AppConfig {
    @Bean
    public JPASearchProcessor searchProcessor(MetadataUtil metadataUtil) {
        return new JPASearchProcessor(metadataUtil);
    }

    @Bean
    public MetadataUtil metadataUtil(EntityManagerFactory emf) {
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        return HibernateMetadataUtil.getInstanceForSessionFactory(sessionFactory);
    }
}  
 