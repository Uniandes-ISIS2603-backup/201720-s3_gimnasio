(function (ng) {
    // Definición:
    var mod = ng.module("tipoMedidaModule", ['ui.router']);
    //constantes:
    mod.constant("tipoMedidaContext", "api/tipoMedidas");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/tipoMedidas/';
            
            // default
            $urlRouterProvider.otherwise("/tipoMedidasList");
            
            // estados
            $stateProvider.state('tipoMedidas', {
                url: '/tipoMedidas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tipoMedidas.html',
                        controller: 'tipoMedidasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('createtipoMedida',{
                url:'/crear',
                parent:'tipoMedidas',
                views: {
                    'listView':{
                        templateUrl: basePath + 'tipoMedida.create.html',
                        controller: 'tipoMedidasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tipoMedidasList', {
                url: '/list',
                parent: 'tipoMedidas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'tipoMedidas.list.html',
                        controller: 'tipoMedidasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);