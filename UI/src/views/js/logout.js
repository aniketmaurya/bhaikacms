import { mapActions } from 'vuex';
export default {
    name:'Logout',
    data() {
        return {
            user: {
                userId:"",
                token:""
            }
        }
    },
    methods: {
        ...mapActions([
          'logout'
        ]),
        init() {
            this.user.userId = this.$session.get('userId')
            this.user.token = this.$session.get('token')
            this.logout(this.user).then( (resp) => {
            this.$session.destroy()
            localStorage.setItem('isLoggedIn', 'false')
            this.$swal('','Successfully logout','success')
            this.$router.push('/login')

            }).catch( (err) => {
                console.log(err)
            })
        }

    },
    mounted() {
        this.init()
    }
    
}