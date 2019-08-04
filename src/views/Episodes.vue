<template>
<div class="card-wrapper">
<div v-for="episode in episodes.content" :key="episode.id"  class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  :src="episode.episodeImgUrls.thumbnail" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            <div class="card-block col-md-6 px-3">
                <router-link :to="'#'"><h3 class="card-title">{{ episode.episodeTitle }}</h3></router-link>
                <p class="card-text"><b>Episode Number : </b>{{ episode.episodeNumber }}</p>
                <p class="card-text"><b>Episode Description : </b>{{ episode.episodeDescription }}</p>
                <p class="card-text"><b>Uploaded on : </b>{{ episode.creationDate }}</p>
            </div>
            <div class="card-block col-md-3 px-3">
                <div class="help1"> </div>
                <p><button @click="deleteEpisode(episode.id)"  class="btn btn-danger">Remove</button></p>
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
    name:'Episodes',
    computed: {
        ...mapGetters([
            'episodes'
        ])
    },
    data() {
        return {
            page: {
                seasonId:"",
                pageNumber:0,
                pageSize:5
            },
            episodeData: {
                id:"",
                // userId:""
            }
        }
    },
    methods: {
        ...mapActions([
            'getEpisodesBySeason',
            'deleteEpisodeById'
        ]),
        init() {
            this.page.seasonId = this.$route.params.id
            this.getEpisodesBySeason(this.page)
        },
        deleteEpisode(id) {
            this.episodeData.id = id
            //this.episodeData.userId = this.$session.get('userId')
            this.deleteEpisodeById(this.episodeData).then( (resp)=> {
                if (resp) {
                    this.$swal("Deleted")
                    this.getEpisodesBySeason(this.page)
                }
            }).catch( (err) => {
                console.log(err)
            })
        }
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
