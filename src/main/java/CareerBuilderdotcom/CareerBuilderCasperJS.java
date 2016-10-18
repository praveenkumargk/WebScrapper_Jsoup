package CareerBuilderdotcom;

public class CareerBuilderCasperJS {
	
	public static String CareerBuilderJS(String URL, String Filepath)
	{
		String filepath=Filepath.replace("\\", "/");
		String str="var casper=require('casper').create();"+
"var fs = require('fs');"+
"var key='dump checker';"+
"var x=require('casper').selectXPath;"+
"var utils=require('utils');"+
"casper.userAgent('Mozilla/25.0 (compatible; MSIE 6.0; Windows 7)');"+
"casper.start('"+URL+"');"+
"console.log('Carrerbuilder job page loaded');"+
"casper.wait(500,function(){"+
"casper.then(function()"+
"{"+
"fs.write('"+filepath+"/carrdesc.txt','','w');"+
"});"+
"casper.then(function(){"+
"+casper.then(function(){"+
"{"+
"casper.wait(5000,function(){"+
"casper.capture('"+filepath+"/real.png');"+
"var town_info_text2='Not able reteive data';"+
"if (casper.exists(x('//body')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
"var nodes = document.querySelectorAll('body');"+
 "return [].map.call(nodes, function(node) {"+
   "return node.textContent;"+
" });"+
   "});"+
"}"+
"if (casper.exists(x('//*[@id=\"CJT_jobBodyContentid=\"JobDetails_ucJobDetailsSkin_tdJSCenter\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "var nodes = document.querySelectorAll('#JobDetails_ucJobDetailsSkin_tdJSCenter');"+
 "return [].map.call(nodes, function(node) {"+
   "return node.textContent;"+
 "});"+
"});"+
"}"+
"if (casper.exists(x('//*[@id=\"pnlJobDescription\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
  "var nodes = document.querySelectorAll('div#CBBody_contentmain.content-main div.content-sections');"+
  "return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
  "});"+
 "});"+
"}"+
"if (casper.exists(x('//*[@class=\"pjb-box-inner\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "  var nodes = document.querySelectorAll('div.pjb-box-inner');"+
  " return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
  "});"+
"});"+
"}"+
"if (casper.exists(x('//*[@class=\"pjb-content\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
   "var nodes = document.querySelectorAll('div.pjb-content');"+
  "return [].map.call(nodes, function(node) { "+
   "return node.textContent;"+
 " });"+
 "});"+
"}"+
"if (casper.exists(x('//*[@class=\"pjb-table2\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
  "var nodes = document.querySelectorAll('div.pjb-text div.RichText_Content');"+
  "return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
   "});"+
 "});"+
"}"+
"if (casper.exists(x('//*[@class=\"content\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
   "var nodes = document.querySelectorAll('div.content');"+
   "return [].map.call(nodes, function(node) { "+
 "   return node.textContent;"+
 "});"+
"});"+
"}"+
"if (casper.exists(x('//*[@id=\"job-description\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "var nodes = document.querySelectorAll('div#main div#job-description');"+
 "return [].map.call(nodes, function(node) { "+
  " return node.textContent;"+
 "});"+
 "});"+
"}"+
"if (casper.exists(x('//*[@class=\"job_desc\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
   " var nodes = document.querySelectorAll('table tbody tr td div.job_desc');"+
  "return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
  "});"+
 "});"+
"}"+
"if (casper.exists(x('//*[@id=\"content\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() { "+
 "  var nodes = document.querySelectorAll('div#content');"+
 " return [].map.call(nodes, function(node) { "+
  "  return node.textContent;"+
 "  });"+
 " });"+
"}"+
"if (casper.exists(x('//*[@class=\"contain1\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
    "var nodes = document.querySelectorAll('div.contain1');"+
  " return [].map.call(nodes, function(node) { "+
  "   return node.textContent;"+
  "});"+
" });"+
"}"+
"if (casper.exists(x('//*[@id=\"bodycontentcontainer\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
    "var nodes = document.querySelectorAll('div#bodycontentcontainer');"+
   "return [].map.call(nodes, function(node) { "+
    " return node.textContent;"+
  "});"+
"});"+
"}"+
"if (casper.exists(x('//*[@class=\"job\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
  "var nodes = document.querySelectorAll('div.job');"+
 " return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
 " });"+
"});"+
"}"+
"if (casper.exists(x('//*[@class=\"jobInfo\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
  "var nodes = document.querySelectorAll('div.jobInfo');"+
  "return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
  "});"+
 "});"+
"}"+
"if (casper.exists(x('//*[@id=\"CJT_jobBodyContent\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "var nodes = document.querySelectorAll('div#CJT_jobBodyContent');"+
  "return [].map.call(nodes, function(node) { "+
   "return node.textContent;"+
  "});"+
"});"+
"}"+
"if (casper.exists(x('//*[@id=\"jobHeader\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "   var nodes = document.querySelectorAll('div.jobHeader');"+
  "  return [].map.call(nodes, function(node) { "+
     " return node.textContent;"+
   "});"+
  "});"+
"}"+
"if (casper.exists(x('//*[@id=\"jobMain1\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
  " var nodes = document.querySelectorAll('div.jobMain1');"+
  "return [].map.call(nodes, function(node) { "+
   "return node.textContent;"+
  "});"+
"});"+
"}"+
"if (casper.exists(x('//*[@class=\"article\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 " var nodes = document.querySelectorAll('div.article');"+
  "return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
  "});"+
"});"+
"}"+
"if (casper.exists(x('//*[@class=\"companyoverviewbox\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
  "var nodes = document.querySelectorAll('div#JobDetails_pnlSkinnedJDP.jdpPageWrapper table#JobDetails_ucJobDetailsSkin__tblJobSkin tbody tr#JobDetails_ucJobDetailsSkin_trContentJobSkin td#JobDetails_ucJobDetailsSkin_tdJSCenter table.frame tbody tr td.companyoverviewbox table tbody tr td div#data');"+
   "return [].map.call(nodes, function(node) { "+
   " return node.textContent;"+
  "});"+
 "});"+
"}"+
"if (casper.exists(x('//*[@id=\"mainContent\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "  var nodes = document.querySelectorAll('td#JobDetails_ucJobDetailsSkin_tdJSCenter div#wrapper div#mainContent div#rightColumn');"+
  " return [].map.call(nodes, function(node) { "+
   "  return node.textContent;"+
   "});"+
 "});"+
"}"+
"if (casper.exists(x('//*[@id=\"pjb-zcontent-col1\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
   "var nodes = document.querySelectorAll('div#pjb-zcontent-col1');"+
  "return [].map.call(nodes, function(node) { "+
  "  return node.textContent;"+
 " });"+
"});"+
"}"+
"if (casper.exists(x('//div[@class=\"bodytext\"]')))"+
"{"+
"town_info_text2=this.evaluate(function() {"+
 "  var nodes = document.querySelectorAll('div.bodytext');"+
"  return [].map.call(nodes, function(node) { "+
  "   return node.textContent;"+
  "});"+
 "});"+
"}"+
"var objArray= town_info_text2.map(function(str) {"+
"str=str.replace(/(\\r\\n|\\n|\\r)/gm,\"\");"+
 "  var data = {"+
"Description:     str.trim()"+
 "};"+
 "return data;"+
 "});"+
"casper.then(function()"+
"{"+
"fs.write('"+filepath+"/carrdesc.txt',JSON.stringify(objArray),'a');"+
"});"+
"});"+
"}"+
"});"+
" });"+
"});"+
"casper.run();";

		
		return str;
		
		
	}

	
	
	
	
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
