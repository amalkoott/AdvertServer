
(function (ph){
try{
var A = self['DSPCounter' || 'AdriverCounter'],
	a = A(ph);
a.reply = {
ph:ph,
rnd:'871537',
bt:62,
sid:224073,
pz:0,
sz:'%2f',
bn:0,
sliceid:0,
netid:0,
ntype:0,
tns:0,
pass:'',
adid:0,
bid:2864425,
geoid:81,
cgihref:'//ad.adriver.ru/cgi-bin/click.cgi?sid=224073&ad=0&bid=2864425&bt=62&bn=0&pz=0&xpid=Dl60xjW0XLBY2ffELK13hnXT8ObtlREcWd3SbL1d00d4Q1jxzaYimXpKynUWoKtNlDFkgwK4apFPpTA&ref=https:%2f%2fspb.cian.ru%2f&custom=10%3D300769744%252C300739134%252C300869894%252C214883185%252C300829506%252C300840050%252C300635563%252C300818024%252C300781993%252C300840926%252C300202292%252C300484666%252C296370832%252C299070497%252C300642956%252C282891720%252C300580712%252C281354442%252C300875784%252C287155912%252C300799813%252C300828286%252C300230451%252C278199952%252C296717078%252C299372488%252C300796483%252C269313560%3B206%3DDSPCounter',
target:'_blank',
width:'0',
height:'0',
alt:'AdRiver',
mirror:A.httplize('//servers1.adriver.ru'), 
comp0:'0/script.js',
custom:{"10":"300769744%2C300739134%2C300869894%2C214883185%2C300829506%2C300840050%2C300635563%2C300818024%2C300781993%2C300840926%2C300202292%2C300484666%2C296370832%2C299070497%2C300642956%2C282891720%2C300580712%2C281354442%2C300875784%2C287155912%2C300799813%2C300828286%2C300230451%2C278199952%2C296717078%2C299372488%2C300796483%2C269313560","206":"DSPCounter"},
cid:'',
uid:0,
xpid:'Dl60xjW0XLBY2ffELK13hnXT8ObtlREcWd3SbL1d00d4Q1jxzaYimXpKynUWoKtNlDFkgwK4apFPpTA'
}
var r = a.reply;

r.comppath = r.mirror + '/images/0002864/0002864425/' + (/^0\//.test(r.comp0) ? '0/' : '');
r.comp0 = r.comp0.replace(/^0\//,'');
if (r.comp0 == "script.js" && r.adid){
	A.defaultMirror = r.mirror; 
	A.loadScript(r.comppath + r.comp0 + '?v' + ph) 
} else if ("function" === typeof (A.loadComplete)) {
   A.loadComplete(a.reply);
}
(function (o) {
	var i, w = o.c || window, d = document, y = 31;
	function oL(){
		if (!w.postMessage || !w.addEventListener) {return;}
		if (w.document.readyState == 'complete') {return sL();}
		w.addEventListener('load', sL, false);
	}
	function sL(){try{i.contentWindow.postMessage('pgLd', '*');}catch(e){}}
	function mI(u, oL){
		var i = d.createElement('iframe'); i.setAttribute('src', o.hl(u)); i.onload = oL; with(i.style){width = height = '10px'; position = 'absolute'; top = left = '-10000px'} d.body.appendChild(i);
		return i;
	}
	function st(u, oL){
		if (d.body){return i = mI(u, oL)}
		if(y--){setTimeout(function(){st(u, oL)}, 100)}
	}
	st(o.hl('https://content.adriver.ru/banners/0002186/0002186173/0/s.html?0&0&1&0&871537&0&0&81&128.204.75.112&javascript&' + (o.all || 0)), oL);
}({
	hl: function httplize(s){return ((/^\/\//).test(s) ? ((location.protocol == 'https:')?'https:':'http:') : '') + s},
        
	
	all: 1
	
}));
}catch(e){} 
}('1'));
