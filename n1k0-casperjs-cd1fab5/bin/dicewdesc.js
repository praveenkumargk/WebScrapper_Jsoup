var casper=require('casper').create();


var fs = require('fs');


var key='\"selenium\"';


var x=require('casper').selectXPath;


var utils=require('utils');

casper.userAgent('Mozilla/23.0 (compatible; MSIE 6.0; Windows 7)');


casper.start('http://www.dice.com/');

console.log('page loaded');

casper.wait(500,function()
{
this.sendKeys('#FREE_TEXT',key);
console.log('keys sent');
casper.thenClick('#searchSubmit',function(){
console.log('key prtessed');
});
});


casper.wait(5000,function()
{

console.log('detail view');
var page=document.querySelectorAll('div.pageRow div.pageProg a');

casper.capture('D:/gert.png');

var town_info_text=this.evaluate(function() {
    var nodes = document.querySelectorAll('table.summary tbody tr.STDsrRes');
    return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
      return node.textContent;
    });
  });


console.log('desc got');





var objArray= town_info_text.map(function(str) {
var reg = new RegExp("[ ]+","g");
        str= str.replace(reg,"");
str=str.replace("\n\n\n\n","");
var elements = str.split("\n\n\n\n\n");

    var data = {

Title:      (elements[2]).replace(/(\r\n|\n|\r)/gm,""),
company: (elements[3]).replace(/(\r\n|\n|\r)/gm,""),
      location : (elements[4]).replace(/(\r\n|\n|\r)/gm,""),
      date       : (elements[5]).replace(/(\r\n|\n|\r)/gm,""),
      
      source :'dice.com'    
    };
    return data;
  });

utils.dump(objArray);
casper.then(function()
{

fs.write('D:/PPC/WebScrapper/WebScrapper/dicedetail.txt',JSON.stringify(objArray),'w');
fs.write('D:/PPC/WebScrapper/WebScrapper/dicedesc.txt','','w');

});
casper.then(function(){
//console.log(list.length);

var town_info_text1=this.evaluate(function() {
    var nodes = document.querySelectorAll('.STDsrRes td div a');
    return Array.prototype.map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
      return node.getAttribute("href");;
    });
  });

casper.then(function(){
for(var i=0;i<town_info_text1.length;++i)
{
casper.mytest1=this.getCurrentUrl();
casper.mytest='http://www.dice.com'+town_info_text1[i];
casper.thenOpen(casper.mytest);
casper.wait(5000,function(){
console.log('loading desc');
casper.capture('D:/real.png');

var town_info_text2=this.evaluate(function() {
    var nodes = document.querySelectorAll('body');
    return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
      return node.textContent;
    });
  });
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

var objArray= town_info_text2.map(function(str) {
str=str.replace(/(\r\n|\n|\r)/gm,"");

    var data = {

Description:      str,
      
      source :'dice.com'    
    };
    return data;
  });
utils.dump(objArray);
casper.then(function()
{

fs.write('D:/PPC/WebScrapper/WebScrapper/dicedesc.txt',JSON.stringify(objArray),'a');
});


});
casper.thenOpen(casper.mytest1);
casper.wait(500,function(){
casper.capture('D:/hello5.png');
});

}
})

casper.then(function(){

//this.click();

casper.wait(5000);

});

});


});

casper.wait(500,function()
{
var page=document.querySelectorAll('div.pageRow div.pageProg a');
casper.capture('D:/gert.png');




var node1=casper.fetchText('#searchResHD');

 var element1=node1.split(/\s+/g);

var num=element1[7];
console.log(num);
var quo=num/30;
var rem=num%30;
if(rem!=0)
{
quo=quo+1;
}
console.log(rem);
for(var a=0;a<(quo-2);a++)
{
casper.thenClick('.nextPage',function()
{
casper.wait(5000,function(){console.log("next clicked")});
console.log(a);
  var town_info_text=this.evaluate(function() {
    var nodes = document.querySelectorAll('table.summary tbody tr.STDsrRes');
    return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
      return node.textContent;
    });
  });



var objArray= town_info_text.map(function(str) {
var reg = new RegExp("[ ]+","g");

        str= str.replace(reg,"");


str=str.replace("\n\n\n\n","");
var elements = str.split("\n\n\n\n\n");

    var data = {

      Title       : elements[2],
      company     : elements[3],
      location : elements[4],
      date       : elements[5],
source :'dice.com'
    };
    return data;
  });

utils.dump(objArray);

casper.then(function()
{

fs.write('D:/PPC/WebScrapper/WebScrapper/dicedetail.txt',JSON.stringify(objArray),'a');
});
});
casper.then(function(){
//console.log(list.length);

var town_info_text1=this.evaluate(function() {
    var nodes = document.querySelectorAll('.STDsrRes td div a');
    return Array.prototype.map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
      return node.getAttribute("href");;
    });
  });

casper.then(function(){
for(var i=0;i<town_info_text1.length;++i)
{
casper.mytest1=this.getCurrentUrl();
casper.mytest='http://www.dice.com'+town_info_text1[i];
casper.thenOpen(casper.mytest);
casper.wait(5000,function(){
console.log('loading desc');
casper.capture('D:/real.png');


var town_info_text2=this.evaluate(function() {
    var nodes = document.querySelectorAll('body');
    return [].map.call(nodes, function(node) { // Alternatively: return Array.prototype.map.call(...
      return node.textContent;
    });
  });
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

var objArray= town_info_text2.map(function(str) {
str=str.replace(/(\r\n|\n|\r)/gm,"");

    var data = {

Description:      str,
      
      source :'dice.com'    
    };
    return data;
  });
utils.dump(objArray);
casper.then(function()
{

fs.write('D:/PPC/WebScrapper/WebScrapper/dicedesc.txt',JSON.stringify(objArray),'a');
});


});
casper.thenOpen(casper.mytest1);
casper.wait(500,function(){
casper.capture('D:/hello5.png');
});

}
})

casper.then(function(){

//this.click();


});

});
}





});

casper.run();
