import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cyz.ob.authority.service.AuthoritiesService;

@Ignore
public class MapperTest {
	
    ApplicationContext context = null;
	
	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("spring-dao.xml");
	}
	
	@After
	public void after () {
		 context.getBean("authorityService", AuthoritiesService.class);
	}

}
