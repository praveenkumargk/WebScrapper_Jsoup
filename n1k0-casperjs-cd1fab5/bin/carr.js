var document=[];
var casper = require('casper').create({
      verbose: true,
          logLevel: "debug",
timeout: 15000000,
 clientScripts:  [
	 	'http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js'
	 ]
});

var x=require('casper').selectXPath;

casper.userAgent('Mozilla/15.0 (compatible; MSIE 6.0; Windows 7)');

casper.start("https://qa-hybris-sf.qa.purchasingpwr.com:9002/store/login?_escaped_fragment_=mystate#", function() {
     
casper.capture('D:/ppc.png');

});


casper.run();

