<template>    
    <ul v-if="this.$session.has('userId')" class="main-container">
        <div v-if="this.$session.get('roleId')==1" class="heading">ADMIN</div>
        <div v-else class="heading">USER</div>
        <li v-if="this.$session.get('roleId')==1"   class="navbar-li-contents"><router-link class="navbar-contents" :to="'/userList'">All Users</router-link></li>
        <li v-if="this.$session.get('roleId')==1" class="navbar-li-contents"><router-link class="navbar-contents" :to="'/audit'">Audit</router-link></li>
        <li v-if="this.$session.get('roleId')==1" class="navbar-li-contents"><router-link class="navbar-contents" :to="'/addUser'">Add User</router-link></li>
        <ul class="sub-heading">Video</ul>
            <li class="navbar-li-contents"><router-link class="navbar-contents" :to="'/singleVideo'">Single Video Program</router-link></li>
            <li class="navbar-li-contents"><router-link class="navbar-contents" :to="'/multiVideo'">Multi Video Program</router-link></li>
            <li class="navbar-li-contents"><router-link class="navbar-contents" :to="'/seasonalVideo'">Seasonal Program</router-link></li>
        <ul v-if="this.$session.get('roleId')==1" class="sub-heading">Add Metadata</ul>
            <li v-if="this.$session.get('roleId')==1" class="navbar-li-contents"><router-link class="navbar-contents" to="#">Add Category</router-link></li>
            <li v-if="this.$session.get('roleId')==1" class="navbar-li-contents"><router-link class="navbar-contents" :to="'/addCrew'">Add Crew</router-link></li>
            <li v-if="this.$session.get('roleId')==1" class="navbar-li-contents"><router-link class="navbar-contents" :to="'/addLanguage'">Add Language</router-link></li>
        <div v-if="this.$session.has('userId')"  class="sub-heading"><router-link :to="'/logout'" style="color:white;margin-left:20px;">Logout</router-link></div>
    </ul>
</template>

<script src="./js/side-bar.js"></script>

<style>
.main-container {
 min-height:100vh;
 background: #232526;
 background: -webkit-linear-gradient(to right, #414345, #232526);
 background: linear-gradient(to right, #414345, #232526);
 padding:10px;
}
.heading {
    color:white;
    text-align:center;
    padding:10px;
    font-size:30px;
    margin-left:-40px;
}
.sub-heading{
    padding:15px;
    color:White;
    font-size:20px;
    font-weight: bold;
}
.navbar-contents{
    padding:13px;
    font-size:15px;
}
.navbar-contents:hover{
    text-decoration: none;
}
.navbar-li-contents {
    padding-bottom:15px;
    list-style:none;
}
</style>
