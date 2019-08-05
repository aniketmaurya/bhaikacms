import { mapGetters } from "vuex";

export default {
    name:'SideBar',
    computed: {
        ...mapGetters([
            'userDetails',
            'roleId'
        ])
    }
}