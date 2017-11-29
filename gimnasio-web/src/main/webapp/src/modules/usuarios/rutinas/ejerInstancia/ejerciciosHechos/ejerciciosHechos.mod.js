(function (ng) {
    var mod = ng.module("ejerciciosHechosModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', 
        
        function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/usuarios/rutinas/ejerInstancia/ejerciciosHechos/';
            $urlRouterProvider.otherwise("/ejerciciosHechosList");
                
            //Estados
            $stateProvider.state('ejerciciosHechos', 
            {
                url: '/usuarios/{usuariosId:int}/rutinas/{rutinaId:int}/ejercicios/{instanciaId:int}/ejerciciosHechos',
                abstract: true,
                param : {
                    usuariosId:null,
                    rutinaId:null,
                    instanciaId:null
                },
                views: 
                {
                    'mainView':
                    {
                        templateUrl: basePath + '/ejerciciosHechos.html',
                        controller: 'ejerciciosHechosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejercicioHechoCreate', 
            {
                url: '/{instanciaId:int}/nuevoEjercicioRealizado',
                parent: 'instancias',
                param : {
                    instanciaId: null
                },
                views: 
                {
                    'detailView': 
                    {
                        templateUrl: basePath + 'ejerciciosHechos.new.html',
                        controller: 'ejercicioHechoNewCtrl'
                    }
                }
            }).state('ejerciciosHechosList', {
                url: '/{instanciaId:int}/ejerciciosHechosList',
                parent: 'instancias',  
                param: {
                    instanciaId: null
                },
                views: {
                    'listView': {
                            templateUrl: 'src/modules/usuarios/rutinas/ejerInstancia/ejerciciosHechos/ejerciciosHechos.list.html'
                    }
                }                 
            })
        }]);
})(window.angular);