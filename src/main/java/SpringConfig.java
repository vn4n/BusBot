import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:myBusBot.properties", encoding = "UTF-8")
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
