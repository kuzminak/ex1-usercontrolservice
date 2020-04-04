let users = [];

function findUser(userId) {
    return users[findUserKey(userId)];
}

function findUserKey(userId) {
    for (let key = 0; key < users.length; key++) {
        if (users[key].id == userId) {
            return key;
        }
    }
}

let userservice = {
    findAll(fn) {
        axios
            .get('/api/users')
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    findById(id, fn) {
        axios
            .get('/api/users/' + id)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    create(User, fn) {
        axios
            .post('/api/users', User)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    update(id, User, fn) {
        axios
            .put('/api/users/' + id, User)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    deleteUser(id, fn) {
        axios
            .delete('/api/users/' + id)
            .then(response => fn(response))
            .catch(error => console.log(error))
    }
}

var List = Vue.extend({
    template: '#user-list',
    data: function () {
        return {users: [], searchKey: ''};
    },
    computed: {
        filteredusers() {
            return this.users;
        }
    },
    mounted() {
        userservice.findAll(r => {
            this.users = r.data;
            users = r.data
        })
    }
});

let User = Vue.extend({
    template: '#user',
    data: function () {
        return {user: findUser(this.$route.params.user_id)};
    }
});

let UserEdit = Vue.extend({
    template: '#user-edit',
    data: function () {
        return {user: findUser(this.$route.params.user_id)};
    },
    methods: {
        updateUser: function () {
            userservice.update(this.user.id, this.user, r => router.push('/'))
        }
    }
});

let UserDelete = Vue.extend({
    template: '#user-delete',
    data: function () {
        return {user: findUser(this.$route.params.user_id)};
    },
    methods: {
        deleteUser: function () {
            userservice.deleteUser(this.user.id, r => router.push('/'))
        }
    }
});

let AddUser = Vue.extend({
    template: '#add-user',
    data() {
        return {
            user: {username: '', password: '', familyName: '', givenName: '', description: '', address: ''}
        }
    },
    methods: {
        createUser() {
            userservice.create(this.user, r => router.push('/'))
        }
    }
});

let router = new VueRouter({
    routes: [
        {path: '/', component: List},
        {path: '/user/:user_id', component: User, name: 'user'},
        {path: '/add-user', component: AddUser},
        {path: '/user/:user_id/edit', component: UserEdit, name: 'user-edit'},
        {path: '/user/:user_id/delete', component: UserDelete, name: 'user-delete'}
    ]
});

new Vue({
    router
}).$mount('#app')
