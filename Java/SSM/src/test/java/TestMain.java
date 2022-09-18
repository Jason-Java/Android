import com.jason.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@ContextConfiguration(classes = SpringConfig.class)
public class TestMain {


    @Value("123")
    private String name;

    @Test
    public void testad() {

        System.out.println(name);
    }
}
