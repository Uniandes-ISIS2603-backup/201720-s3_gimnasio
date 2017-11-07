(function (ng) {
    // Definición:
    var mod = ng.module("instanciaModule", ['ui.router','objetivoModule']);
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/objetivos/atributosDeInstancia/';
            
            // default
            $urlRouterProvider.otherwise("/instanciasList");
            
            // estados
            $stateProvider.state('instancias', {
                url: '/objetivos/{objetivoId:int}/atributosDeInstancia',
                abstract: true,
                param : {
                    objetivoId:null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'instancia.html',
                        controller: 'instanciaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('instanciasList', {
                url: '/list',
                parent: 'instancias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'instancia.list.html'
                    }
                }
            }).state('instanciaDelete', {
                url: '/delete/{instanciaId:int}',
                parent: 'instancias',
                param: {
                    instanciaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'instancia.delete.html',
                        controller: 'instanciaDeleteCtrl'
                    }
                }
            }).state('instanciaCreate', {
                url: '/create',
                parent: 'instancias',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'instancia.new.html',
                        controller: 'instanciaNewCtrl'
                    }
                }
            }).state('instanciaUpdate', {
                url: '/update/{instanciaId:int}',
                parent: 'instancias',
                param: {
                    instanciaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'instancia.edit.html',
                        controller: 'instanciaUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);

