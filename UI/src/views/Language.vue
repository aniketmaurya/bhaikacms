<template>
<div class="box">
    <form class="program-form" style="padding:15px;" @submit.prevent="handleSubmit()">
        <h2 class="form-heading">ADD LANGUAGE</h2>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label>Language</label>
                <input v-model="language.name"  type="text" class="form-control " required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>    
</template>

<script>
import { mapActions } from 'vuex';
export default {
    name:'Language',
    data() {
        return {
            language: {
                name:""
            }
        }
    },
    methods: {
        ...mapActions([
            'addLanguage'
        ]),
        handleSubmit() {
            this.addLanguage(this.language).then ( (resp) => {
                console.log(resp)
                $this.swal('Added')
            }).catch((err) => {
                console.log(err)
            })
        }
    }
    
}
</script>


<style>

</style>
