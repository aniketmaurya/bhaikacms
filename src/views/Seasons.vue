<template>
<div class="card-wrapper">
<div v-for="season in seasons.content" :key="season.id"  class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  :src="season.seasonImgUrls.thumbnail" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            <div class="card-block col-md-6 px-3">
                <router-link :to="'/episodes/'+season.id"><h3 class="card-title">{{ season.seasonName }}</h3></router-link>
                <p class="card-text"><b>Season Number : </b>{{ season.seasonNumber }}</p>
                <p class="card-text"><b>Season Description : </b>{{ season.seasonDescription }}</p>
                
            </div>
            <div class="card-block col-md-3 px-3">
                <div class="help1"> </div>
                <p><button @click="deleteProgram(season.id)"  class="btn btn-danger">Remove</button></p>
                <router-link :to="'/seasonVideoForm'"><button class="btn btn-primary">Edit</button></router-link>
            </div>
        </div>
    </div>
</div>
</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
    name:'Seasons',
    computed: {
        ...mapGetters([
            'seasons',
        ])
    },
    data() {
        return {
            page: {
                programId:"",
                pageNumber:0,
                pageSize:5
            }
        }
    },
    methods: {
        ...mapActions([
            'getSeasonByProgram'
        ]),
        init() {
            this.page.programId = this.$route.params.id
            this.getSeasonByProgram(this.page)

        },
    },
    mounted() {
        this.init()
    }
    
}
</script>

<style>
.card-wrapper {
    padding:10px;
}
.card {
    border:1px solid black;
    margin:10px;
    padding:10px;
}
.help {
    margin-bottom:28%;
}
.help1 {
    margin-bottom:38%;
}
</style>
