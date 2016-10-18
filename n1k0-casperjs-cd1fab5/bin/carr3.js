var document=[];
 var casper = require('casper').create({
 verbose: true,
logLevel: "debug",
timeout: 14000000,
 clientScripts:  [
'http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js'
]
});
var x=require('casper').selectXPath;
var status='false';
var fs = require('fs');
casper.userAgent('Mozilla/15.0 (compatible; MSIE 6.0; Windows 7)');
casper.start("https://qa-hybris-sf.qa.purchasingpwr.com:9002/store/login?_escaped_fragment_=mystate#", function() {
casper.capture('C:\PowerUp\PPC LinkChecker\4\Test_Data_Automation/ppc.png');
});
casper.then(function(){fs.write('C:/PowerUp/PPC LinkChecker/4/Test_Data_Automation/ter6.txt','false','w');});
casper.then(function() {
this.echo('Select the radio button');
 this.evaluate(function() {
document.querySelector('div[id=formExpand]').setAttribute('class',null);
 });
casper.capture('C:\PowerUp\PPC LinkChecker\4\Test_Data_Automation/ppc.png');
});
casper.then(function() {
 this.fill('form[id="PPCRegistrationForm"]', 
 { 
   email: 'Robert234Sherry@mailinator.com' 
  }, false); 
 });
 casper.waitForSelector('input#checkEmail', function() {
console.log("Check email part");
 this.fill('form[id="PPCRegistrationForm"]', 
{ 
   checkEmail: 'Robert234Sherry@mailinator.com' 
  }, false);
 });
casper.then(function() {
console.log("lastname");
this.fill('form[id="PPCRegistrationForm"]', 
   { 
   lastName: 'Robert' 
  }, false);
 });
casper.then(function() {
console.log("first name");
this.fill('form[id="PPCRegistrationForm"]', 
  { 
firstName: 'Robert' 
 }, false);
});
casper.then(function() {
console.log("passwordpart");
  this.fill('form[id="PPCRegistrationForm"]', 
 { 
 pwd: 'test123' 
}, false);
});
casper.then(function() {
console.log("check passwordpart");
this.fill('form[id="PPCRegistrationForm"]', 
 { 
 checkPwd: 'test123' 
}, false);
});
casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'A', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'M', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'E', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'R', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'I', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'C', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'A', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'N', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', ' ', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'F', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'E', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'D', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'E', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'R', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'A', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'T', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'I', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'O', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'N', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', ' ', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'O', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'F', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', ' ', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'G', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'O', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'V', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'E', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'R', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'N', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'M', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'E', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'N', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'T', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', ' ', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'E', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'M', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'P', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'L', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'O', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'Y', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'E', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'E', {keepFocus: true});
});casper.then(function() {
this.sendKeys('input#employerOrganizationGroup', 'S', {keepFocus: true});
});
casper.wait(14000,function()
{
console.log('submit passed');
casper.capture('C:/PowerUp/PPC LinkChecker/4/Test_Data_Automation/submit.png');
});
casper.wait(4000,function(){
casper.then(function() {
this.click('input#employerOrganizationGroup',function()
{});
});
casper.then(function() {
this.wait(4000, function() {
if (this.visible('a[text()=AMERICAN FEDERATION OF GOVERNMENT EMPLOYEES]')) {
this.click('a[text()=AMERICAN FEDERATION OF GOVERNMENT EMPLOYEES]');
} else {
console.log('Error clicking the first confirmation box', 'error');
}
});
});
 this.click('#agreeTermsAndConditions',function()
{});
 });
 casper.then(function()
{
this.page.injectJs('relative/local/path/to/jquery.js');
this.evaluate(function () { jq = $.noConflict(true) } );
});
casper.thenClick('#registerbtn',function(){});
casper.wait(500,function()
{
console.log('submit passed');
casper.capture('C:/PowerUp/PPC LinkChecker/4/Test_Data_Automation/ppcgroupfinal24.png');
});
casper.then(function(){
casper.waitForSelector('li.logged_in', function() {
console.log("Registration completed");
fs.write('C:/PowerUp/PPC LinkChecker/4/Test_Data_Automation/ter6.txt','true','w');
var welcome=document.querySelector('li.logged_in').fetchText();
Console.log('Welcome message is');
Console.log(welcome);
if(welcome.indexOf('Hello')>-1)
{
this.click(x('//a[@href="/store/logout"]'),function(){
console.log('signout clicked');
casper.capture('C:/PowerUp/PPC LinkChecker/4/Test_Data_Automation/ppcgroupsignout.png');
});
}
});
});
casper.then(function(){
var link=this.getCurrentUrl();
if(link.indexOf('my-account')!=-1){fs.write('C:/PowerUp/PPC LinkChecker/4/Test_Data_Automation/ter6.txt','true','w');}else{fs.write('C:/PowerUp/PPC LinkChecker/4/Test_Data_Automation/ter6.txt','false','w');}
});
casper.run();
