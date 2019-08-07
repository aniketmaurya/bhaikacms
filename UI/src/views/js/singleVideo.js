import { mapActions, mapGetters } from "vuex";

export default {
    name:'SingleVideo',
    data() {
        return {
            page: {
                pageSize:5,
                pageNumber:0
            },
            programData: {
                id:"",
                userId:""
            },
            searchData: {
                searchText:"",
                videoType:"Single video program"
            }
            
        }
    },
    computed: {
        ...mapGetters([
            'singleVideos'
        ])
    },
    methods: {
        ...mapActions([
            'getSingleVideoPrograms',
            'deleteProgramById',
            'searchInSingleVideo'
        ]),
        init() {
           this.getSingleVideoPrograms(this.page) 
        },
        changeTime(date) {
            let d = new Date(date) 
            return d.toDateString();
        },
        deleteProgram(id) {
            this.programData.id = id
            this.programData.userId = this.$session.get('userId')
            this.deleteProgramById(this.programData).then( (resp) => {
                if(resp) {
                    this.getSingleVideoPrograms(this.page)
                } else {
                    this.$swal('Not deleted')
                }
            }).catch( (err) => {
                console.log(err)
            })
        },
        nextPage() {
            if (!this.singleVideos.last) {
                this.page.pageNumber = this.page.pageNumber+1
                this.page.pageSize = 5
                this.getSingleVideoPrograms(this.page)
            }
        },
        prvPage() {
            if (!this.singleVideos.first) {
                this.page.pageNumber = this.page.pageNumber-1
                this.page.pageSize = 5
                this.getSingleVideoPrograms(this.page)
            }
        },
        handleSubmit() {
            this.searchInSingleVideo(this.searchData).then( (resp) => {
                console.log(resp)
            }).catch((err)=> {
                console.log(err)
            }) 

        }

    },
    mounted() {
        this.init()
    }
    
}