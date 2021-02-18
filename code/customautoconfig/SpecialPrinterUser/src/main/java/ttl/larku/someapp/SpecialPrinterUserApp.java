package ttl.larku.someapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ttl.larku.specialprinter.SpecialPrinter;

/**
 * User class.  This application wants to use our SpecialPrinter library (in the MyRunner
 * class).  If it doesn't declare a Bean of type SpecialPrinter, then it will get
 * our AutoConfigured Bean.  If it does declare a Bean, then that's the one that goes
 * into the Spring context.
 */
@SpringBootApplication//(exclude = {SpecialPrinterAutoConfig.class})//(excludeName = {"ttl.larku.specialprinter.SpecialPrinterAutoConfig"})
public class SpecialPrinterUserApp {

    public static void main(String[] args) {
        SpringApplication.run(SpecialPrinterUserApp.class, args);
    }

    //If this bean is declared, then that's what Spring will use.
    //If not, then the default AutoConfigured Bean will get used.
//    @Bean
//    public SpecialPrinter specialPrinter() {
//        SpecialPrinter sp = new SpecialPrinter();
//        sp.setPrefix("{");
//        sp.setSuffix("}");
//        return sp;
//    }
}

@Configuration
class OtherConfig
{
    //If this bean is declared, then that's what Spring will use.
    //If not, then the default AutoConfigured Bean will get used.
    @Bean
    public SpecialPrinter specialPrinter() {
        SpecialPrinter sp = new SpecialPrinter();
        sp.setPrefix("<");
        sp.setSuffix(">");
        return sp;
    }

}

@Component
class MyRunner implements CommandLineRunner
{

    //We will get either the AutoConfigurd Bean, or
    //our own Bean if we declare one.
    @Autowired
    private SpecialPrinter specialPrinter;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(specialPrinter.log("boo"));
    }
}
