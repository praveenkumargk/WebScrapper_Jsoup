package CareerBuilderdotcom;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CareerBuilderScrapper extends Thread {
	String searchkeyword;
	String Location;

	public CareerBuilderScrapper(String keyword, String location) {
		this.searchkeyword = keyword;
		this.Location = location;
	}

	public void run() {

		System.out.println(System.getProperty("user.dir"));
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", false);
		dCaps.setCapability("phantomjs.page.settings.userAgent",
				"Custom Agent/1.0");
		dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
				new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });

		dCaps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				System.getProperty("user.dir") + "\\phantomjs\\phantomjs.exe");

		PhantomJSDriver driver = new PhantomJSDriver(dCaps);
		driver.manage().window().setSize(new Dimension(1920, 1080));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		/*
		 * try { driver.findElement(By.id("header-keywords")).clear();
		 * driver.findElement(By.id("header-keywords"))
		 * .sendKeys(searchkeyword); } catch (Exception t) {
		 * 
		 * } driver.findElement(By.id("header-location")).clear();
		 * driver.findElement(By.id("header-location")).sendKeys("usa");
		 * 
		 * driver.findElement(By.cssSelector("input.btn.search-jobs")).click();
		 * System.out.println("Button clicked");
		 */
		// System.out.println(url);
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		WritableSheet sheet1 = null;
		try {
			workbook = Workbook.createWorkbook(new File(
					"CareerBuilderdotcom.xls"));
			sheet = workbook.createSheet("Details", 0);
			sheet1 = workbook.createSheet("Description", 1);
			Label idheader = new Label(0, 0, "ID");
			Label titleheader = new Label(1, 0, "TITLE");
			Label companyheader = new Label(2, 0, "COMPANY");
			Label locationheader = new Label(3, 0, "LOCATION");
			Label dateheader = new Label(4, 0, "DATE");
			Label urlheader = new Label(5, 0, "JOBURL");
			Label sourceheader = new Label(6, 0, "SOURCE");

			Label desidheader = new Label(0, 0, "ID");
			Label desheader = new Label(1, 0, "DESCRIPTION");
			sheet.addCell(idheader);
			sheet.addCell(titleheader);
			sheet.addCell(companyheader);
			sheet.addCell(locationheader);
			sheet.addCell(dateheader);
			sheet.addCell(urlheader);
			sheet.addCell(sourceheader);
			sheet1.addCell(desidheader);
			sheet1.addCell(desheader);
		} catch (Exception t) {
		}
		boolean b = true;

		// int quotient = 0;
		// int remainder = 0;
		// -startPage-2-limit-30-jobs.html
		int a = 1;
		// for(int k=1;k<=quotient;k++)
		/* {} */
		int nop = 1;
		int jobsize = 0;
		while (b) {
			// for (int s = 46; s < 50; s++) {
			// System.out.println("CareerBuilder job details got");
			String searchKe = searchkeyword.replaceAll("\"", "%22");
			String searchKey = searchKe.replaceAll(" ", "%20");
			// System.out.println(searchKey);
			String url = "http://www.careerbuilder.com/jobs-" + searchKey
					+ "-in-" + Location + "?emp=ALL&page_number=" + nop;
			// Elements jobs = null;
			System.out.println(url);
			try {
				Document doc = null;
				// if (jobsize > 24) {
				doc = Jsoup
						.connect(url)
						.header("Accept-Encoding", "gzip, deflate")
						.userAgent(
								"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
						.maxBodySize(0).timeout(600000).get();

				// } else {
				// System.out.println("Only " + noOfJobs + " jobs found");
				// break;
				// }
				if (a == 1) {
					doc = Jsoup
							.connect(
									"http://www.careerbuilder.com/jobs-"
											+ searchKey + "-in-" + Location
											+ "?emp=ALL")
							.header("Accept-Encoding", "gzip, deflate")
							.userAgent(
									"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
							.maxBodySize(0).timeout(600000).get();
				}
				Elements jobCount = doc
						.select("div#main-content > div.page-header > div.row > div.small-12.columns > div.count");
				String jobSize = jobCount.get(0).text();
				jobSize = jobSize.replace("(", "");
				jobSize = jobSize.split(" ")[0];
				// int noOfJobs = Integer.parseInt(jobSize);
				// System.out.println("noOfJobs: " + noOfJobs);
				Elements jobs = doc.select("h2.job-title > a");
				jobsize = jobs.size();
				System.out.println("JobSize: " + jobs.size());
				Elements location = doc
						.select("div.medium-3.end > h4.job-text");
				Elements postsed = doc
						.select("div[class='column small-2 time-posted'] > div.show-for-medium-up");
				Elements employer = doc
						.select("div[class='columns large-2 medium-3 small-12'] > h4.job-text");
				for (int i = 0; i < jobs.size(); i++) {
					Label ID = new Label(0, a, "CAB" + a);
					Label title = new Label(1, a, jobs.get(i).text());
					Label employername = new Label(2, a, employer.get(i).text());
					String loco = location.get(i).text();
					String[] lo = loco.split(",");
					try {
						Label city = new Label(3, a, lo[0].trim());
						sheet.addCell(city);
					} catch (Exception t) {
						Label city = new Label(3, a, "-");
						sheet.addCell(city);
					}

					String fullUrl = "http://www.careerbuilder.com"
							+ jobs.get(i).attr("href");
					Label state = null;
					try {
						state = new Label(4, a, lo[1]);
					} catch (Exception t) {
						state = new Label(4, a, "-");
					}
					Label date = new Label(5, a, postsed.get(i).text());
					Label urls = new Label(6, a, fullUrl);
					Label source = new Label(7, a, "CareerBuilder.com");
					Label DesID = new Label(0, a, "CAB" + a);
					Label descontent = new Label(1, a, "*");
					sheet.addCell(ID);
					sheet.addCell(title);
					sheet.addCell(employername);
					sheet.addCell(state);
					sheet.addCell(date);
					sheet.addCell(urls);
					sheet.addCell(source);
					sheet1.addCell(DesID);
					sheet1.addCell(descontent);
					a++;
				}
			} catch (Exception t) {
				t.printStackTrace();
			}
			if (jobsize < 25) {
				b = false;
			} else {
				b = true;
			}
			nop++;
		}
		try {
			workbook.write();
			workbook.close();
		} catch (Exception t) {
		}
		try {
			driver.quit();

		} catch (Exception t) {
		}

		try {
			careerBuilderDescriptionWritter.descriptionwriter();
		} catch (Exception t) {
		}
	}
}
