<template>
<div class="content-area">
    <div class="count-users">TOTAL USERS<div id="user-nos">{{ this.userCount }}</div></div>
    <div class="count-videos">TOTAL VIDEOS<div id="video-nos">50</div></div>
    <div class="count-admin">TOTAL ADMINS<div id="admin-nos">{{ this.adminCount }}</div></div>
    <div class="count-activities">RECENT ACTIVITIES<span id="activities-nos"></span></div>
</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
    name:'Widgets',
    computed: {
        ...mapGetters([
            'userCount',
            'adminCount'
        ])
    },
    methods: {
        ...mapActions([
            'getCount'
        ]),
        init() {
            this.getCount()
        } 
    },
    mounted() {
        this.init()
    }
}
</script>



<style>
.content-area{
    float: left;
    width:100vw;
    padding: 10px;
    background: rgba(229, 231, 231, 0.767);
    height: 100vh;

}
.admin-text{
    font-size: 1.8rem;
    margin-top: 34px;
    text-align: center;

}
.logout-link{
    position: absolute;
    bottom:50px;
    margin-left: 10px;

}
.count-users {
    float: left;
    width:26%;
    padding: 10px;
    height: 100px; 
    border-right:1px solid lightgray;
    background: rgba(75, 77, 82);
    color: #fff;
    margin-left:4%;
    margin-top:40px;
}
.count-videos {
    float: left;
    width:26%;
    padding: 10px;
    height: 100px; 
    border-right:1px solid lightgray;
    background: rgba(75, 77, 82);
    color: #fff;
    margin-left:3%;
    margin-top:40px;

}
.count-admin {
    float: left;
    width:26%;
    padding: 10px;
    height: 100px; 
    border-right:1px solid lightgray;
    background: rgba(75, 77, 82);
    color: #fff;
    margin-left:3%;
    margin-top:40px;
}

#user-nos{
    text-align: center;
    margin-top: 20px;
    font-size: 35px;
}
#video-nos{
    text-align: center;
    margin-top: 20px;
    font-size: 35px;
}
#admin-nos{
    text-align: center;
    margin-top: 20px;
    font-size: 35px;
}
.count-activities{
    position: fixed;
    text-align: justify;
    margin-top: 200px;
    font-size: 30px;
}
</style>
