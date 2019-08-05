import { mapGetters, mapActions } from 'vuex';

export default {
    name:'UserList',
    computed: {
        ...mapGetters([
            'userList'
        ])
    },
    data() {
        return {
            page: {
                pageNumber:0,
                pageSize:5 
            },
            deleteData : {
                idDelete:"",
                id:""
            },
            count:0,
        }
    },
    methods: {
        ...mapActions([
            'getUserList',
            'deleteUser'
        ]),
        init() {
            if(this.$session.get('roleId') === 0) {
                this.$router.push('/')
            }
            this.getUserList(this.page)
        },
        chanegRoleName(roleId) {
            if (roleId==0)
               return "User"
            else
               return "Admin"
        },
        changeIsActive(isActive) {
            if(isActive)
                return "Active"
            else
                return "Not active"
        },
        nextPage() {
            if(this.page.pageNumber < this.userList.totalPages-1)
            {
                this.page.pageNumber = this.page.pageNumber+1
                this.page.pageSize = 5
                this.getUserList(this.page)
            }
        },
        prvPage() {
            if (this.page.pageNumber!=0) {
                this.page.pageNumber = this.page.pageNumber-1
                this.page.pageSize = 5
                this.getUserList(this.page)
            }
        },
        handleDelete(id) {
            this.deleteData.idDelete = id
            this.deleteData.id = this.$session.get('userId')
            console.log(this.deleteData)
            this.deleteUser(this.deleteData).then( (resp) => {
                if(resp.deleted) {
                   this.$swal('Deleted')
                   this.getUserList(this.page)
                } else {
                    this.$swal('Not able to delete')
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
