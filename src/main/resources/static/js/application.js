 
var app_menu = new Vue({
    'el': '#app-menu',
    'data': {
        'selected': ''
    },
    'created': function () {
        console.log("Navbar Geldi");
    },
    'methods': {
        'home_onclick': function() {
            app.selected = 'home';
            app.fetchConfigs();
            console.log('home');
        },
        'add_onclick': function() {

            app.selected = 'add';
            console.log('add');

        },
        'delete_onclick': function() {
            app.selected = 'delete';
            console.log('delete');
        },
    }
});

var app = new Vue({
    el: '#app',
    data: {
        'configs': [],
        'selected': 'add',
        'selected_costtype': '',
        'selected_teamname': '',
        'selected_status': '',
        'selected_hour': '',
        'cost_types': [],
        'team_names': [],
        'status_types': [],
    },

    created: function () {
        console.log('Addd Alındı');
    },

    mounted(){
        this.fetchConfigs();
        this.fetchCostTypes();
        this.fetchTeamNames();
        this.fetchStatusTypes();
    },
    methods: {
        fetchConfigs(){
            console.log("Hosgeldin Sahip");
        },

        deleteConfig(){
            id = document.getElementById('id').value;
            console.log(id);
            axios.get("/delete?id="+id);
        },

        fetchCostTypes() {
            axios.get("/app/api/costtype").then(function(response){
                this.cost_types = response.data;
            }.bind(this));
        },

        fetchTeamNames() {
        axios.get("/app/api/userteam").then(function(response){
            this.team_names = response.data;
        }.bind(this));
       },

       fetchStatusTypes() {
            axios.get("/app/api/statustype").then(function(response){
                this.status_types = response.data;
            }.bind(this));
       }
    },

    filters: {
        Upper(value) {
            return value.toUpperCase();
        }
    }
});