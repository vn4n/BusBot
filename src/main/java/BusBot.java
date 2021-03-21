import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BusBot {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        MyBusBot myBusBot = context.getBean("myBusBot",MyBusBot.class);
        myBusBot.botConnect();


        context.close();


    }
}
