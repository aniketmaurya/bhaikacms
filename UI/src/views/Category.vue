<template>
    <div class="container">
        <div class="add-container">
        <div @click="dropAddOptions()">Add Category</div>
        <div v-if="this.dropAdd">
            <div class="input-container">
                  <label class="input-label">Parent Name: </label>
                  <input type="text" class="input-field" v-model="addCategory.parentName" />
            </div>
            <div class="input-container"> 
                  <label class="input-label">Category Name : </label>
                  <input type="text" class="input-field" v-model="addCategory.categoryName" pattern="[A-za-z0-9\ ]"/>
            </div>
            <div class="input-container">
                  <button class="btn" @click="submit">Add</button>
            </div>
        </div>
        <div @click="dropDeleteOptions()">Delete Category</div>
        <div v-if="this.dropDelete">
            <div class="input-container">
                <label class="input-label">Category Name: </label>
                <input type="text" class="input-field" v-model="addCategory.parentName" />
            </div>
            <div class="input-container">
                <button class="btn" @click="deleteCategory">Delete</button>
            </div>
        </div>
        <div @click="dropEditOptions()">Edit Category</div>
        <div v-if="this.dropEdit">
            <div class="input-container">
                <label class="input-label">Category Name: </label>
                <input type="text" class="input-field" v-model="addCategory.parentName" />
            </div>
            <div class="input-container">
                <button class="btn" @click="editCategory">Edit</button>
            </div>
        </div>
        </div>
        <div class="category-component">
            <CategoryChild 
            :node='getCategories'
            @click="nodeWasClicked" />
        </div>
    </div>
</template>
<script src='./js/category.js'></script>
<style >
.category-component{
  display:flex;
  position:absolute;
  border: 1px solid black;
  min-height:100%;
  min-width:250px;
  color:black;
  font-weight: bold;
}
.add-container{
  display:block;
  position:absolute;
  margin-left:350px;
  text-align:right;
}
.container{
  display:flex;
  flex-wrap:wrap;
  flex-direction: row;
  justify-content: space-between;
}
.add-container .input-label{
  color:black;
  font-weight:bold;
}
.add-container .input-field{
  padding:5px;
  font-size: 12px;
  width:200px;
  border-radius:5px;
}
.add-container .btn{
  padding:10px;
  border-radius:5px;
  background-color:dodgerblue;
  color:white;
  font-size: 12px;
  font-weight: bold;
}
.add-container .input-container{
  padding:10px;
}
</style>