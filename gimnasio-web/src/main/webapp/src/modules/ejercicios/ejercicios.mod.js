(function (ng) {
    // Definición:
    var mod = ng.module("ejercicioModule", ['ui.router']);
    //constantes:
    mod.constant("ejerciciosContext", "api/ejercicios");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejercicios/';
            
            // default
            $urlRouterProvider.otherwise("/ejerciciosList");
            
            // estados
            $stateProvider.state('ejercicios', {
                url: '/ejercicios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ejercicios.html',
                        controller: 'ejercicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejerciciosList', {
                url: '/list',
                parent: 'ejercicios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ejercicios.list.html'
                    }
                }
            }).state('ejercicioDetail',{
                url: '/{ejercicioId:int}/detail',
                parent: 'ejercicios',
                param : {
                    ejercicioId:null
                },
                views: {
                    'detailView':{
                        templateUrl: basePath + 'ejercicios.detail.html',
                        controller: 'ejercicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejercicioDelete', {
                url: '/delete/{ejercicioId:int}',
                parent: 'ejercicios',
                param: {
                    ejercicioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.delete.html',
                        controller: 'ejercicioDeleteCtrl'
                    }
                }
            }).state('ejerciciosCreate', {
                url: '/create',
                parent: 'ejercicios',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.new.html',
                        controller: 'ejercicioNewCtrl'
                    }
                }
            }).state('ejercicioUpdate', {
                url: '/update/{ejercicioId:int}',
                parent: 'ejercicios',
                param: {
                    ejercicioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejercicios.new.html',
                        controller: 'ejercicioUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);

