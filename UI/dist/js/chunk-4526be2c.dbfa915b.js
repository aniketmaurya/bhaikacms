(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4526be2c"],{"1fa0":function(e,t,r){"use strict";r.r(t);var s=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"box"},[r("form",{staticClass:"program-form",staticStyle:{padding:"15px"},on:{submit:function(t){return t.preventDefault(),e.handleSubmit()}}},[r("h2",{staticClass:"form-heading"},[e._v("REGISTER NEW USER")]),r("div",{staticClass:"form-row"},[r("div",{staticClass:"form-group col-md-6"},[r("label",[e._v("Name")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.user.name,expression:"user.name"}],staticClass:"form-control ",attrs:{type:"text",required:""},domProps:{value:e.user.name},on:{input:function(t){t.target.composing||e.$set(e.user,"name",t.target.value)}}})]),r("div",{staticClass:"form-group col-md-6"},[r("label",{attrs:{for:"inputPassword4"}},[e._v("Email")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.user.email,expression:"user.email"}],staticClass:"form-control",attrs:{type:"text",required:""},domProps:{value:e.user.email},on:{input:function(t){t.target.composing||e.$set(e.user,"email",t.target.value)}}})])]),r("div",{staticClass:"form-row"},[r("div",{staticClass:"form-group col-md-6"},[r("label",[e._v("Password")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.user.password,expression:"user.password"}],staticClass:"form-control ",attrs:{type:"password",required:""},domProps:{value:e.user.password},on:{input:function(t){t.target.composing||e.$set(e.user,"password",t.target.value)}}})]),r("div",{staticClass:"form-group col-md-6"},[r("label",[e._v("Role Id")]),r("input",{directives:[{name:"model",rawName:"v-model",value:e.user.roleId,expression:"user.roleId"}],staticClass:"form-control",attrs:{type:"text",required:""},domProps:{value:e.user.roleId},on:{input:function(t){t.target.composing||e.$set(e.user,"roleId",t.target.value)}}})])]),r("button",{staticClass:"btn btn-primary",attrs:{type:"submit"}},[e._v("Save")])])])},a=[],o=(r("8e6e"),r("ac6a"),r("456d"),r("bd86")),n=r("2f62");function i(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var s=Object.getOwnPropertySymbols(e);t&&(s=s.filter(function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable})),r.push.apply(r,s)}return r}function u(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?i(r,!0).forEach(function(t){Object(o["a"])(e,t,r[t])}):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):i(r).forEach(function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))})}return e}var l={name:"AddUser",data:function(){return{user:{name:"",password:"",email:"",roleId:""}}},methods:u({},Object(n["b"])(["addUser"]),{handleSubmit:function(){var e=this;this.addUser(this.user).then(function(t){t.added?e.$swal("",t.message,"success"):e.$swal(t.message)}).catch(function(e){console.log(e)})},init:function(){0===this.$session.get("roleId")&&this.$router.push("/")}}),mounted:function(){this.init()}},c=l,d=(r("96a3"),r("2877")),m=Object(d["a"])(c,s,a,!1,null,"442c8b39",null);t["default"]=m.exports},"96a3":function(e,t,r){"use strict";var s=r("ad2e"),a=r.n(s);a.a},ad2e:function(e,t,r){}}]);
//# sourceMappingURL=chunk-4526be2c.dbfa915b.js.map