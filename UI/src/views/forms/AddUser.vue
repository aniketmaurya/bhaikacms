<template>
<div class="box">
    <form class="program-form" style="padding:15px;" @submit.prevent="handleSubmit()">
        <h2 class="form-heading">REGISTER NEW USER</h2>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Name</label>
                <input  v-model="user.name" type="text" class="form-control " required>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Email</label>
                <input v-model="user.email" type="text" class="form-control" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Password</label>
                <input v-model="user.password"  type="password" class="form-control " required>
            </div>
            <div class="form-group col-md-6">
                <label >Role Id</label>
                <input v-model="user.roleId" type="text" class="form-control" required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>    
</template>


<script>
import { mapGetters, mapActions } from 'vuex';
export default {
    name:'AddUser',
    data() {
        return {
            user: {
                name:"",
                password:"",
                email:"",
                roleId:""
            }
        }
    },
    methods: {
        ...mapActions([
            'addUser'
        ]),
        handleSubmit() {
            this.addUser(this.user).then( (resp) => {
                if(resp.added) {
                    this.$swal('',resp.message,'success')
                }
                else {
                    this.$swal(resp.message)
                }
            }).catch( (err) => {
                console.log(err)
            })
        },
        init() {
            if(this.$session.get('roleId') === 0) {
                this.$router.push('/')
            }
        }
    },
    mounted() {
        this.init()
    }
}
</script>

<style scoped>
.box {
    padding:20px;
    margin:20px;
    border-radius: 10px;
	box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
}
.form-heading {
    text-align:center;
    color: #2980B9;
}
.btn {
    margin:0px auto;
    display:block;
}
</style>
