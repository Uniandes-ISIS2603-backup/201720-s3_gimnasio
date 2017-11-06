(function (ng) {
    // Definición:
    var mod = ng.module("maquinaModule", ['ui.router']);
    //constantes:
    mod.constant("maquinaContext", "api/maquinas");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/maquinas/';
            
            // default
            $urlRouterProvider.otherwise("/maquinasList");
            
            // estados
            $stateProvider.state('maquinas', {
                url: '/maquinas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'maquinas.html',
                        controller: 'maquinasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('createMaquina',{
                url:'/crear',
                parent:'maquinas',
                views: {
                    'listView':{
                        templateUrl: basePath + 'maquina.create.html',
                        controller: 'maquinasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('maquinasList', {
                url: '/list',
                parent: 'maquinas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'maquinas.list.html',
                        controller: 'maquinasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


