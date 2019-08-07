<template>
<div class="box">
    <div class="row">
        <div class="col-md-12">
            <form class="program-form" style="padding:15px;" @submit.prevent="handleSubmit()">
                <h2 class="form-heading">LANGUAGE</h2>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Language</label>
                        <input v-model="language[0].name"  type="text" class="form-control " required>
                    </div>
                </div>
                <div style="margin-top:22px;" class="col-md-6 form-group">
                    <button type="submit" class="btn btn-success">Save</button>
                </div>
            </form>
            <!-- <div class="row">
                <div class="col-md-6">
                    <vue-tags-input v-model="tag" :tags="tags" @tags-changed="newTags => tags = newTags"/>
                </div>
                <div class="col-md-6">
                    <button @click="handleSubmit()" class="btn btn-success">Save</button>
                </div>    
            </div> -->
                

        </div>
    </div>
    <div class="row">
        <div v-if="languages.length!=0" class="col-md-12" style="margin-top:10px;">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th></th>
                        <th></th>
                    </tr>    
                </thead>
                <tbody>
                    <tr v-for="language in languages" :key="language.id">
                        <td>{{ language.name }}</td>
                        <td><button @click="handleDelete(language)" class="btn btn-danger">Remove</button></td>
                        <td><button @click="handleEdit(language)" class="btn btn-primary">Edit</button></td> 
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>    
</template>

<script>
import VueTagsInput from '@johmun/vue-tags-input';

import { mapActions, mapGetters } from 'vuex';
export default {
    name:'Language',
    components:{
        VueTagsInput
    },
    data() {
        return {
            language: [
                {
                    name:""
                }
            ],
            editLang:{
                id:"",
                name:""
            },
            tag:'',
            tags: [],
            isEdit:true
        }
    },
    computed: {
        ...mapGetters([
            'languages'
        ])
    },
    methods: {
        ...mapActions([
            'addLanguage',
            'getLanguages',
            'deleteLanguageByName',
            'editLanguage'
        ]),
        handleSubmit() {
            this.addLanguage(this.language).then ( (resp) => {
                if(resp) {
                    this.$swal(this.language[0].name+' Added')
                    this.getLanguages()
                    this.language[0].name=""
                } else {
                    this.$swal(this.language[0].name+" already exists")
                    this.tags=[]
                }
            }).catch((err) => {
                console.log(err)
            })
        },
        init() {
            this.getLanguages()
        },
        handleDelete(name) {
            this.deleteLanguageByName(name).then( (resp) => {
                console.log(resp)
                if(resp) {
                    this.$swal(name.name+" Deleted")
                    this.getLanguages()
                }
            }).catch( (err) => {
                console.log(err)
            })
        },
        handleEdit(language) {
            this.language[0].name = language.name           
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
