import { mapActions, mapGetters } from 'vuex';
export default {
	name:'Login',
	data() {
		return {
			user: {
				email:"",
				password:""
			}
		}
	},
	methods: {
		...mapActions([
			'signIn',
		]),
		handleSubmit() {
           this.signIn(this.user).then( (resp) => {
			    if (resp.login==true) {
				    // this.$swal('',resp.message,'success')
					this.$session.set('userId',resp.userId)
					this.$session.set('roleId',resp.roleId)
					this.$session.set('email',resp.email)
					localStorage.setItem('isLoggedIn', 'true')
					this.$session.set('token',resp.token)
					localStorage.setItem('token',resp.token)
					this.$store.commit('setUsetDetails', {
						status:true,
						...resp
					})
					this.$router.push('/')
			    } else {
				    this.$swal(resp.message)
				}

		    }).catch( (err) => {
			    console.log(err)
			})
		}
	}
	
}