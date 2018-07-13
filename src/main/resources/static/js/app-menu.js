 
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