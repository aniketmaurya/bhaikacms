<template>
    <!-- <div class="container"> -->
        <div class="table-wrapper">
            <div class="table-title">
                <div class="form-inline">
                    <div class="form-group">
                            From
                            <input v-model="startDate"  style="margin-right:20px;" class="form-control" type="date">
                    </div>
                        <div class="form-group">
                            To
                            <input  v-model="endDate"  class="form-control" type="date">
                        </div>
                    <div class="form-group">
                        <div class="pull-right">
                            <button @click="handleExport()" style="margin-left:10px;" class="btn btn-primary">Export to PDF</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th width="15%" @click="sort('asset')" class="hover">Asset <i class="fa fa-sort"></i></th>
                                    <th width="10%" @click="sort('action')" class="hover">Action <i class="fa fa-sort"></i></th>
                                    <th width="15%" @click="sort('actionTime')" class="hover">Timestamp <i class="fa fa-sort"></i></th>
                                    <th width="13%" @click="sort('modifier')" class="hover">Modifier <i class="fa fa-sort"></i></th>
                                    <th width="24%">Old Value</th>
                                    <th width="24%">New Value</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="audit in allAudits.content" :key="audit.auditId">
        
                                    <td width="15%">{{ audit.asset }}</td>
                                    <td width="10%">{{ audit.action }}</td>
                                    <td width="15%">{{ changeDate(audit.actionTime) }}</td>
                                    <td width="13%">{{ audit.modifier }}</td>
                                    <td width="24%">{{ audit.oldValue }}</td>
                                    <td width="24%">{{ audit.newValue }}</td>
                                </tr>
                            </tbody>
                        </table>
                </div>    
            </div>
            <div v-if="allAudits.totalElements!=0">
            <div class="clearfix">
                <div class="hint-text">Showing <b>{{((allAudits.size)*allAudits.number)+1}} - 
                    <span v-if="allAudits.number!=(allAudits.totalPages-1)">{{(allAudits.size*(allAudits.number+1))}}</span>
                    <span v-else>{{ allAudits.totalElements }}</span>
                    </b> out of <b>{{ allAudits.totalElements }}</b> entries</div>
                <!-- <div class="hint-text">Showing <b>{{ allAudits.size }}</b> out of <b>{{ allAudits.totalElements }}</b> entries</div> -->
                <ul class="pagination">
                    Page:
                    <input style="width:60px;" type="number" v-model="currentPage"/>
                    <button style="margin:10px;" class="btn btn-success" v-if="currentPage<=allAudits.totalPages" @click="renderPage()">Go</button>
                    <button style="margin:10px;" class="btn btn-danger" v-else>Go</button>

                    <button v-if="page.pageNumber!=0" @click="prvPage()"   class="btn btn-primary">Prev</button>
                    <button v-if="page.pageNumber!=(allAudits.totalPages-1)" @click="nextPage()" style="margin:10px;" class="btn btn-primary">Next</button>
                </ul>
            </div>
            </div>
            <!-- <div class="clearfix">
                <div class="hint-text">Showing <b>{{ allAudits.size }}</b> out of <b>{{ allAudits.totalElements }}</b> entries</div>
                <ul class="pagination">
                    <button style="margin:10px;"  @click="prvPage()"  class="btn btn-primary">Previous page</button>
                    <button @click="nextPage()"  class="btn btn-primary">Next page</button>
                </ul>
            </div> -->
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
    .hover:hover{
        cursor: pointer;
    }
    .pull-right {
        float: right;
    }
</style>