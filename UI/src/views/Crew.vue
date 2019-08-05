<template>
<div class="box">
    <form class="program-form" style="padding:15px;" @submit.prevent="handleSubmit()">
        <h2 class="form-heading">ADD CREW</h2>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label>Crew</label>
                <input type="text" class="form-control " required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>    
</template>

<script>

</script>


<style>

</style>
