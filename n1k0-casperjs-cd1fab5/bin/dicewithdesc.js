var casper=require('casper').create();
var fs = require('fs');
var x=require('casper').selectXPath;
var utils=require('utils');
casper.userAgent('Mozilla/23.0 (compatible; MSIE 6.0; Windows 7)');
casper.start('https://www.dice.com/jobs/detail/Apache-JMeter-needed%21-Robert-Half-Technology-Cleveland-OH-44114/rhalfint/03340-120784?icid=sr5-1p&q=%22jmeter%22&l=');
console.log('Dice Description page loaded');
casper.then(function()
{
fs.write('D:/chanel-test-automation/WebScrapper_Jsoup/dicedesc.txt','','w');
});
casper.then(function(){
casper.wait(5000,function(){
casper.capture('E:/Projects/Workspace/chanel-test-automation/Web_Scrapper/WebScrapper/real.png');
var town_info_text2="No Detail Is Found";
if (casper.exists(x('//*[@id="detailDescription"]')))
{
town_info_text2=this.evaluate(function() {
var nodes = document.querySelectorAll('#detailDescription');
return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
return node.textContent;
});
});
}
if (casper.exists(x('//*[@class="job_description"]')))
{
town_info_text2=this.evaluate(function() {
var nodes = document.querySelectorAll('.job_description');
return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
return node.textContent;
});
});
}
if (!(casper.exists(x('//*[@class="job_description"]'))) && !casper.exists(x('//*[@id="detailDescription"]')))
{
town_info_text2=this.evaluate(function() {
var nodes = document.querySelectorAll('body');
return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
return node.textContent;
});
});
}
var objArray= town_info_text2.map(function(str) {
str=str.replace(/(\r\n|\n|\r)/gm,"");
	var data = {
Description:      str,
};
return data;
});
casper.then(function()
{
	fs.write('D:/chanel-test-automation/WebScrapper_Jsoup/dicedesc.txt',JSON.stringify(objArray),'a');
});
});
});
casper.run();