import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.cyz.basic.config.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.cyz.ob.common.util.DesUtil;

@Ignore
public class CommonTest {
	
	@Test
	public void testPassword() {
		String result = DesUtil.getInstance().decrypt("+rsirJe+eCBORa50q0O8EA==", "c1996224");
		System.out.println("111");
		System.out.println(result);
		//assertTrue(encoder.matches("myPassword", result));
	}

}
