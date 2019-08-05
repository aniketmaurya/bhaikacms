<template>
<div class="card-wrapper">
<div class="row">
<div class="col-md-12">
    <div class="col-md-6">
        <router-link :to="'/program/seasonalVideo'"><button class="btn btn-success">Upload new program</button></router-link>
    </div>
    <!-- <div class="col-md-6">
        <form>
            <input type="text" placeholder="search here"> 
        </form>    
    </div> -->
</div>
</div>
<div v-for="program in seasonalVideos.content" :key="program.id"  class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  :src="program.imgUrls.thumbnail" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            <div class="card-block col-md-5 px-3">
                <router-link :to="'/seasons/'+program.id"><h3 class="card-title">{{ program.name }}</h3></router-link>
                <p class="card-text"><b>Upload Id : </b>xxxx</p>
                <p class="card-text"><b>Start Date : </b>{{ changeTime(program.startDate) }}</p>
                <p class="card-text"><b>Expiry Date : </b>{{ changeTime(program.expiryDate) }}</p>
                <p class="card-text"><b>Upload Date : </b>{{ changeTime(program.creationDate) }}</p>
            </div>
            <div class="card-block col-md-4 px-3">
                <div class="help"> </div>
                <p class="card-text"><b>Description : </b>{{ program.description }}</p>
                <p class="card-text"><b>Category : </b>{{ program.category.name }}</p>
                <p class="card-text"><b>Languages : </b>{{ program.languages }}</p>
                <p class="card-text"><b>Parental Rating : </b>{{ program.parentalRating }}</p>
            </div>
            <div class="card-block col-md-3 px-3">
                <div class="help1"> </div>
                <p><button @click="deleteProgram(program.id)"  class="btn btn-danger">Remove</button></p>
                <router-link :to="'/seasonVideoForm'"><button class="btn btn-primary">Edit</button></router-link>
            </div>
        </div>
    </div>
</div>
<div class="col-md-12">
        <ul class="pagination">
            <button  @click="prvPage()"   class="btn btn-primary">Previous page</button>
            <button  @click="nextPage()" style="margin:10px;" class="btn btn-primary">Next page</button>
        </ul>
</div>
</div>
</template>

<script src="./js/seasonalVideo.js"></script>


<style>
.card-wrapper {
    padding:10px;
}
.card {
    border:1px solid black;
    margin:10px;
    padding:10px;
}
.help {
    margin-bottom:28%;
}
.help1 {
    margin-bottom:38%;
}
</style>
