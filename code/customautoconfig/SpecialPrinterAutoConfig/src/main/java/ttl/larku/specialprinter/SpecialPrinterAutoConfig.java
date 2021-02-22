package ttl.larku.specialprinter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A very simple AutoConfiguration class.  This Configuration
 * will not be used by Spring if you have declared a bean
 * of type SpecialPrinter.class.
 *
 * Spring first process your bean configurations, and then
 * process auto configurations that apply, based on various
 * @ConditionalXYX annotations, like below.
 *
 * For this to work, you *have* to declare this class
 * as an auto configuration class in resource/META-INF/spring.factories
 * Look there for an example.  The uber example of this is in
 * the spring-boot-autoconfigure jar file (Ctrl-Shift-R and search for spring.factories)
 *
 */
@Configuration
@ConditionalOnMissingBean(SpecialPrinter.class)
public class SpecialPrinterAutoConfig {

    @Bean
    public SpecialPrinter logger() {
        return new SpecialPrinter();
    }
}
