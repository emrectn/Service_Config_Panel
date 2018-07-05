var app = new Vue({
		el: '#app',
		data: {
			'configs': [],
			'selected': 'home'},
        mounted(){
            this.fetchConfigs();
        },
        methods: {
            fetchConfigs(){
                axios.get("/findall").then(function(response){
                	console.log(response.data)
                    this.configs = response.data;
                }.bind(this));
            },
            deleteConfig(){
            	id = document.getElementById('id').value;
            	console.log(id);
            	axios.get("/delete?id="+id);
            }
        }
	});
	
var	app_menu = new Vue({
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