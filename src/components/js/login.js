import { mapActions } from 'vuex';
export default {
	name:'Login',
	data() {
		return {
			user: {
				name:"",
				email:"",
				password:""
			}
		}
	},
	methods: {
		...mapActions([
			'signIn'
		]),
		handleSubmit() {
           this.signIn(this.user).then( (resp) => {
			    if (resp.login==true) {
				    this.$swal('',resp.message,'success')
					this.$session.set('userId',resp.userId)
					localStorage.setItem('isLoggedIn', 'true')
				    this.$session.set('token',resp.token)
					 this.$router.push('/'),
					 this.$stoe.commit('setUsetDetails', {
						status: true,
						...res.body.data
					 })
			    } else {
				    this.$swal(resp.message)
				}

		    }).catch( (err) => {
			    console.log(err)
			})
		}
	}
	
}