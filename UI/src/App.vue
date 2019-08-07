<template>
<div id="app">
    <div class="fluid-container">
        <div class="row">
            <div style="padding:0px;" class="col-sm-2">
               <SideBar></SideBar>
            </div>
            <div style="padding:0px;" class="col-sm-10">
                <router-view/>
            </div> 
        </div>
    </div>
</div>
</template>

<script>
import SideBar from '@/components/SideBar.vue'
import { mapGetters,mapActions } from 'vuex';

export default {
  name:'App',
  computed: {
      ...mapGetters([
          'isLoggedIn',
          'getCategories'
      ])
  },
  components:{
      SideBar,   
  },
  methods: {
      ...mapActions(['fetchCategoriesAction','calculateStack'])
  },
  created() {
      this.fetchCategoriesAction()
      this.calculateStack(this.getCategories)
  }
  
}
</script>



<style>
html, body {
    margin: 0px;
    padding: 0px;
}
</style>
