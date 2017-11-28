(function (ng) {
    var mod = ng.module("ejercicioModule", ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejercicios/';
            $urlRouterProvider.otherwise("/ejerciciosList");
            
            //estadosAbstractos de la apk
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
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.list.html',
                        controller: 'ejercicioCtrl',
                        controllerAs: 'ctrl'}
                }
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
                url: '/{ejercicioId:int}/detail',
                parent: 'ejercicios_list',
                param: {ejercicioId: null},
                views: {
                    'detailView2': {
                        templateUrl: basePath + 'ejercicios.detail.html',
                        controller: 'ejercicioDetailCtrl',
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

