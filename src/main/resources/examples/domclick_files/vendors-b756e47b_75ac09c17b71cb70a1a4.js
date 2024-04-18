/*! For license information please see vendors-b756e47b_75ac09c17b71cb70a1a4.js.LICENSE.txt */
"use strict";(self.__LOADABLE_LOADED_CHUNKS__=self.__LOADABLE_LOADED_CHUNKS__||[]).push([[2382],{85221:(e,t,n)=>{n.d(t,{V:()=>b});var r=n(97582),a=n(47442);let i=!1;window.addEventListener("pageshow",(e=>{i=Boolean(e.persisted)}));class s{constructor(e){var{broker:t}=e,n=(0,r._T)(e,["broker"]);this.emit=e=>{const{name:t,value:n}=e,a=(0,r._T)(e,["name","value"]);this.broker.emit({name:t,value:n,more:a})},this.broker=t,this.options=n}}class o extends s{constructor(e){if(super(e),this._onceEmittedEventNames=new Set,this._enabled=!0,this.onMetric=e=>{this._enabled&&this.emit(e)},this.onMetricOnce=e=>{this._onceEmittedEventNames.has(e.name)||(this._onceEmittedEventNames.add(e.name),this.onMetric(e))},this.options.spaMode){const e=(e=>{let t=document.location.href;const n=new MutationObserver((()=>{t!==document.location.href&&(t=document.location.href,e())})),r=document.querySelector("body");return r&&n.observe(r,{childList:!0,subtree:!0}),()=>n.disconnect()})((()=>{e(),this.stop()}))}}stop(){this._enabled=!1}run(){(0,a.Fu)(this.onMetric),(0,a.NO)(this.onMetric),(0,a.Yn)(this.onMetricOnce),Boolean(window.chrome)&&(0,a.a4)(this.onMetric),i||(0,a.mr)(this.onMetric),(0,a.mw)(this.onMetricOnce)}}o.metrics=["CLS","FCP","FID","INP","LCP","TTFB"];class l{constructor(e){this.transports=e.transports}send(e){const t=Array.isArray(e)?e:[e];this.transports.forEach((e=>{e.send(t)}))}}class c{constructor(){this._queue=new Set}add(e){this._queue.add(e)}flush(){const e=this.getAll();return this.clear(),e}getAll(){return Array.from(this._queue)}clear(){this._queue.clear()}}class h extends l{constructor(){super(...arguments),this.queue=new c,this.visibilityChangeCallback=()=>{if("hidden"===document.visibilityState){const e=this.queue.flush();e.length&&this.send(e)}},this.hideCallback=()=>{const e=this.queue.flush();e.length&&this.send(e)}}run(){addEventListener("visibilitychange",this.visibilityChangeCallback),addEventListener("pagehide",this.hideCallback)}onEvent(e){this.queue.add(e)}}class u{constructor(e={}){this.logger=console,e.logger&&(this.logger=e.logger)}send(e){this.logger.info("[Telemetry] flush events"),e.forEach((e=>{this.logger.info(`[Telemetry] event "${e.name}":`,e)}))}}const d=e=>e;class p{constructor(e){var t;this.url=e.url,this.adapter=null!==(t=e.adapter)&&void 0!==t?t:d}send(e){const t=this.adapter({events:e}),n=JSON.stringify(t);if("function"==typeof navigator.sendBeacon){const t={type:"text/plain; charset=UTF-8"};try{if(navigator.sendBeacon(this.url,new Blob([n],t)))return}catch(e){console.error("error send telemetry with navigator.sendBeacon",e)}}fetch(this.url,{method:"POST",headers:{"Content-Type":"application/json;charset=utf-8"},keepalive:!0,body:n}).catch((e=>{console.error("error send telemetry with fetch",e)}))}}const f=e=>({metrics:e.events.map((e=>({name:e.name,value:e.value,env:e.env,page:e.page,platform:e.platform,cmdb_id:e.cmdb_id,version:e.version})))}),m={qa:"https://telemetry.qa-dc.ru/api/metrics/v2",stage:"https://telemetry.stage-dc.ru/api/metrics/v2",prod:"https://telemetry.domclick.ru/api/metrics/v2"};class g extends p{constructor(e){super({url:m[e.env],adapter:f})}}const v=e=>{var t;const n=e.find((([,e])=>{try{return new RegExp(e).test(location.pathname)}catch(e){return!1}}));return null!==(t=null==n?void 0:n[0])&&void 0!==t?t:"unknown"},w="undefined"!=typeof window&&window.requestIdleCallback||((e,t)=>{var n;const r=Date.now();return setTimeout((()=>{e({didTimeout:!1,timeRemaining:()=>Math.max(0,50-(Date.now()-r))})}),null!==(n=null==t?void 0:t.timeout)&&void 0!==n?n:1)});class y{constructor(e){this.reporters=[],this.collectors=[],this._options=e}registerReporter(e,t,n){this.reporters.push([t,new e(n)])}registerCollector(e,t){this.collectors.push(new e(Object.assign(Object.assign({},t),{broker:this})))}emit(e){this.reporters.forEach((([t,n])=>{t.test(e.name)&&n.onEvent(Object.assign(Object.assign({},e),{cmdb_id:this._options.cmdb_id,env:this._options.env,version:this._options.version,platform:matchMedia("(max-width: 768px)").matches?"mobile":"desktop",page:v(this._options.routesMap),additional:this._options.additional}))}))}runCollectMetric(){"undefined"!=typeof window&&w((()=>{this.collectors.forEach((e=>{e.run()})),this.reporters.forEach((([,e])=>{e.run()}))}))}}const b=e=>{var{debug:t,spaMode:n}=e,a=(0,r._T)(e,["debug","spaMode"]);if(a.routesMap.length>20)throw new Error("Telemetry: routesMap.length > 20. Запрещено регистрировать более 20 маршрутов.");const i=new y(a),s=[new g({env:a.env})];t&&s.push(new u),i.registerCollector(o,{spaMode:null!=n&&n}),i.registerReporter(h,new RegExp(`^(${o.metrics.join("|")})$`),{transports:s}),i.runCollectMetric()}},67753:(e,t,n)=>{function r(){return r=Object.assign?Object.assign.bind():function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},r.apply(this,arguments)}var a;n.d(t,{Ep:()=>u,J0:()=>o,LX:()=>M,RQ:()=>$,WK:()=>B,X3:()=>j,Zn:()=>O,Zq:()=>k,aU:()=>a,cP:()=>d,fp:()=>f,lX:()=>s,pC:()=>R}),function(e){e.Pop="POP",e.Push="PUSH",e.Replace="REPLACE"}(a||(a={}));const i="popstate";function s(e){return void 0===e&&(e={}),function(e,t,n,s){void 0===s&&(s={});let{window:l=document.defaultView,v5Compat:d=!1}=s,p=l.history,f=a.Pop,m=null,g=v();function v(){return(p.state||{idx:null}).idx}function w(){f=a.Pop;let e=v(),t=null==e?null:e-g;g=e,m&&m({action:f,location:b.location,delta:t})}function y(e){let t="null"!==l.location.origin?l.location.origin:l.location.href,n="string"==typeof e?e:u(e);return o(t,"No window.location.(origin|href) available to create URL for href: "+n),new URL(n,t)}null==g&&(g=0,p.replaceState(r({},p.state,{idx:g}),""));let b={get action(){return f},get location(){return e(l,p)},listen(e){if(m)throw new Error("A history only accepts one active listener");return l.addEventListener(i,w),m=e,()=>{l.removeEventListener(i,w),m=null}},createHref:e=>t(l,e),createURL:y,encodeLocation(e){let t=y(e);return{pathname:t.pathname,search:t.search,hash:t.hash}},push:function(e,t){f=a.Push;let r=h(b.location,e,t);n&&n(r,e),g=v()+1;let i=c(r,g),s=b.createHref(r);try{p.pushState(i,"",s)}catch(e){if(e instanceof DOMException&&"DataCloneError"===e.name)throw e;l.location.assign(s)}d&&m&&m({action:f,location:b.location,delta:1})},replace:function(e,t){f=a.Replace;let r=h(b.location,e,t);n&&n(r,e),g=v();let i=c(r,g),s=b.createHref(r);p.replaceState(i,"",s),d&&m&&m({action:f,location:b.location,delta:0})},go:e=>p.go(e)};return b}((function(e,t){let{pathname:n,search:r,hash:a}=e.location;return h("",{pathname:n,search:r,hash:a},t.state&&t.state.usr||null,t.state&&t.state.key||"default")}),(function(e,t){return"string"==typeof t?t:u(t)}),null,e)}function o(e,t){if(!1===e||null==e)throw new Error(t)}function l(e,t){if(!e){"undefined"!=typeof console&&console.warn(t);try{throw new Error(t)}catch(e){}}}function c(e,t){return{usr:e.state,key:e.key,idx:t}}function h(e,t,n,a){return void 0===n&&(n=null),r({pathname:"string"==typeof e?e:e.pathname,search:"",hash:""},"string"==typeof t?d(t):t,{state:n,key:t&&t.key||a||Math.random().toString(36).substr(2,8)})}function u(e){let{pathname:t="/",search:n="",hash:r=""}=e;return n&&"?"!==n&&(t+="?"===n.charAt(0)?n:"?"+n),r&&"#"!==r&&(t+="#"===r.charAt(0)?r:"#"+r),t}function d(e){let t={};if(e){let n=e.indexOf("#");n>=0&&(t.hash=e.substr(n),e=e.substr(0,n));let r=e.indexOf("?");r>=0&&(t.search=e.substr(r),e=e.substr(0,r)),e&&(t.pathname=e)}return t}var p;function f(e,t,n){void 0===n&&(n="/");let r=O(("string"==typeof t?d(t):t).pathname||"/",n);if(null==r)return null;let a=m(e);!function(e){e.sort(((e,t)=>e.score!==t.score?t.score-e.score:function(e,t){return e.length===t.length&&e.slice(0,-1).every(((e,n)=>e===t[n]))?e[e.length-1]-t[t.length-1]:0}(e.routesMeta.map((e=>e.childrenIndex)),t.routesMeta.map((e=>e.childrenIndex)))))}(a);let i=null;for(let e=0;null==i&&e<a.length;++e)i=L(a[e],C(r));return i}function m(e,t,n,r){void 0===t&&(t=[]),void 0===n&&(n=[]),void 0===r&&(r="");let a=(e,a,i)=>{let s={relativePath:void 0===i?e.path||"":i,caseSensitive:!0===e.caseSensitive,childrenIndex:a,route:e};s.relativePath.startsWith("/")&&(o(s.relativePath.startsWith(r),'Absolute route path "'+s.relativePath+'" nested under path "'+r+'" is not valid. An absolute child route path must start with the combined path of all its parent routes.'),s.relativePath=s.relativePath.slice(r.length));let l=$([r,s.relativePath]),c=n.concat(s);e.children&&e.children.length>0&&(o(!0!==e.index,'Index routes must not have child routes. Please remove all child routes from route path "'+l+'".'),m(e.children,t,c,l)),(null!=e.path||e.index)&&t.push({path:l,score:x(l,e.index),routesMeta:c})};return e.forEach(((e,t)=>{var n;if(""!==e.path&&null!=(n=e.path)&&n.includes("?"))for(let n of g(e.path))a(e,t,n);else a(e,t)})),t}function g(e){let t=e.split("/");if(0===t.length)return[];let[n,...r]=t,a=n.endsWith("?"),i=n.replace(/\?$/,"");if(0===r.length)return a?[i,""]:[i];let s=g(r.join("/")),o=[];return o.push(...s.map((e=>""===e?i:[i,e].join("/")))),a&&o.push(...s),o.map((t=>e.startsWith("/")&&""===t?"/":t))}!function(e){e.data="data",e.deferred="deferred",e.redirect="redirect",e.error="error"}(p||(p={})),new Set(["lazy","caseSensitive","path","id","index","children"]);const v=/^:\w+$/,w=3,y=2,b=1,E=10,_=-2,S=e=>"*"===e;function x(e,t){let n=e.split("/"),r=n.length;return n.some(S)&&(r+=_),t&&(r+=y),n.filter((e=>!S(e))).reduce(((e,t)=>e+(v.test(t)?w:""===t?b:E)),r)}function L(e,t){let{routesMeta:n}=e,r={},a="/",i=[];for(let e=0;e<n.length;++e){let s=n[e],o=e===n.length-1,l="/"===a?t:t.slice(a.length)||"/",c=M({path:s.relativePath,caseSensitive:s.caseSensitive,end:o},l);if(!c)return null;Object.assign(r,c.params);let h=s.route;i.push({params:r,pathname:$([a,c.pathname]),pathnameBase:A($([a,c.pathnameBase])),route:h}),"/"!==c.pathnameBase&&(a=$([a,c.pathnameBase]))}return i}function M(e,t){"string"==typeof e&&(e={path:e,caseSensitive:!1,end:!0});let[n,r]=function(e,t,n){void 0===t&&(t=!1),void 0===n&&(n=!0),l("*"===e||!e.endsWith("*")||e.endsWith("/*"),'Route path "'+e+'" will be treated as if it were "'+e.replace(/\*$/,"/*")+'" because the `*` character must always follow a `/` in the pattern. To get rid of this warning, please change the route path to "'+e.replace(/\*$/,"/*")+'".');let r=[],a="^"+e.replace(/\/*\*?$/,"").replace(/^\/*/,"/").replace(/[\\.*+^$?{}|()[\]]/g,"\\$&").replace(/\/:(\w+)/g,((e,t)=>(r.push(t),"/([^\\/]+)")));return e.endsWith("*")?(r.push("*"),a+="*"===e||"/*"===e?"(.*)$":"(?:\\/(.+)|\\/*)$"):n?a+="\\/*$":""!==e&&"/"!==e&&(a+="(?:(?=\\/|$))"),[new RegExp(a,t?void 0:"i"),r]}(e.path,e.caseSensitive,e.end),a=t.match(n);if(!a)return null;let i=a[0],s=i.replace(/(.)\/+$/,"$1"),o=a.slice(1);return{params:r.reduce(((e,t,n)=>{if("*"===t){let e=o[n]||"";s=i.slice(0,i.length-e.length).replace(/(.)\/+$/,"$1")}return e[t]=function(e,t){try{return decodeURIComponent(e)}catch(n){return l(!1,'The value for the URL param "'+t+'" will not be decoded because the string "'+e+'" is a malformed URL segment. This is probably due to a bad percent encoding ('+n+")."),e}}(o[n]||"",t),e}),{}),pathname:i,pathnameBase:s,pattern:e}}function C(e){try{return decodeURI(e)}catch(t){return l(!1,'The URL path "'+e+'" could not be decoded because it is is a malformed URL segment. This is probably due to a bad percent encoding ('+t+")."),e}}function O(e,t){if("/"===t)return e;if(!e.toLowerCase().startsWith(t.toLowerCase()))return null;let n=t.endsWith("/")?t.length-1:t.length,r=e.charAt(n);return r&&"/"!==r?null:e.slice(n)||"/"}function P(e,t,n,r){return"Cannot include a '"+e+"' character in a manually specified `to."+t+"` field ["+JSON.stringify(r)+"].  Please separate it out to the `to."+n+'` field. Alternatively you may provide the full path as a string in <Link to="..."> and the router will parse it for you.'}function k(e){return e.filter(((e,t)=>0===t||e.route.path&&e.route.path.length>0))}function R(e,t,n,a){let i;void 0===a&&(a=!1),"string"==typeof e?i=d(e):(i=r({},e),o(!i.pathname||!i.pathname.includes("?"),P("?","pathname","search",i)),o(!i.pathname||!i.pathname.includes("#"),P("#","pathname","hash",i)),o(!i.search||!i.search.includes("#"),P("#","search","hash",i)));let s,l=""===e||""===i.pathname,c=l?"/":i.pathname;if(a||null==c)s=n;else{let e=t.length-1;if(c.startsWith("..")){let t=c.split("/");for(;".."===t[0];)t.shift(),e-=1;i.pathname=t.join("/")}s=e>=0?t[e]:"/"}let h=function(e,t){void 0===t&&(t="/");let{pathname:n,search:r="",hash:a=""}="string"==typeof e?d(e):e,i=n?n.startsWith("/")?n:function(e,t){let n=t.replace(/\/+$/,"").split("/");return e.split("/").forEach((e=>{".."===e?n.length>1&&n.pop():"."!==e&&n.push(e)})),n.length>1?n.join("/"):"/"}(n,t):t;return{pathname:i,search:T(r),hash:W(a)}}(i,s),u=c&&"/"!==c&&c.endsWith("/"),p=(l||"."===c)&&n.endsWith("/");return h.pathname.endsWith("/")||!u&&!p||(h.pathname+="/"),h}const $=e=>e.join("/").replace(/\/\/+/g,"/"),A=e=>e.replace(/\/+$/,"").replace(/^\/*/,"/"),T=e=>e&&"?"!==e?e.startsWith("?")?e:"?"+e:"",W=e=>e&&"#"!==e?e.startsWith("#")?e:"#"+e:"";class j extends Error{}function B(e){return null!=e&&"number"==typeof e.status&&"string"==typeof e.statusText&&"boolean"==typeof e.internal&&"data"in e}const U=["post","put","patch","delete"],q=(new Set(U),["get",...U]);new Set(q),new Set([301,302,303,307,308]),new Set([307,308]),Symbol("deferred")}}]);