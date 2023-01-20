package megalab.news.configuration;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TikaConfiguration {

    @Bean
    Tika tika() {
        return new Tika();
    }
}