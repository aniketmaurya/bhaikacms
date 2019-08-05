import { mapActions } from 'vuex';
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
			'signIn'
		]),
		handleSubmit() {
           this.signIn(this.user).then( (resp) => {
			    if (resp.login==true) {
				    this.$swal('',resp.message,'success')
					this.$session.set('userId',resp.userId)
					this.$session.set('roleId',resp.roleId)
					this.$store.commit('setRoleId',resp.roleId)
					localStorage.setItem('isLoggedIn', 'true')
				    this.$session.set('token',resp.token)
					 this.$router.push('/'),
					 this.$store.commit('setUsetDetails', {
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