import { mapGetters, mapActions } from "vuex";

export default {
    name:'MultiVideo',
    computed: {
        ...mapGetters([
            'multiVideos'
        ])
    },
    data() {
        return {
            page: {
                pageSize:5,
                pageNumber:0
            },
            programData: {
                id:"",
                userId:""
            }
        }
    },
    methods: {
        ...mapActions([
            'getMultiVideoPrograms',
            'deleteProgramById'
        ]),
        init() {
            this.getMultiVideoPrograms(this.page) 
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
                    this.getMultiVideoPrograms(this.page)
                } else {
                    this.$swal('Not deleted')
                }
            }).catch( (err) => {
                console.log(err)
            })
        },
        nextPage() {
            if (!this.multiVideos.last) {
                this.page.pageNumber = this.page.pageNumber+1
                this.page.pageSize = 5
                this.getSeasonalVideoPrograms(this.page)
            }
        },
        prvPage() {
            if (!this.multiVideos.first) {
                this.page.pageNumber = this.page.pageNumber-1
                this.page.pageSize = 5
                this.getSeasonalVideoPrograms(this.page)
            }
        },

    },
    mounted() {
        this.init()
    }
    
}