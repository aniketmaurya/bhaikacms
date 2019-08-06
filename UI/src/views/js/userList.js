import { mapGetters, mapActions } from 'vuex';

export default {
    name:'UserList',
    data() {
        return {
            page: {
                pageNumber:0,
                pageSize:10,
                sortBy:"",
                order:0,
            },
            deleteData : {
                idDelete:"",
                id:""
            },
            tempValue: null,
            editing: false
        }
    },
    computed: {
        ...mapGetters([
            'userList',
            'userListEdit'
        ])
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
        toggleRoleName (roleId) {
            let roleName = ''
            roleId === 0 ? roleName = 'Admin' : roleName = 'User'
            return roleName
        },
        toggleRoleId (id) {
            let roleId = 0
            id === 0 ? roleId = 1 : 0
            return roleId
        },
        chanegRoleName(roleId) {
            if (roleId === 0)
               return "User"
            else
               return "Admin"
        },
        checkToggleValue(event) {
            // check select change
            console.log('event', event.target)
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
                this.page.pageSize = 10
                this.getUserList(this.page)
            }
        },
        prvPage() {
            if (this.page.pageNumber!=0) {
                this.page.pageNumber = this.page.pageNumber-1
                this.page.pageSize = 10
                this.getUserList(this.page)
            }
        },
        handleDelete(id) {
            this.$swal({
                title: "Are you sure?",
                text: "",
                icon: "warning",
                buttons: true,
                dangerMode: true,
              }).then((willDelete) => {
                if (willDelete) {
                    this.deleteData.idDelete = id
                    this.deleteData.id = this.$session.get('userId')
                    console.log(this.deleteData)
                    this.deleteUser(this.deleteData).then( (resp) => {
                    if(resp.deleted) {
                        this.$swal(resp.message)
                        this.getUserList(this.page)
                    } else {
                        this.$swal('Not able to delete')
                    }
                    }).catch( (err) => {
                        console.log(err)
                    })
                } else {
                  this.$swal("Not deleted");
                }
              })
        },
        handleActivate(id) {
            this.deleteData.idDelete = id
            this.deleteData.id = this.$session.get('userId')
            console.log(this.deleteData)
            this.deleteUser(this.deleteData).then( (resp) => {
            if(resp.deleted) {
                this.$swal(resp.message)
                this.getUserList(this.page)
            } else {
                this.$swal('Not able to activate')
            }
            }).catch( (err) => {
                console.log(err)
            })
        },
        search() {
            this.searchUser

        },
        enableEditing: function(value) {
            this.tempValue = value;
            this.editing = true;
        },
        disableEditing: function() {
            this.tempValue = null;
            this.editing = false;
        },
        saveEdit: function() {
            
            this.value = this.tempValue;
            this.disableEditing();
            //function call to save the details
        },
        sort(name) {
            this.page.sortBy = name
            if(this.page.order==0)
               this.page.order=1
            else
               this.page.order=0
            this.getUserList(this.page)
        }

    },
    mounted() {
        this.init()
    }
}
