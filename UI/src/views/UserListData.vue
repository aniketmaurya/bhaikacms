<template>
    <!-- <tr @focusout="toggleEditState"> -->
    <tr>
        <td>
            
            <div v-if="!this.editState">
                <span class='text' @dblclick="toggleEditState(true)">{{data.name}}</span>
            </div>
            <div v-else>
                <input v-model="userName" class="input"/>
                <button @click="disableEditing"> Cancel </button>
                <button @click="saveEdit()"> Save </button>
            </div>
        </td>
        <!-- <td><div class="editable-text">{{ data.name }}</div></td> -->
        <td>{{ this.data.email }}</td>
        <td>
            <div v-if="!this.editState">
                <span class='text' @dblclick="toggleEditState(true)">{{ chanegRoleName(this.data.roleId) }}</span>
            </div>
            <div v-else>
                <select v-model="role">
                    <option :value="this.role" selected>{{chanegRoleName(this.role)}}</option>
                    <option :value="toggleRoleId(this.role)">{{ toggleRoleName(this.role) }}</option>
                </select>
                
            </div>
        </td>
        <td>{{ changeIsActive(data.active) }}</td>
        <td v-if="this.data.active==1"><button @click="handleDelete" class="btn btn-danger">Deactivate</button></td>
        <td v-else><button @click="handleActivate" class="btn btn-success">Activate</button></td>
    </tr>
</template>
<script src='./js/userListData.js'></script>
<style>
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
</style>
