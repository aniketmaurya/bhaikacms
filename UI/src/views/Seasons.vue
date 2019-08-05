<template>
<div class="card-wrapper">
<center><button @click="addNewSeason(seasons.content)"  class="btn btn-success">Upload new season</button></center>
<div v-for="season in seasons.content" :key="season.id"  class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  :src="season.seasonImgUrls.thumbnail" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            <div class="card-block col-md-6 px-3">
                <router-link :to="'/episodes/'+season.id"><h3 class="card-title">{{ season.seasonName }}</h3></router-link>
                <p class="card-text"><b>Season Number : </b>{{ season.seasonNumber }}</p>
                <p class="card-text"><b>Season Description : </b>{{ season.seasonDescription }}</p>
                
            </div>
            <div class="card-block col-md-3 px-3">
                <div class="help1"> </div>
                <p><button @click="deleteProgram(season.id)"  class="btn btn-danger">Remove</button></p>
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

<script src="./js/seasons.js"></script>

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
