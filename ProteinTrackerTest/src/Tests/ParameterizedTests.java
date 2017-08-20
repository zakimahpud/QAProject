package Tests;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

import com.simpleprogrammer.proteintracker.Notifier;
import com.simpleprogrammer.proteintracker.NotifierStub;
import com.simpleprogrammer.proteintracker.TrackingService;

@RunWith(Parameterized.class)
public class ParameterizedTests {

	private static TrackingService service = new TrackingService(new NotifierStub() {

		@Override
		public boolean send(String message) {
			// TODO Auto-generated method stub
			return false;
		}
	});

	private int input;
	private int expected;

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] { { 5, 5 }, { 5, 10 }, { -12, 0 }, { 50, 50 }, { 1, 51 }, });

	}

	public ParameterizedTests(int input, int expected) {
		super();
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void test() {
		if (input >= 0)
			service.addProtein(input);
		else
			service.removeProtein(-input);

		assertEquals(expected, service.getTotal());
	}

}
