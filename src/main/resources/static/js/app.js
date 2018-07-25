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
        'formData' : {},
        'upHere': false,
        'notify': false,
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
            let self = this;
            axios({
            	method:'delete',
            	url:'/app/api/register/' + id,
     	   }).then(function (response) {
    		   console.log(response + " : Başarılı");
    	   
    	   }).catch(function (error) {
    		   console.log(error + ": Hata oluştu");
    	   })
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
    
       addConfig() {
    	   var formObj = document.getElementById("addRegister").elements;
    	   this.formData['tag'] = formObj.namedItem("tag").value;
    	   this.formData['cfp'] = formObj.namedItem("cfp").value;
    	   this.formData['ftid'] = formObj.namedItem("ftid").value;
    	   this.formData['jiratask'] = formObj.namedItem("jiratask").value;
    	   this.formData['springt'] = formObj.namedItem("springt").value;
    	   this.formData['defination'] = formObj.namedItem("defination").value;
    	   this.formData['hour'] = this.selected_hour;
    	   
    	   console.log(this.formData);
    	   
    	   let self = this;
    	   axios({
    		  method: 'post',
    		  url: '/app/api/register',
    		  data: self.formData
    	   }).then(function (response) {
    		   console.log(response + " : Başarılı");
    	   
    	   }).catch(function (error) {
    		   console.log(error + ": Hata oluştu");
    	   })
       },

       snackBarShow(a) {
            var x = document.getElementById("snackbar");
            	x.textContent = a ;

                // Add the "show" class to DIV
                x.className = "show";

                // After 3 seconds, remove the show class from DIV
                setTimeout(function(){ x.className = x.className.replace("show", ""); }, 4000);
       },
       
   		getParams() {
	   		var vars={};
	   		var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    
   				function(m,key,value) {
	   			  value = value.split("+").join(" ");
   			      vars[key] = value;
   			    });
		    return vars;
   		},
    },

    filters: {
        Upper(value) {
            return value.toUpperCase();
        }
    }
});
