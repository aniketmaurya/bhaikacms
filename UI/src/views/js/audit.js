import { mapActions, mapGetters } from 'vuex'
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
                userId:null,
                sortBy:null,
                sortOrder:1
            },
            prevSort:null,
            currentPage:1,
            startDate:"",
            endDate:"",
            report: {
                startDate:"",
                endDate:""
            }
        
        }
    },
    methods: {
        ...mapActions([
            'getAllAudits',
            'getReport'
        ]),
        init() {
            if(this.$session.get('roleId') === 0) {
                this.$router.push('/')
            }
            this.getAllAudits(this.page)
        },
        changeDate(date) {
            let d = new Date(date) 
            return d.toDateString()+", "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
        },
        prvPage() {
            if(this.page.pageNumber != 0 ) {
                this.page.pageNumber = this.page.pageNumber-1
                if(this.currentPage!=0)
                    this.currentPage = this.currentPage-1
                this.page.size=5
                this.getAllAudits(this.page)
            }
        },
        nextPage() {
            if(!this.allAudits.last) {
                this.page.pageNumber = this.page.pageNumber+1
                this.currentPage = this.currentPage+1
                this.page.size=5
                this.getAllAudits(this.page)
            }
        },
        sort(value){
            if(this.prevSort==value){
                this.page.sortOrder=this.page.sortOrder==0?1:0;
            }else{
                this.page.sortBy=value;
                this.page.sortOrder=1;
            }
            this.prevSort=value;
            this.getAllAudits(this.page);
        },
        renderPage() {
            this.page.pageNumber = this.currentPage-1
            this.getAllAudits(this.page);
        },
        handleExport() {
            this.startDate = this.convertDateToLong(new Date(this.startDate))
            this.endDate = this.convertDateToLong(new Date(this.endDate))
            //
            this.report.startDate=this.startDate
            this.report.endDate=this.endDate

            this.getReport(this.report).then( (resp) => {
                console.log(resp)
            }).catch( (err) =>  {
                console.log(err)
            })
        },
        handleFilter() {
            this.page.startDate = this.convertDateToLong(new Date(this.startDate))
            this.page.endDate = this.convertDateToLong(new Date(this.endDate))
            this.getAllAudits(this.page)
        },
        convertDateToLong(date) {
            return date.getTime()
        },
    },
    mounted() {
        this.init()
    }
    
}
