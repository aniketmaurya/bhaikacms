<template>
<div class="box">
    <form class="program-form" style="padding:15px;" @submit.prevent="handleSubmit()">
        <h2 class="form-heading">SINGLE VIDEO FORM</h2>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Program type</label>
                <input v-model="program.type" type="text" class="form-control " required readOnly>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Video name</label>
                <input v-model="singleVideo.videoTitle"  type="text" class="form-control" required>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label for="inputAddress">Description</label>
            <input v-model="singleVideo.description"  type="text" class="form-control" required>
        </div>
        <div class="form-group col-md-12">
            <label for="inputAddress">Video Url</label>
            <input v-model="singleVideo.videoUrl"  type="text" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Save video</button>
    </form>
</div>   
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
    name:'SingleVideoForm',
    data() {
        return {
            singleVideo: {
                program: {
                    id: ""
                },
                videoTitle:"",
                videoUrl: null,
                description: "",
                videoImgUrls: null,
                crewList: null
            }
        }
    },
    computed: {
        ...mapGetters([
            'program'
        ])
    },
    methods: {
        ...mapActions([
            'addSingleVideo'
        ]),
        handleSubmit() {
            //setting programId here
            this.singleVideo.program.id = this.program.id

            this.addSingleVideo(this.singleVideo).then ( (resp) => {
                if (resp) {
                    this.$swal('','Successfully added','success')
                    this.$router.push('/singleVideo')
                } else {
                    console.log("Something wrong")
                } 
            }).catch ( (err) => {
                console.log(err)
            })

        }

    },
    
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
