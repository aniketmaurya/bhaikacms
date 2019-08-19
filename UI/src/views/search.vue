<template>

<div v-if="getSearchResult.content.length==0" class="card-wrapper">
No Result Found
</div>
 <div v-else>
 <div  v-for="(search, index) in getSearchResult.content" :key="index"  class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  :src="search.imgUrls.thumbnail" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            
            <!-- <div class="card-block col-md-5 px-3">
                <router-link :to="'/seasons/'+search.id"><h3 class="card-title">{{ search.name }}</h3></router-link>
                <p class="card-text"><b>Upload Id : </b>{{ program.id }}</p>
                <p class="card-text"><b>Start Date : </b>{{ changeTime(search.startDate) }}</p>
                <p class="card-text"><b>Expiry Date : </b>{{ changeTime(search.expiryDate) }}</p>
                <p class="card-text"><b>Upload Date : </b>{{ changeTime(search.creationDate) }}</p>
            </div> -->
            <div class="card-block col-md-8 px-2">
                <div class="help"> </div>
                <p class="card-text"><b>Name : </b>{{ search.name }}</p>
                <p class="card-text"><b>Description : </b>{{ search.description }}</p>
                <p class="card-text"><b>Categories : </b>{{ search.path }}</p>
                <p class="card-text"><b>Languages : </b>{{ search.languages }}</p>
                <p class="card-text"><b>Video Type : </b>{{ search.type }}</p>
                <p class="card-text"><b>Parental Rating : </b>{{ search.parentalRating }} +</p>
            </div>
           
        </div> 
    </div>
</div>  
<!-- <div v-if="getSearchResult.content!=0"  class="col-md-12">
    <div class="clearfix">
        <div class="hint-text">Showing <b><span v-if="getSearchResult.number!=(getSearchResultgetSearchResult.totalPages-1)">{{((page.pageNumber+1)*singleVideos.numberOfElements)}}</span>
            <span v-else>{{((singleVideos.number*singleVideos.size)+singleVideos.numberOfElements)}}</span></b> out of <b>{{ singleVideos.totalElements }}</b> entries</div>
        <div class="hint-text">Showing <b>{{ allAudits.size }}</b> out of <b>{{ allAudits.totalElements }}</b> entries</div>
        <ul class="pagination">
            <button v-if="page.pageNumber!=0" @click="prvPage()"   class="btn btn-primary">Prev</button>
            <span> {{page.pageNumber+1}} Out of {{singleVideos.totalPages}}</span>
            <button v-if="page.pageNumber!=(singleVideos.totalPages-1)" @click="nextPage()" style="margin:10px;" class="btn btn-primary">Next</button>
        </ul>
    </div>
</div>  -->
<!-- <div v-else>
    <div class="col-md-6"> 
        <h1 class="msg">Nothing to Show</h1>
    </div>
</div> -->
</div>
</template>
<script src="./js/search.js"></script>
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