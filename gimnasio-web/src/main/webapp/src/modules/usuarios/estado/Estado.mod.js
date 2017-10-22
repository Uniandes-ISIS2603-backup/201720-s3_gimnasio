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
            });
        }
    ]);
})(window.angular);

