package Linkedindotcom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LinkedInDescription {
	public static void descriptionwriter() {

		try {

			FileInputStream file = new FileInputStream(new File("LinkedIn.xls"));

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
					Document doc = Jsoup.connect(c).timeout(10 * 1000)
							.userAgent("Mozilla").get();
					Thread.sleep(5000);
					Elements links = doc.select("div.summary");

					if (links.size() > 0) {
						data = "";

						for (int c1 = 0; c1 < links.size(); c1++) {
							data = data + links.get(c1).text();

						}

					}

					if (links.size() == 0) {
						data = "";
						Element body = doc.select("body").get(0);
						data = body.text();

					}
				} catch (Exception t) {
					t.printStackTrace();
				}
				/*
				 * if (data.length() == 0) { DiceDetailgetter.output(c); String
				 * currentPath = System.getProperty("user.dir");
				 * 
				 * BufferedReader dicedescription = new BufferedReader( new
				 * FileReader(new File(currentPath + "\\dicedesc.txt"))); String
				 * dice = null; while ((dice = dicedescription.readLine()) !=
				 * null) { data = data + dice; } }
				 */
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

				System.out.println("Writing LinkedIn Job Description");
			}
			file.close();
			FileOutputStream out = new FileOutputStream(
					new File("LinkedIn.xls"));
			workbook.write(out);
			out.close();
		} catch (Exception t) {
		}

	}
}
