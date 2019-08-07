import {mapGetters , mapActions} from 'vuex'
import { throws } from 'assert';
export default {
    name: 'UserListData',
    props: ['data', 'refresh'],
    data () {
        return {
            newData: {},
            sendData:{
                id:"",
                name:"",
                roleId:"",
                isActive:false
            },
            editState: false,
            role:"",
            userName:"",
            deleteData : {
                idDelete:"",
                id:""
            },
            page: {
                pageNumber:0,
                pageSize:10,
                sortBy:"",
                order:0,
            }
        }
    },
    computed:{
        ...mapGetters([])
    },
    created () {
        console.log(this.data),
        this.newData = this.data
        this.userName=this.data.name
        this.role=this.data.roleId
    },
    methods: {
        ...mapActions(['deleteUser','getUserList']),
        isDataModified () {
            if(this.newData.name!=this.data.name||this.newData.roleName!=this.data.roleName){
                this.updateUserData()
            }
            // TODO
            // logic to check if data and newData are different
            
        },
        handleDelete() {
            this.$swal({
                title: "Are you sure?",
                text: "",
                icon: "warning",
                buttons: true,
                dangerMode: true,
              }).then((willDelete) => {
                if (willDelete) {
                    this.deleteData.idDelete = this.data.id
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
        handleActivate() {
            this.deleteData.idDelete = this.data.id
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
        changeIsActive(isActive) {
            if(isActive)
                return "Active"
            else
                return "Not active"
        },
        updateUserData () {
            // TODO
            // call api to update new data
        },
        toggleEditState (value = false) {
            this.editState = !this.editState
        },
        toggleRoleName (roleId) {
            let roleName = ''
            
            roleId === 0 ? roleName = 'Admin' : roleName = 'User'
            //console.log(roleName)
            return roleName
        },
        toggleRoleId (id) {
            let roleId = 0
            id === 0 ? roleId = 1 : roleId=0
            //this.tempValueRole= roleId
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
        disableEditing(){
            this.editState=!this.editState
        },
        saveEdit(){
            window.console.log(this.userName)
            window.console.log(this.data.isActive)
            if(this.userName!=this.data.name||this.role!=this.data.roleId){
                this.sendData.name=this.userName
                this.sendData.roleId=this.role
                this.sendData.id=this.data.id
                this.sendData.isActive=this.data.active
                window.console.log(this.role)
                window.console.log(this.sendData)
                window.console.log()
                this.editState=false,
                window.console.log()
                this.$http.post("http://172.16.20.78:8080/useradmin/editCredentials",JSON.stringify(this.sendData))
                .then(response => response.json())
                .then(response => {
                    if(response){
                        this.$swal("Updated")
                        this.getUserList()
                    }
                })
            }
        }
    }
}
