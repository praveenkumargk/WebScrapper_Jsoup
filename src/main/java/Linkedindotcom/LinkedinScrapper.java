package Linkedindotcom;

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

public class LinkedinScrapper extends Thread {
	String searchkeyword;

	public LinkedinScrapper(String keyword) {
		this.searchkeyword = keyword;
	}

	public void run() {

		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", true);
		/*
		 * dCaps.setCapability("phantomjs.page.settings.userAgent",
		 * "Custom Agent/1.0");
		 */
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
		driver.get("https://www.linkedin.com");

		System.out.println("LinkedIn Page Loading");
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		try {
			driver.findElement(By.id("login-email")).clear();
			driver.findElement(By.id("login-email")).sendKeys(
					"praveeng@softcrylic.co.in");
			driver.findElement(By.id("login-password")).sendKeys("Tintin@12");
			driver.findElements(By.cssSelector("form.login-form > input"))
					.get(5).click();
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
		driver.findElements(
				By.cssSelector("div#main-site-nav > ul.nav-bar > li.nav-item > a.nav-link"))
				.get(2).click();
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("button#toggle-advanced")));
		} catch (Exception t) {
		}
		// To set country code
		/*
		 * driver.findElement(By.cssSelector("button#toggle-advanced")).click();
		 * driver.findElement(By.id("countryCode")).click();
		 */

		/*
		 * driver.findElement(By.id("job-search-box")).clear();
		 * driver.findElement
		 * (By.id("job-search-box")).sendKeys("searchkeyword");
		 * driver.findElement(By.cssSelector("button.search-button")).click();
		 */

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.cssSelector("div.search-info")));
		} catch (Exception t) {
		}
		String url = driver.getCurrentUrl();
		String joburl = url;
		System.out.println("URL: " + url);

		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		WritableSheet sheet1 = null;
		try {
			workbook = Workbook.createWorkbook(new File("LinkedIn.xls"));

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
			int a = 1;
			int pageNumber = 1;
			b = true;
			while (b) {
				System.out.println("LinkedIn Job detail Scrapped in Page:"
						+ pageNumber);
				try {
					Map<String, String> cookies = new HashMap<String, String>();
					driver.get("https://www.linkedin.com/vsearch/j?keywords="
							+ searchkeyword
							+ "&countryCode=us&orig=ADVS&distance=50&locationType=I&rsid=4043068121462173368929&openFacets=L,C&page_num="
							+ pageNumber + "&pt=jobs");
					List<WebElement> jobs = driver
							.findElements(By
									.cssSelector("ol.search-results > li.result > div.bd > h3 > a.main-headline"));
					List<WebElement> employer = driver
							.findElements(By
									.cssSelector("ol.search-results > li.result > div.bd > div.description > bdi"));
					List<WebElement> location = driver
							.findElements(By
									.cssSelector("ol.search-results > li.result > div.bd > dl.demographic > dd.separator"));
					List<WebElement> postsed = driver
							.findElements(By
									.cssSelector("ol.search-results > li.result > div.bd > dl.demographic > dd:nth-child(4n)"));

					if (jobs.size() > 0) {
						for (int i = 0; i < jobs.size(); i++) {
							try {
								Label ID = new Label(0, a, "Link" + a);
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
									Label source = new Label(7, a,
											"LinkedIn.com");
									Label DesID = new Label(0, a, "Link" + a);
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

				} catch (Exception t) {
				}
				if (driver.findElement(By.cssSelector("li.next > a.page-link"))
						.isDisplayed()) {
					pageNumber++;
				} else {
					b = false;
				}

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
		LinkedInDescription.descriptionwriter();
	}
}
