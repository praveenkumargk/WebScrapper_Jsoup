package CareerBuilderdotcom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import jxl.write.WriteException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class careerBuilderDescriptionWritter {
	public static void descriptionwriter() throws IOException, WriteException {
		try {
			FileInputStream file = new FileInputStream(new File(
					"CareerBuilderdotcom.xls"));

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFSheet sheet1 = workbook.getSheetAt(1);
			int a = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < a; i++) {
				String c = sheet.getRow(i).getCell(6).getStringCellValue();
				String data = "";
				Document doc = Jsoup.connect(c).timeout(10 * 1000)
						.userAgent("Mozilla").ignoreHttpErrors(true)
						.followRedirects(true).get();
				Thread.sleep(5000);
				Elements links = doc.select("div.description");
				Elements links1 = doc.select(".pjb-box-inner");
				Elements links2 = doc.select(".pjb-content");
				Elements links3 = doc.select(".pjb-table2");
				Elements links4 = doc.select(".content");
				Elements links5 = doc.select("#job-description");
				Elements links6 = doc.select(".job_desc");
				Elements links7 = doc.select("#content");
				Elements links8 = doc.select(".contain1");
				Elements links9 = doc.select("#bodycontentcontainer");
				Elements links10 = doc.select(".job");
				Elements links11 = doc.select(".jobInfo");
				Elements links12 = doc.select("#CJT_jobBodyContent");
				Elements links13 = doc.select("#jobHeader");
				Elements links14 = doc.select("#jobMain1");
				Elements links15 = doc.select(".article");
				Elements links16 = doc.select(".companyoverviewbox");
				Elements links17 = doc.select("#mainContent");
				Elements links18 = doc.select("#pjb-zcontent-col1");
				Elements links19 = doc.select(".bodytext");
				Elements links20 = doc.select(".content-sections");

				if (links.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links.size(); c1++) {
						data = data + links.get(c1).text();

					}

				}
				if (links1.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links.size(); c1++) {
						data = data + links1.get(c1).text();
					}

				}
				if (links2.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links2.size(); c1++) {
						data = data + links2.get(c1).text();
					}

				}
				if (links3.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links3.size(); c1++) {
						data = data + links3.get(c1).text();
					}

				}
				if (links4.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links4.size(); c1++) {
						data = data + links4.get(c1).text();
					}

				}
				if (links5.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links5.size(); c1++) {
						data = data + links5.get(c1).text();
					}

				}
				if (links6.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links6.size(); c1++) {
						data = data + links6.get(c1).text();
					}

				}
				if (links7.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links7.size(); c1++) {
						data = data + links7.get(c1).text();
					}

				}
				if (links8.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links8.size(); c1++) {
						data = data + links8.get(c1).text();
					}

				}
				if (links9.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links9.size(); c1++) {
						data = data + links9.get(c1).text();
					}

				}
				if (links10.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links10.size(); c1++) {
						data = data + links10.get(c1).text();
					}

				}
				if (links11.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links11.size(); c1++) {
						data = data + links11.get(c1).text();
					}

				}
				if (links12.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links12.size(); c1++) {
						data = data + links12.get(c1).text();
					}

				}
				if (links13.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links13.size(); c1++) {
						data = data + links13.get(c1).text();
					}

				}
				if (links14.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links14.size(); c1++) {
						data = data + links14.get(c1).text();
					}

				}
				if (links15.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links15.size(); c1++) {
						data = data + links15.get(c1).text();
					}

				}
				if (links16.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links16.size(); c1++) {
						data = data + links16.get(c1).text();
					}

				}
				if (links17.size() > 0) {

					data = "";
					for (int c1 = 0; c1 < links17.size(); c1++) {
						data = data + links17.get(c1).text();
					}

				}
				if (links18.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links18.size(); c1++) {
						data = data + links18.get(c1).text();
					}

				}
				if (links19.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links19.size(); c1++) {
						data = data + links19.get(c1).text();
					}

				}
				if (links20.size() > 0) {
					data = "";

					for (int c1 = 0; c1 < links20.size(); c1++) {
						data = data + links20.get(c1).text();
					}

				}
				if (links.size() == 0 && links1.size() == 0
						&& links2.size() == 0 && links3.size() == 0
						&& links4.size() == 0 && links5.size() == 0) {
					if (links6.size() == 0 && links7.size() == 0
							&& links8.size() == 0 && links9.size() == 0
							&& links10.size() == 0 && links11.size() == 0) {
						if (links12.size() == 0 && links13.size() == 0
								&& links14.size() == 0 && links15.size() == 0
								&& links16.size() == 0 && links17.size() == 0) {
							if (links18.size() == 0 && links19.size() == 0
									&& links20.size() == 0) {
								Element body = doc.select("body").get(0);
								data = body.text();
							}
						}
					}

				}

				if (data.length() == 0) {
					CarrerBuilderDetailgetter.output(c);
					String currentPath = System.getProperty("user.dir");

					BufferedReader careerdescription = new BufferedReader(
							new FileReader(new File(currentPath
									+ "\\carrdesc.txt")));
					String career = null;
					while ((career = careerdescription.readLine()) != null) {
						data = data + career;
					}
				}
				if (data.length() == 0) {
					data = "No Data Found";
				}
				data = data.replace("[{\"Description\":\"", "");
				Cell description = sheet1.getRow(i).getCell(1);
				try {

					description.setCellValue(data);
				} catch (Exception t) {
					description.setCellValue("No Data found");
				}

				System.out.println("careerBuilder Description Written");
			}
			file.close();
			FileOutputStream out = new FileOutputStream(new File(
					"CareerBuilderdotcom.xls"));
			workbook.write(out);
			out.close();
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
}
