export default {   
    data() {
        return {
            selected:'',
            link:'',
            clicked:false,
            form: ''
        }
    },
    methods: {
        changeSelected(value,link){
            this.selected=value;
            this.link=link;
            this.clicked=true;
            document.getElementById("input").value='';
        },
        getFile(event){
                this.form=new FormData();
                this.form.append('file', event.target.files[0]);
        },
        //BULK UPLOAD CHECK RESPONSE AND GIVE PROPER MESSAGE
        uploadFile(){
            window.console.log(this.form)
            window.console.log("Link:: "+this.link)
            this.$http.post("http://172.16.20.43:8082/metadata/"+this.link,this.form)
            .then(response => response.json())
            .then(response => {
                window.console.log(response)
                //debugger   
                if(response){
                   alert("Uploaded"+response.body)
               }
            })
        }
    }
}
