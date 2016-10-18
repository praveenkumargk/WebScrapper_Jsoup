package Monsterdotcom;

public class MonsterCasperJS {
	
		

	public static String MonsterJS(String URL, String Filepath)
	{
		String filepath=Filepath.replace("\\", "/");
		String str="var casper=require('casper').create();"+
"var fs = require('fs');"+
"var x=require('casper').selectXPath;"+
"var utils=require('utils');"+
"casper.userAgent('Mozilla/24.0 (compatible; MSIE 6.0; Windows 7)');"+
"casper.start('"+URL+"');"+
"console.log('Monster Description page loaded');"+
"casper.wait(500,function(){"+
"casper.then(function()"+
"{"+
"fs.write('"+filepath+"/monsdesc.txt','','w');"+
"});"+
"casper.then(function(){"+
"casper.then(function(){"+

"{"+

"casper.wait(5000,function(){"+
"var town_info_text2='Not able reteive data';"+
"if (casper.exists(x('//body')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "var nodes = document.querySelectorAll('body');"+
 "return [].map.call(nodes, function(node) {"+
  " return node.textContent;"+
 "});"+
"});"+
"}"+
"if (casper.exists(x('//div[@class=\"job-details\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
   "var nodes = document.querySelectorAll('div.job-details');"+
   "return [].map.call(nodes, function(node) { "+
    " return node.textContent;"+
   "});"+
  "});"+
"}"+
"if (casper.exists(x('//div[@id=\"jobBodyContent\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
  " var nodes = document.querySelectorAll('div#jobBodyContent');"+
  " return [].map.call(nodes, function(node) { "+
   "  return node.textContent;"+
  " });"+
 "});"+
"}"+
"if (casper.exists(x('//div[@id=\"CJT-jobBodyContent\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "  var nodes = document.querySelectorAll('div#CJT-jobBodyContent');"+
  " return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
  "});"+
"});"+
"}"+
"if (casper.exists(x('//div[@id=\"jobDESC\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "  var nodes = document.querySelectorAll('div#jobDESC');"+
  "return [].map.call(nodes, function(node) { "+
   "return node.textContent;"+
 "});"+
 "});"+
"}"+
"if (casper.exists(x('//*[@id=\"TrackingJobBody\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "  var nodes = document.querySelectorAll('#TrackingJobBody');"+
  " return [].map.call(nodes, function(node) { "+
   "return node.textContent;"+
  "});"+
"});"+
"}"+
"if (casper.exists(x('//div[@class=\"pcBgMid\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
  "var nodes = document.querySelectorAll('div.pcBgMid');"+
  "return [].map.call(nodes, function(node) { "+
   "return node.textContent;"+
  "});"+
"});"+
"}"+
"var objArray= town_info_text2.map(function(str) {"+
"str=str.replace(/(\\r\\n|\\n|\\r)/gm,\"\");"+
 " var data = {"+
"Description:     str};"+
"return data;"+
"});"+
"casper.then(function()"+
"{"+
"fs.write('"+filepath+"/monsdesc.txt',JSON.stringify(objArray),'a');"+
"});"+
"});"+
"casper.thenOpen(casper.mytest1);"+
"casper.wait(500,function(){"+
"});"+
"}"+
"});"+
"});"+
"});"+
"casper.run();";

		
		return str;
		
		
	}
	
	
}
