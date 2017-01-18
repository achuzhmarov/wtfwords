package wtf.wtfgames.wtfwords.config;

import com.googlecode.genericdao.search.MetadataUtil;
import com.googlecode.genericdao.search.hibernate.HibernateMetadataUtil;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;
import java.util.Properties;

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

    @Bean
    RestTemplate restTemplate() throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @Value("${smtp.support.email}")
    private String supportEmail;

    @Value("${smtp.support.password}")
    private String supportPassword;

    @Bean
    JavaMailSender mailSender() throws Exception {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(supportEmail);
        mailSender.setPassword(supportPassword);

        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(mailProperties);

        return mailSender;
    }
}  
 