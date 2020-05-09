import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.cyz.basic.config.security.crypto.bcrypt.BCryptPasswordEncoder;

@Ignore
public class CommonTest {
	
	@Test
	public void testPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		String result = encoder.encode("OB1000");
		System.out.println(result);
		assertTrue(encoder.matches("myPassword", result));
	}

}
