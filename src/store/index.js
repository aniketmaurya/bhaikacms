import Vue from 'vue'
import Vuex from 'vuex'
import vueResource from 'vue-resource'

// import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)
Vue.use(vueResource)

export default new Vuex.Store({
  state: {
    allAudits:{},
    program: {},
    userDetails: {
      status: false,
    },
    season: {},
    episode: {},
    userList: {},
    singleVideos: {},
    multiVideos: {},
    seasonalVideos: {},
    seasons:{},
    episodes:{}
  },
  
  mutations: {
    setAllAudits(state,payload) {
      state.allAudits = payload
    },
    setProgram(state,payload) {
      state.program = payload
    },
    setSeason(state,payload) {
      state.season = payload
    },
    setEpisode(state,payload) {
      state.episode = payload
    },
    setUserList(state,payload) {
      state.userList = payload
    },
    setSingleVideos(state,payload) {
      state.singleVideos = payload
    },
    setMultiVideos(state,payload) {
      state.singleVideos = payload
    },
    setSeasonalVideos(state,payload) {
      state.singleVideos = payload
    },
    setSeasons(state,payload) {
      state.seasons = payload
    },
    setEpisodes(state,payload) {
      state.episodes = payload
    }
  },

  actions: {
   
    signIn: ({commit},payload) => {
      return new Promise( (resolve,reject) => {
        let header = {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        };
        Vue.http.post("http://172.16.20.78:8080/useradmin/authenticate",JSON.stringify(payload),
        {headers : header}).then( (resp) => {
        resolve(resp.body)
        }).catch( (err) => {
        console.log(err)
        reject(false)
      })
      })
    },
    logout : ({commit},payload) => {
      return new Promise( (resolve,reject) => {
        Vue.http.post("http://172.16.20.78:8080/useradmin/logout",JSON.stringify(payload)).then( (resp) => {
        resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
      })
      }) 
    },
    getAllAudits : ({commit},payload) => {
      let header = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      };
      Vue.http.post("http://172.16.20.83:8082/audit/getAudits",JSON.stringify(payload),
      {headers : header}).then( (resp) => {
      commit('setAllAudits',resp.body)
    }).catch( (err) => {
      console.log(err)
    })
    },
    addProgram : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.put("http://172.16.20.95:8081/metadata/addProgram",JSON.stringify(payload)).then( (resp) => {
          commit('setProgram',resp.body)
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    addSingleVideo : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.put("http://172.16.20.95:8081/metadata/addSingleVideo",JSON.stringify(payload)).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    addSeason : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.put("http://172.16.20.95:8081/metadata/addSeason",JSON.stringify(payload)).then( (resp) => {
          commit('setSeason',resp.body)
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    addEpisodes : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.put("http://172.16.20.95:8081/metadata/addEpisodes",JSON.stringify(payload)).then( (resp) => {
          commit('setEpisode',resp.body)
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    getUserList : ({commit},payload) => {
      Vue.http.get("http://172.16.20.78:8080/useradmin/getAllUsers?pageNumber="+payload.pageNumber+"&pageSize="+payload.pageSize).then( (resp) => {
        commit('setUserList',resp.body)
      }).catch( (err) => {
      console.log(err)
      reject(false)
    })
    },

    addUser : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.post("http://172.16.20.78:8080/useradmin/addUserAdmin",JSON.stringify(payload)).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      }) 
    },
    deleteUser : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.delete("http://172.16.20.78:8080/useradmin/userDeleteByDetails?idDelete="+payload.idDelete+"&id="+payload.id).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    imageUpload: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.post("http://172.16.20.83:8082/image/uploadImage",payload).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(err)
        })
      })
    },
    getSingleVideoPrograms: ({commit},payload) => {
      Vue.http.get("http://172.16.20.95:8081/metadata/getAllSingleVideoProgram?pageSize="+payload.pageSize+"&pageNumber="+payload.pageNumber).then( (resp) => {
        console.log(resp)
        debugger
        commit('setSingleVideos',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    getMultiVideoPrograms: ({commit},payload) => {
      Vue.http.get("http://172.16.20.95:8081/metadata/getAllMultiVideoProgram?pageSize="+payload.pageSize+"&pageNumber="+payload.pageNumber).then( (resp) => {
        console.log(resp)
        debugger
        commit('setMultiVideos',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    getSeasonalVideoPrograms: ({commit},payload) => {
      Vue.http.get("http://172.16.20.95:8081/metadata/getAllSeasonalVideoProgram?pageSize="+payload.pageSize+"&pageNumber="+payload.pageNumber).then( (resp) => {
        console.log(resp.body.content)
        debugger
        commit('setSeasonalVideos',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    getSeasonByProgram: ({commit},payload) => {
      Vue.http.get("http://172.16.20.95:8081/metadata/getSeasonsByProgramId?programId="+payload.programId+"&pageNumber="+payload.pageNumber+"&pageSize="+payload.pageSize).then( (resp) => {
        commit('setSeasons',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    getEpisodesBySeason: ({commit},payload) => {
      Vue.http.get("http://172.16.20.95:8081/metadata/getEpisodesBySeasonId?seasonId="+payload.seasonId+"&pageNumber="+payload.pageNumber+"&pageSize="+payload.pageSize).then( (resp) => {
        console.log(resp)
        debugger
        commit('setEpisodes',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },

    //Testing Phase
    searchInVideo : ({commit},payload) => { 
      Vue.http.get("http://172.16.20.101:8080/solrSearch/search?searchTerm=ipl&pageNumber=0&pageSize=5").then( (resp) => {
          console.log(resp.body)
        }).catch( (err) => {
          console.log(err)
        })
    },  
    deleteProgramById: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.delete("http://172.16.20.95:8081/metadata/deleteProgramById",JSON.stringify(payload)).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    deleteEpisodeById: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        console.log(payload)
        Vue.http.delete("http://172.16.20.95:8081/metadata/deleteEpisodeById",JSON.stringify(payload)).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    }, 
    
  },
  getters: {
    allAudits (state) {
      return state.allAudits
    },
    episode (state) {
      return state.episode
    },
    isLoggedIn (state) {
      return state.userDetails.status
    },
    program (state) {
      return state.program
    },
    season (state) {
      return state.season
    },
    singleVideos(state) {
      return state.singleVideos
    },
    userList(state) {
      return state.userList
    },
    multiVideos(state) {
      return state.multiVideos
    },
    seasonalVideos(state) {
      return state.singleVideos
    },
    seasons(state) {
      return state.seasons
    },
    episodes(state) {
      return state.episodes
    }
  }
})
