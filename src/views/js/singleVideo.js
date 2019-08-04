import { mapActions, mapGetters } from "vuex";

export default {
    name:'SingleVideo',
    data() {
        return {
            page: {
                pageSize:5,
                pageNumber:0
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
            'getSingleVideoPrograms'
        ]),
        init() {
           this.getSingleVideoPrograms(this.page) 
        }

    },
    mounted() {
        this.init()
    }
    
}