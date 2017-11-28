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
            var basePath = 'src/modules/rutinas/';
            
            // default
            $urlRouterProvider.otherwise("/rutinas");
            
            // estados
            $stateProvider.state('rutinas', {
                url: '/rutinas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'rutinas.html',
                        controller: 'rutinasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('createRutina',{
                url:'/crear',
                parent:'rutinas',
                views: {
                    'listView':{
                        templateUrl: basePath + 'rutina.create.html',
                        controller: 'rutinasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


