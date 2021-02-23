package ttl.larku.didemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

interface Trick {
    public void doTrick();
}

@Component
//@Qualifier("us-east")
@Profile("dev")
class Trick1 implements Trick {
    @Override
    public void doTrick() {
        System.out.println("Handstand");
    }
}

@Component
//@Qualifier("us-west")
//@Order(2)
@Profile("prod")
class Trick2 implements Trick {
    @Override
    public void doTrick() {
        System.out.println("Juggling");
    }
}

//@Component
//@Qualifier("us-west")
//@Order(1)
//class Trick3 implements Trick {
//    @Override
//    public void doTrick() {
//        System.out.println("Somersault");
//    }
//}

@Component
class Circus
{
    //@Resource //(name = "trick1")
//    @Qualifier("us-west")

    @Autowired
    private Trick trick1;

    @Autowired
//    @Qualifier("us-west")
    private List<Trick> allTricks;


    public void startShow() {
        trick1.doTrick();
//        allTricks.forEach(Trick::doTrick);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.scan("ttl.larku.didemo");
        context.refresh();

        Circus circus = context.getBean("circus", Circus.class);

        circus.startShow();

    }
}