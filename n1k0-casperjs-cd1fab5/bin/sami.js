var casper=require('casper').create({
        verbose: true,
        logLevel: 'debug',
        pageSettings: { javascriptEnabled:  true },
        viewportSize: {width: 1024, height: 768}
    });
var fs = require('fs');
var x=require('casper').selectXPath;var utils=require('utils');casper.userAgent('Mozilla/24.0 (compatible; MSIE 6.0; Windows 7)');
casper.start('https://qa-hybris-sf.qa.purchasingpwr.com:9002/productcockpit/login.zul');
casper.wait(5000,function(){});
console.log('Loginpage loaded');
casper.wait(15000,function()
{
console.log('Entered');

this.click('#Textbox_c66aac7$0');
this.sendKeys('#Textbox_c66aac7$0','');
this.sendKeys('#Textbox_c66aac7$0','productmanager');
this.click('#Textbox_e1b8366$0');
this.sendKeys('#Textbox_e1b8366$0','');
this.sendKeys('#Textbox_e1b8366$0','1234');

console.log('keys sent');
casper.thenClick('#LoginButton_c6f5a474$0!real',function(){
console.log('key prtessed');
});
});
casper.wait(5000,function(){
casper.capture('D:/PCm.png');
});
casper.run();