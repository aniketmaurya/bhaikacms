import { mapGetters, mapActions } from "vuex";
import { throws } from "assert";

export default {
    name:'SeasonalVideo',
    computed:{
        ...mapGetters([
            'seasonalVideos'
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
            'getSeasonalVideoPrograms',
            'deleteProgramById'
        ]),
        init() {
            this.getSeasonalVideoPrograms(this.page)            
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
                    this.getSeasonalVideoPrograms(this.page)
                } else {
                    this.$swal('Not deleted')
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