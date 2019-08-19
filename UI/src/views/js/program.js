
import VueTagsInput from '@johmun/vue-tags-input';

import { mapActions, mapGetters } from 'vuex';
export default {
    name:'Program',
    computed:{
        ...mapGetters([
            'languages',
            'getStackOfCategories',
            'crewRoles'
        ])
    },
    components:{
        VueTagsInput
    },
    data() {
        return {
            inputArray: [],
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
                  id:"",
                },
                imgUrls: {
                    thumbnail: "",
                    avatar: "",
                },
                userId:null,
                userEmail:"",
                crewList: {
        
                }
            },
            tag:'',
            tags: [],
        }
    },
    methods: {
        ...mapActions([
            'addProgram',
            'imageUpload',
            'getLanguages',
            'getCrewRoles'
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
            this.getLanguages()
            this.getCrewRoles()
        },
        handleSubmit() {
            //change date to long
            this.program.startDate = this.convertDateToLong(new Date(this.program.startDate))
            this.program.expiryDate = this.convertDateToLong(new Date(this.program.expiryDate))
            this.program.userId = this.$session.get('userId')
            this.program.userEmail = this.$session.get('email')
            // this.program.category.
             
            for(let i=0;i<this.tags.length;i++) {
                if(i==0) {
                    this.program.keywords = this.tags[i].text
                }
                this.program.keywords  = this.program.keywords+','+this.tags[i].text
            }
            // let index =1
            // this.program.crewList[index] = "xyz"
            // console.log(this.program.crewList)
            console.log(this.inputArray)
            this.inputArray.forEach(input => {
                let index = input.role
                console.log(index)
                this.program.crewList[index] = input.roleName
            })
            console.log(this.program.crewList)

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
                formData.append('file', event.target.files[0])
                formData.append('filetype','image')
                formData.append('type','Thumbnail')
                this.imageUpload(formData).then( (resp) => {
                    if(resp.uploadLink!="") {
                        this.program.imgUrls.thumbnail = resp.uploadLink
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
                        this.program.imgUrls.avatar = resp.uploadLink
                    } else {
                        this.$swal(resp.message)
                    }
                }).catch( (err) => {
                    console.log(err)
                })
            }
             
        },
        handleCancel() {
            this.$router.go(-1)
        },
        addInput() {
            const input = {
                role: '',
                roleName: ''
            }
            this.inputArray.push(input)
        },
        removeInput(index) {
            this.inputArray.splice(index, 1)
        }

    },
    mounted() {
        this.init()
    }
    
}