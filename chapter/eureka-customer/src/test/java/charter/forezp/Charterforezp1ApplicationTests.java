package charter.forezp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.HelloService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Charterforezp1Application.class)
public class Charterforezp1ApplicationTests {
	@Autowired
	HelloService helloService;


	@Test
	public void contextLoads() {
		System.out.println(helloService.hiService("aaa"));
	}

}
