 
var app_config = new Vue({
    'el': '#app-config',
    'data': {
        'selected_config': '',
        'id':'',
        'name':'',
        'data':{},
        'config_list': {},
        'header_list': [
            { text: 'User Team', value: 'userteam'},
            { text: 'Permission Type', value: 'permtype'},
            { text: 'Status Type', value: 'statustype'},
            { text: 'Cost Type', value: 'costtype'}]
    },
    'created': function () {
        console.log("Navbar Configration");
        this.initialize();
    },
    'methods': {
    	initialize() {
    		var vars = this.getParams();
    		if (vars['status'] != null){
    			this.selected_config = vars['status'];
    		}
    	},
    	
    	getParams() {
    		var vars={};
    		var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    
    				function(m,key,value) {
    			      vars[key] = value;
    			    });
    			    return vars;
    	},
    	
    	showModal(ctype_id, ctype_name){
    		 this.id = ctype_id;
    		 this.name = ctype_name;
        },
        
        deleteConfig(id) {
        	let self = this;
        	
        	axios({
        		method: 'delete',
        		url: "/app/api/" + self.selected_config + "/" + id
        	}).then(function (response) {
        		console.log(this.url + " : Başarılı");
        	}).catch(function(error) {
				console.log(this.url + " : Bir hata oluştu");
				console.log(error);
			});
        	location.href="/configure?status=" + self.selected_config;
        
        },
        
        createConfig() {
        	let self = this;
        	console.log
        	this.data[this.getConfigName(this.selected_config)] = this.name;
        	
        	axios({
        		method: 'post',
        		url: "/app/api/" + self.selected_config,
        		data: self.data
        	}).then(function (response) {
        		console.log(this.url + " : Başarılı");
        		
        	}).catch(function(error) {
				console.log(this.url + " : Bir hata oluştu");
				console.log(error);
			});
        	location.href="/configure?status=" + self.selected_config;
        },
        
        getConfigName(configType) {
        	if(configType === 'userteam')
        		return 'username';
        	if(configType === 'costtype')
        		return 'costname';
        	if(configType === 'permtype')
        		return 'permname';
        	if(configType === 'statustype')
        		return 'statusname';
        },

    },

    filters: {
        Upper(value) {
            return value.toUpperCase();
        }
    }
});