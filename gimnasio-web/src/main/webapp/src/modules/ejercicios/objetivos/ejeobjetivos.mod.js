(function (ng) {
    // Definición:
    var mod = ng.module("ejeobjetivoModule", ['ui.router', 'ejercicioModule']);
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',

        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/ejercicios/objetivos/';

            // default
            $urlRouterProvider.otherwise("/ejeobjetivosList");

            // estados
            $stateProvider.state('ejeobjetivos', {
                url: '/objetivos',
                abstract: true,
                parent:'ejercicio_detail',
                param: {
                    ejercicioId: null
                },
                views: {
                    'mainView2': {
                        templateUrl: basePath + 'ejeobjetivos.html',
                        controller: 'ejeobjetivoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejeobjetivosList', {
                url: '/list',
                parent: 'ejeobjetivos',
                views: {
                    'detailView':{
                        templateUrl: basePath + 'ejeobjetivos.list.html'
                    }
                }
            }).state('ejeobjetivosCreate',{
                url: '/create',
                parent: 'ejeobjetivos',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejeobjetivos.new.html',
                        controller: 'ejeobjetivoNewCtrl'
                    }
                }
            }).state('ejeobjetivoDelete', {
                url: '/delete/{ejeobjetivoId:int}',
                parent: 'ejeobjetivos',
                param: {
                    ejeobjetivoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ejeobjetivos.delete.html',
                        controller: 'ejeobjetivoDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);

