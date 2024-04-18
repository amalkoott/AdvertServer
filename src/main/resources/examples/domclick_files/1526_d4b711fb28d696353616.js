"use strict";(self.__LOADABLE_LOADED_CHUNKS__=self.__LOADABLE_LOADED_CHUNKS__||[]).push([[1526],{31526:(e,o,r)=>{r.d(o,{_:()=>he});var n,s,a,i=r(66867),l=r.n(i),t=r(86354),c=r(64050),d=r(99044),u=r(8843),f=r(75671),h=r(83239),m=r(4914),p=r(89238),x=r(61241),j=r(49721),g=r(81653),v=r(88418),L=r(59534),b=r(63565),B=function(){var e,o=(0,c.p)(),r=o.data,n=o.settings,s=o.themeOptions,a=r.complex,i=s.complex.hideName,l=!s.complex.hideIcon;return i||!a?null:(0,b.jsx)(L.i,{onClick:n.onComplexLinkClick,building:a.building,name:a.name,showReleasedInfo:!0,slug:null!=(e=a.slug)?e:"",hasIcon:l})},N=r(33355),T=r(77717),A=r(75900),y=r.n(A),S=r(82790),$=r(95182),M=function(e){e.stopPropagation()},C=function(e){var o=e.flatCount,r=e.link,n=(0,S.L)(o,["предложение","предложения","предложений"],"%d %s такой планировки");return(0,b.jsx)("a",{href:r+"?to_flats=true",className:y()("j0RP8X",$.Z.interactiveElement),"data-e2e-test":"same-flat",onClick:M,target:"_blank",rel:"noopener",children:n})},F=function(){var e=(0,c.p)().data;return e.offersCount<2?null:(0,b.jsx)(C,{flatCount:e.offersCount,link:e.path})},I=r(95098),z=r(82388),D=r(37938),_=r(55408),w=r(28476),O=r(49423),k=r(14155),H="P2PmuO",W=r(84704),E=((n={})[-1]=function(e){return e.isApartment?"Апартаменты со свободной планировкой":"Свободная планировка"},n[0]=function(e){return e.isApartment?"Апартаменты-студия":"Студия"},n.default=function(e){var o=e.isApartment?["ап.","апартаменты"]:["кв.","квартира"];return e.rooms+"-комн. "+(e.simple?o[0]:o[1])},n),R=function(e,o){return void 0===o&&(o="м²"),e&&o?String(e).replace(".",",")+" "+o:null},P=function(e,o,r){void 0===o&&(o=!1),void 0===r&&(r={});var n=function(e){var o,r,n,s,a,i,l,t,c,d,u,f,h,m,p,x,j,g,v,L,b;return{rooms:null!=(o=null==(r=e.objectInfo)?void 0:r.rooms)?o:null==(n=e.generalInfo)?void 0:n.rooms,floor:null!=(s=null==(a=e.objectInfo)?void 0:a.floor)?s:null==(i=e.generalInfo)?void 0:i.minFloor,objectArea:null!=(l=null==(t=e.objectInfo)?void 0:t.area)?l:null==(c=e.generalInfo)?void 0:c.area,isApartment:null!=(d=null!=(u=null==(f=e.objectInfo)?void 0:f.isApartment)?u:null==(h=e.generalInfo)?void 0:h.isApartment)&&d,roomArea:null==(m=e.objectInfo)?void 0:m.roomArea,landArea:null==(p=e.land)?void 0:p.area,lotType:null==(x=e.land)?void 0:x.lotType.displayName,floors:null!=(j=null==(g=e.house)?void 0:g.floors)?j:null==(v=e.generalInfo)?void 0:v.floors,floorMin:null==(L=e.generalInfo)?void 0:L.minFloor,floorMax:null==(b=e.generalInfo)?void 0:b.maxFloor,offerType:e.offerType,roomsOffered:e.roomsOffered}}(e),s=n.rooms,a=n.floor,i=n.objectArea,l=n.roomArea,t=n.landArea,c=n.lotType,d=n.floors,u=n.floorMin,f=n.floorMax,h=n.offerType,m=n.roomsOffered,p=n.isApartment,x=R(i),j=R(t,"сот."),g=R(l),v=function(e,o){return e&&o&&o>e?e+"-"+o:null}(u,f),L=function(e,o){return e&&o?e+"/"+o+" эт.":null}(v||a,d),b=function(e){var o=e.offerType,r=void 0===o?"":o,n=e.rooms,s=e.isApartment,a=e.roomsOffered,i=e.floors,l=e.isShortFormat;switch(r){case"flat":case"layout":return function(e,o,r){return void 0===r&&(r=!1),e||0===e?(0===e||-1===e?E[e]:E.default)({isApartment:r,rooms:e,simple:o}):null}(n,l,s);case"room":return function(e,o){return e&&o?e+"-комн. из "+o:null}(a,n);case"house":return function(e){return e?e+"-эт. дом":"Дом"}(i);case"townhouse":return function(e){return e?e+"-эт. таунхаус":"Таунхаус"}(i);case"lot":case"house_part":return Y[r]||null;default:return Y[r]}}({offerType:h,rooms:s,isApartment:p,roomsOffered:m,floors:d,isShortFormat:o}),B=function(e){var o=e.offerType,r=e.lotType;switch(void 0===o?"":o){case"house":case"townhouse":case"lot":case"house_part":return r;default:return""}}({offerType:h,lotType:c}),N="room"===h&&g?g:x,T=r.hideLandArea&&!["lot","commercial_land"].includes(h);return o?[b,N,T?null:j,r.hideLotType?null:B,r.hideFloor?null:L].filter((function(e){return Boolean(e)})):[b,N,j,B,L].filter((function(e){return e}))},Y={complex:"Новостройки",new_flat:"Квартира в ЖК",house_part:"Часть дома",lot:"Участок",office:"Офис",retail:"Торг. помещ.",catering:"Общепит",free_purpose:"Своб. назнач.",warehouse:"Склад",manufacturing:"Производство",hotel:"Гостиница",commercial_land:"Земельный участок",garage:"Гараж",garage_box:"Бокс",parking_place:"Машиноместо"},Q=function(e){var o=e.children,r=(0,c.p)(),n=r.data,s=r.themeOptions,a=r.settings,i=n.offerType,l=n.generalInfo,d=s.properties,u=d.fontSize,f=d.isSimple,h=d.hideLabels,m=a.hasOverlayLink,p=(0,t.useMemo)((function(){return P({offerType:i,generalInfo:l},f,h)}),[i,l,h]);return(0,b.jsx)(W.E,{link:n.path,labels:p,fontSize:u,oneLine:f,withOverlayLink:m,children:o})},U=r(22141),K=r(19560),G=r(86664),X={locationInfo:"GWRpSQ",propertyWrapper:"XAHADp",buildingInfo:"qG4U-s",complexLinkWrapper:"_1VhMN7",addressWrapper:"xJf6OZ",priceWrapper:"TepBAb",locationInfoItem:"hlyuoL",descriptionInfo:"LC8A3Q",sellerInfo:"uKMl9G",buttons:"_4NM0cI",addressInfo:"azF1on",badges:"Q3kmKs",developer:"_3r2Dpf"},Z=r(58306),V=r(99068),q="Yh7CEf",J="ol3pFp",ee=((s={})[d.Y.LayoutSnippetDesktopTemplate]=function(){return(0,b.jsx)(u.A.DesktopLayout,{left:function(){return(0,b.jsx)(j.l,{children:(0,b.jsx)(z.X,{})})},center:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(h.$,{className:"ZgRuSG",children:(0,b.jsx)(Q,{})}),(0,b.jsx)(h.$,{className:"U-rcNz",children:(0,b.jsx)(F,{})}),(0,b.jsxs)(h.$,{className:"XQ4US9",column:!0,children:[(0,b.jsx)(h.$,{className:H,children:(0,b.jsx)(B,{})}),(0,b.jsx)(h.$,{className:H,children:(0,b.jsx)(g.W,{})}),(0,b.jsx)(h.$,{className:H,children:(0,b.jsx)(D.u,{})})]}),(0,b.jsx)(h.$,{className:"kFRGEp",children:(0,b.jsx)(m.N,{})}),(0,b.jsx)(U.Z,{}),(0,b.jsx)(p.F,{}),(0,b.jsx)(h.$,{className:"VO8KRm",children:(0,b.jsx)(h.$,{className:"ULeXvC",children:(0,b.jsx)(N.G,{})})})]})},right:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(T.$,{}),(0,b.jsx)(I.P,{}),(0,b.jsx)(h.$,{className:"y-pHrp",children:(0,b.jsx)(v.K,{})})]})},sidebar:function(e){var o=e.data;return(0,b.jsxs)(f.H,{showCondition:!o.isSharedCompilation,children:[(0,b.jsx)(h.$,{className:"-Vbggn",children:(0,b.jsx)(x.n,{})}),(0,b.jsxs)(_.T,{children:[(0,b.jsx)(w.U,{}),(0,b.jsx)(O.K,{})]})]})},bottom:function(){return(0,b.jsx)(k.r,{})}})},s[d.Y.LayoutSnippetTabletTemplate]=function(){return(0,b.jsx)(u.A.TabletLayout,{left:function(){return(0,b.jsx)(j.l,{children:(0,b.jsx)(z.X,{})})},center:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsxs)(h.$,{className:X.priceWrapper,children:[(0,b.jsx)(T.$,{}),(0,b.jsx)(h.$,{className:X.regularPriceWrapper,children:(0,b.jsx)(G.R,{})})]}),(0,b.jsx)(F,{}),(0,b.jsx)(h.$,{className:X.propertyWrapper,children:(0,b.jsx)(Q,{})}),(0,b.jsx)(h.$,{className:X.buildingInfo,children:(0,b.jsx)(B,{})}),(0,b.jsx)(h.$,{className:X.addressWrapper,children:(0,b.jsx)(g.W,{})}),(0,b.jsx)(h.$,{className:X.locationInfoItem,children:(0,b.jsx)(D.u,{})}),(0,b.jsx)(h.$,{className:X.badges,children:(0,b.jsx)(v.K,{})}),(0,b.jsx)(U.Z,{}),(0,b.jsx)(h.$,{className:X.developer,children:(0,b.jsx)(p.F,{})}),(0,b.jsx)(h.$,{className:X.buttons,children:(0,b.jsx)(N.G,{})})]})},sidebar:function(e){var o=e.data;return(0,b.jsxs)(f.H,{showCondition:!o.isSharedCompilation,children:[(0,b.jsx)(x.n,{}),(0,b.jsx)(U.Z,{}),(0,b.jsx)(K.B,{})]})},bottom:function(){return(0,b.jsx)(k.r,{})}})},s[d.Y.LayoutSnippetMobileTemplate]=function(){return(0,b.jsx)(u.A.MobileLayout,{center:function(e){var o=e.data;return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(j.l,{children:(0,b.jsx)(z.X,{})}),(0,b.jsx)(h.$,{className:"tOK1nQ",children:(0,b.jsx)(k.r,{})}),(0,b.jsxs)(h.$,{className:"EdBNsq",children:[(0,b.jsx)(h.$,{className:"mhvmJE",children:(0,b.jsx)(T.$,{})}),(0,b.jsx)(x.n,{})]}),(0,b.jsx)(F,{}),(0,b.jsx)(h.$,{className:"-Bf-Vv",children:(0,b.jsx)(Q,{})}),(0,b.jsx)(h.$,{className:"WDcCLf",children:(0,b.jsx)(B,{})}),(0,b.jsx)(h.$,{className:"QqTfB5",column:!0,children:(0,b.jsx)(g.W,{})}),(0,b.jsx)(h.$,{className:"hcxUZn",children:(0,b.jsx)(D.u,{})}),(0,b.jsx)(h.$,{className:"_2kVeox",children:(0,b.jsx)(v.K,{})}),(0,b.jsxs)(h.$,{className:"Ayp0py",children:[(0,b.jsx)(N.G,{}),(0,b.jsx)(f.H,{showCondition:!o.isSharedCompilation,children:(0,b.jsx)(h.$,{className:"qU9YQK",children:(0,b.jsx)(K.B,{})})})]})]})}})},s[d.Y.MainFlexTemplate]=function(){return(0,b.jsx)(u.A.MobileLayout,{center:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(Z.D,{children:(0,b.jsx)(V.c,{})}),(0,b.jsx)(h.$,{className:"_5YC-MP",children:(0,b.jsx)(h.$,{className:"D4S1tY",children:(0,b.jsx)(T.$,{})})}),(0,b.jsx)(h.$,{className:"OKt3JK",children:(0,b.jsx)(Q,{})}),(0,b.jsxs)(h.$,{className:"UsEZO5",column:!0,children:[(0,b.jsx)(h.$,{className:q,children:(0,b.jsx)(g.W,{})}),(0,b.jsx)(h.$,{className:q,children:(0,b.jsx)(D.u,{})})]})]})}})},s[d.Y.MicroFlexTemplate]=function(){return(0,b.jsx)(u.A.MobileLayout,{center:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(Z.D,{}),(0,b.jsx)(h.$,{className:"GYCOF4",children:(0,b.jsx)(h.$,{className:"_2Q-PFo",children:(0,b.jsx)(T.$,{})})}),(0,b.jsx)(Q,{}),(0,b.jsxs)(h.$,{className:"r1h5-1",column:!0,children:[(0,b.jsx)(h.$,{className:J,children:(0,b.jsx)(D.u,{})}),(0,b.jsx)(h.$,{className:J,children:(0,b.jsx)(g.W,{})})]})]})}})},s),oe=r(41226),re=r(67445),ne=r(37021),se={template:d.Y.LayoutSnippetDesktopTemplate,gallery:{coverDisabled:!1,thumbsCount:3,isHorizontal:!0,size:re.ee.LARGE_CARD},complex:{hideIcon:!1},phone:{fluid:!1,isNative:!0},moreOffers:{fluid:!1},price:{isHorizontal:!1,isThanksForTheFlatBonus:!0},discountedPrice:{fontSize:oe.B.SMALL},badges:{hide:!1,showTariffBadge:!1,isVertical:!0,maxCount:4},address:{},overlay:{showProfitBadge:!0,showTariffBadge:!1,fromDeveloperBadge:!0},note:{showDeleteButton:!0},favoriteButton:{hideBorder:!1},properties:{fontSize:ne.j.LARGE},moreButton:{hide:!0},developer:{isTruncated:!0,hasLogoFallback:!0}},ae={template:d.Y.LayoutSnippetTabletTemplate,gallery:{coverDisabled:!1,thumbsCount:3,isHorizontal:!0,size:re.ee.MEDIUM_CARD},complex:{hideIcon:!1},phone:{fluid:!1,isNative:!0},moreOffers:{fluid:!1},price:{isHorizontal:!0,isThanksForTheFlatBonus:!1},discountedPrice:{fontSize:oe.B.SMALL},badges:{hide:!1,showTariffBadge:!1,isVertical:!1,maxCount:4},address:{oneLined:!0},overlay:{showProfitBadge:!0,showTariffBadge:!1,fromDeveloperBadge:!0},favoriteButton:{hideBorder:!1},properties:{fontSize:ne.j.MEDIUM},moreButton:{hasLocation:!0,hasNote:!0},developer:{isTruncated:!0,hasLogoFallback:!0}},ie={template:d.Y.LayoutSnippetMobileTemplate,gallery:{coverDisabled:!1,thumbsCount:3,isHorizontal:!0,size:re.ee.SMALL_CARD},complex:{hideIcon:!0},phone:{fluid:!0,forceCall:!0,isNative:!0},moreOffers:{fluid:!1},price:{isHorizontal:!1,isThanksForTheFlatBonus:!0},discountedPrice:{fontSize:oe.B.EXTRASMALL},badges:{hide:!1,showTariffBadge:!1,maxCount:4},address:{},overlay:{showProfitBadge:!0,showTariffBadge:!1,fromDeveloperBadge:!0},favoriteButton:{hideBorder:!0},properties:{fontSize:ne.j.SMALL},moreButton:{hasLocation:!0,isBottomSheetMode:!0,hasNote:!0},developer:{isTruncated:!0,hasLogoFallback:!0}},le={template:d.Y.LayoutSnippetMobileTemplate,gallery:{coverDisabled:!1,thumbsCount:3,isHorizontal:!0,size:re.ee.SMALL_CARD},complex:{hideName:!0,hideIcon:!0},phone:{fluid:!0,isNative:!0},moreOffers:{fluid:!1},price:{isHorizontal:!1,isThanksForTheFlatBonus:!0},discountedPrice:{fontSize:oe.B.EXTRASMALL},badges:{hide:!1,showTariffBadge:!1,maxCount:4},address:{},overlay:{showProfitBadge:!0,showTariffBadge:!1,fromDeveloperBadge:!0},favoriteButton:{hideBorder:!0},properties:{fontSize:ne.j.SMALL},moreButton:{hide:!0,hasNote:!0},developer:{isTruncated:!0,hasLogoFallback:!0}},te=r(71735),ce={template:d.Y.MainFlexTemplate,gallery:{coverDisabled:!0,thumbsCount:0,isHorizontal:!0,size:re.ee.SMALL_CARD},complex:{hideIcon:!0},phone:{fluid:!0,forceCall:!0,isNative:!0},moreOffers:{fluid:!1},price:{fontSize:oe.B.MEDIUM,isHorizontal:!1,isOneLine:!0,hideMeasure:!0},badges:{hide:!1,showTariffBadge:!1,maxCount:3},address:{},overlay:{showProfitBadge:!0,showTariffBadge:!1,fromDeveloperBadge:!0},favoriteButton:{hideBorder:!0},properties:{fontSize:ne.j.SMALL,isSimple:!0},moreButton:{},subway:{singleIcon:!0},layout:{behaviour:te.pi.flexWidth,minWidth:te.gl.minWidth232},developer:{isTruncated:!0,hasLogoFallback:!0}},de={template:d.Y.MicroFlexTemplate,gallery:{coverDisabled:!0,height:re.z2.height104,thumbsCount:0,isHorizontal:!0,size:re.ee.SMALL_CARD},complex:{hideIcon:!0},phone:{fluid:!0,forceCall:!0,isNative:!0},moreOffers:{fluid:!1},price:{fontSize:oe.B.MEDIUM,isHorizontal:!1,isOneLine:!0,hideMeasure:!0},badges:{hide:!1,showTariffBadge:!1,maxCount:3},address:{},subway:{hideTimeOnFoot:!0,singleIcon:!0},overlay:{showProfitBadge:!1,showTariffBadge:!1,fromDeveloperBadge:!1},favoriteButton:{hideBorder:!0},properties:{fontSize:ne.j.MICRO,isSimple:!0,hideLabels:{hideFloor:!0}},moreButton:{},layout:{behaviour:te.pi.flexWidth,font:{fontSize:te.Iz.fontSize12,lineHeight:te.rO.lineHeight16},minWidth:te.gl.minWidth136},developer:{isTruncated:!0,hasLogoFallback:!0}},ue=((a={})[d.Q.Large]=se,a[d.Q.Medium]=ae,a[d.Q.Small]=ie,a[d.Q.Rail]=le,a[d.Q.MainFlex]=ce,a[d.Q.MicroFlex]=de,a),fe=["product","theme","debug"],he=function(e){var o=e.product,r=e.theme,n=void 0===r?d.Q.Large:r,s=e.debug,a=void 0!==s&&s,i=l()(e,fe);!function(e,o){(0,t.useEffect)((function(){e&&console.warn("[Snippet] detect change callbacks")}),[e,o])}(a,i);var u=ue[n];if(!u.template)return null;var f=ee[u.template];return f?(0,b.jsx)(c.F,{data:o,settings:i,themeOptions:u,debug:a,children:(0,b.jsx)(f,{})}):null}}}]);