import { mapActions, mapGetters } from 'vuex';



export default {
    name:'Audit',
    computed: {
        ...mapGetters([
            'allAudits'
        ])
    },
    data() {
        return {
            page : {
                startDate:null,
                endDate:null,
                pageNumber:0,
                pageSize:5,
                userId:null
            }
        }
    },
    methods: {
        ...mapActions([
            'getAllAudits'
        ]),
        init() {
            this.getAllAudits(this.page)
        },
        changeDate(date) {
            let d = new Date(date) 
            return d.toDateString();
        },
        prvPage() {
            if(this.page.pageNumber != 0 ) {
                this.page.pageNumber = this.page.pageNumber-1
                this.page.size=5
                this.getAllAudits(this.page)
            }
        },
        nextPage() {
            if(!this.allAudits.last) {
                this.page.pageNumber = this.page.pageNumber+1
                this.page.size=5
                this.getAllAudits(this.page)
            }
        }

    },
    mounted() {
        this.init()
    }
    
}