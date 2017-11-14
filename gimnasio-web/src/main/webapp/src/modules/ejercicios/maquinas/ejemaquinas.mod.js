(function (ng) {
    var mod = ng.module("ejemaquinaModule", ['ui.router', 'ejercicioModule']);
    mod.config(['$stateProvider', '$urlRouterProvider',

        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejercicios/maquinas/';
            $urlRouterProvider.otherwise("/ejemaquinasList");

            $stateProvider.state('ejemaquinas', {
                url: '/maquinas',
                abstract: true,
                parent:'ejercicio_detail',
                param: {
                    ejercicioId: null
                },
                views: {
                    'mainView2': {
                        templateUrl: basePath + 'ejemaquinas.html',
                        controller: 'ejemaquinaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejemaquinasList', {
                url: '/list',
                parent: 'ejemaquinas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ejemaquinas.list.html'
                    }
                }
            }).state('ejemaquinasCreate',{
                url: '/create',
                parent: 'ejemaquinas',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejemaquinas.new.html',
                        controller: 'ejemaquinaNewCtrl'
                    }
                }
            }).state('ejemaquinaDelete', {
                url: '/{ejemaquinaId:int}/delete',
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

