(function (ng) {
    // Definición:
    var mod = ng.module("objetivoModule", ['ui.router']);
    //constantes:
    mod.constant("objetivosContext", "api/objetivos");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/objetivos/';
            
            // default
            $urlRouterProvider.otherwise("/objetivosList");
            
            // estados
            $stateProvider.state('objetivos', {
                url: '/objetivos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'objetivos.html',
                        controller: 'objetivoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('objetivosList', {
                url: '/list',
                parent: 'objetivos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'objetivos.list.html'
                    }
                }
            }).state('objetivoDetail',{
                url: '/{objetivoId:int}/detail',
                parent: 'objetivos',
                param : {
                    objetivoId:null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'objetivos.detail.html',
                        controller: 'objetivoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('objetivoDelete', {
                url: '/delete/{objetivoId:int}',
                parent: 'objetivos',
                param: {
                    objetivoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'objetivos.delete.html',
                        controller: 'objetivoDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);

