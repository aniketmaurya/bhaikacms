// import CategoryChild from './views/CategoryChild'
// import {mapGetters ,mapActions} from 'vuex'

// export default {
//     name:'Category',
//     data(){
//         return{
//             dropAdd:false,
//             dropDelete:false,
//             dropEdit:false,
//             addCategory:{
//               categoryId:"",
//               parentName:"",
//               categoryName:"",
//             },
//         }
//     },
//     components:{
//         CategoryChild,
//     },
//     computed:{
//         ...mapGetters(['getCategories'])
//     },
//     methods:{
//         ...mapActions(['fetchCategoriesAction']),
//         refreshPage(){
//             this.fetchCategoriesAction()
//         },
//         nodeWasClicked(nodeChild){
//             window.console.log(nodeChild.name)
//             this.addCategory.categoryName=""
//             if(nodeChild.name==="Categories"){
//             this.addCategory.parentName=""
//             }
//             else{
//             this.addCategory.categoryId=nodeChild.id
//             this.addCategory.parentName=nodeChild.name
//             }
//           },
//           submit(){
//             window.console.log(this.addCategory)
//             this.$http.post("http://10.177.69.5:8083/admin/addCategory?categoryName="+this.addCategory.categoryName+"&parentName="+this.addCategory.parentName)
//             .then(response => response.json())
//             .then(response => {
//               if(response.categoryName===this.addCategory.categoryName){
//                 this.refreshPage()
//                 alert("successfully added")
//               }
//             })
//             .catch(function(response){
//               alert('Invalid data')
//             })
//           },
//           deleteCategory(){
//             window.console.log(this.addCategory.categoryId)
//             this.$http.delete("http://10.177.69.5:8083/admin/deleteCategory?categoryId="+this.addCategory.categoryId)
//             .then(response => response.json())
//             .then(response => {
//               if(response){
//                 this.refreshPage()
//                 alert("Category Deleted")
//               }
//             })
//           },
//           editCategory(){
//             window.console.log(this.addCategory.parentName)
//             window.console.log(this.addCategory.newName)
//             this.$http.put("http://10.177.69.5:8083/admin/updateCategory?categoryName="+this.addCategory.parentName+"&newName="+this.addCategory.newName)
//             .then(response => response.json())
//             .then(response => {
//               if(response.categoryName===this.addCategory.newName){
//                 alert("updated Successfully")
//                 this.refreshPage()
//               }
//             })
//           },
//           dropAddOptions(){
//             this.dropAdd=!this.dropAdd
//             this.dropEdit=false
//             this.dropDelete=false
//           },
//           dropDeleteOptions(){
//             this.dropDelete=!this.dropDelete
//             this.dropAdd=false
//             this.dropEdit=false      
//           },
//           dropEditOptions(){
//             this.dropEdit=!this.dropEdit
//             this.dropAdd=false
//             this.dropDelete=false
//           }
//     },
//     mounted(){
//         this.fetchCategoriesAction()
//     }
// }