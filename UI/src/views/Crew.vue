<template>
<div class="box">
    <div class="row">
        <div class="col-md-12">
            <form  @submit.prevent="handleSubmit()" class="program-form" style="padding:15px;">
                <h2 class="form-heading">CREW ROLES</h2>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Crew</label>
                        <input v-model="crew[0].role" type="text" class="form-control " required>
                    </div>
                </div>
                <div style="margin-top:22px;" class="col-md-6 form-group">
                    <button type="submit" class="btn btn-success">Save</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12" style="margin-top:10px;">
            <table v-if="true"  class="table table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th></th>
                        <th></th>
                    </tr>    
                </thead>
                <tbody>
                    <tr v-for="crewRole in crewRoles" :key="crewRole.id">
                        <td>{{ crewRole.role }}</td>
                        <td><button @click="handleDelete(crewRole)" class="btn btn-danger">Remove</button></td>
                        <td><button @click="handleEdit(crewRole)" class="btn btn-primary">Edit</button></td> 
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>    
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
    name:'Crew',
    computed:{
        ...mapGetters([
            'crewRoles'
        ])
    },
    data() {
        return {
            searchText:"",
            crew:[
                {
                    role:""
                }
            ]
        }
    },
    methods: {
        ...mapActions([
            'getCrewRoles',
            'addCrewRoles',
            'deleteCrewRoles',
            'editCrewRoles'
        ]),
        init() {
            this.getCrewRoles()
        },
        handleSubmit() {
            this.addCrewRoles(this.crew).then( (resp) => {
                if(resp) {
                    this.getCrewRoles()
                    this.crew[0].role=""
                } else {
                    this.$swal("Not added")
                }
            }).catch( (err) => {
                console.log(err)
            })
        },
        handleDelete(role) {
            this.deleteCrewRoles(role).then( (resp) => {
                if(resp) {
                    this.$swal(role.role+" deleted")
                    this.getCrewRoles()
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
</script>


<style>
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
</style>
