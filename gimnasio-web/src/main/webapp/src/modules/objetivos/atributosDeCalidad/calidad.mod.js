(function (ng) {
    // Definición:
    var mod = ng.module("calidadModule", ['ui.router']);
    //constantes:
    mod.constant("calidadsContext", "api/calidads");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/calidads/';
            
            // default
            $urlRouterProvider.otherwise("/calidadsList");
            
            // estados
            $stateProvider.state('calidads', {
                url: '/calidads',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'calidads.html',
                        controller: 'calidadCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('calidadsList', {
                url: '/list',
                parent: 'calidads',
                views: {
                    'listView': {
                        templateUrl: basePath + 'calidads.list.html'
                    }
                }
            }).state('calidadDetail',{
                url: '/{calidadId:int}/detail',
                parent: 'calidads',
                param : {
                    calidadId:null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'calidads.detail.html',
                        controller: 'calidadCtrl',
                        controllerAs: 'ctrl'
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
                        templateUrl: basePath + 'calidads.delete.html',
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

