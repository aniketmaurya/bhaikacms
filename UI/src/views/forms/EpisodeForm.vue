<template>
<div class="box">
    <h2 class="form-heading">EPISODE FORM</h2>
        <button class="btn btn-success" @click="addNewEmployeeForm">New Episode</button>
        <div class="card mb-3" v-for="(episode,index) in Episodes" :key="episode.name">
        <div class="card-body" >
            <span  class="float-right" style="cursor:pointer" @click="deleteEmployeeForm(index)">X</span>
            <h4 class="ard-title">Episode </h4>
            <div class="employee-form">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <!-- <label>Program type</label> -->
                        <input v-model="season.seasonName" type="text" class="form-control" required readOnly>
                    </div>
                    <div class="form-group col-md-6">
                        <!-- <label for="inputPassword4">Season name</label> -->
                        <input v-model="episode.episodeTitle"  type="text" class="form-control" placeholder="Episode name" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <!-- <label>Program type</label> -->
                        <input v-model="episode.episodeDescription" type="text" class="form-control" placeholder="Description"  required>
                    </div>
                    <div class="form-group col-md-6">
                        <div class="col-md-6">
                            <input @change="processFile($event,'Thumbnail',index)" type="file" class="form-control" required>
                            <img height="50" width="50" v-if="episode.episodeImgUrls.thumbnail" :src="episode.episodeImgUrls.thumbnail" alt="">
                        </div>
                        <div class="col-md-6">
                            <input @change="processFile($event,'Avatar',index)" type="file" class="form-control" required>
                            <img height="50" width="50" v-if="episode.episodeImgUrls.avatar" :src="episode.episodeImgUrls.avatar" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>  
    <button @click="handleSubmit()" type="submit" class="btn btn-primary btn-submit">Save episodes</button>
</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
    name:'EpisodeForm',
    data() {
        return {
            // getSeason: {},
            Episodes:[
                {
                    season: {},
                    episodeNumber:"",
                    episodeTitle:"",
                    episodeDescription:"",
                    episodeVideoUrl:"",
                    episodeImgUrls:{
                        thumbnail:"",
                        avatar:""
                    },
                    crewList:null,
                    userEmail:"",
                } 
            ]
        }
    },
    computed: {
        ...mapGetters([
            'season',
            'episodes'
        ])
    },
    methods:{
        ...mapActions([
            'addEpisodes',
            'imageUpload'
        ]),
        addNewEmployeeForm(){
            this.Episodes.push({
                season: {},
                    episodeNumber:"",
                    episodeTitle:"",
                    episodeDescription:"",
                    episodeVideoUrl:"",
                    episodeImgUrls:{
                        thumbnail:"",
                        avatar:""
                    },
                    crewList:null,
            })
        },
        deleteEmployeeForm(index) {
            this.episodes.splice(index, 1)
        },
        handleSubmit() {
            let counter = 1
            if(this.episodes.totalElements>0) 
                counter = this.episodes.totalElements
    
            debugger
            for(let i in this.episodes) {
                this.Episodes[i].season = this.season
                this.Episodes[i].episodeNumber = counter++
                this.Episodes[i].userEmail = this.$session.get('email')
            }
            this.addEpisodes(this.Episodes).then( (resp) => {
                if(resp) { 
                    this.$swal('','Successfully added','success')
                    this.$router.push('/seasonalVideo')
                } else {
                    console.log("Something went wrong")
                }
            }).catch( (err) => {
                console.log(err)
            })
        },
        processFile(event,type,index) {
            if (type=='Thumbnail') {
                let formData = new FormData()
                formData.append('file', event.target.files[0])
                formData.append('filetype','image')
                formData.append('type','Thumbnail')
                this.imageUpload(formData).then( (resp) => {
                    if(resp.uploadLink) {
                        this.Episodes[index].episodeImgUrls.thumbnail = resp.uploadLink
                    } else {
                        this.$swal(resp.message)
                    }
                }).catch( (err) => {
                    console.log(err)
                })
            } else {
                let formData = new FormData()
                formData.append('file', event.target.files[0])
                formData.append('filetype','image')
                formData.append('type','Avatar')
                this.imageUpload(formData).then( (resp) => {
                    if(resp.uploadLink) {
                        this.Episodes[index].episodeImgUrls.avatar = resp.uploadLink
                    } else {
                        this.$swal(resp.message)
                    }
                }).catch( (err) => {
                    console.log(err)
                })
            }  
        },
        init() {
            console.log()
        }
    },
    mounted() {
        this.init()
    }
}
</script>

<style scoped>
.box {
    padding:10px;
    height:100vh;
    border-radius: 10px;
}
.form-heading {
    text-align:center;
    color: #2980B9;
}
.float-right {
    float:right;
}
.card {
    height:165px;
    padding:10px;
    margin-top:10px;
    margin-bottom:10px;
    border-radius: 10px;
    /* border: 0.5px solid #333; */
	box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
}
.btn-submit {
    margin:0 auto;
    display: block;
}
</style>
