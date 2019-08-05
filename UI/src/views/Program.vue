<template>
<div class="box">
    <form class="program-form" style="padding:15px;" @submit.prevent="handleSubmit()">
        <h2 class="form-heading">PROGRAM FORM</h2>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Program type</label>
                <input v-model="program.type" type="text" class="form-control " required readOnly>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Program name</label>
                <input v-model="program.name"  type="text" class="form-control" required>
            </div>
        </div>
        <div class="form-group col-md-12">
            <label for="inputAddress">Description</label>
            <input v-model="program.description"  type="text" class="form-control" required>
        </div>
        <div class="form-group col-md-12">
            <label for="inputAddress2">Keywords</label>
            <input v-model="program.keywords" type="text" class="form-control" required>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputCity">Parental rating</label>
                <input v-model="program.parentalRating"  type="text" class="form-control" required>
            </div>
            <div class="form-group col-md-6">
                <label for="inputState">Language</label>
                <input v-model="program.languages" type="text" class="form-control" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="inputCity">Start date</label>
                <input  v-model="program.startDate" type="date" class="form-control" required>
            </div>
            <div class="form-group col-md-4">
                <label for="inputZip">Expiry date</label>
                <input v-model="program.expiryDate" type="date" class="form-control" required>
            </div>
             <div class="form-group col-md-4">
                <label for="inputState">Category</label>
                <select id="inputState" class="form-control">
                    <option selected>Choose...</option>
                    <option>...</option>
                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label>Thumbnail</label>
                <input @change="processFile($event,'Thumbnail')" type="file" class="form-control" required>
            </div>
            <div class="form-group col-md-4">
                <label>Avatar</label>
                <input @change="processFile($event,'Avatar')" type="file" class="form-control" required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Save program</button>
    </form>
</div>   
</template>

<script src="./js/program.js"></script>

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
