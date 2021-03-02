package ttl.larku;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ttl.larku.dao.repository.GlobalRepoEventHandler;

@SpringBootApplication
@EnableJpaRepositories
public class SpringDataRestApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication springApp = new SpringApplication(SpringDataRestApp.class);

        springApp.run(args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Init completed");
    }

    @Bean
    GlobalRepoEventHandler globalRepoEventHandler() {
        return new GlobalRepoEventHandler();
    }
}

//@Component
//class SpringRestConfiguration implements RepositoryRestConfigurer {
//    @Override
//    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
//    }
//}

