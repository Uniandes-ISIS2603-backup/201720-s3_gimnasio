(function (ng) {
    var mod = ng.module("ejemedidaModule", ['ui.router', 'ejercicioModule']);
    mod.config(['$stateProvider', '$urlRouterProvider',

        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejercicios/medidas/';
            $urlRouterProvider.otherwise("/ejemedidasList");

            $stateProvider.state('ejemedidas', {
                url: '/medidas',
                abstract: true,
                parent:'ejercicio_detail',
                param: {
                    ejercicioId: null
                },
                views: {
                    'mainView2': {
                        templateUrl: basePath + 'medidas.html',
                        controller: 'ejemedidaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejemedidasList', {
                url: '/list',
                parent: 'ejemedidas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'medidas.list.html'
                    }
                }
            }).state('ejemedidasCreate',{
                url: '/create',
                parent: 'ejemedidas',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'medidas.new.html',
                        controller: 'ejemedidaNewCtrl'
                    }
                }
            }).state('ejemedidaDelete', {
                url: '/{ejemedidaId:int}/delete',
                parent: 'ejemedidas',
                param: {
                    ejemedidaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'medidas.delete.html',
                        controller: 'ejemedidaDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);

