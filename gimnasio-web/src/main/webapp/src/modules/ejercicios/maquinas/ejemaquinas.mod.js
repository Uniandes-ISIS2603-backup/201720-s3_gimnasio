(function (ng) {
    // Definición:
    var mod = ng.module("ejemaquinaModule", ['ui.router', 'ejercicioModule']);
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',

        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejercicios/maquinas/';

            // default
            $urlRouterProvider.otherwise("/ejemaquinasList");

            // estados
            $stateProvider.state('ejemaquinas', {
                url: '/ejercicios/{ejercicioId:int}/atributosDeEjeejemaquina',
                abstract: true,
                param: {
                    ejercicioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ejemaquinas.html',
                        controller: 'ejemaquinaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejemaquinasList', {
                url: '/list',
                parent: 'ejemaquinas',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejemaquinas.new.html',
                        controller: 'ejemaquinaNewCtrl'
                    },
                    'listView': {
                        templateUrl: basePath + 'ejemaquinas.list.html'
                    }
                }
            }).state('ejemaquinaDelete', {
                url: '/delete/{ejemaquinaId:int}',
                parent: 'ejemaquinas',
                param: {
                    ejemaquinaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejemaquinas.delete.html',
                        controller: 'ejemaquinaDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);

