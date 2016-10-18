package CareerBuilderdotcom;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class CarrerBuilderDetailgetter {
	
	public static void CarrerBuilderDetailget(String url) throws IOException
	{
		
		String currentPath = System.getProperty("user.dir");
				
				String filename = currentPath + "\\n1k0-casperjs-cd1fab5\\bin\\" + "carrwithdesc.js";
				File fr= new File(filename);
				FileOutputStream fos11 = new FileOutputStream(fr);
				DataOutputStream dos11 = new DataOutputStream(fos11);
			//	System.out.println(Javascriptswriter.carrJS(searchKeyword, currentPath));
		
				
			dos11.writeBytes(CareerBuilderCasperJS.CareerBuilderJS(url, currentPath));
				
			
			
dos11.close();
fos11.close();

	}
	
	public static void batchwriter() throws IOException
	{
		String currentPath = System.getProperty("user.dir");
		String casperpath = currentPath
				+ "\\n1k0-casperjs-cd1fab5\\batchbin";
		String phanthompath = currentPath + "\\phantomjs";

	//	System.out.println("Present Project Directory : "
		//		+ currentPath);
		String csr = currentPath;

		String currentPath1 = csr.replace("\\", "/");
		//System.out.println(currentPath1);
		//System.out.println(currentPath1.split("/")[0]);
		String[] parts1 = currentPath.split("/");
		
		String batchpath = currentPath+"\\Execute2.bat";
		//System.out.println(batchpath);
		File file = new File(batchpath);
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeBytes("set PATH=%PATH%;" + casperpath + ";"
				+ phanthompath);
		
		dos.writeBytes("\n");
		String[] parts = currentPath1.split("/");
		dos.writeBytes("CD\\");
		dos.writeBytes("\n");
		

		dos.writeBytes(parts[0]);
		dos.writeBytes("\n");
		for (int i1 = 1; i1 < parts.length; i1++) {
		
			dos.writeBytes("CD " + parts[i1]);
	
			dos.writeBytes("\n");
		}
		dos.writeBytes("CD n1k0-casperjs-cd1fab5");
		dos.writeBytes("\n");
		dos.writeBytes("CD bin");
		dos.writeBytes("\n");
		dos.writeBytes("casperjs carrwithdesc.js");
		dos.writeBytes("\n");
		dos.writeBytes("exit");
		dos.writeBytes("\n");
		fos.close();
		dos.close();
	}

	public static void output(String url) throws ClassNotFoundException, IOException {
		CarrerBuilderDetailget(url);
		batchwriter();
		String currentPath = System.getProperty("user.dir");
		String batchpath=currentPath+"\\Execute2.bat";

		String cmd = "cmd /c start" + batchpath;
		Process proc = null;
		try {
			
			Runtime rt = Runtime.getRuntime();
		Process p=rt.exec(batchpath);
		
		InputStream inputStream = p.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputStream);
        BufferedReader bufferedrReader = new BufferedReader(inputstreamreader);
        String strLine = "";
        while (((strLine = bufferedrReader.readLine()) != null)) {
            System.out.println(strLine);
           
        }
        
        
        
        try {
			Runtime.getRuntime()
					.exec("taskkill /f /im cmd.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}

	int exitVal = p.exitValue();
		}catch(Exception e)
		{}

	}
}
