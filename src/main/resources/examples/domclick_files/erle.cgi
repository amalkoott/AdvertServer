
(function (ph){
try{
var A = self['' || 'AdriverCounter'],
	a = A(ph);
a.reply = {
ph:ph,
rnd:'796937',
bt:62,
sid:224247,
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
cgihref:'//ad.adriver.ru/cgi-bin/click.cgi?sid=224247&ad=0&bid=2864425&bt=62&bn=0&pz=0&xpid=Dp4HsU6Oc25RWRwxLVQDySURVuW0mW2RxRVo4HmOI7fXbLNogMOkoOeYSp1rjoN-7Rh96Ybs7Zstg-Q&ref=https:%2f%2fspb.domclick.ru%2f&custom=',
target:'_blank',
width:'0',
height:'0',
alt:'AdRiver',
mirror:A.httplize('//servers4.adriver.ru'), 
comp0:'0/script.js',
custom:{},
cid:'',
uid:0,
xpid:'Dp4HsU6Oc25RWRwxLVQDySURVuW0mW2RxRVo4HmOI7fXbLNogMOkoOeYSp1rjoN-7Rh96Ybs7Zstg-Q'
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
}catch(e){} 
}('1'));
