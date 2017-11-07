(function (ng) {
    // Definición:
    var mod = ng.module("calidadModule", ['ui.router','objetivoModule']);
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/objetivos/atributosDeCalidad/';
            
            // default
            $urlRouterProvider.otherwise("/calidadsList");
            
            // estados
            $stateProvider.state('calidads', {
                url: '/objetivos/{objetivoId:int}/atributosDeCalidad',
                abstract: true,
                param : {
                    objetivoId:null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'calidad.html',
                        controller: 'calidadCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('calidadsList', {
                url: '/list',
                parent: 'calidads',
                views: {
                    'listView': {
                        templateUrl: basePath + 'calidad.list.html'
                    }
                }
            }).state('calidadDelete', {
                url: '/delete/{calidadId:int}',
                parent: 'calidads',
                param: {
                    calidadId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calidad.delete.html',
                        controller: 'calidadDeleteCtrl'
                    }
                }
            }).state('calidadsCreate', {
                url: '/create',
                parent: 'calidads',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calidads.new.html',
                        controller: 'calidadNewCtrl'
                    }
                }
            }).state('calidadUpdate', {
                url: '/update/{calidadId:int}',
                parent: 'calidads',
                param: {
                    calidadId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'calidads.new.html',
                        controller: 'calidadUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);

