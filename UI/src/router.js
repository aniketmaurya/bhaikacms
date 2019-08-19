import Vue from 'vue'
import Router from 'vue-router'
import VueSession from 'vue-session'

import store from './store/index'

Vue.use(Router)
Vue.use(VueSession)

let router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('./views/Home.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('./views/About.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./components/Login.vue')
    },
    {
      path: '/audit',
      name: 'audit',
      component: () => import('./views/Audit.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/singleVideo',
      name: 'singleVideo',
      component: () => import('./views/SingleVideo.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/seasonalVideo',
      name: 'seasonalVideo',
      component: () => import('./views/SeasonalVideo.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/multiVideo',
      name: 'multiVideo',
      component: () => import('./views/MultiVideo.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/logout',
      name: 'logout',
      component: () => import('./views/Logout.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/program/:name',
      name: 'program',
      component: () => import('./views/Program.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/singleVideoForm',
      name: 'singleVideoForm',
      component: () => import('./views/forms/SingleVideoForm.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/multiVideoForm',
      name: 'multiVideoForm',
      component: () => import('./views/forms/MultiVideoForm.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/seasonalVideoForm',
      name: 'seasonalVideoForm',
      component: () => import('./views/forms/SeasonalVideoForm.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/episodeForm',
      name: 'episodeForm',
      component: () => import('./views/forms/EpisodeForm.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/addUser',
      name: 'addUser',
      component: () => import('./views/forms/AddUser.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/userList',
      name: 'userList',
      component: () => import('./views/UserList.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/seasons/:id',
      name: 'seasons',
      component: () => import('./views/Seasons.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/episodes/:id',
      name: 'episodes',
      component: () => import('./views/Episodes.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/addLanguage',
      name: 'addLanguage',
      component: () => import('./views/Language.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/addCrew',
      name: 'addCrew',
      component: () => import('./views/Crew.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/addCategory',
      name: 'addCategory',
      component: () => import('./views/Category.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/bulkUpload',
      name: 'bulkUpload',
      component: () => import('./views/BulkUpload.vue'),
      meta: {
        authentication: 'required'
      }
    },
    {
      path: '/search',
     name: 'search',
     component: () => import('./views/search.vue'),
     meta: {
       authentication: 'required'
     }
    }


  ]
})

router.beforeEach((to, from, next) => {
  let isLoggedIn = JSON.parse(localStorage.getItem('isLoggedIn'))
  if (authenticationCheck(to) && isLoggedIn) {
    // console.log(' @@ MY SESSION (IF)= ', isLoggedIn)
    next();
  } else if (authenticationCheck(to) && !isLoggedIn){
    // console.log(' @@ MY SESSION (ELSE IF)= ', isLoggedIn)
    next({name: 'login'});
  } else {
    // console.log(' @@ MY SESSION (ELSE)= ', isLoggedIn)
    next()
  }
})

function authenticationCheck (route) {
  let flag = false

  if (route.meta.authentication === 'required') {
    flag = true
  } else {
    flag = false
  }

  return flag
}

export default router