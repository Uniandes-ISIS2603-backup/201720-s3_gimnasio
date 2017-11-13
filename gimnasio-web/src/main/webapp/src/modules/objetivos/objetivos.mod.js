(function (ng) {
    var mod = ng.module("objetivoModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider',

        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/objetivos/';
            var basePathCalidad = basePath + '/atributosDeCalidad/';
            $urlRouterProvider.otherwise("/objetivosList");

            $stateProvider.state('objetivos', {
                url: '/objetivos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'objetivos.html',
                        controller: 'objetivoCtrl',
                        controllerAs: 'ctrl'
                    }}
            }).state('objetivo', {
                url: '/objetivos/{objetivoId:int}',
                abstract: true,
                parent: 'objetivos',
                param: {objetivoId: null},
                views: {
                    'detailView': {
                        templateUrl: basePath + 'objetivos.html',
                        controller: 'objetivoCtrl',
                        controllerAs: 'ctrl'
                    }}
            });

            $stateProvider.state('objetivos_list', {
                url: '/list',
                parent: 'objetivos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'objetivos.list.html'
                    }}
            }).state('objetivos_create', {
                url: '/create',
                parent: 'objetivos',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'objetivos.new.html',
                        controller: 'objetivoNewCtrl'
                    }
                }
            });

            $stateProvider.state('objetivo_detail', {
                url: '/detail',
                parent: 'objetivo',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'objetivos.detail.html',
                        controller: 'objetivoCtrl',
                        controllerAs: 'ctrl'
                    },
                    'listView': {
                        templateUrl: basePathCalidad + 'calidad.list.html',
                        controller: 'calidadCtrl',
                        controllerAs: 'calidadCtrl'
                    }
                }
            }).state('objetivo_delete', {
                url: '/delete',
                parent: 'objetivo',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'objetivos.delete.html',
                        controller: 'objetivoDeleteCtrl'
                    }
                }
            }).state('objetivo_update', {
                url: '/update',
                parent: 'objetivo',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'objetivos.new.html',
                        controller: 'objetivoUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);

