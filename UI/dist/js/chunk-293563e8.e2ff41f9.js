(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-293563e8"],{"42f0":function(t,e,o){"use strict";var a=o("586c"),r=o.n(a);r.a},"586c":function(t,e,o){},a76e:function(t,e,o){"use strict";o.r(e);var a=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"box"},[o("form",{staticClass:"program-form",staticStyle:{padding:"15px"},on:{submit:function(e){return e.preventDefault(),t.handleSubmit()}}},[o("h2",{staticClass:"form-heading"},[t._v("MULTI VIDEO FORM")]),o("div",{staticClass:"form-row"},[o("div",{staticClass:"form-group col-md-6"},[o("label",[t._v("Program type")]),o("input",{directives:[{name:"model",rawName:"v-model",value:t.program.type,expression:"program.type"}],staticClass:"form-control ",attrs:{type:"text",required:"",readOnly:""},domProps:{value:t.program.type},on:{input:function(e){e.target.composing||t.$set(t.program,"type",e.target.value)}}})]),o("div",{staticClass:"form-group col-md-6"},[o("label",{attrs:{for:"inputPassword4"}},[t._v("Title")]),o("input",{directives:[{name:"model",rawName:"v-model",value:t.multiVideo.seasonName,expression:"multiVideo.seasonName"}],staticClass:"form-control",attrs:{type:"text",required:""},domProps:{value:t.multiVideo.seasonName},on:{input:function(e){e.target.composing||t.$set(t.multiVideo,"seasonName",e.target.value)}}})])]),o("div",{staticClass:"form-group col-md-12"},[o("label",{attrs:{for:"inputAddress"}},[t._v("Description")]),o("input",{directives:[{name:"model",rawName:"v-model",value:t.multiVideo.seasonDescription,expression:"multiVideo.seasonDescription"}],staticClass:"form-control",attrs:{type:"text",required:""},domProps:{value:t.multiVideo.seasonDescription},on:{input:function(e){e.target.composing||t.$set(t.multiVideo,"seasonDescription",e.target.value)}}})]),o("div",{staticClass:"form-row"},[o("div",{staticClass:"form-group col-md-4"},[o("label",[t._v("Thumbnail")]),o("input",{staticClass:"form-control",attrs:{type:"file",required:""},on:{change:function(e){return t.processFile(e,"Thumbnail")}}})]),o("div",{staticClass:"form-group col-md-4"},[o("label",[t._v("Avatar")]),o("input",{staticClass:"form-control",attrs:{type:"file",required:""},on:{change:function(e){return t.processFile(e,"Avatar")}}})])]),o("button",{staticClass:"btn btn-primary",attrs:{type:"submit"}},[t._v("Save video")])])])},r=[],i=(o("8e6e"),o("ac6a"),o("456d"),o("bd86")),s=o("2f62");function n(t,e){var o=Object.keys(t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(t);e&&(a=a.filter(function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable})),o.push.apply(o,a)}return o}function l(t){for(var e=1;e<arguments.length;e++){var o=null!=arguments[e]?arguments[e]:{};e%2?n(o,!0).forEach(function(e){Object(i["a"])(t,e,o[e])}):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(o)):n(o).forEach(function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(o,e))})}return t}var c={name:"MultiVideoForm",data:function(){return{multiVideo:{program:{id:""},seasonName:"",seasonNumber:0,seasonDescription:"",seasonImgUrls:{thumbnail:"",avatar:""}}}},methods:l({},Object(s["b"])(["addSeason","imageUpload"]),{handleSubmit:function(){var t=this;this.multiVideo.program.id=this.program.id,this.addSeason(this.multiVideo).then(function(e){e?(t.$swal("","Successfully added","success"),t.$router.push("/episodeForm")):console.log("Something went wrong")}).catch(function(t){console.log(t)})},processFile:function(t,e){var o=this;if("Thumbnail"==e){var a=new FormData;a.append("image",t.target.files[0]),a.append("type","Thumbnail"),this.imageUpload(a).then(function(t){t.uploadLink?o.multiVideo.seasonImgUrls.thumbnail=t.uploadLink:o.$swal("File type not supported")}).catch(function(t){console.log(t)})}else{var r=new FormData;r.append("image",t.target.files[0]),r.append("type","avatar"),this.imageUpload(r).then(function(t){t.uploadLink?o.multiVideo.seasonImgUrls.avatar=t.uploadLink:o.$swal("File type not supported")}).catch(function(t){console.log(t)})}}}),computed:l({},Object(s["c"])(["program"]))},u=c,p=(o("42f0"),o("2877")),m=Object(p["a"])(u,a,r,!1,null,null,null);e["default"]=m.exports}}]);
//# sourceMappingURL=chunk-293563e8.e2ff41f9.js.map