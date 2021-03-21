import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:myBusBot.properties")
public class SpringConfig {
    @Bean
    public TrafficParser trafficParser(){
        return new TrafficParser();
    }
    @Bean
    public MyBusBot myBusBot() {
        return new MyBusBot();
    }
}
