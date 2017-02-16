package lcb.com.pe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import lcb.com.pe.SpringBootOauth2Application;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=AnnotationConfigWebContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootOauth2Application.class)
@WebAppConfiguration
public class SpringBootOauth2ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
