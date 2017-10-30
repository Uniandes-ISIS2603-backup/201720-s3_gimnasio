(function (ng) {
    // Definición:
    var mod = ng.module("entrenadorModule", ['ui.router']);
    //constantes:
    mod.constant("entrenadorContext", "api/entrenadores");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/entrenadores/';
            
            // default
            $urlRouterProvider.otherwise("/entrenadoresList");
            
            // estados
            $stateProvider.state('entrenadores', {
                url: '/entrenadores',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'entrenadores.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('createEntrenador',{
                url:'/crear',
                parent:'entrenadores',
                views: {
                    'listView':{
                        templateUrl: basePath + 'entrenador.create.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('entrenadoresList', {
                url: '/list',
                parent: 'entrenadores',
                views: {
                    'listView': {
                        templateUrl: basePath + 'entrenadores.list.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



