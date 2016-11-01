package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import CareerBuilderdotcom.CareerBuilderScrapper;
import Dicedotcom.DiceScrapper;
import Indeeddotcom.IndeedScrapper;
import Monsterdotcom.MonsterScrapper;

public class MainClass {
	public static int row = 0;

	@Test
	@Parameters("searchKeyword")
	public void JobDetailsScrapper(String searchKeyword) throws Exception {
		// String searchKeyword1 = "smash";
		String searchKeyword1 = System.getenv("SEARCH_KEYWORD");
		String location = System.getenv("Location");
		String Dice = System.getenv("Dice.com");
		String careerBuilder = System.getenv("CareerBuilder.com");
		String monster = System.getenv("Monster.com");
		String indeed = System.getenv("Indeed.com");
		try {
			if (!searchKeyword1.equalsIgnoreCase("null")) {
				searchKeyword = System.getenv("SEARCH_KEYWORD");
			}

			/*
			 * if (location.equalsIgnoreCase("null")) { location = ""; }
			 */

		} catch (Exception t) {
		}

		System.out.println(searchKeyword);
		searchKeyword = "\"" + searchKeyword + "\"";
		System.out.println(searchKeyword);

		/*
		 * MonsterScrapper mons = new MonsterScrapper(searchKeyword);
		 * mons.start();
		 */

		DiceScrapper dice = new DiceScrapper(searchKeyword, location);

		// dice.start();
		CareerBuilderScrapper career = new CareerBuilderScrapper(searchKeyword,
				location);
		// career.start();
		MonsterScrapper mons = new MonsterScrapper(searchKeyword, location);
		// mons.start();
		IndeedScrapper indy = new IndeedScrapper(searchKeyword, location);

		if (Dice.equalsIgnoreCase("true")) {
			dice.start();
		}
		if (careerBuilder.equalsIgnoreCase("true")) {
			career.start();
		}
		if (monster.equalsIgnoreCase("true")) {
			mons.start();
		}
		if (indeed.equalsIgnoreCase("true")) {
			indy.start();
		}

		/**
		 * 
		 * wait until all threads to complete the execution
		 * 
		 */

		while ((dice.isAlive()) || (career.isAlive()) || (mons.isAlive())
				|| (indy.isAlive())) {

		}

		ExcelSheetgenerator();
		copysheets();

	}

	public static void ExcelSheetgenerator() {
		System.out.println("Creating consolidated workbook");
		int rows = 0;
		rows = rows + (getrownumbers("Dicedotcom.xls") - 1);
		rows = rows + (getrownumbers("CareerBuilderdotcom.xls") - 1);
		rows = rows + (getrownumbers("Monsterdotcom.xls") - 1);

		WritableWorkbook workbook = null;
		WritableSheet sheet = null;
		WritableSheet sheet1 = null;
		try {
			workbook = Workbook.createWorkbook(new File(
					"WebScrapper_Results.xls"));
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
			for (int a = 1; a <= rows; a++) {
				Label id = new Label(0, a, "*");
				Label title = new Label(1, a, "*");
				Label company = new Label(2, a, "*");
				Label city = new Label(3, a, "*");
				Label state = new Label(4, a, "*");
				Label date = new Label(5, a, "*");
				Label url = new Label(6, a, "*");
				Label source = new Label(7, a, "*");
				Label desid = new Label(0, a, "*");
				Label desh = new Label(1, a, "*");
				sheet.addCell(id);
				sheet.addCell(title);
				sheet.addCell(company);
				sheet.addCell(city);
				sheet.addCell(state);
				sheet.addCell(date);
				sheet.addCell(url);
				sheet.addCell(source);
				sheet1.addCell(desid);
				sheet1.addCell(desh);
			}
			workbook.write();
			workbook.close();
		} catch (Exception t) {
		}

	}

	public static int getrownumbers(String file) {
		int a = 0;
		try {
			FileInputStream file11 = new FileInputStream(new File(file));

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file11);

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFSheet sheet1 = workbook.getSheetAt(1);
			a = sheet.getPhysicalNumberOfRows();
		} catch (Exception t) {
		}
		return a;
	}

	public static void copysheets() {
		try {
			System.out.println("Consolidating WebScrapper results");
			int b = 1;
			if (System.getenv("Monster.com").equalsIgnoreCase("true")) {
				System.out.println("Monster.com written");
				FileInputStream diceFile = new FileInputStream(new File(
						"Monsterdotcom.xls"));
				HSSFWorkbook diceworkbook = new HSSFWorkbook(diceFile);
				HSSFSheet detailsheet = diceworkbook.getSheetAt(0);
				HSSFSheet descriptionsheet = diceworkbook.getSheetAt(1);

				FileInputStream allsiteFile = new FileInputStream(new File(
						"WebScrapper_Results.xls"));
				HSSFWorkbook allsiteworkbook = new HSSFWorkbook(allsiteFile);
				HSSFSheet allsitedetailsheet = allsiteworkbook.getSheetAt(0);
				HSSFSheet allsitedescriptionsheet = allsiteworkbook
						.getSheetAt(1);
				int a = detailsheet.getPhysicalNumberOfRows();
				for (int i = 1; i < a; i++) {
					Cell id = allsitedetailsheet.getRow(b).getCell(0);
					id.setCellValue(detailsheet.getRow(i).getCell(0)
							.getStringCellValue());
					Cell title = allsitedetailsheet.getRow(b).getCell(1);
					title.setCellValue(detailsheet.getRow(i).getCell(1)
							.getStringCellValue());
					Cell company = allsitedetailsheet.getRow(b).getCell(2);
					company.setCellValue(detailsheet.getRow(i).getCell(2)
							.getStringCellValue());
					Cell city = allsitedetailsheet.getRow(b).getCell(3);
					city.setCellValue(detailsheet.getRow(i).getCell(3)
							.getStringCellValue());
					Cell state = allsitedetailsheet.getRow(b).getCell(4);
					state.setCellValue(detailsheet.getRow(i).getCell(4)
							.getStringCellValue());
					Cell date = allsitedetailsheet.getRow(b).getCell(5);
					date.setCellValue(detailsheet.getRow(i).getCell(5)
							.getStringCellValue());
					Cell url = allsitedetailsheet.getRow(b).getCell(6);
					url.setCellValue(detailsheet.getRow(i).getCell(6)
							.getStringCellValue());
					Cell source = allsitedetailsheet.getRow(b).getCell(7);
					source.setCellValue(detailsheet.getRow(i).getCell(7)
							.getStringCellValue());
					Cell desid = allsitedescriptionsheet.getRow(b).getCell(0);
					desid.setCellValue(descriptionsheet.getRow(i).getCell(0)
							.getStringCellValue());
					Cell description = allsitedescriptionsheet.getRow(b)
							.getCell(1);
					description.setCellValue(descriptionsheet.getRow(i)
							.getCell(1).getStringCellValue());
					b++;
				}
				diceFile.close();
				allsiteFile.close();
				FileOutputStream out = new FileOutputStream(new File(
						"WebScrapper_Results.xls"));
				allsiteworkbook.write(out);
				out.close();
			}
			if (System.getenv("Dice.com").equalsIgnoreCase("true")) {
				System.out.println("Dice.com written");
				FileInputStream diceFile = new FileInputStream(new File(
						"Dicedotcom.xls"));
				HSSFWorkbook diceworkbook = new HSSFWorkbook(diceFile);
				HSSFSheet detailsheet = diceworkbook.getSheetAt(0);
				HSSFSheet descriptionsheet = diceworkbook.getSheetAt(1);

				FileInputStream allsiteFile = new FileInputStream(new File(
						"WebScrapper_Results.xls"));
				HSSFWorkbook allsiteworkbook = new HSSFWorkbook(allsiteFile);
				HSSFSheet allsitedetailsheet = allsiteworkbook.getSheetAt(0);
				HSSFSheet allsitedescriptionsheet = allsiteworkbook
						.getSheetAt(1);
				int a = detailsheet.getPhysicalNumberOfRows();

				for (int i = 1; i < a; i++) {
					Cell id = allsitedetailsheet.getRow(b).getCell(0);
					id.setCellValue(detailsheet.getRow(i).getCell(0)
							.getStringCellValue());
					Cell title = allsitedetailsheet.getRow(b).getCell(1);
					title.setCellValue(detailsheet.getRow(i).getCell(1)
							.getStringCellValue());
					Cell company = allsitedetailsheet.getRow(b).getCell(2);
					company.setCellValue(detailsheet.getRow(i).getCell(2)
							.getStringCellValue());
					Cell city = allsitedetailsheet.getRow(b).getCell(3);
					city.setCellValue(detailsheet.getRow(i).getCell(3)
							.getStringCellValue());
					Cell state = allsitedetailsheet.getRow(b).getCell(4);
					state.setCellValue(detailsheet.getRow(i).getCell(4)
							.getStringCellValue());
					Cell date = allsitedetailsheet.getRow(b).getCell(5);
					date.setCellValue(detailsheet.getRow(i).getCell(5)
							.getStringCellValue());
					Cell url = allsitedetailsheet.getRow(b).getCell(6);
					url.setCellValue(detailsheet.getRow(i).getCell(6)
							.getStringCellValue());
					Cell source = allsitedetailsheet.getRow(b).getCell(7);
					source.setCellValue(detailsheet.getRow(i).getCell(7)
							.getStringCellValue());
					Cell desid = allsitedescriptionsheet.getRow(b).getCell(0);
					desid.setCellValue(descriptionsheet.getRow(i).getCell(0)
							.getStringCellValue());
					Cell description = allsitedescriptionsheet.getRow(b)
							.getCell(1);
					description.setCellValue(descriptionsheet.getRow(i)
							.getCell(1).getStringCellValue());
					b++;
				}
				diceFile.close();
				allsiteFile.close();
				FileOutputStream out = new FileOutputStream(new File(
						"WebScrapper_Results.xls"));
				allsiteworkbook.write(out);
				out.close();
			}
			if (System.getenv("CareerBuilder.com").equalsIgnoreCase("true")) {
				System.out.println("CareerBuilder.com written");
				FileInputStream diceFile = new FileInputStream(new File(
						"CareerBuilderdotcom.xls"));
				HSSFWorkbook diceworkbook = new HSSFWorkbook(diceFile);
				HSSFSheet detailsheet = diceworkbook.getSheetAt(0);
				HSSFSheet descriptionsheet = diceworkbook.getSheetAt(1);

				FileInputStream allsiteFile = new FileInputStream(new File(
						"WebScrapper_Results.xls"));
				HSSFWorkbook allsiteworkbook = new HSSFWorkbook(allsiteFile);
				HSSFSheet allsitedetailsheet = allsiteworkbook.getSheetAt(0);
				HSSFSheet allsitedescriptionsheet = allsiteworkbook
						.getSheetAt(1);
				int a = detailsheet.getPhysicalNumberOfRows();
				for (int i = 1; i < a; i++) {
					Cell id = allsitedetailsheet.getRow(b).getCell(0);
					id.setCellValue(detailsheet.getRow(i).getCell(0)
							.getStringCellValue());
					Cell title = allsitedetailsheet.getRow(b).getCell(1);
					title.setCellValue(detailsheet.getRow(i).getCell(1)
							.getStringCellValue());
					Cell company = allsitedetailsheet.getRow(b).getCell(2);
					company.setCellValue(detailsheet.getRow(i).getCell(2)
							.getStringCellValue());
					Cell city = allsitedetailsheet.getRow(b).getCell(3);
					city.setCellValue(detailsheet.getRow(i).getCell(3)
							.getStringCellValue());
					Cell state = allsitedetailsheet.getRow(b).getCell(4);
					state.setCellValue(detailsheet.getRow(i).getCell(4)
							.getStringCellValue());
					Cell date = allsitedetailsheet.getRow(b).getCell(5);
					date.setCellValue(detailsheet.getRow(i).getCell(5)
							.getStringCellValue());
					Cell url = allsitedetailsheet.getRow(b).getCell(6);
					url.setCellValue(detailsheet.getRow(i).getCell(6)
							.getStringCellValue());
					Cell source = allsitedetailsheet.getRow(b).getCell(7);
					source.setCellValue(detailsheet.getRow(i).getCell(7)
							.getStringCellValue());
					Cell desid = allsitedescriptionsheet.getRow(b).getCell(0);
					desid.setCellValue(descriptionsheet.getRow(i).getCell(0)
							.getStringCellValue());
					Cell description = allsitedescriptionsheet.getRow(b)
							.getCell(1);
					description.setCellValue(descriptionsheet.getRow(i)
							.getCell(1).getStringCellValue());
					b++;
				}
				diceFile.close();
				allsiteFile.close();
				FileOutputStream out = new FileOutputStream(new File(
						"WebScrapper_Results.xls"));
				allsiteworkbook.write(out);
				out.close();
			}
		}

		catch (Exception t) {
		}

	}

	public static void copysheets1() {
		try {
			FileInputStream diceFile = new FileInputStream(new File(
					"Dicedotcom.xls"));
			HSSFWorkbook diceworkbook = new HSSFWorkbook(diceFile);
			HSSFSheet detailsheet = diceworkbook.getSheetAt(0);
			HSSFSheet descriptionsheet = diceworkbook.getSheetAt(1);

			FileInputStream allsiteFile = new FileInputStream(new File(
					"WebScrapper_Results.xls"));

			int a = detailsheet.getPhysicalNumberOfRows();
			int b = 1;

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");
			conn.setAutoCommit(true);

			for (int i = 1; i < a; i++) {
				String ID = detailsheet.getRow(i).getCell(0)
						.getStringCellValue();
				String TITLE = detailsheet.getRow(i).getCell(1)
						.getStringCellValue();
				String COMPANY = detailsheet.getRow(i).getCell(2)
						.getStringCellValue();
				String CITY = detailsheet.getRow(i).getCell(3)
						.getStringCellValue();
				String STATE = detailsheet.getRow(i).getCell(4)
						.getStringCellValue();
				String DATE = detailsheet.getRow(i).getCell(5)
						.getStringCellValue();
				String URL = detailsheet.getRow(i).getCell(6)
						.getStringCellValue();
				String SOURCE = detailsheet.getRow(i).getCell(7)
						.getStringCellValue();
				String DESCRIPTION = descriptionsheet.getRow(i).getCell(1)
						.getStringCellValue();
				String query = "Insert into DICETODAY values(\"" + ID + "\",\""
						+ TITLE + "\",\"" + COMPANY + "\",\"" + CITY + "\",\""
						+ STATE + "\",\"" + DATE + "\",\"" + URL + "\",\""
						+ DESCRIPTION + "\",\"" + SOURCE + "\");";
				System.out.println(query);
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet result = stmt.executeQuery();
			}
			diceFile.close();
			allsiteFile.close();
			conn.close();

		}

		catch (Exception t) {
		}

	}

}