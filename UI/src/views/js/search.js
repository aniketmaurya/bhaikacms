import { mapActions, mapGetters } from "vuex";
export default {
    name:'search',
    data(){
        return{
            search:this.$route.query.searchTerm
        }
    },
    computed :{
        ...mapGetters([
            'getSearchResult'
        ]),
        routeCheck(){
            return this.$route.query
        }
    },
    created () {
        this.searchData()
    },
    watch:{
        routeCheck:function(){
            console.log("Watcher called")
            this.search=this.$route.query.searchTerm
            this.searchData()
        }
    },
    methods: {
        ...mapActions([
            'searchInVideo'
        ]),
        searchData () {
            let data = {
                searchTerm: this.search
            }
            this.searchInVideo({data})
        }
    }
}

