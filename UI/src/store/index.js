import Vue from 'vue'
import Vuex from 'vuex'
import vueResource from 'vue-resource'
import { stat } from 'fs';

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
    episodes:{},
    userCount:0,
    adminCount:0,
    programCount:0,
    // VK
    userListEdit:{
      editable: false,
    },
    languages:{},
    crewRoles:{},
    categories:{
      name:"Categories",
      id:"category",
      subCategories:[],
    },
    stackOfCategories:[],
    // nameMap:{
    //   name="",
    //   id=""
    // }
    searchResult:{}
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
      state.multiVideos = payload
    },
    setSeasonalVideos(state,payload) {
      state.seasonalVideos = payload
    },
    setSeasons(state,payload) {
      state.seasons = payload
    },
    setEpisodes(state,payload) {
      state.episodes = payload
    },
    setRoleId(state,payload) {
      state.roleId = payload
    },
    setUsetDetails(state,payload) {
      state.userDetails = payload
    },
    setLanguages(state,payload) {
      state.languages = payload
    },
    // VK
    setUserListEdit(state,payload){
      state.userList = payload
    },
    setUserCount(state,payload) {
      state.userCount = payload
    },
    setAdminCount(state,payload) {
      state.adminCount = payload
    },
    setCrewRoles(state,payload) {
      state.crewRoles = payload
    },
    setCategories(state,payload){
      state.categories.subCategories=payload
    },
    setStackOfCategories(state,nodes){
      state.stackOfCategories.push(nodes)
    },
    setProgramCount(state,payload){
      console.log(payload)
      state.programCount=payload
    },
    setSearchResult(state,payload){
      state.searchResult = payload
    }
  },

  actions: {

    /*---------------------------User Service--------------------------------*/
    signIn: ({commit},payload) => {
      return new Promise( (resolve,reject) => {
        let header = {
          'Content-Type':'application/json',
          'Accept': 'application/json',
        };
        Vue.http.post("http://10.177.7.38:8084/useradmin/authenticate",JSON.stringify(payload),
        {headers : header}).then( (resp) => {
          console.log(resp)
          resolve(resp.body)
        }).catch( (err) => {
        console.log(err)
        reject(false)
      })
      })
    },
    getUserList : ({commit},payload) => {
      Vue.http.get("http://10.177.7.38:8084/useradmin/getAllUsers?pageNumber="+payload.pageNumber+"&pageSize="+payload.pageSize+"&sortBy="+payload.sortBy+"&order="+payload.order).then( (resp) => {
        commit('setUserList',resp.body)
      }).catch( (err) => {
      console.log(err)
    })
    },
    searchUser : ({commit},payload) => {
      Vue.http.get("http://10.177.7.38:8084/useradmin/searchUser?input="+payload.searchText+"&pageNumber="+payload.pageNumber+"&pageSize="+payload.pageSize+"&sortBy="+payload.sortBy+"&order="+payload.order).then( (resp) => {
        console.log(resp.body)
        commit('setUserList',resp.body)
      }).catch( (err) => {
      console.log(err)
      })
    },
    addUser : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.post("http://10.177.7.38:8084/useradmin/addUserAdmin",JSON.stringify(payload)).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      }) 
    },
    deleteUser : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.delete("http://10.177.7.38:8084/useradmin/userDeleteByDetails?idDelete="+payload.idDelete+"&id="+payload.id).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    logout : ({commit},payload) => {
      return new Promise( (resolve,reject) => {
        console.log(payload)
        Vue.http.post("http://10.177.7.38:8084/useradmin/logout?token="+payload.token).then( (resp) => {
        resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
      })
      }) 
    },
    getCount: ({commit}) => {
      Vue.http.get("http://10.177.7.38:8084/useradmin/countUser?roleId=1").then( (resp) => {
        commit('setUserCount',resp.body)
        Vue.http.get("http://10.177.7.38:8084/useradmin/countUser?roleId=0").then( (resp) => {
          commit('setAdminCount',resp.body)
          Vue.http.get("http://10.177.7.38:8084/metadata/count").then( (resp) => {
            console.log(resp.body)
            commit('setProgramCount',resp.body)
          }).catch( (err) => {
             console.log(err)
          })
          }).catch( (err) => {
            console.log(err)
            reject(false)
          }) 
      }).catch( (err) => {
      console.log(err)
      reject(false)
    })
    },

    /*---------------------------Metadata Service--------------------------------*/
    addProgram : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.put("http://10.177.7.38:8081/metadata/addProgram",JSON.stringify(payload)).then( (resp) => {
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
        Vue.http.put("http://10.177.7.38:8081/metadata/addSingleVideo",JSON.stringify(payload)).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    addSeason : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.put("http://10.177.7.38:8081/metadata/addSeason",JSON.stringify(payload)).then( (resp) => {
          commit('setSeason',resp.body)
          console.log(resp.body)
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    addEpisodes : ({commit},payload) => {
      return new Promise ((resolve,reject) => {
        Vue.http.put("http://10.177.7.38:8081/metadata/addEpisodes",JSON.stringify(payload)).then( (resp) => {
          commit('setEpisode',resp.body)
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    getSingleVideoPrograms: ({commit},payload) => {
      Vue.http.get("http://10.177.7.38:8081/metadata/getAllSingleVideoProgram?pageSize="+payload.pageSize+"&pageNumber="+payload.pageNumber).then( (resp) => {
        console.log(resp)
        commit('setSingleVideos',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    getMultiVideoPrograms: ({commit},payload) => {
      Vue.http.get("http://10.177.7.38:8081/metadata/getAllMultiVideoProgram?pageSize="+payload.pageSize+"&pageNumber="+payload.pageNumber).then( (resp) => {
        commit('setMultiVideos',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    getSeasonalVideoPrograms: ({commit},payload) => {
      Vue.http.get("http://10.177.7.38:8081/metadata/getAllSeasonalVideoProgram?pageSize="+payload.pageSize+"&pageNumber="+payload.pageNumber).then( (resp) => {
        commit('setSeasonalVideos',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    getSeasonByProgram: ({commit},payload) => {
      Vue.http.get("http://10.177.7.38:8081/metadata/getSeasonsByProgramId?programId="+payload.programId+"&pageNumber="+payload.pageNumber+"&pageSize="+payload.pageSize).then( (resp) => {
        console.log(resp.body)
        commit('setSeasons',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    getEpisodesBySeason: ({commit},payload) => {
      Vue.http.get("http://10.177.7.38:8081/metadata/getEpisodesBySeasonId?seasonId="+payload.seasonId+"&pageNumber="+payload.pageNumber+"&pageSize="+payload.pageSize).then( (resp) => {
        console.log(resp)
        commit('setEpisodes',resp.body)
      }).catch( (err) => {
        console.log(err)
      })
    },
    deleteProgramById: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.post("http://10.177.7.38:8081/metadata/deleteProgramById",JSON.stringify(payload)).then( (resp) => {
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
        Vue.http.post("http://10.177.7.38:8081/metadata/deleteEpisodeById",JSON.stringify(payload)).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    deleteSeasonById: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        console.log(payload)
        Vue.http.post("http://10.177.7.38:8081/metadata/deleteSeasonById",JSON.stringify(payload)).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    //Language
    addLanguage: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.post("http://10.177.7.38:8081/admin/addLanguage",JSON.stringify(payload)).then( (resp) => {
          // console.log(resp.body)
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    getLanguages: ({commit}) => {
      Vue.http.get("http://10.177.7.38:8081/admin/getAllLanguages").then((resp) => {
          commit('setLanguages',resp.body)
      }).catch((err) => {
          console.log(err)
      })
    },
    deleteLanguageByName: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.delete("http://10.177.7.38:8081/admin/deleteLanguageByName?languageName="+payload.name).then((resp) => {
          console.log(resp)
          resolve(resp.body)
        }).catch((err) => {
          console.log(err)
          reject(resp.body)
        })
      })
    },
    editLanguage: (payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.put("http://10.177.7.38:8081/admin/editLanguage?newName="+payload.name).then((resp) => {
          resolve(resp.body)
        }).catch((err) => {
          console.log(err)
          reject(resp.body)
        })
      })
    },
    //CREW ROLES
    getCrewRoles: ({commit}) => {
      Vue.http.get("http://10.177.7.38:8081/admin/getAllCrewRoles").then((resp) => {
          commit('setCrewRoles',resp.body)
      }).catch((err) => {
          console.log(err)
      })
    },
    
    addCrewRoles: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.put("http://10.177.7.38:8081/admin/addCrew",JSON.stringify(payload)).then( (resp) => {
          // console.log(resp.body)
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(false)
        })
      })
    },
    deleteCrewRoles: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.delete("http://10.177.7.38:8081/admin/deleteCrewRole?crewId="+payload.id).then((resp) => {
          resolve(resp.body)
        }).catch((err) => {
          console.log(err)
          reject(resp.body)
        })
      })
    },
    

    

    /*---------------------------Audit Service--------------------------------*/
    getAllAudits : ({commit},payload) => {
      let header = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      };
      Vue.http.post("http://10.177.7.38:8082/audit/getAudits",JSON.stringify(payload),
      {headers : header}).then( (resp) => {
      commit('setAllAudits',resp.body)
    }).catch( (err) => {
      console.log(err)
    })
    },
    getReport: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.post("http://10.177.7.38:8082/audit/getReport?start="+payload.startDate+"&end="+payload.endDate).then( (resp) => {
        
        var fileName = "myDefaultFileName.pdf";

        //replace leading and trailing slashes that C# added to your file name
        // fileName = fileName.replace(/\"/g, "");

        //determine the content type from the header or default to octect stream
        var contentType = resp.headers["content-type"];

    //finally, download it
    try {
        var blob = new Blob([resp.body], {type: contentType});

        //downloading the file depends on the browser
        //IE handles it differently than chrome/webkit
        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
            window.navigator.msSaveOrOpenBlob(blob, fileName);
        } else {
            var objectUrl = URL.createObjectURL(blob);
            window.open(objectUrl);
        }
    } catch (exc) {
        console.log("Save Blob method failed with the following exception.");
        console.log(exc);
    }

           
           console.log(resp)
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(err)
        })
      })
    },

    /*---------------------------Other Services--------------------------------*/
    imageUpload: ({commit},payload) => {
      return new Promise((resolve,reject) => {
        console.log(payload.get('file'))
        Vue.http.post("http://10.177.7.38:8082/image/upload",payload).then( (resp) => {
          resolve(resp.body)
        }).catch( (err) => {
          console.log(err)
          reject(err)
        })
      })
    },
    



    //Testing Phase
    searchVideo : ({commit},payload) => { 
      return new Promise((resolve,reject) => {
        Vue.http.get("http://10.177.7.38:8084/solrSearch/search?searchTerm="+payload.searchText+"&pageNumber=0&pageSize=5&videoType="+payload.videoType).then( (resp) => {
          commit('setMultiVideos',resp.body)
          console.log(resp.body)
        }).catch( (err) => {
          console.log(err)
        })
      })
    }, 
    
    searchInVideo : ({commit},payload) => {
      console.log('payload', payload.data)
      return new Promise((resolve,reject) => {
        Vue.http.get("http://10.177.7.47:8080/solrSearch/search?searchTerm="+payload.data.searchTerm)
        .then( (resp) => {
          commit('setSearchResult',resp.body)
          console.log("it is a dummy    ",resp.body)
        }).catch( (err) => {
          console.log(err)
        })
      })
    },
 

    searchInSingleVideo : ({commit},payload) => {
      return new Promise((resolve,reject) => {
        Vue.http.get("http://10.177.7.38:8084/solrSearch/search?searchTerm="+payload.searchText+"&pageNumber=0&pageSize=5&videoType="+payload.videoType).then( (resp) => {
          commit('setSingleVideos',resp.body)
          console.log(resp.body)
        }).catch( (err) => {
          console.log(err)
        })
      })
    },


    //Categories
    fetchCategoriesAction({commit,dispatch}){
      Vue.http.get("http://10.177.7.38:8081/admin/getCompleteTree")
      .then(response => response.json())
      .then(response => {
        commit('setCategories',response)
        dispatch('createList',response)
      })
    },
    createList({commit,dispatch,state},nodes){
      for(let i=0;i<nodes.length;i++){
        commit('setStackOfCategories',{name:nodes[i].name,id:nodes[i].id})
        if(nodes[i].subCategories&& nodes[i].subCategories.length>0){
          dispatch('createList',nodes[i].subCategories)
        }
      }
  }

    
  },
  getters: {
    allAudits (state) {
      return state.allAudits
    },
    adminCount (state) {
      return state.adminCount
    },
    episode (state) {
      return state.episode
    },
    languages (state) {
      return state.languages
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
      return state.seasonalVideos
    },
    seasons(state) {
      return state.seasons
    },
    episodes(state) {
      return state.episodes
    },
    userDetails(state) {
      return state.userDetails
    },
    userListEdit(state) {
      return state.userListEdit
    },
    userCount(state) {
      return state.userCount
    },
    crewRoles(state) {
      return state.crewRoles
    },
    getCategories(state){
      return state.categories
    },
    getStackOfCategories(state){
      return state.stackOfCategories
    },
    programCount(state) {
      return state.programCount
    },
    getSearchResult(state) {
      return state.searchResult
    }
  }
})
