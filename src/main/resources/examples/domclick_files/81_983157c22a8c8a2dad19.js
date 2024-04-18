"use strict";(self.__LOADABLE_LOADED_CHUNKS__=self.__LOADABLE_LOADED_CHUNKS__||[]).push([[81],{30081:(e,n,t)=>{t.d(n,{E:()=>Se});var a,i,s=t(86354),r=t(66867),o=t.n(r),l=t(64050),c=t(66658),d=t(8843),u=t(83239),h=t(89238),m=t(61241),p=t(81653),f=t(88418),x=t(33355),k=t(77717),C=t(95098),v=t(82388),j=t(37938),g=t(19757),b=t(63565),T=function(e){var n=e.children,t=(0,l.p)(),a=t.data,i=t.settings,s=t.themeOptions,r=i.onGallerySlideChange,o=a.complexName,c=a.isPlacementPaid?s.gallery.thumbsCount:void 0,d=!a.isPlacementPaid||s.gallery.hideCounter,u=a.isPlacementPaid&&Boolean(a.developerOffersCount)&&a.photos.length>1;return(0,b.jsx)(g.r,{onGallerySlideChange:r,carouselOverlay:n,showThumbs:u,photos:a.photos,env:a.env,alt:o,galleryLoadingEager:i.galleryLoadingEager,thumbsCount:c,hideCounter:d,size:s.gallery.size})},B=t(75900),y=t.n(B),N=t(95182),F=t(65934),P=function(e){var n=e.path,t=e.nameMainPart,a=e.nameLastPart,i=e.withOverlayLink,s=void 0!==i&&i,r=e.onOverlayLinkClick;return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsxs)("a",{href:n,className:y()("sCf2uV",N.Z.interactiveElement),target:"_blank",rel:"noopener",children:[(0,b.jsx)("span",{className:"V0hAKM",children:t}),(0,b.jsx)("span",{className:"HE96JB",children:a})]}),s?(0,b.jsx)(F.u,{link:n,onClick:r,text:t+" "+a}):null]})},S=function(){var e=(0,l.p)(),n=e.data,t=e.settings,a=t.hasOverlayLink,i=t.onOverlayLinkClick,s=n.complexName,r=n.path,o=s.split(" "),c=o.length>1,d=(c?o.slice(0,-1):o).join(" "),u=c?" "+o[o.length-1]:"";return(0,b.jsx)(P,{path:r,nameMainPart:d,nameLastPart:u,withOverlayLink:a,onOverlayLinkClick:i})},L=t(82790),_=t(39601),O=function(e){return e.Flats="flats",e.Apartments="apartments",e}({}),w=function(e){var n=e.flatsCount,t=e.withPrice,a=e.minPrice,i=e.isShortFormat,r=e.link,o=e.onClick,l=e.placementType,c=(0,s.useCallback)((function(e){e.stopPropagation(),o("all")}),[o]);if(!n)return null;var d,u=(d=l,function(e,n){var t="квартира",a="квартиры",i="квартир";return e&&(t="апартаменты",a="апартамента",i="апартаментов"),n&&(t="квартиры и апартаменты",a="квартиры и апартаментов",i="квартир и апартаментов"),{oneProposal:t,someProposals:a,manyProposals:i}}(Boolean(null==d?void 0:d.includes(O.Apartments)),Boolean(null==d?void 0:d.includes(O.Apartments))&&Boolean(null==d?void 0:d.includes(O.Flats)))),h=(0,L.L)(n,[u.oneProposal,u.someProposals,u.manyProposals],"%d %s")+(i?"":" от застройщика")+(t&&a?" от "+(0,_.V)(a)+" ₽":"");return(0,b.jsx)("a",{href:r,target:"_blank",rel:"noopener",onClick:c,className:y()("AgwPdy",N.Z.interactiveElement),children:h})},M=function(){var e=(0,l.p)(),n=e.data,t=e.themeOptions,a=e.settings,i=n.offersCount,s=n.price,r=n.searchLinks,o=n.placementType,c=a.onFlatsLinkClick;return(0,b.jsx)(w,{flatsCount:i,minPrice:s,withPrice:t.flatsCount.withPrice,isShortFormat:t.flatsCount.isShortForm,link:r.searchLinkByDeveloperAndComplex,onClick:c,placementType:o})},E=function(e){return e.Done="done",e.PartialReady="partial_ready",e.InProgress="in_progress",e.Unknown="unknown",e}({}),R="QWzi8-",A=t(50519),D=function(e){var n=e.readinessState,t=e.firstEndBuildYear,a=e.firstEndBuildQuarter,i=e.endBuildQuarter,s=e.endBuildYear,r=t&&a,o=s&&i,l=n===E.InProgress||n===E.Unknown,c=n===E.Done||n===E.PartialReady,d=o&&r&&t===s&&a===i,u=d||o&&!r,h=!d&&o&&r;return!o&&l?null:(0,b.jsxs)("div",{className:"_6Rkhfi","data-test-id":"content",children:[u?(0,b.jsx)("span",{className:R,"data-testid":"oneDateText",children:"Сдача: "+i+" кв. "+s+" г."}):null,h?(0,b.jsx)("span",{className:R,"data-testid":"startAndEndText",children:a+" кв. "+t+" - "+i+" кв. "+s}):null,c?(0,b.jsxs)("span",{className:"s4Bk46","data-testid":"doneOrPartialDone",children:[(0,b.jsx)(A.JO,{type:A.Tu.Check,size:A.Jh.Small}),(0,b.jsx)("span",{className:"Jz670f",children:n===E.Done?"Сдан":"Есть сданные"})]}):null]})},I=function(){var e=(0,l.p)().data.buildingsInfo,n=e.readinessState,t=e.endBuildQuarter,a=e.endBuildYear,i=e.firstEndBuildQuarter,s=e.firstEndBuildYear;return(0,b.jsx)(D,{readinessState:n,endBuildQuarter:t,endBuildYear:a,firstEndBuildQuarter:i,firstEndBuildYear:s})},$=t(91463),Q=t.n($),Y=t(44721),z=function(e){var n=e.room,t=e.onClick,a=e.link,i=(0,s.useCallback)((function(e){e.stopPropagation(),t(0===n.rooms?"studio":String(n.rooms))}),[t]),r=n.rooms+"-комн.";4===n.rooms&&(r=n.rooms+"-комн.+"),0===n.rooms&&(r="Студия");var o=function(e){void 0===e&&(e=0);var n="млн",t=0;if(e<1e6)n="тыс.",t=Math.trunc(e/1e3);else{var a=Number((e/1e6).toFixed(1));t=Number(a.toFixed(a%1>0?1:0))}return"от "+t.toLocaleString("ru-RU")+" "+n+" ₽"}(n.minPrice);return(0,b.jsx)(Y.B,{onClick:i,link:a,labelText:r,priceText:o})},G=function(e){var n,t=e.roomsInfo,a=e.links,i=e.onClick;return(0,b.jsx)("div",{className:y()("eRISxI",(n={},n.yiTqAH=t.length>2,n.LMfXlf=t.length>4,n)),children:t.map((function(e,n){return(0,b.jsx)(z,{link:a[n],room:e,onClick:i},e.rooms)}))})},X=function(e){var n=e.roomsInfo,t=e.links,a=e.onFlatsLinkClick;return n.length?(0,b.jsx)(G,{roomsInfo:n,links:t,onClick:a}):null},K=function(){var e=(0,l.p)(),n=e.data,t=e.settings,a=n.roomsInfo,i=n.isPlacementPaid,r=n.searchLinks.searchRoomLinks,o=t.onFlatsLinkClick,c=s.useMemo((function(){var e=[];return a.forEach((function(n){if(n.rooms>=4){var t=e.findIndex((function(e){return 4===e.rooms}));if(t>=0){var a=e[t];a.minPrice=a.minPrice<n.minPrice?a.minPrice:n.minPrice}else e.push({rooms:4,minPrice:n.minPrice})}else e.push(Q()({},n))})),e}),[a]);return i?(0,b.jsx)(X,{roomsInfo:c,links:r,onFlatsLinkClick:o}):null},H=t(22141),V=t(93306),Z=function(){var e=(0,l.p)(),n=e.data,t=e.settings,a=e.themeOptions;return n.path&&!a.moreButton.hide&&n.developerOffersCount?(0,b.jsx)(V.p,{shareUrl:n.path,offerType:n.offerType,isDomclickOffer:n.isDomclickOffer,duplicatesOfferCount:n.duplicatesOfferCount,onCopyClick:t.onCopyClick}):null},U=t(86664),W=((a={})[c.Y.ComplexSnippetDesktopTemplate]=function(){return(0,b.jsx)(d.A.DesktopLayout,{left:function(){return(0,b.jsx)(T,{children:(0,b.jsx)(v.X,{})})},center:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(S,{}),(0,b.jsx)(u.$,{className:"hFBP0x",children:(0,b.jsx)(M,{})}),(0,b.jsx)(u.$,{className:"qqwO-h",children:(0,b.jsx)(I,{})}),(0,b.jsx)(u.$,{className:"OtuCI-",children:(0,b.jsx)(p.W,{})}),(0,b.jsx)(j.u,{}),(0,b.jsx)(u.$,{className:"T1gR0y",children:(0,b.jsx)(K,{})}),(0,b.jsx)(H.Z,{}),(0,b.jsx)(u.$,{className:"_58pK1X",children:(0,b.jsx)(h.F,{})}),(0,b.jsx)(u.$,{className:"_7-y5fz",children:(0,b.jsx)(x.G,{})})]})},right:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsxs)(u.$,{className:"HJGGne",children:[(0,b.jsx)(k.$,{}),(0,b.jsx)(C.P,{})]}),(0,b.jsx)(f.K,{})]})},sidebar:function(){return(0,b.jsx)(m.n,{})}})},a[c.Y.ComplexSnippetTabletTemplate]=function(){return(0,b.jsx)(d.A.TabletLayout,{left:function(){return(0,b.jsx)(T,{children:(0,b.jsx)(v.X,{})})},center:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(S,{}),(0,b.jsx)(u.$,{className:"DjQj-u",children:(0,b.jsx)(M,{})}),(0,b.jsx)(u.$,{className:"M8IgIa",children:(0,b.jsx)(U.R,{})}),(0,b.jsx)(u.$,{className:"G2ljMw",children:(0,b.jsx)(I,{})}),(0,b.jsx)(u.$,{className:"-d1-tp",children:(0,b.jsx)(p.W,{})}),(0,b.jsx)(u.$,{className:"Gbk51M",children:(0,b.jsx)(j.u,{})}),(0,b.jsx)(u.$,{className:"CRyWYq",children:(0,b.jsx)(f.K,{})}),(0,b.jsx)(H.Z,{}),(0,b.jsx)(u.$,{className:"gC5O0e",children:(0,b.jsx)(h.F,{})}),(0,b.jsx)(u.$,{className:"BIMuks",children:(0,b.jsx)(x.G,{})})]})},sidebar:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(m.n,{}),(0,b.jsx)(H.Z,{}),(0,b.jsx)(Z,{})]})}})},a[c.Y.ComplexSnippetMobileTemplate]=function(){return(0,b.jsx)(d.A.MobileLayout,{center:function(){return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(T,{children:(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(u.$,{className:"vBfYVS",children:(0,b.jsx)(m.n,{})}),(0,b.jsx)(v.X,{})]})}),(0,b.jsx)(u.$,{className:"ehHB-8",children:(0,b.jsx)(S,{})}),(0,b.jsx)(u.$,{className:"zAIxYO",children:(0,b.jsx)(M,{})}),(0,b.jsx)(u.$,{className:"bovdQ3",children:(0,b.jsx)(U.R,{})}),(0,b.jsx)(u.$,{className:"woQ4xj",children:(0,b.jsx)(I,{})}),(0,b.jsx)(u.$,{className:"_1CQguM",children:(0,b.jsx)(p.W,{})}),(0,b.jsx)(j.u,{}),(0,b.jsx)(u.$,{className:"KixeyA",children:(0,b.jsx)(f.K,{})}),(0,b.jsx)(u.$,{className:"lCQjv5",children:(0,b.jsx)(h.F,{})}),(0,b.jsxs)(u.$,{className:"qZo92j",children:[(0,b.jsx)(x.G,{}),(0,b.jsx)(u.$,{className:"vWKydH",children:(0,b.jsx)(Z,{})})]})]})}})},a),q=t(67445),J={template:c.Y.ComplexSnippetDesktopTemplate,gallery:{thumbsCount:3,size:q.ee.LARGE_CARD,hideCounter:!1},overlay:{showTariffBadge:!1},flatsCount:{withPrice:!1,isShortForm:!1},phone:{fluid:!1,isNative:!1,forceCall:!1},moreOffers:{fluid:!1},badges:{hide:!1,showTariffBadge:!1,isVertical:!0,maxCount:4},favoriteButton:{hideBorder:!1,isRound:!1},address:{hide:!1},price:{withPretext:!0,isThanksForTheFlatBonus:!0},moreButton:{hide:!0},developer:{isTruncated:!0,hasLogoFallback:!0}},ee={template:c.Y.ComplexSnippetTabletTemplate,gallery:{thumbsCount:3,size:q.ee.MEDIUM_CARD,hideCounter:!1},overlay:{showTariffBadge:!1},flatsCount:{withPrice:!0,isShortForm:!1},phone:{fluid:!1,isNative:!1,forceCall:!1},moreOffers:{fluid:!1},badges:{hide:!1,showTariffBadge:!1,isVertical:!1,maxCount:4},favoriteButton:{hideBorder:!1,isRound:!1},address:{hide:!1},price:{isThanksForTheFlatBonus:!0},moreButton:{},developer:{isTruncated:!0,hasLogoFallback:!0}},ne={template:c.Y.ComplexSnippetMobileTemplate,gallery:{thumbsCount:3,size:q.ee.SMALL_CARD,hideCounter:!1},overlay:{showTariffBadge:!1},flatsCount:{withPrice:!0,isShortForm:!1},phone:{fluid:!0,isNative:!0,forceCall:!0},moreOffers:{fluid:!1},badges:{hide:!1,showTariffBadge:!1,isVertical:!1,maxCount:4},favoriteButton:{hideBorder:!0,isRound:!0},address:{hide:!1},price:{isThanksForTheFlatBonus:!0},moreButton:{isBottomSheetMode:!0},developer:{isTruncated:!0,hasLogoFallback:!0}},te={template:c.Y.ComplexSnippetMobileTemplate,gallery:{thumbsCount:3,size:q.ee.SMALL_CARD,hideCounter:!1},overlay:{showTariffBadge:!1},flatsCount:{withPrice:!0,isShortForm:!0},phone:{fluid:!0,isNative:!0},moreOffers:{fluid:!1},badges:{hide:!1,showTariffBadge:!1,isVertical:!1,maxCount:4},favoriteButton:{hideBorder:!0,isRound:!0},address:{hide:!1},price:{isThanksForTheFlatBonus:!0},moreButton:{},developer:{isTruncated:!0,hasLogoFallback:!0}},ae=((i={})[c.Q.Large]=J,i[c.Q.Medium]=ee,i[c.Q.Small]=ne,i[c.Q.Rail]=te,i),ie=["product","theme","debug"],se=function(e){var n=e.product,t=e.theme,a=void 0===t?c.Q.Large:t,i=e.debug,r=void 0!==i&&i,d=o()(e,ie);!function(e,n){(0,s.useEffect)((function(){e&&console.warn("[Snippet] detect change callbacks")}),[e,n])}(r,d);var u=ae[a];if(!u.template)return null;var h=W[u.template];return h?(0,b.jsx)(l.F,{data:n,settings:d,themeOptions:u,debug:r,children:(0,b.jsx)(h,{})}):null},re=(t(45005),t(1094)),oe=t(46875),le=t.n(oe),ce=t(84500),de=t.n(ce),ue=t(12714),he=t(26079),me=t(3611),pe=t(88816),fe=t(78386),xe=t(74049),ke=t(98138),Ce=t(55320),ve=t(92316),je=t(18137),ge={title:"Произошла ошибка",description:"Проверьте интернет и попробуйте снова",status:me.ls.ERROR},be=t(39256),Te=t(63888),Be=t(28923),ye=t(26731),Ne="greenMortgageCard",Fe=t(39423),Pe=t(61400),Se=(0,s.memo)((function(e){var n=e.item,t=e.size,a=e.pageName,i=e.index,r=function(e){var n=e.index,t=e.item,a=e.pageName,i=t.telephony,r=i.isThisReplacedPhoneNumber,o=i.metricsData,l=(0,be.r1)(),c=n+1,d=r?"replacedPhone":"directNumber",u=o.realNumber,h=o.replacedNumber,m=o.advCampaignId,p=(0,s.useMemo)((function(){return{contactType:d,realNumber:u,replacedNumber:h,advCampaignId:m}}),[m,d,u,h]),f=(0,s.useCallback)((function(){(0,ke.j)((0,Ce.yX)({item:t,position:c,eventSection:a})),(0,Te.h6)(a,Q()({},p),t,l)}),[t,c,p,a,l]),x=(0,s.useCallback)((function(){(0,ke.j)((0,Ce.Mn)({item:t,position:c,eventSection:a})),(0,Be.WN)()}),[t,c,a]),k=(0,s.useCallback)((function(){(0,ke.j)((0,Ce.rg)({item:t,position:c,eventSection:a}))}),[t,c,a]),C=(0,s.useCallback)((function(){(0,ke.j)((0,Ce.Zx)({item:t,position:c,eventSection:a}))}),[t,c,a]);return{handleComplexHiddenPhoneClick:x,handleComplexNativeCallClick:f,handleComplexPhoneClick:k,handleMortgageCalcClick:(0,s.useCallback)((function(){(0,ke.j)((0,Ce.z1)({item:t,eventSection:a,position:c})),(0,Be.Jp)()}),[a,t,c]),onGallerySlideChange:C,handleBadgeTooltipShow:(0,s.useCallback)((function(e){e===Ne&&(0,ke.j)((0,ye.er)({item:t,eventSection:a,position:c}))}),[t,a,c]),handleBadgeMoreLinkClick:(0,s.useCallback)((function(e){e===Ne&&(0,ke.j)((0,ye.iz)({item:t,eventSection:a,position:c}))}),[t,a,c]),handleThanksForTheFlatBadgeLinkClick:(0,s.useCallback)((function(){(0,ke.j)((0,ye.LN)({item:t,eventSection:a,position:c}))}),[t,a,c]),handleThanksForTheFlatBadgeTooltipOpen:(0,s.useCallback)((function(){(0,ke.j)((0,ye.Ld)({item:t,eventSection:a,position:c}))}),[t,a,c])}}({index:i,item:n,pageName:a}),o=r.handleComplexHiddenPhoneClick,l=r.handleComplexNativeCallClick,c=r.handleComplexPhoneClick,d=r.onGallerySlideChange,u=r.handleMortgageCalcClick,h=r.handleBadgeTooltipShow,m=r.handleBadgeMoreLinkClick,p=r.handleThanksForTheFlatBadgeLinkClick,f=r.handleThanksForTheFlatBadgeTooltipOpen,x=(0,re.u)(t,n.$$type),k=function(e,n){var t=(0,ue.useDispatch)(),a=e.id,i=(0,s.useCallback)((function(e){return(0,fe.y5)(e,a)}),[a]),r=(0,ue.useSelector)(i),o=(0,pe.C1)().changeAlertList,l=(0,s.useCallback)(de()(le()().mark((function i(){return le()().wrap((function(i){for(;;)switch(i.prev=i.next){case 0:if(!r){i.next=10;break}return i.prev=1,i.next=4,t((0,xe.K6)({favoriteId:r,id:a,itemType:je.NB})).unwrap();case 4:i.next=9;break;case 6:i.prev=6,i.t0=i.catch(1),o([ge]);case 9:(0,ke.j)((0,Ce.Qj)({item:e,position:n,actionType:"delete",eventSection:ve.H.FAVORITES_COMPLEXES}));case 10:case"end":return i.stop()}}),i,null,[[1,6]])}))),[o,t,r,a,e,n]),c=(0,s.useCallback)(de()(le()().mark((function i(){return le()().wrap((function(i){for(;;)switch(i.prev=i.next){case 0:return i.prev=0,i.next=3,t((0,xe._p)({id:a,productType:he.TYPE_COMPLEX})).unwrap();case 3:i.next=8;break;case 5:i.prev=5,i.t0=i.catch(0),o([ge]);case 8:(0,ke.j)((0,Ce.Qj)({item:e,position:n,actionType:"add",eventSection:ve.H.FAVORITES_COMPLEXES}));case 9:case"end":return i.stop()}}),i,null,[[0,5]])}))),[o,t,a,e,n]);return{favoriteId:r,removeFromFavorites:l,addToFavorites:c}}(n,i+1),C=k.favoriteId,v=k.addToFavorites,j=k.removeFromFavorites,g=(0,Fe.S)(n,{pageName:a,position:i+1}).copyButtonClick;return(0,b.jsx)(Pe.P,{item:n,index:i,pageName:a,children:(0,b.jsx)(se,{theme:x,onNativePhoneClick:l,onShowPhoneClick:o,onDirectPhoneClick:c,onMortgageCalcClick:u,onGallerySlideChange:d,galleryLoadingEager:0===i,favoriteId:C,addToFavorites:v,removeFromFavorites:j,product:n,onCopyClick:g,hasOverlayLink:!0,onBadgeClick:m,onBadgeTooltipShow:h,onThanksForTheFlatBadgeLinkClick:p,onThanksForTheFlatBadgeTooltipOpen:f})})}))},26731:(e,n,t)=>{t.d(n,{LN:()=>l,Ld:()=>c,er:()=>o,iz:()=>r});var a=t(91463),i=t.n(a),s=t(25421),r=function(e){var n=e.item,t=e.position,a=e.eventSection;return{senseData:i()({position:t},(0,s.X)(n),{event_section:a,event_element:"greenMortgageBubble",block_in_section:"searchResultBlockGreenMortgageBubble"}),elementId:"f499655dafe7c3c655b7bcf957324c3b",elementType:"link",eventType:"click"}},o=function(e){var n=e.item,t=e.position,a=e.eventSection;return{senseData:i()({position:t},(0,s.X)(n),{event_section:a,event_element:"greenMortgageBubble",block_in_section:"searchResultBlockGreenMortgageBubble"}),elementId:"f499655dafe7c3c655b7bcf957324c3b",elementType:"bubble",eventType:"show"}},l=function(e){var n=e.item,t=e.position,a=e.eventSection;return{senseData:i()({position:t},(0,s.X)(n),{event_section:a,event_element:"spasiboBubble",block_in_section:"searchResultBlockSpasiboBubble"}),elementId:"a2c9ba08c6f1a3f84b83aefa5582bd5b",elementType:"link",eventType:"click"}},c=function(e){var n=e.item,t=e.position,a=e.eventSection;return{senseData:i()({position:t},(0,s.X)(n),{event_section:a,event_element:"spasiboBubble",block_in_section:"searchResultBlockSpasiboBubble\t"}),elementId:"a2c9ba08c6f1a3f84b83aefa5582bd5b",elementType:"bubble",eventType:"show"}}},44721:(e,n,t)=>{t.d(n,{B:()=>o});var a=t(75900),i=t.n(a),s=(t(86354),t(95182)),r=t(63565),o=function(e){var n=e.link,t=e.onClick,a=e.labelText,o=e.priceText;return(0,r.jsxs)("li",{className:"EE-rAE",children:[(0,r.jsx)("a",{className:i()("_7BhLq8",s.Z.interactiveElement),href:n,target:"_blank",rel:"noopener",onClick:t,children:a}),(0,r.jsx)("span",{className:"PKhjbb"}),(0,r.jsx)("span",{className:"YeRMdK",children:o})]})}}}]);