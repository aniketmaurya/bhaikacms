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
            }
        }
    },
    methods: {
        ...mapActions([
            'getMultiVideoPrograms'
        ]),
        init() {
            this.getMultiVideoPrograms(this.page) 
        }

    },
    mounted() {
        this.init()
    }
    
}