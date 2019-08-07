<template>
<div class="box">
    <form class="program-form" style="padding:15px;" @submit.prevent="handleSubmit()">
        <h2 class="form-heading">MULTI VIDEO FORM</h2>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Program type</label>
                <input v-model="program.type" type="text" class="form-control " required readOnly>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Title</label>
                <input v-model="multiVideo.seasonName"  type="text" class="form-control" required>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label for="inputAddress">Description</label>
            <input v-model="multiVideo.seasonDescription" type="text" class="form-control" required>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label>Thumbnail</label>
                <input @change="processFile($event,'Thumbnail')" type="file" class="form-control" required>
            </div>
            <div class="form-group col-md-4">
                <label>Avatar</label>
                <input @change="processFile($event,'Avatar')" type="file" class="form-control" required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save video</button>
    </form>
</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
    name:'MultiVideoForm',
    data() {
       return {
           multiVideo: {
               program: {
                   id: ""
               },
               seasonName:"",
               seasonNumber:0,
               seasonDescription:"",
               seasonImgUrls:{
                   thumbnail: "",
                    avatar: "",
               }
           }
       }
    },
    methods: {
        ...mapActions([
            'addSeason',
            'imageUpload'
        ]),
        handleSubmit() {
            this.multiVideo.program.id = this.program.id

            this.addSeason(this.multiVideo).then( (resp) => {
                if(resp) {
                    this.$swal('','Successfully added','success')
                    this.$router.push('/episodeForm')
                } else {
                    console.log("Something went wrong")
                }
            }).catch((err) => {
                console.log(err)
            })
        },
         processFile(event,type) {
            if (type=='Thumbnail') {
                let formData = new FormData()
                formData.append('file', event.target.files[0])
                formData.append('filetype','image')
                formData.append('type','Thumbnail')
                this.imageUpload(formData).then( (resp) => {
                    if(resp.uploadLink) {
                        this.multiVideo.seasonImgUrls.thumbnail = resp.uploadLink
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
                        this.multiVideo.seasonImgUrls.avatar = resp.uploadLink
                    } else {
                        this.$swal(resp.message)
                    }
                }).catch( (err) => {
                    console.log(err)
                })
            }
        }

    },
    computed: {
        ...mapGetters([
            'program'
        ])
    }
    
}
</script>

<style>
.box {
    padding:20px;
    margin:20px;
    border-radius: 10px;
	box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
}
.form-heading {
    text-align:center;
    color: #2980B9;
}
</style>
