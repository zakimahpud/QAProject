package Tests;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import com.simpleprogrammer.proteintracker.HistoryItem;
import com.simpleprogrammer.proteintracker.InvalidGoalException;
import com.simpleprogrammer.proteintracker.NotifierStub;
import com.simpleprogrammer.proteintracker.TrackingService;

//import java.time.Instant;
//import org.junit.matchers.JUnitMatchers;

public class ProteinTrackerSecondTest {

	private TrackingService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AfterClass");
	}

	@Before
	public void SetUp() {
		System.out.println("Before");
		service = new TrackingService(new NotifierStub());
	}

	@Test
	@Category(GoodTestsCategory.class)
	public void TrackingServiceTotalIsZero() {
		assertEquals("Tracking Service total was not zero", 0, service.getTotal());
		assertThat(service.getTotal(), is(0));
		assertThat(0, is(service.getTotal()));
	}

	@Test
	@Category(GoodTestsCategory.class) // ({ GoodTestsCategory.class, BadCategory.class })
	public void WhenAddingProteinTotalIncreaseByThatAmount() {
		service.addProtein(10);
		// assertEquals("Tracking Service total was not zero", 10, service.getTotal());
		// assertThat(10, is (service.getTotal()));

		// assertThat(service.getTotal(), is(10));

		assertThat(service.getTotal(), allOf(is(10), instanceOf(Integer.class)));
	}

	@Test
	@Category(EndCaseTestsCategory.class)
	public void WhenRemovingProteinToMinusTotalRemainZero() {
		service.removeProtein(5);
		assertEquals("Tracking Service total was not zero", 0, service.getTotal());
	}

	@Test
	@Category(GoodTestsCategory.class)
	public void WhenRemovingProteinTotalRemainOk() {
		service.addProtein(10);
		service.removeProtein(3);
		assertEquals("Tracking Service total was not zero", 7, service.getTotal());
	}

	@Test(expected = InvalidGoalException.class)
	@Category(GoodTestsCategory.class)
	public void WhenGoalIsSetToLessThanZeroExceptionIsThrow() throws InvalidGoalException {
		service.setGoal(-5);

	}

	
//-----------------------------------------------------------------------------------------
//	Stub Demonstration
//-----------------------------------------------------------------------------------------
	
	@Test
	public void WhenGoalIsMetHostoryIsUpdated() throws InvalidGoalException {
		service.setGoal(5);
		service.addProtein(6);
		

		HistoryItem result = service.getHistory().get(1);
		assertEquals("sent: goal met", result.getOperation());
	}

//-----------------------------------------------------------------------------------------
//	Jmock Demonstration
//-----------------------------------------------------------------------------------------
//	@Test
//	public void WhenGoalIsMetHostoryIsUpdatedMock() throws InvalidGoalException {
//		
//		Mockery context = new Mockery();
//		final Notifier mockNotifier = context.mock(Notifier.class);
//		service = new TrackingService(mockNotifier);
//		
//		context.checking(new Expectations() {{
//			oneOf(mockNotifier).send("goal met");
//			will(returnValue(true));
//		}});
//		
//		service.setGoal(5);
//		service.addProtein(6);
//		
//		
//		HistoryItem result = service.getHistory().get(1);
//		assertEquals("sent: goal met", result.getOperation());
//	}
	
	

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void WhenGoalIsSetToLessThanZerowExpectionIsThrown() throws InvalidGoalException {
		thrown.expect(InvalidGoalException.class);
		// thrown.expectMessage("!Goal Was Less Than zero!");
		// thrown.expectMessage(containsString("goal"));
		thrown.expectMessage(containsString("Goal")); // Case Sensitive : Gaol - Pass, goal - failed

		service.setGoal(-5);

	}

	@Rule
	public Timeout timeOut = new Timeout(20000);

	@Test(timeout = 200)
	@Category(BadCategory.class)
	public void BadTimeTest() {
		for (int i = 0; i < 1000; i++)
			service.addProtein(1);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After");
	}

}
