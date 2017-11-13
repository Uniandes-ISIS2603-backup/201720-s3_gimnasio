(function (ng) {
    var mod = ng.module("calidadModule", ['ui.router','objetivoModule']);
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/objetivos/atributosDeCalidad/';
            $urlRouterProvider.otherwise("/calidadsList");
            
            $stateProvider.state('calidads', {
                url: '/atributosDeCalidad',
                parent: 'objetivo',
                abstract: true,
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calidad.html',
                        controller: 'objetivoCtrl',
                        controllerAs: 'ctrl'
                    }}
            }).state('calidad',{
                url: '/{calidadId:int}',
                parent: 'calidads',
                param: {
                    calidadId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calidad.html',
                        controller: 'objetivoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
            $stateProvider.state('calidads_create', {
                url: '/create',
                parent: 'calidads',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calidad.new.html',
                        controller: 'calidadNewCtrl'
                    }}
            });
            
            $stateProvider.state('calidad_delete', {
                url: '/delete',
                parent: 'calidad',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calidad.delete.html',
                        controller: 'calidadDeleteCtrl'
                    }
                }
            }).state('calidad_update', {
                url: '/update',
                parent: 'calidad',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calidad.edit.html',
                        controller: 'calidadUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);

