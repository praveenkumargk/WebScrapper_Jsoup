package Dicedotcom;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

public class Sample extends Thread {
	String searchkeyword;

	public Sample(String keyword) {
		this.searchkeyword = keyword;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		String searchKe = searchkeyword.replaceAll("\"", "%22");
		String searchKey = searchKe.replaceAll(" ", "%20");
		String url = "https://www.dice.com/jobs?q=" + searchKey + "&l=";
		System.out.println(url);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.get(url);
		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		WritableSheet sheet1 = null;
		try {
			workbook = Workbook.createWorkbook(new File("Dicedotcom.xls"));
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
		url = driver.getCurrentUrl();
		List<WebElement> spans = driver.findElements(By
				.cssSelector("h4.pull-left.posiCount.sort > span"));
		String str = spans.get(spans.size()).getText();
		str = str.replace(",", "");
		System.out.println("Number of jobs found in Dice.com: " + str);
		System.out.println("url: " + url);
		int nop = 0;
		while (b) {
			System.out.println("Dice.com job detail received");
			try {

				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.cssSelector("h4.pull-left.posiCount.sort > span")));

			} catch (Exception t) {
				t.printStackTrace();
			}
			try {
				url = "https://www.dice.com/jobs/q-" + searchKey
						+ "-limit-30-startPage-" + pagenumber
						+ "-limit-30-jobs";
				System.out.println("URL: " + url);
				Document doc = Jsoup
						.connect(url)
						.header("Accept-Encoding", "gzip, deflate")
						.userAgent(
								"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
						.maxBodySize(0).timeout(600000).ignoreHttpErrors(true)
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

				Thread.sleep(5000);
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
				System.out.println("JobSize in page " + pagenumber + ": "
						+ jobs.size());
				if (jobs.size() == 0) {
					b = false;
				}
				for (int i = 0; i < jobs.size(); i++) {
					Label ID = new Label(0, a, "MON" + a);

					Label title = new Label(1, a, jobs.get(i).getText());
					Label employername = new Label(2, a, employer.get(i)
							.getText().replace("Company:", ""));
					String locationstr = location.get(i).getText()
							.replace("Location:", "");
					String[] st = locationstr.split(",");

					Label citysn = new Label(3, a, st[0].trim());
					Label statesn = null;
					try {
						statesn = new Label(4, a, st[1].trim());
					} catch (Exception tp) {
						statesn = new Label(4, a, "-");
					}
					Label date = new Label(5, a, postsed.get(i).getText()
							.replace("Posted:", ""));
					Label urls = new Label(6, a, ((Element) jobs.get(i))
							.select("a").attr("href"));
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
			driver.close();
			driver.quit();
		} catch (Exception t) {
		}
		try {
			DiceDescriptionWritter.descriptionwriter();
		} catch (Exception t) {
		}
	}
}
