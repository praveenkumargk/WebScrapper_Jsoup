package Dicedotcom;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DiceScrapper extends Thread {
	String searchkeyword;
	String Location;

	public DiceScrapper(String keyword, String location) {
		this.searchkeyword = keyword;
		this.Location = location;
	}

	public void run() {

		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", false);
		/*
		 * dCaps.setCapability("phantomjs.page.settings.userAgent",
		 * "Custom Agent/1.0");
		 */
		// searchkeyword = searchkeyword.replaceAll("\"", "");
		dCaps.setCapability(
				"phantomjs.page.settings.userAgent",
				"Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1");
		dCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
				new String[] { "--web-security=no", "--ignore-ssl-errors=yes",
						"--ignore-ssl-errors=true", "--ssl-protocol=tlsv1" });

		dCaps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				System.getProperty("user.dir") + "\\phantomjs\\phantomjs.exe");
		PhantomJSDriver driver = new PhantomJSDriver(dCaps);
		driver.manage().window().setSize(new Dimension(1920, 1080));
		WebDriverWait wait = new WebDriverWait(driver, 120);
		driver.get("https://www.dice.com/jobs?q=&l=");

		System.out.println("Dice Page Loading");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("job")));
			driver.findElement(By.id("job")).clear();
			driver.findElement(By.id("job")).sendKeys(searchkeyword);

			Thread.sleep(10000);
		} catch (Exception e) {
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
			System.out.println("Done");
			e.printStackTrace();
		}
		System.out.println(driver.findElement(By.id("location")).getText());
		if (!Location.equalsIgnoreCase("US")) {
			driver.findElement(By.id("location")).click();
			driver.findElement(By.id("location")).clear();
			driver.findElement(By.id("location")).sendKeys(Location);
		}
		driver.findElement(
				By.cssSelector("input.btn.btn-primary.btn-lg.btn-block"))
				.click();

		try {
			// wait.until(ExpectedConditions.presenceOfElementLocated(By
			// .cssSelector("h4.pull-left.posiCount.sort")));
		} catch (Exception t) {
		}
		String joburl = "https://www.dice.com/jobs?q=" + searchkeyword + "&l=";
		// driver.get(joburl);
		String url = driver.getCurrentUrl();
		url = url.split("&")[0];
		joburl = url.replace("?q=", "/q-");
		System.out.println("CurrentUrl: " + url);
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		WritableSheet sheet1 = null;
		try {
			workbook = Workbook.createWorkbook(new File("Dicedotcom.xls"));

			sheet = workbook.createSheet("Details", 0);
			sheet1 = workbook.createSheet("Dexcription", 1);
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
		try {

			List<WebElement> spans = driver.findElements(By
					.cssSelector("h4.pull-left.posiCount.sort > span"));
			String str = spans.get(spans.size() - 1).getText();
			str = str.replace(",", "");
			System.out.println("Number of jobs found in Dice.com: " + str);
			int a1 = Integer.parseInt(str);
			// int quotient = a1 / 30;
			// int remainder = a1 % 30;

			int a = 1;
			// for(int k=1;k<=quotient;k++)
			int k = 1;
			b = true;
			while (b) {
				// for (int s = 22; s < 30; s++) {
				try {
					System.out.println("Dice Job detail Scrapped in Page:" + k);
					if (Location.equalsIgnoreCase("US")) {
						url = joburl + "-limit-30-startPage-" + k
								+ "-limit-30-jobs";
					} else {
						url = joburl + "-limit-30-l-" + Location
								+ "-radius-30-startPage-" + k
								+ "-limit-30-jobs";
					}
					System.out.println("URL: " + url);
					Map<String, String> cookies = new HashMap<String, String>();
					Connection conn = Jsoup
							.connect(url)
							.header("Accept-Encoding", "gzip, deflate")
							.userAgent(
									"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
							.maxBodySize(0).timeout(600000)
							.ignoreHttpErrors(true).followRedirects(true)
							.timeout(10 * 1000).userAgent("Mozilla")
							.method(Method.GET);
					Response res = conn.execute();
					Document doc = res.parse();

					Elements jobs11 = doc
							.select("div.col-md-9 > div#serp > div.complete-serp-result-div > div.serp-result-content > h3 > a.dice-btn-link");
					driver.get(url);
					List<WebElement> jobs = driver
							.findElements(By
									.cssSelector("div.col-md-9 > div#serp > div.complete-serp-result-div > div.serp-result-content > h3 > a.dice-btn-link"));
					if (jobs.size() == 0) {
						throw new Exception("no jobs found in page");
					}
					List<WebElement> employer = driver
							.findElements(By
									.cssSelector("div.col-md-9 > div#serp > div.complete-serp-result-div > div.serp-result-content > ul.list-inline.details > li.employer"));
					List<WebElement> location = driver
							.findElements(By
									.cssSelector("div.col-md-9 > div#serp > div.complete-serp-result-div > div.serp-result-content > ul.list-inline.details > li.location"));
					List<WebElement> postsed = driver
							.findElements(By
									.cssSelector("div.col-md-9 > div#serp > div.complete-serp-result-div > div.serp-result-content > ul.list-inline.details > li.posted"));
					System.out.println("JobSize in page " + k + ": "
							+ jobs.size());
					if (jobs.size() > 0) {
						for (int i = 0; i < jobs.size(); i++) {
							try {
								Label ID = new Label(0, a, "DICE" + a);
								String titile = jobs.get(i).getText();
								if (titile.length() > 0) {
									Label title = new Label(1, a, jobs.get(i)
											.getText());
									Label employername = new Label(2, a,
											employer.get(i).getText());
									String loc = location.get(i).getText();
									String[] lo = loc.split(",");
									Label city = new Label(3, a, lo[0].trim());
									try {
										Label state = new Label(4, a,
												lo[1].trim());
										sheet.addCell(state);
									} catch (Exception tp) {
										Label state = new Label(4, a, "-");
										sheet.addCell(state);
									}
									Label date = new Label(5, a, postsed.get(i)
											.getText());

									Label urls = new Label(6, a, jobs.get(i)
											.getAttribute("href"));
									Label source = new Label(7, a, "Dice.com");
									Label DesID = new Label(0, a, "DICE" + a);
									Label descontent = new Label(1, a, "*");
									sheet.addCell(ID);
									sheet.addCell(title);
									sheet.addCell(employername);
									sheet.addCell(city);
									sheet.addCell(date);
									sheet.addCell(urls);
									sheet.addCell(source);
									sheet1.addCell(DesID);
									sheet1.addCell(descontent);

									a++;
								}
							} catch (Exception tp) {
							}
						}
					} else {
						b = false;
					}
				} catch (Exception tp) {
					b = false;
				}
				k++;

			}

			try {
				// driver.close();
				driver.quit();
			} catch (Exception t) {
			}
		} catch (Exception t) {
			t.printStackTrace();
		}
		try {
			workbook.write();
			workbook.close();
		} catch (Exception t) {
		}
		DiceDescriptionWritter.descriptionwriter();
	}
}
