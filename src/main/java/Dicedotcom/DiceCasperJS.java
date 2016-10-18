package Dicedotcom;

public class DiceCasperJS {
	
	public static String diceJS(String URL, String Filepath)
	{
		String filepath=Filepath.replace("\\", "/");
		String str="var casper=require('casper').create();"
				+"\n"+"var fs = require('fs');"
			
				+"\n"+"var x=require('casper').selectXPath;"
				+"\n"+"var utils=require('utils');"
				+"\n"+"casper.userAgent('Mozilla/23.0 (compatible; MSIE 6.0; Windows 7)');"
				+"\n"+"casper.start('"+URL+"');"
	+"\n"+"console.log('Dice Description page loaded');"
	+"\n"+"casper.then(function()"
	+"\n"+"{"
	+"\n"+"fs.write('"+filepath+"/dicedesc.txt','','w');"
	+"\n"+"});"
	+"\n"+"casper.then(function(){"

	+"\n"+"casper.wait(5000,function(){"
	+"\n"+"casper.capture('E:/Projects/Workspace/chanel-test-automation/Web_Scrapper/WebScrapper/real.png');"
	+"\n"+"var town_info_text2=\"No Detail Is Found\";"
	+"\n"+"if (casper.exists(x('//*[@id=\"detailDescription\"]')))"
	+"\n"+"{"
	+"\n"+"town_info_text2=this.evaluate(function() {"
	+"\n"+"var nodes = document.querySelectorAll('#detailDescription');"
	+"\n"+"return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(..."
	+"\n"+"return node.textContent;"
	+"\n"+"});"
	+"\n"+"});"
	+"\n"+"}"
	+"\n"+"if (casper.exists(x('//*[@class=\"job_description\"]')))"
	+"\n"+"{"
	+"\n"+"town_info_text2=this.evaluate(function() {"
	+"\n"+"var nodes = document.querySelectorAll('.job_description');"
	+"\n"+"return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(..."
	+"\n"+"return node.textContent;"
	+"\n"+"});"
	+"\n"+"});"
	+"\n"+"}"
	+"\n"+"if (!(casper.exists(x('//*[@class=\"job_description\"]'))) && !casper.exists(x('//*[@id=\"detailDescription\"]')))"
	+"\n"+"{"
	+"\n"+"town_info_text2=this.evaluate(function() {"
	+"\n"+"var nodes = document.querySelectorAll('body');"
	+"\n"+"return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(..."
	+"\n"+"return node.textContent;"
	+"\n"+"});"
	+"\n"+"});"
+"\n"+"}"
+"\n"+"var objArray= town_info_text2.map(function(str) {"
	+"\n"+"str=str.replace(/(\\r\\n|\\n|\\r)/gm,\"\");"
+"\n"+"	var data = {"
			+"\n"+"Description:      str,"
	
	+"\n"+"};"
	+"\n"+"return data;"
	+"\n"+"});"
+"\n"+"casper.then(function()"
			+"\n"+"{"
		+"\n"+"	fs.write('"+filepath+"/dicedesc.txt',JSON.stringify(objArray),'a');"
	+"\n"+"});"
	
	+"\n"+"});"
	+"\n"+"});"
	+"\n"+"casper.run();";

		
		return str;
		
		
	}

}
