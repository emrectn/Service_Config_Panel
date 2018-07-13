var app = new Vue({
    el: '#app',
    data: {
        'configs': [],
        'selected': 'home',
        'selected_costtype': '',
        'selected_teamname': '',
        'selected_status': '',
        'selected_hour': '',
        'cost_types': [],
        'team_names': [],
        'status_types': [],
        'a': '',
        'upHere': false
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
       },

       snackBarShow(a) {
            var x = document.getElementById("snackbar");
            	x.textContent = a ;

                // Add the "show" class to DIV
                x.className = "show";

                // After 3 seconds, remove the show class from DIV
                setTimeout(function(){ x.className = x.className.replace("show", ""); }, 4000);
       },
    },

    filters: {
        Upper(value) {
            return value.toUpperCase();
        }
    }
});
