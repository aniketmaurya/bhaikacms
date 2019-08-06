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
                pageSize:10,
                userId:null,
                sortBy:null,
                sortOrder:0
            }
        }
    },
    methods: {
        ...mapActions([
            'getAllAudits'
        ]),
        init() {
            if(this.$session.get('roleId') === 0) {
                this.$router.push('/')
            }
            this.getAllAudits(this.page)
        },
        changeDate(date) {
            let d = new Date(date) 
            return d.toDateString() + d.toTimeString();
        },
        prvPage() {
            if(this.page.pageNumber != 0 ) {
                this.page.pageNumber = this.page.pageNumber-1
                this.page.size=10
                this.getAllAudits(this.page)
            }
        },
        nextPage() {
            if(!this.allAudits.last) {
                this.page.pageNumber = this.page.pageNumber+1
                this.page.size=10
                this.getAllAudits(this.page)
            }
        }

    },
    mounted() {
        this.init()
    }
    
}