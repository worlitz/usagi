import Vue from "vue";
import VueRouter from "vue-router";
import Navigater from "../views/Navigater.vue";
import Stars from "../views/Stars.vue";
import Grade from "../views/Grade.vue";
import Search from "../views/Search.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Navigater",
    component: Navigater,
    children: [
      { path: "search", component: Search },
      { path: "stars", component: Stars },
      { path: "grade", component: Grade }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;
