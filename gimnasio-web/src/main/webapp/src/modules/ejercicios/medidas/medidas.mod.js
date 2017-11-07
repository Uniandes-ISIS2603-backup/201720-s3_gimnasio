(function (ng) {
    // Definición:
    var mod = ng.module("medidaModule", ['ui.router', 'ejercicioModule']);
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',

        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejercicios/medidas/';

            // default
            $urlRouterProvider.otherwise("/medidasList");

            // estados
            $stateProvider.state('medidas', {
                url: '/ejercicios/{ejercicioId:int}/atributosDeMedida',
                abstract: true,
                param: {
                    ejercicioId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'medidas.html',
                        controller: 'medidaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('medidasList', {
                url: '/list',
                parent: 'medidas',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'medidas.new.html',
                        controller: 'medidaNewCtrl'
                    },
                    'listView': {
                        templateUrl: basePath + 'medidas.list.html'
                    }
                }
            }).state('medidaDelete', {
                url: '/delete/{medidaId:int}',
                parent: 'medidas',
                param: {
                    medidaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'medidas.delete.html',
                        controller: 'medidaDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);

