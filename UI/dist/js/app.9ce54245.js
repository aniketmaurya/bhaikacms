(function(e){function t(t){for(var o,a,c=t[0],s=t[1],u=t[2],d=0,l=[];d<c.length;d++)a=c[d],i[a]&&l.push(i[a][0]),i[a]=0;for(o in s)Object.prototype.hasOwnProperty.call(s,o)&&(e[o]=s[o]);f&&f(t);while(l.length)l.shift()();return r.push.apply(r,u||[]),n()}function n(){for(var e,t=0;t<r.length;t++){for(var n=r[t],o=!0,a=1;a<n.length;a++){var c=n[a];0!==i[c]&&(o=!1)}o&&(r.splice(t--,1),e=s(s.s=n[0]))}return e}var o={},a={app:0},i={app:0},r=[];function c(e){return s.p+"js/"+({}[e]||e)+"."+{"chunk-04134d31":"0fba9430","chunk-0cca1938":"86611c56","chunk-2036469e":"b73bf9b6","chunk-293563e8":"e2ff41f9","chunk-2d215fa4":"a39b53e0","chunk-2d22c8e2":"9317685f","chunk-2d22d746":"8d41a580","chunk-3ee0fd6c":"eaf5b728","chunk-4220c661":"09653f0e","chunk-4526be2c":"dbfa915b","chunk-475bb5d5":"3b8d5cdc","chunk-4c262e53":"9ce217a4","chunk-64037fe0":"ae842281","chunk-6a914aa7":"295d0fc4","chunk-6cdcac72":"c52ed74a","chunk-76e34681":"fb51772a","chunk-9cde6506":"a09c96f4","chunk-e99fbd7a":"a71e28b5","chunk-ef41a3f8":"0e1f376f"}[e]+".js"}function s(t){if(o[t])return o[t].exports;var n=o[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,s),n.l=!0,n.exports}s.e=function(e){var t=[],n={"chunk-04134d31":1,"chunk-0cca1938":1,"chunk-2036469e":1,"chunk-293563e8":1,"chunk-3ee0fd6c":1,"chunk-4220c661":1,"chunk-4526be2c":1,"chunk-475bb5d5":1,"chunk-4c262e53":1,"chunk-64037fe0":1,"chunk-6a914aa7":1,"chunk-6cdcac72":1,"chunk-76e34681":1,"chunk-9cde6506":1,"chunk-ef41a3f8":1};a[e]?t.push(a[e]):0!==a[e]&&n[e]&&t.push(a[e]=new Promise(function(t,n){for(var o="css/"+({}[e]||e)+"."+{"chunk-04134d31":"933eaf8d","chunk-0cca1938":"cb2d7ae6","chunk-2036469e":"cae9eb39","chunk-293563e8":"ef65ca66","chunk-2d215fa4":"31d6cfe0","chunk-2d22c8e2":"31d6cfe0","chunk-2d22d746":"31d6cfe0","chunk-3ee0fd6c":"0339a7fb","chunk-4220c661":"0339a7fb","chunk-4526be2c":"cd637992","chunk-475bb5d5":"cae9eb39","chunk-4c262e53":"ef65ca66","chunk-64037fe0":"24e98470","chunk-6a914aa7":"0339a7fb","chunk-6cdcac72":"1c9ba8ee","chunk-76e34681":"14fdcb52","chunk-9cde6506":"d58f75a5","chunk-e99fbd7a":"31d6cfe0","chunk-ef41a3f8":"0e69a79a"}[e]+".css",i=s.p+o,r=document.getElementsByTagName("link"),c=0;c<r.length;c++){var u=r[c],d=u.getAttribute("data-href")||u.getAttribute("href");if("stylesheet"===u.rel&&(d===o||d===i))return t()}var l=document.getElementsByTagName("style");for(c=0;c<l.length;c++){u=l[c],d=u.getAttribute("data-href");if(d===o||d===i)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var o=t&&t.target&&t.target.src||i,r=new Error("Loading CSS chunk "+e+" failed.\n("+o+")");r.code="CSS_CHUNK_LOAD_FAILED",r.request=o,delete a[e],f.parentNode.removeChild(f),n(r)},f.href=i;var h=document.getElementsByTagName("head")[0];h.appendChild(f)}).then(function(){a[e]=0}));var o=i[e];if(0!==o)if(o)t.push(o[2]);else{var r=new Promise(function(t,n){o=i[e]=[t,n]});t.push(o[2]=r);var u,d=document.createElement("script");d.charset="utf-8",d.timeout=120,s.nc&&d.setAttribute("nonce",s.nc),d.src=c(e),u=function(t){d.onerror=d.onload=null,clearTimeout(l);var n=i[e];if(0!==n){if(n){var o=t&&("load"===t.type?"missing":t.type),a=t&&t.target&&t.target.src,r=new Error("Loading chunk "+e+" failed.\n("+o+": "+a+")");r.type=o,r.request=a,n[1](r)}i[e]=void 0}};var l=setTimeout(function(){u({type:"timeout",target:d})},12e4);d.onerror=d.onload=u,document.head.appendChild(d)}return Promise.all(t)},s.m=e,s.c=o,s.d=function(e,t,n){s.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},s.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},s.t=function(e,t){if(1&t&&(e=s(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(s.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)s.d(n,o,function(t){return e[t]}.bind(null,o));return n},s.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return s.d(t,"a",t),t},s.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},s.p="/",s.oe=function(e){throw console.error(e),e};var u=window["webpackJsonp"]=window["webpackJsonp"]||[],d=u.push.bind(u);u.push=t,u=u.slice();for(var l=0;l<u.length;l++)t(u[l]);var f=d;r.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var o=n("64a9"),a=n.n(o);a.a},1:function(e,t){},1872:function(e,t,n){"use strict";var o=n("b63c"),a=n.n(o);a.a},"56d7":function(e,t,n){"use strict";n.r(t);n("cadf"),n("551c"),n("f751"),n("097d");var o=n("2b0e"),a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("div",{staticClass:"fluid-container"},[n("div",{staticClass:"row"},[n("div",{staticClass:"col-sm-2",staticStyle:{padding:"0px"}},[n("SideBar")],1),n("div",{staticClass:"col-sm-10",staticStyle:{padding:"0px"}},[n("router-view")],1)])])])},i=[],r=(n("8e6e"),n("ac6a"),n("456d"),n("bd86")),c=function(){var e=this,t=e.$createElement,n=e._self._c||t;return this.$session.has("userId")?n("ul",{staticClass:"main-container"},[1==this.$session.get("roleId")?n("div",{staticClass:"heading"},[e._v("ADMIN")]):n("div",{staticClass:"heading"},[e._v("USER")]),1==this.$session.get("roleId")?n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"/userList"}},[e._v("All Users")])],1):e._e(),1==this.$session.get("roleId")?n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"/audit"}},[e._v("Audit")])],1):e._e(),1==this.$session.get("roleId")?n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"/addUser"}},[e._v("Add User")])],1):e._e(),n("ul",{staticClass:"sub-heading"},[e._v("Video")]),n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"/singleVideo"}},[e._v("Single Video Program")])],1),n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"/multiVideo"}},[e._v("Multi Video Program")])],1),n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"/seasonalVideo"}},[e._v("Seasonal Program")])],1),1==this.$session.get("roleId")?n("ul",{staticClass:"sub-heading"},[e._v("Add Metadata")]):e._e(),1==this.$session.get("roleId")?n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"#"}},[e._v("Add Category")])],1):e._e(),1==this.$session.get("roleId")?n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"/addCrew"}},[e._v("Add Crew")])],1):e._e(),1==this.$session.get("roleId")?n("li",{staticClass:"navbar-li-contents"},[n("router-link",{staticClass:"navbar-contents",attrs:{to:"/addLanguage"}},[e._v("Add Language")])],1):e._e(),this.$session.has("userId")?n("div",{staticClass:"sub-heading"},[n("router-link",{staticStyle:{color:"white","margin-left":"20px"},attrs:{to:"/logout"}},[e._v("Logout")])],1):e._e()]):e._e()},s=[],u=n("2f62");function d(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter(function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable})),n.push.apply(n,o)}return n}function l(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?d(n,!0).forEach(function(t){Object(r["a"])(e,t,n[t])}):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):d(n).forEach(function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))})}return e}var f={name:"SideBar",computed:l({},Object(u["c"])(["userDetails","roleId"]))},h=f,p=(n("1872"),n("2877")),m=Object(p["a"])(h,c,s,!1,null,null,null),g=m.exports;function b(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter(function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable})),n.push.apply(n,o)}return n}function v(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?b(n,!0).forEach(function(t){Object(r["a"])(e,t,n[t])}):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):b(n).forEach(function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))})}return e}var y={name:"App",computed:v({},Object(u["c"])(["isLoggedIn"])),components:{SideBar:g}},k=y,S=(n("034f"),Object(p["a"])(k,a,i,!1,null,null,null)),O=S.exports,P=n("8c4f"),w=n("0628"),V=n.n(w),C=n("28dd");o["a"].use(u["a"]),o["a"].use(C["a"]);var j=new u["a"].Store({state:{allAudits:{},program:{},userDetails:{status:!1},season:{},episode:{},userList:{},singleVideos:{},multiVideos:{},seasonalVideos:{},seasons:{},episodes:{}},mutations:{setAllAudits:function(e,t){e.allAudits=t},setProgram:function(e,t){e.program=t},setSeason:function(e,t){e.season=t},setEpisode:function(e,t){e.episode=t},setUserList:function(e,t){e.userList=t},setSingleVideos:function(e,t){e.singleVideos=t},setMultiVideos:function(e,t){e.multiVideos=t},setSeasonalVideos:function(e,t){e.seasonalVideos=t},setSeasons:function(e,t){e.seasons=t},setEpisodes:function(e,t){e.episodes=t},setRoleId:function(e,t){e.roleId=t}},actions:{signIn:function(e,t){e.commit;return new Promise(function(e,n){var a={"Content-Type":"application/json",Accept:"application/json"};o["a"].http.post("http://172.16.20.78:8080/useradmin/authenticate",JSON.stringify(t),{headers:a}).then(function(t){console.log(t),e(t.body)}).catch(function(e){console.log(e),n(!1)})})},getUserList:function(e,t){var n=e.commit;o["a"].http.get("http://172.16.20.78:8080/useradmin/getAllUsers?pageNumber="+t.pageNumber+"&pageSize="+t.pageSize).then(function(e){n("setUserList",e.body)}).catch(function(e){console.log(e),reject(!1)})},addUser:function(e,t){e.commit;return new Promise(function(e,n){o["a"].http.post("http://172.16.20.78:8080/useradmin/addUserAdmin",JSON.stringify(t)).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(!1)})})},deleteUser:function(e,t){e.commit;return new Promise(function(e,n){o["a"].http.delete("http://172.16.20.78:8080/useradmin/userDeleteByDetails?idDelete="+t.idDelete+"&id="+t.id).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(!1)})})},logout:function(e,t){e.commit;return new Promise(function(e,n){o["a"].http.post("http://172.16.20.78:8080/useradmin/logout",JSON.stringify(t)).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(!1)})})},addProgram:function(e,t){var n=e.commit;return new Promise(function(e,a){o["a"].http.put("http://172.16.20.95:8081/metadata/addProgram",JSON.stringify(t)).then(function(t){n("setProgram",t.body),e(t.body)}).catch(function(e){console.log(e),a(!1)})})},addSingleVideo:function(e,t){e.commit;return new Promise(function(e,n){o["a"].http.put("http://172.16.20.95:8081/metadata/addSingleVideo",JSON.stringify(t)).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(!1)})})},addSeason:function(e,t){var n=e.commit;return new Promise(function(e,a){o["a"].http.put("http://172.16.20.95:8081/metadata/addSeason",JSON.stringify(t)).then(function(t){n("setSeason",t.body),e(t.body)}).catch(function(e){console.log(e),a(!1)})})},addEpisodes:function(e,t){var n=e.commit;return new Promise(function(e,a){o["a"].http.put("http://172.16.20.95:8081/metadata/addEpisodes",JSON.stringify(t)).then(function(t){n("setEpisode",t.body),e(t.body)}).catch(function(e){console.log(e),a(!1)})})},getSingleVideoPrograms:function(e,t){var n=e.commit;o["a"].http.get("http://172.16.20.95:8081/metadata/getAllSingleVideoProgram?pageSize="+t.pageSize+"&pageNumber="+t.pageNumber).then(function(e){console.log(e),n("setSingleVideos",e.body)}).catch(function(e){console.log(e)})},getMultiVideoPrograms:function(e,t){var n=e.commit;o["a"].http.get("http://172.16.20.95:8081/metadata/getAllMultiVideoProgram?pageSize="+t.pageSize+"&pageNumber="+t.pageNumber).then(function(e){n("setMultiVideos",e.body)}).catch(function(e){console.log(e)})},getSeasonalVideoPrograms:function(e,t){var n=e.commit;o["a"].http.get("http://172.16.20.95:8081/metadata/getAllSeasonalVideoProgram?pageSize="+t.pageSize+"&pageNumber="+t.pageNumber).then(function(e){n("setSeasonalVideos",e.body)}).catch(function(e){console.log(e)})},getSeasonByProgram:function(e,t){var n=e.commit;o["a"].http.get("http://172.16.20.95:8081/metadata/getSeasonsByProgramId?programId="+t.programId+"&pageNumber="+t.pageNumber+"&pageSize="+t.pageSize).then(function(e){console.log(e.body),n("setSeasons",e.body)}).catch(function(e){console.log(e)})},getEpisodesBySeason:function(e,t){var n=e.commit;o["a"].http.get("http://172.16.20.95:8081/metadata/getEpisodesBySeasonId?seasonId="+t.seasonId+"&pageNumber="+t.pageNumber+"&pageSize="+t.pageSize).then(function(e){console.log(e),n("setEpisodes",e.body)}).catch(function(e){console.log(e)})},deleteProgramById:function(e,t){e.commit;return new Promise(function(e,n){o["a"].http.post("http://172.16.20.95:8081/metadata/deleteProgramById",JSON.stringify(t)).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(!1)})})},deleteEpisodeById:function(e,t){e.commit;return new Promise(function(e,n){console.log(t),o["a"].http.post("http://172.16.20.95:8081/metadata/deleteEpisodeById",JSON.stringify(t)).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(!1)})})},deleteSeasonById:function(e,t){e.commit;return new Promise(function(e,n){console.log(t),o["a"].http.post("http://172.16.20.95:8081/metadata/deleteSeasonById",JSON.stringify(t)).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(!1)})})},addLanguage:function(e,t){e.commit;return new Promise(function(e,n){console.log(t),o["a"].http.put("http://172.16.20.95:8081/admin/addLanguage",JSON.stringify(t)).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(!1)})})},getAllAudits:function(e,t){var n=e.commit,a={"Content-Type":"application/json",Accept:"application/json"};o["a"].http.post("http://172.16.20.83:8082/audit/getAudits",JSON.stringify(t),{headers:a}).then(function(e){n("setAllAudits",e.body)}).catch(function(e){console.log(e)})},imageUpload:function(e,t){e.commit;return new Promise(function(e,n){o["a"].http.post("http://172.16.20.83:8082/image/uploadImage",t).then(function(t){e(t.body)}).catch(function(e){console.log(e),n(e)})})},searchInVideo:function(e,t){e.commit;o["a"].http.get("http://172.16.20.101:8080/solrSearch/search?searchTerm=ipl&pageNumber=0&pageSize=5").then(function(e){console.log(e.body)}).catch(function(e){console.log(e)})}},getters:{allAudits:function(e){return e.allAudits},episode:function(e){return e.episode},isLoggedIn:function(e){return e.userDetails.status},program:function(e){return e.program},season:function(e){return e.season},singleVideos:function(e){return e.singleVideos},userList:function(e){return e.userList},multiVideos:function(e){return e.multiVideos},seasonalVideos:function(e){return e.seasonalVideos},seasons:function(e){return e.seasons},episodes:function(e){return e.episodes}}});o["a"].use(P["a"]),o["a"].use(V.a);var _=new P["a"]({mode:"history",base:"/",routes:[{path:"/",name:"home",component:function(){return n.e("chunk-64037fe0").then(n.bind(null,"bb51"))},meta:{authentication:"required"}},{path:"/about",name:"about",component:function(){return n.e("chunk-2d22d746").then(n.bind(null,"f820"))}},{path:"/login",name:"login",component:function(){return n.e("chunk-76e34681").then(n.bind(null,"578a"))}},{path:"/audit",name:"audit",component:function(){return n.e("chunk-6cdcac72").then(n.bind(null,"8e70"))},meta:{authentication:"required"}},{path:"/singleVideo",name:"singleVideo",component:function(){return n.e("chunk-9cde6506").then(n.bind(null,"82e2"))},meta:{authentication:"required"}},{path:"/seasonalVideo",name:"seasonalVideo",component:function(){return n.e("chunk-3ee0fd6c").then(n.bind(null,"f087"))},meta:{authentication:"required"}},{path:"/multiVideo",name:"multiVideo",component:function(){return n.e("chunk-04134d31").then(n.bind(null,"4707"))},meta:{authentication:"required"}},{path:"/logout",name:"logout",component:function(){return n.e("chunk-2d215fa4").then(n.bind(null,"c100"))},meta:{authentication:"required"}},{path:"/program/:name",name:"program",component:function(){return n.e("chunk-475bb5d5").then(n.bind(null,"6ec4"))},meta:{authentication:"required"}},{path:"/singleVideoForm",name:"singleVideoForm",component:function(){return n.e("chunk-2036469e").then(n.bind(null,"d397"))},meta:{authentication:"required"}},{path:"/multiVideoForm",name:"multiVideoForm",component:function(){return n.e("chunk-293563e8").then(n.bind(null,"a76e"))},meta:{authentication:"required"}},{path:"/seasonalVideoForm",name:"seasonalVideoForm",component:function(){return n.e("chunk-4c262e53").then(n.bind(null,"8f98"))},meta:{authentication:"required"}},{path:"/episodeForm",name:"episodeForm",component:function(){return n.e("chunk-ef41a3f8").then(n.bind(null,"6b19"))},meta:{authentication:"required"}},{path:"/addUser",name:"addUser",component:function(){return n.e("chunk-4526be2c").then(n.bind(null,"1fa0"))},meta:{authentication:"required"}},{path:"/userList",name:"userList",component:function(){return n.e("chunk-0cca1938").then(n.bind(null,"ab3a"))},meta:{authentication:"required"}},{path:"/seasons/:id",name:"seasons",component:function(){return n.e("chunk-4220c661").then(n.bind(null,"a931"))},meta:{authentication:"required"}},{path:"/episodes/:id",name:"episodes",component:function(){return n.e("chunk-6a914aa7").then(n.bind(null,"2c97"))},meta:{authentication:"required"}},{path:"/addLanguage",name:"addLanguage",component:function(){return n.e("chunk-2d22c8e2").then(n.bind(null,"f475"))},meta:{authentication:"required"}},{path:"/addCrew",name:"addCrew",component:function(){return n.e("chunk-e99fbd7a").then(n.bind(null,"26c6"))},meta:{authentication:"required"}}]});function I(e){var t=!1;return t="required"===e.meta.authentication,t}_.beforeEach(function(e,t,n){var o=JSON.parse(localStorage.getItem("isLoggedIn"));I(e)&&o?n():I(e)&&!o?n({name:"login"}):n()});var A=_,N=n("14ba"),E=n.n(N);o["a"].use(V.a),o["a"].use(E.a),o["a"].config.productionTip=!1,new o["a"]({router:A,store:j,render:function(e){return e(O)}}).$mount("#app")},"64a9":function(e,t,n){},b63c:function(e,t,n){}});
//# sourceMappingURL=app.9ce54245.js.map