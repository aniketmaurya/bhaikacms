import { mapActions } from 'vuex';
export default {
    name:'Program',
    data() {
        return {
            program: {
                type:"",
                name:"",
                description:"",
                keywords:"",
                parentalRating:"",
                languages:"",
                startDate:"",
                expiryDate:"",
                category: {
                  id: "1",
                },
                imgUrls: {
                    thumbnail: "",
                    avatar: "",
                },
                userId:null
            }
        }
    },
    methods: {
        ...mapActions([
            'addProgram',
            'imageUpload'
        ]),
        init() {
            if (this.$route.params.name == "singleVideo") {
                this.program.type = "Single video program"
            }
            else if (this.$route.params.name == "multiVideo") {
                this.program.type = "Multi video program"
            }
            else {
                this.program.type = "Seasonal video program"
            }
        },
        handleSubmit() {
            //change date to long
            this.program.startDate = this.convertDateToLong(new Date(this.program.startDate))
            this.program.expiryDate = this.convertDateToLong(new Date(this.program.expiryDate))
            this.program.userId = this.$session.get('userId')
             
            //adding program to database 
            this.addProgram(this.program).then( (resp) => {
                this.$swal('','Successfully added','success')
                if (this.$route.params.name == "singleVideo") {
                    this.$router.push('/singleVideoForm')
                }
                if (this.$route.params.name == "multiVideo") {
                    this.$router.push('/multiVideoForm')
                }
                if (this.$route.params.name == "seasonalVideo") {
                    this.$router.push('/seasonalVideoForm')
                }
            }).catch( (err) => {
                console.log(err)
            })

        },
        convertDateToLong(date) {
            return date.getTime()
        },
        processFile(event,type) {
            if (type=='Thumbnail') {
                let formData = new FormData()
                formData.append('image', event.target.files[0])
                formData.append('type','Thumbnail')
                this.imageUpload(formData).then( (resp) => {
                    if(resp.uploadLink=="") {
                        this.program.imgUrls.thumbnail = resp.uploadLink
                    } else {
                        this.$swal('File type not supported')
                    }
                }).catch( (err) => {
                    console.log(err)
                })
            } else {
                let formData = new FormData()
                formData.append('image', event.target.files[0])
                formData.append('type','avatar')
                this.imageUpload(formData).then( (resp) => {
                    if(resp.uploadLink) {
                        this.program.imgUrls.avatar = resp.uploadLink
                    } else {
                        this.$swal('File type not supported')
                    }
                }).catch( (err) => {
                    console.log(err)
                })
            }
             
        }

    },
    mounted() {
        this.init()
    }
    
}