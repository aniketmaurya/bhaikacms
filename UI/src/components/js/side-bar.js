import { mapGetters } from "vuex";

export default {
    name:'SideBar',
    data () {
        return {
            searchTerm:""
        }
    },
    computed: {
        ...mapGetters([
            'userDetails',
            'isLoggedIn'
        ])
    },
    methods:{
        pushToSearch() {
            this.$router.push({
                name:'search',
                query:{
                    searchTerm: this.searchTerm
                }
            })
        }
    }
}