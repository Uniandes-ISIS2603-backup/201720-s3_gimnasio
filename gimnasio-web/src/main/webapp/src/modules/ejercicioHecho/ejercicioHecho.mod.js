(function (ng) {
    // Definición:
    var mod = ng.module("ejercicioHechoModule", ['ui.router']);
    //constantes:
    mod.constant("ejercicioHechoContext", "api/ejerciciosHechos");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejerciciosHechos/';
            
            // default
            $urlRouterProvider.otherwise("/ejercicioHechoList");
            
            // estados
            $stateProvider.state('ejerciciosHechos', {
                url: '/ejerciciosHechos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ejercicioHecho.html',
                        controller: 'ejercicioHechoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('createEjercicioHecho',{
                url:'/crear',
                parent:'ejerciciosHechos',
                views: {
                    'listView':{
                        templateUrl: basePath + 'ejercicioHecho.create.html',
                        controller: 'ejercicioHechoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            })/*.state('EjercicioHechoMedicion',
            
            {
                url: '/{Eid:int}/medicionMaquina',
                parent:'ejerciciosHechos',
                param:{
                    Eid: null,
                    e: null
                },
                views:{
                    'listView':
                            {
                                templateUrl: basePath + 'EjercicioHechoMedicion/MedicionesEjercicio.html',
                                controller: 'ejercicioHechoCtrl',
                                controllerAs: 'ctrl'                            }
                }
            }
                    
                    ).state('ejercicioHechoList', {
                url: '/list',
                parent: 'ejerciciosHechos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ejercicioHecho.list.html',
                        controller: 'ejercicioHechoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });*/
        }
    ]);
})(window.angular);