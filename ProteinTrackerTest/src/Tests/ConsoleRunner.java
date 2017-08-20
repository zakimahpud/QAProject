package Tests;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import com.simpleprogrammer.proteintracker.TrackingService;

public class ConsoleRunner {
	
	public static void main(String arg[]) {
		
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		
		junit.run(ProteinTrackerSecondTest.class);
		
		
	}
}
