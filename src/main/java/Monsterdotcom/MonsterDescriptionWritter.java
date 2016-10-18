package Monsterdotcom;

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

public class MonsterDescriptionWritter {
	public static void descriptionwriter() throws IOException, WriteException {

		FileInputStream file = new FileInputStream(
				new File("Monsterdotcom.xls"));

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFSheet sheet1 = workbook.getSheetAt(1);
		int a = sheet.getPhysicalNumberOfRows();
		for (int i = 1; i < a; i++)

		{
			String c = sheet.getRow(i).getCell(6).getStringCellValue();
			String data = "";
			try {
				System.out.println(c);
				/*
				 * Document doc = Jsoup
				 * .connect(c).timeout(10*1000).userAgent("Mozilla").get();
				 */
				Document doc = Jsoup
						.connect(c)
						.header("Accept-Encoding", "gzip, deflate")
						.userAgent(
								"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
						.maxBodySize(0).timeout(600000).ignoreHttpErrors(true).followRedirects(true).get();
				// Thread.sleep(5000);
				Elements links = doc.select(".job-details");

				Elements links1 = doc.select("#jobBodyContent");
				Elements links2 = doc.select("#CJT-jobBodyContent");
				Elements links3 = doc.select("#jobDESC");
				Elements links4 = doc.select("#TrackingJobBody");
				Elements links5 = doc.select(".pcBgMid");

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

				if (links.size() == 0 && links1.size() == 0
						&& links2.size() == 0 && links3.size() == 0
						&& links4.size() == 0 && links5.size() == 0) {

					Element body = doc.select("body").get(0);
					data = body.text();

				}

				if (data.length() == 0) {
					MonsterDetailgetter.output(c);
					String currentPath = System.getProperty("user.dir");

					BufferedReader monsterdescription = new BufferedReader(
							new FileReader(new File(currentPath
									+ "\\monsdesc.txt")));
					String monster = null;
					while ((monster = monsterdescription.readLine()) != null) {
						data = data + monster;
					}
				}
			} catch (Exception t) {
				t.printStackTrace();
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

			System.out.println("Monster.com Description Written");
		}
		file.close();
		FileOutputStream out = new FileOutputStream(new File(
				"Monsterdotcom.xls"));
		workbook.write(out);
		out.close();

	}
}
