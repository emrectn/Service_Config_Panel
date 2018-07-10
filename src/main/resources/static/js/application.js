 
var app_menu = new Vue({
    'el': '#app-menu',
    'data': {
        'selected': ''
    },
    'created': function () {
        axios.get("/save"); 
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
        'status': [],
    },

    created: function () {
        console.log('Addd Alındı');
    },

    mounted(){
        this.fetchConfigs();
        this.fetchCostTypes();
        this.fetchTeamNames();
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
                console.log(response.data);
                this.cost_types = response.data;
            }.bind(this));
        },
       fetchTeamNames() {
        axios.get("/app/api/userteam").then(function(response){
            console.log(response.data);
            this.team_names = response.data;
        }.bind(this));
       }
    },

    filters: {
        Upper(value) {
            return value.toUpperCase();
        }
    }
});