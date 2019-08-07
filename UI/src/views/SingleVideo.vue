<template>
<div class="card-wrapper">
    <div class="row">
    <div class="col-md-12">
        <div class="col-md-6">
            <form  v-if="singleVideos.content!=0" @submit.prevent="handleSubmit()">
                <div class="col-md-8">
                    <!-- <input v-model="searchData.searchText"  class="form-control" type="text" placeholder="Search here"> -->
                </div>
                <div class="col-md-4">
                    <!-- <button class="btn btn-primary">Search</button>  -->
                </div>
            </form>
       </div>
        <div class="col-md-6">
            <router-link :to="'/program/singleVideo'"><button  style="margin:10px;" class="btn btn-success">Upload new program</button></router-link>
        </div>
    </div>
    </div>
<div  v-for="program in singleVideos.content" :key="program.id"  class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  :src="program.imgUrls.thumbnail" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            <div class="card-block col-md-5 px-3">
                <router-link :to="'/seasons/'+program.id"><h3 class="card-title">{{ program.name }}</h3></router-link>
                <p class="card-text"><b>Upload Id : </b>{{ program.id }}</p>
                <p class="card-text"><b>Start Date : </b>{{ changeTime(program.startDate) }}</p>
                <p class="card-text"><b>Expiry Date : </b>{{ changeTime(program.expiryDate) }}</p>
                <p class="card-text"><b>Upload Date : </b>{{ changeTime(program.creationDate) }}</p>
            </div>
            <div class="card-block col-md-4 px-3">
                <div class="help"> </div>
                <p class="card-text"><b>Description : </b>{{ program.description }}</p>
                <p class="card-text"><b>Category : </b>{{ program.category.categoryName }}</p>
                <p class="card-text"><b>Languages : </b>{{ program.languages }}</p>
                <p class="card-text"><b>Parental Rating : </b>{{ program.parentalRating }} +</p>
            </div>
            <div class="card-block col-md-3 px-3">
                <div class="help1"> </div>
                <p><button @click="deleteProgram(program.id)"  class="btn btn-danger">Remove</button></p>
                <router-link :to="'/seasonVideoForm'"><button class="btn btn-primary">Edit</button></router-link>
            </div>
        </div>
    </div>
</div>   
<div v-if="singleVideos.content!=0"  class="col-md-12">
    <div class="clearfix">
        <div class="hint-text">Showing <b><span v-if="singleVideos.number!=(singleVideos.totalPages-1)">{{((page.pageNumber+1)*singleVideos.numberOfElements)}}</span>
            <span v-else>{{((singleVideos.number*singleVideos.size)+singleVideos.numberOfElements)}}</span></b> out of <b>{{ singleVideos.totalElements }}</b> entries</div>
        <!-- <div class="hint-text">Showing <b>{{ allAudits.size }}</b> out of <b>{{ allAudits.totalElements }}</b> entries</div> -->
        <ul class="pagination">
            <button v-if="page.pageNumber!=0" @click="prvPage()"   class="btn btn-primary">Prev</button>
            <span> {{page.pageNumber+1}} Out of {{singleVideos.totalPages}}</span>
            <button v-if="page.pageNumber!=(singleVideos.totalPages-1)" @click="nextPage()" style="margin:10px;" class="btn btn-primary">Next</button>
        </ul>
    </div>
</div> 
<div v-else>
    <div class="col-md-6"> 
        <h1 class="msg">Nothing to Show</h1>
    </div>
</div>
</div>
</template>

<script src="./js/singleVideo.js"></script>


<style>
.card-wrapper {
    padding:10px;
}
.card {
    border:1px solid black;
    margin:10px;
    padding:10px;
}
  .pagination {
        float: right;
        margin: 0 0 5px;
    }
    .pagination li a {
        border: none;
        font-size: 95%;
        width: 30px;
        height: 30px;
        color: #999;
        margin: 0 2px;
        line-height: 30px;
        border-radius: 30px !important;
        text-align: center;
        padding: 0;
    }
    .pagination li a:hover {
        color: #666;
    }   
    .pagination li.active a {
        background: #03A9F4;
    }
    .pagination li.active a:hover {        
        background: #0397d6;
    }
    .pagination li.disabled i {
        color: #ccc;
    }
    .pagination li i {
        font-size: 16px;
        padding-top: 6px
    }
    .hint-text {
        float: left;
        margin-top: 6px;
        font-size: 95%;
    }  
    .msg {
        height:30px;
        padding:10px;
        margin:10px;
        color:red;
    }
</style>
