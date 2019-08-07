<template>
   <div class="tree"> 
    <div
     @click="nodeClicked()"
     :style="{'margin-left':`${depth*20}px`}"
     class="nodes" 
     :key="node.id">
        <div>
            <span class="type" v-if="hasChildren">{{expanded ? '-' :'+'}}</span>
            <span v-else></span>
            {{node.name}}
        </div>
     </div>
    <CategoryChild v-for="child in node.subCategories"
    :key="child.name" :node="child" :depth="depth+1" @click="(node) => $emit('click',node)" v-if="expanded" />
   </div> 
</template>
<script src="./js/categorychild.js"></script>
<style>
.nodes {
    margin-left:0px;
    margin-right:0px;
    margin-top:0px;
    text-align: left;
}
</style>