(function (ng) {
    // Definición:
    var mod = ng.module("instanciaModule", ['ui.router']);
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',

        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuarios/rutinas/ejerInstancia/';

            // default
            $urlRouterProvider.otherwise("/instanciasList");

            // estados
            $stateProvider.state('instancias', {
                url: '/usuarios/{usuariosId:int}/rutinas/{rutinaId:int}/ejercicios',
                abstract: true,
                param: {
                    usuariosId: null,
                    rutinaId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ejerInstancia.html',
                        controller: 'instanciaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('instanciasList', {
                url: '/list',
                parent: 'instancias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ejerInstancia.list.html'
                    }
                }   
            }).state('ejercicosHechosList', {
                url: '/{instanciaId:int}/ejerciciosHechosList',
                parent: 'instancias',  
                param: {
                    instanciaId: null
                },
                views: {
                    'listView': {
                            templateUrl: 'src/modules/usuarios/rutinas/ejerInstancia/ejerciciosHechos/ejerciciosHechos.list.html'
                    }
                }   
            }).state('instanciaDetail', {
                url: '/{instanciaId:int}/detail',
                parent: 'instancias',
                param: {
                    instanciaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejerInstancia.detail.html',
                        controller: 'instanciaCtrl',
                        controllerAs: 'ctrl'
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
                        templateUrl: basePath + 'ejerInstancia.delete.html',
                        controller: 'instanciaDeleteCtrl'
                    }
                }
            }).state('instanciaCreate', {
                url: '/create',
                parent: 'instancias',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejerInstancia.new.html',
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
                        templateUrl: basePath + 'ejerInstancia.edit.html',
                        controller: 'instanciaUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);

