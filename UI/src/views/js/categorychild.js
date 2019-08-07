export default {
    name:"CategoryChild",
    props:{
        node:Object,
        depth: {
            type:Number,
            default:0
        }
    },
    data(){
        return{
            expanded:false
        }
    },
    methods:{
        nodeClicked(){
            this.expanded=!this.expanded;
            window.console.log(this.node.name)
            this.$emit('click',this.node);
        }
    },
    computed:{
        hasChildren(){
            if(this.node.subCategories.length===0)
            {
                return false;
            }
            else{
                return true;
            }
        }
    }
}