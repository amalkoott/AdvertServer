
(function (ph){
try{
var A = self['' || 'AdriverCounterJS'],
	a = A(ph);
a.reply = {
ph:ph,
rnd:'199829',
bt:62,
sid:225467,
pz:0,
sz:'cian_cian_ru_all%2dpages1',
bn:0,
sliceid:0,
netid:0,
ntype:0,
tns:0,
pass:'',
adid:0,
bid:2864425,
geoid:81,
cgihref:'//ad.adriver.ru/cgi-bin/click.cgi?sid=225467&ad=0&bid=2864425&bt=62&bn=0&pz=0&xpid=D8uTPNGR_57HgedzwAT2_JDvDpMaoT_jFBB3w87asCVB8xIYKshY-wmnyqNGsebv46xSK8uUT1akWcg&ref=https:%2f%2fspb.cian.ru%2f&custom=13%3D855828314.1710526076%3B14%3D1710526076365496799',
target:'_blank',
width:'0',
height:'0',
alt:'AdRiver',
mirror:A.httplize('//mh8.adriver.ru'), 
comp0:'0/script.js',
custom:{"13":"855828314.1710526076","14":"1710526076365496799"},
cid:'',
uid:0,
xpid:'D8uTPNGR_57HgedzwAT2_JDvDpMaoT_jFBB3w87asCVB8xIYKshY-wmnyqNGsebv46xSK8uUT1akWcg'
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
	st(o.hl('https://content.adriver.ru/banners/0002186/0002186173/0/s.html?0&0&1&0&199829&0&0&81&128.204.75.112&javascript&' + (o.all || 0)), oL);
}({
	hl: function httplize(s){return ((/^\/\//).test(s) ? ((location.protocol == 'https:')?'https:':'http:') : '') + s},
        
	
	all: 1
	
}));
}catch(e){} 
}('1'));
