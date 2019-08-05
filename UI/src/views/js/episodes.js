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
            this.episodeData.userId = this.$session.get('userId')
            this.deleteEpisodeById(this.episodeData).then( (resp)=> {
                if (resp) {
                    this.$swal("Deleted")
                    this.getEpisodesBySeason(this.page)
                }
            }).catch( (err) => {
                console.log(err)
            })
        },
        nextPage() {
            if (!this.episodes.last) {
                this.page.seasonId = this.$route.params.id
                this.page.pageNumber = this.page.pageNumber+1
                this.page.pageSize = 5
                this.getSeasonalVideoPrograms(this.page)
            }
        },
        prvPage() {
            if (!this.episodes.first) {
                this.page.seasonId = this.$route.params.id
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