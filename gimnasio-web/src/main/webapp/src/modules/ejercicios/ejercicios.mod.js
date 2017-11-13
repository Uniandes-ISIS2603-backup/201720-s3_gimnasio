(function (ng) {
    var mod = ng.module("ejercicioModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider',

        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejercicios/';
            $urlRouterProvider.otherwise("/ejerciciosList");

            $stateProvider.state('ejercicios', {
                url: '/ejercicios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ejercicios.html',
                        controller: 'ejercicioCtrl',
                        controllerAs: 'ctrl'
                    }}
            }).state('ejercicio', {
                url: '/ejercicios/{ejercicioId:int}',
                abstract: true,
                parent: 'ejercicios',
                param: {ejercicioId: null},
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.html',
                        controller: 'ejercicioCtrl',
                        controllerAs: 'ctrl'
                    }}
            });

            $stateProvider.state('ejercicios_list', {
                url: '/list',
                parent: 'ejercicios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ejercicios.list.html'
                    }}
            }).state('ejercicios_create', {
                url: '/create',
                parent: 'ejercicios',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.new.html',
                        controller: 'ejercicioNewCtrl'
                    }
                }
            });

            $stateProvider.state('ejercicio_detail', {
                url: '/detail',
                parent: 'ejercicio',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.detail.html',
                        controller: 'ejercicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejercicio_delete', {
                url: '/delete',
                parent: 'ejercicio',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.delete.html',
                        controller: 'ejercicioDeleteCtrl'
                    }
                }
            }).state('ejercicio_update', {
                url: '/update',
                parent: 'ejercicio',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.new.html',
                        controller: 'ejercicioUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);

