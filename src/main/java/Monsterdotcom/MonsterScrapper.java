package Monsterdotcom;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MonsterScrapper extends Thread {
	String searchkeyword;
	String Location;

	public MonsterScrapper(String keyword, String location) {
		this.searchkeyword = keyword;
		this.Location = location;
	}

	public void run() {

		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", false);
		dCaps.setCapability(
				"phantomjs.page.settings.userAgent",
				"Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1");
		dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
				new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });

		dCaps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				System.getProperty("user.dir") + "\\phantomjs\\phantomjs.exe");
		PhantomJSDriver driver = new PhantomJSDriver(dCaps);
		driver.manage().window().setSize(new Dimension(1920, 1080));
		// WebDriverWait wait= new WebDriverWait(driver, 120);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		String searchKe = searchkeyword.replaceAll("\"", "%22");
		String searchKey = searchKe.replaceAll(" ", "%20");
		try {
			/*
			 * UrlValidator urlValidator = new UrlValidator();
			 * urlValidator.isValid("http://jobsearch.monster.com/search/?q=" +
			 * temp);
			 */

			driver.get("http://jobsearch.monster.com/search/?q=" + searchKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String url = driver.getCurrentUrl();
		// System.out.println(url);

		// String a=links.get(links.size()-1).text();
		// System.out.println(a);
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		WritableSheet sheet1 = null;
		try {
			workbook = Workbook.createWorkbook(new File("Monsterdotcom.xls"));
			sheet = workbook.createSheet("Details", 0);
			sheet1 = workbook.createSheet("Description", 1);
			Label idheader = new Label(0, 0, "ID");
			Label titleheader = new Label(1, 0, "TITLE");
			Label companyheader = new Label(2, 0, "COMPANY");
			Label cityheader = new Label(3, 0, "CITY");
			Label stateheader = new Label(4, 0, "STATE");
			Label dateheader = new Label(5, 0, "DATE");
			Label urlheader = new Label(6, 0, "JOBURL");
			Label sourceheader = new Label(7, 0, "SOURCE");

			Label desidheader = new Label(0, 0, "ID");
			Label desheader = new Label(1, 0, "DESCRIPTION");
			sheet.addCell(idheader);
			sheet.addCell(titleheader);
			sheet.addCell(companyheader);
			sheet.addCell(cityheader);
			sheet.addCell(stateheader);
			sheet.addCell(dateheader);
			sheet.addCell(urlheader);
			sheet.addCell(sourceheader);
			sheet1.addCell(desidheader);
			sheet1.addCell(desheader);
		} catch (Exception t) {
		}
		boolean b = true;

		int a = 1;
		int pagenumber = 1;
		/*
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By
		 * .cssSelector("div.jsresultsheader h2"))); String noOfjob =
		 * driver.findElement(
		 * By.cssSelector("div.jsresultsheader h2")).getText();
		 */
		// for(int k=1;k<=quotient;k++)
		/* {} */

		url = driver.getCurrentUrl();

		System.out.println("url: " + url);
		int nop = 0;
		while (b) {
			// for (int s = 26; s < 28; s++) {
			System.out.println("Monster.com job detail received");
			try {

				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.cssSelector("div.jsresultsheader > h2")));

			} catch (Exception t) {
				t.printStackTrace();
			}
			try {
				url = "http://jobs.monster.com/search/?q=" + searchKey
						+ "&where=" + Location + "&page=" + (pagenumber);
				System.out.println("URL: " + url);
				/*
				 * Connection.Response res =
				 * Jsoup.connect(url).method(Method.GET) .timeout(220 *
				 * 1000).userAgent("Mozilla").execute();
				 */

				/*
				 * Document doc = Jsoup .connect(url).timeout(220 *
				 * 1000).userAgent("Mozilla").get();
				 */
				Document doc = Jsoup
						.connect(url)
						.header("Accept-Encoding", "gzip, deflate")
						.userAgent(
								"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
						.maxBodySize(0).timeout(8000000).ignoreHttpErrors(true)
						.followRedirects(true).get();
				WebDriver driver1 = (PhantomJSDriver) new Augmenter()
						.augment(driver);
				File srcFile = ((TakesScreenshot) driver1)
						.getScreenshotAs(OutputType.FILE);
				System.out.println("File:" + srcFile);
				try {
					FileUtils.copyFile(srcFile, new File("screenshot.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Thread.sleep(15000);
				Elements links = doc
						.select("div#listings.singleline table.listingsTable tbody tr td div.jobTitleCol.fnt4 div.jobTitleContainer a.slJobTitle.fnt11");
				Elements jobs = doc
						.select("section#resultsWrapper > div.js_result_container > article.js_result_row > div.jobTitle > h2 > a");
				Elements employer = doc
						.select("section#resultsWrapper > div.js_result_container > article.js_result_row > div.company");
				Elements location = doc
						.select("section#resultsWrapper > div.js_result_container > article.js_result_row > div.location");
				Elements postsed = doc
						.select("section#resultsWrapper > div.js_result_container > article.js_result_row > div.extras > div.postedDate");

				System.out.println("job size :" + jobs.size());
				if (jobs.size() == 0) {
					b = false;
				}
				for (int i = 0; i < jobs.size(); i++) {
					Label ID = new Label(0, a, "MON" + a);

					Label title = new Label(1, a, jobs.get(i).text());
					Label employername = new Label(2, a, employer.get(i).text()
							.replace("Company:", ""));
					String locationstr = location.get(i).text()
							.replace("Location:", "");
					String[] st = locationstr.split(",");

					Label citysn = new Label(3, a, st[0].trim());
					Label statesn = null;
					try {
						statesn = new Label(4, a, st[1].trim());
					} catch (Exception tp) {
						statesn = new Label(4, a, "-");
					}
					Label date = new Label(5, a, postsed.get(i).text()
							.replace("Posted:", ""));
					Label urls = new Label(6, a, jobs.get(i).select("a")
							.attr("href"));
					Label source = new Label(7, a, "Monster.com");
					Label DesID = new Label(0, a, "MON" + a);
					Label descontent = new Label(1, a, "*");
					sheet.addCell(ID);
					sheet.addCell(title);
					sheet.addCell(employername);
					sheet.addCell(citysn);
					try {
						sheet.addCell(statesn);
					} catch (Exception t) {
					}
					sheet.addCell(date);
					sheet.addCell(urls);
					sheet.addCell(source);
					sheet1.addCell(DesID);
					sheet1.addCell(descontent);

					a++;
				}

				Elements elem = doc.select("a.next");
				System.out.println("nextSize: " + elem.size());
				if (elem.size() > 0) {

					// b=true;
				} else {
					// b = false;
				}

			} catch (HttpStatusException t1) {
				t1.printStackTrace();
				b = false;
			} catch (Exception t) {
				t.printStackTrace();
			}

			pagenumber = pagenumber + 1;

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
			MonsterDescriptionWritter.descriptionwriter();
		} catch (Exception t) {
		}
	}
}
