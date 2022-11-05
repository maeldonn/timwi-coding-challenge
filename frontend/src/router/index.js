import Vue from 'vue';
import VueRouter from 'vue-router';
import Search from '@/views/Search';
import Management from '@/views/Management';

Vue.use(VueRouter);

const routes = [{
    path: '/', name: 'Search', component: Search
}, {
    path: '/management', name: 'Management', component: Management,
}, {
    path: '*', name: '404', component: Search,
}]

const router = new VueRouter({
    routes
});

export default router;