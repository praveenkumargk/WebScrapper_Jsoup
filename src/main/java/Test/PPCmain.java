package Test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Dicedotcom.DiceScrapper;

public class PPCmain {
	@Test
	@Parameters("searchKeyword")
	public void JobDetailsScrapper(String searchKeyword, String Location)
			throws Exception {
		String searchKeyword1 = "smash";
		searchKeyword1 = "\"" + searchKeyword1 + "\"";
		DiceScrapper dice = new DiceScrapper(searchKeyword1, Location);
		dice.start();
		while ((dice.isAlive())) {

		}
	}
}
