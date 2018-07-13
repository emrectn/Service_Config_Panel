 
var app_config = new Vue({
    'el': '#app-config',
    'data': {
        'selected_config': '',
        'id':'',
        'name':'',
        'config_list': {},
        'header_list': [
            { text: 'User Team', value: 'userteam'},
            { text: 'Permission Type', value: 'permtype'},
            { text: 'Status Type', value: 'statustype'},
            { text: 'Cost Type', value: 'costtype'}]
    },
    'created': function () {
        console.log("Navbar Configration");
    },
    'methods': {
    	showModal(ctype_id, ctype_name){
    		 this.id = ctype_id;
    		 this.name = ctype_name;
        },
        
        deleteConfig(id) {
        	console.log("url : " + "/app/api/" + this.selected_config + "/" + id);
        	console.log(id);
        }

    },

    filters: {
        Upper(value) {
            return value.toUpperCase();
        }
    }
});