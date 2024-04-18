
(function (ph){
try{
var A = self['DSPCounter' || 'AdriverCounter'],
	a = A(ph);
a.reply = {
ph:ph,
rnd:'326371',
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
cgihref:'//ad.adriver.ru/cgi-bin/click.cgi?sid=224073&ad=0&bid=2864425&bt=62&bn=0&pz=0&xpid=Do3lCzY6dPzf6J3trlRg_QRrTNmksDfkCxHi9VvOQAzDu8AgUXc_a0F5aKxECFz84aPgjW0VXvMhcmQ&ref=https:%2f%2fspb.cian.ru%2f&custom=10%3D300524305%252C300524306%252C300774754%252C300234068%252C300568883%252C271403599%252C300767716%252C300872080%252C300558679%252C300790526%252C300876377%252C275611853%252C298573102%252C291788217%252C300788946%252C299593600%252C300887229%252C281354442%252C300875784%252C287155912%252C300799813%252C300828286%252C300230451%252C278199952%252C296717078%252C299372488%252C300796483%252C269313560%3B206%3DDSPCounter',
target:'_blank',
width:'0',
height:'0',
alt:'AdRiver',
mirror:A.httplize('//mlb2.adriver.ru'), 
comp0:'0/script.js',
custom:{"10":"300524305%2C300524306%2C300774754%2C300234068%2C300568883%2C271403599%2C300767716%2C300872080%2C300558679%2C300790526%2C300876377%2C275611853%2C298573102%2C291788217%2C300788946%2C299593600%2C300887229%2C281354442%2C300875784%2C287155912%2C300799813%2C300828286%2C300230451%2C278199952%2C296717078%2C299372488%2C300796483%2C269313560","206":"DSPCounter"},
cid:'',
uid:0,
xpid:'Do3lCzY6dPzf6J3trlRg_QRrTNmksDfkCxHi9VvOQAzDu8AgUXc_a0F5aKxECFz84aPgjW0VXvMhcmQ'
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
	st(o.hl('https://content.adriver.ru/banners/0002186/0002186173/0/s.html?0&0&1&0&326371&0&0&81&128.204.75.112&javascript&' + (o.all || 0)), oL);
}({
	hl: function httplize(s){return ((/^\/\//).test(s) ? ((location.protocol == 'https:')?'https:':'http:') : '') + s},
        
	
	all: 1
	
}));
}catch(e){} 
}('0'));
