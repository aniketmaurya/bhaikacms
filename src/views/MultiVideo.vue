<template>
<div class="card-wrapper">
<center><router-link :to="'/program/multiVideo'"><button class="btn btn-success">Upload new video</button></router-link></center>
<div class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  src="@/assets/img/blog-details/1.jpg" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            <div class="card-block px-3">
                <h4 class="card-title">Name</h4>
                <p class="card-text"><b>Upload Id : </b>xxxx</p>
                <p class="card-text"><b>Added on : </b>xxxx</p>
                <p class="card-text"><b>Category : </b>xxxx</p>
                <a href="#" class="btn btn-danger">Remove</a>
            </div>
        </div>
    </div>
</div>
<div class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  src="@/assets/img/blog-details/1.jpg" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            <div class="card-block px-3">
                <h4 class="card-title">Name</h4>
                <p class="card-text"><b>Upload Id : </b>xxxx</p>
                <p class="card-text"><b>Added on : </b>xxxx</p>
                <p class="card-text"><b>Category : </b>xxxx</p>
                <a href="#" class="btn btn-danger">Remove</a>
            </div>
        </div>
    </div>
</div>   
<div class="card">
    <div class="row ">
        <div class="col-md-4">
            <img width="300" height="180"  src="@/assets/img/blog-details/1.jpg" class="w-100">
        </div>
        <div class="col-md-8 px-3">
            <div class="card-block px-3">
                <h4 class="card-title">Name</h4>
                <p class="card-text"><b>Upload Id : </b>xxxx</p>
                <p class="card-text"><b>Added on : </b>xxxx</p>
                <p class="card-text"><b>Category : </b>xxxx</p>
                <a href="#" class="btn btn-danger">Remove</a>
            </div>
        </div>
    </div>
</div>      
</div>
</template>

<script src="./js/multiVideo.js"></script>


<style>
.card-wrapper {
    padding:10px;
}
.card {
    border:1px solid black;
    margin:10px;
}
</style>
