<template>
    <!-- <div class="container"> -->
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2>Audit <b>Details</b></h2></div>
                    <div class="col-sm-4">
                        <div class="search-box">
                            <input type="text" class="form-control" placeholder="Search here">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                                <tr>
                                    
                                    <th>Content Id <i class="fa fa-sort"></i></th>
                                    <th>Content Name</th>
                                    <th>Action <i class="fa fa-sort"></i></th>
                                    <th>Timestamp</th>
                                    <th>User Id <i class="fa fa-sort"></i></th>
                                    <th>Old Change</th>
                                    <th>New Change</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="audit in allAudits.content" :key="audit">
        
                                    <td>{{ audit.contentId }}</td>
                                    <td>{{ audit.contentName }}</td>
                                    <td>{{ audit.actionName }}</td>
                                    <td>{{ changeDate(audit.actionTime) }}</td>
                                    <td>{{ audit.actionBy }}</td>
                                    <td>{{ audit.oldContent }}</td>
                                    <td>{{ audit.newContent }}</td>
                                </tr>
                            </tbody>
                        </table>
                </div>    
            </div>
            <div class="clearfix">
                <!-- <div class="hint-text">Showing <b>{{ allAudits.size }}</b> out of <b>{{ allAudits.totalElements }}</b> entries</div> -->
                <ul class="pagination">
                    <button style="margin:10px;"  @click="prvPage()"  class="btn btn-primary">Previous page</button>
                    <button @click="nextPage()"  class="btn btn-primary">Next page</button>
                </ul>
            </div>
        </div>
    <!-- </div> -->
</template>

<script src="./js/audit.js"></script>

<style type="text/css">
    .table-wrapper {
        background: #fff;
        padding:10px;
        margin: 0px 0;
        width:auto;
        height:100vh;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    }
    .table-title {
        padding-bottom: 10px;
        margin: 0 0 10px;
    }
    .table-title h2 {
        margin: 8px 0 0;
        font-size: 22px;
    }
    .search-box {
        position: relative;        
        float: right;
    }
    .search-box input {
        height: 34px;
        border-radius: 20px;
        padding-left: 30px;
        border-color: #ddd;
        box-shadow: none;
    }
    .search-box input:focus {
        border-color: #3FBAE4;
    }
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
    }
    table.table-striped tbody tr:nth-of-type(odd) {
        background-color: #fcfcfc;
    }
    table.table-striped.table-hover tbody tr:hover {
        background: #f5f5f5;
    }
    table.table th i {
        font-size: 13px;
        margin: 0 5px;
        cursor: pointer;
    }
    table.table td:last-child {
        width: 130px;
    }
    table.table td a {
        color: #a0a5b1;
        display: inline-block;
        margin: 0 5px;
    }
    table.table td a.view {
        color: #03A9F4;
    }
    table.table td a.edit {
        color: #FFC107;
    }
    table.table td a.delete {
        color: #E34724;
    }
    table.table td i {
        font-size: 19px;
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
</style>
