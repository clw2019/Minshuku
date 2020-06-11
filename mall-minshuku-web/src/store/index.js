import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    // 状态
    state: {
        token: '',
        userId: '',
        username: '',
        // 下单信息
        basicInfo: {},
        bookDate: [],
        orderPeople: '',
        // 修改房源的信息
        homeItem:{}
    },
    //   改变 state 唯一的方法
    mutations: {
        updateToken(state, payload) {
            return state.token = payload;
        },
        updateUserId(state, payload) {
            return state.userId = payload;
        },
        updateUsername(state, payload) {
            return state.username = payload;
        },
        updateBookDate(state, payload) {
            return state.bookDate = payload;
        },
        updateOrderPeople(state, payload) {
            return state.orderPeople = payload;
        },
        updateBasicInfo(state, payload) {
            return state.basicInfo = payload;
        },
        updateHomeItem(state, payload) {
            return state.homeItem = payload;
        },

    },
    // 异步操作
    actions: {
    },
    // 模块化状态管理
    modules: {
    }
})