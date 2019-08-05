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
            },
            seasonData: {
                id:"",
                userId:""
            }
        }
    },
    methods: {
        ...mapActions([
            'getSeasonByProgram',
            'deleteSeasonById'
        ]),
        init() {
            this.page.programId = this.$route.params.id
            this.getSeasonByProgram(this.page)
        },
        deleteProgram(id) {
            this.seasonData.id = id
            this.seasonData.userId = this.$session.get('userId')
            this.deleteSeasonById(this.seasonData).then( (resp) => {
                if(resp) {
                    this.getSeasonByProgram(this.page)
                } else {
                    this.$swal('Not deleted')
                }
            }).catch( (err) => {
                console.log(err)
            })
        },
        nextPage() {
            if (!this.seasons.last) {
                this.page.programId = this.$route.params.id
                this.page.pageNumber = this.page.pageNumber+1
                this.page.pageSize = 5
                this.getSeasonalVideoPrograms(this.page)
            }
        },
        prvPage() {
            if (!this.seasons.first) {
                this.page.programId = this.$route.params.id
                this.page.pageNumber = this.page.pageNumber-1
                this.page.pageSize = 5
                this.getSeasonalVideoPrograms(this.page)
            }
        },
        addNewSeason(data) {
            this.$store.commit('setProgram',data[0].program)
            this.$router.push('/seasonalVideoForm')
        }

    },
    mounted() {
        this.init()
    }
    
}