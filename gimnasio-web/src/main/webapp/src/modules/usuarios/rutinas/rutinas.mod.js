/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    // Definición:
    var mod = ng.module("rutinaModule", ['ui.router']);
    //constantes:
    mod.constant("rutinaContext", "api/rutinas");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
        
            var basePath = 'src/modules/usuarios/rutinas/';
            
            // default
            $urlRouterProvider.otherwise("/rutinas");
            
            // estados
            $stateProvider.state('createRutina',{
                url:'/crear',
                params:{
                    UsId : null
                },
                views: {
                    'mainView':{
                        templateUrl: basePath + 'rutina.create.html',
                        controller: 'createRutinaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


