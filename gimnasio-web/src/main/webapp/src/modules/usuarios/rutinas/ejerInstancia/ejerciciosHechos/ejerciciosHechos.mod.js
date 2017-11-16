(function (ng) {
    var mod = ng.module("ejerciciosHechosModule", ['ui.router']);
    mod.config(['$stateProvider', '$urlRouterProvider', 
        
        function ($stateProvider, $urlRouterProvider) 
        {
            var basePath = 'src/modules/usuarios/rutinas/ejerInstancia/ejerciciosHechos';
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
                        templateUrl: basePath + 'ejerciciosHechos.html',
                        controller: 'ejerciciosHechosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejerciciosHechosList', 
            {
                url: '/list',
                parent: 'ejerciciosHechos',
                views: 
                {
                    'listView': 
                    {
                        templateUrl: basePath + 'ejerciciosHechos.list.html'
                    }
                }
            }).state('ejerciciosHechosDetail', 
            {
                url: '/{ejerciciosHechosId:int}/detail',
                parent: 'ejerciciosHechos',
                param: 
                {
                    ejerciciosHechosId: null
                },
                views: 
                {                    
                    'detailView': 
                    {
                        templateUrl: basePath + 'ejerciciosHechos.detail.html',
                        controller: 'ejerciciosHechosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ejercicioHechoCreate', 
            {
                url: '/create',
                parent: 'ejerciciosHechos',
                views: 
                {
                    'detailView': 
                    {
                        templateUrl: basePath + 'ejerciciosHechos.new.html',
                        controller: 'ejercicioHechoNewCtrl'
                    }
                }
            }).state('ejercicioHechoUpdate', 
            {
                url: '/update/{ejerciciosHechosId:int}',
                parent: 'ejerciciosHechos',
                param: 
                {
                    ejerciciosHechosId: null
                },
                views: 
                {
                    'detailView': 
                    {
                        templateUrl: basePath + 'ejerciciosHechos.update.html',
                        controller: 'ejercicioHechoUpdateCtrl'
                    }
                }
            })
            //Lo comento por si en algun momento es util.
            /*.state('ejercicioHechoDelete', 
            {
                url: '/delete/{ejerciciosHechosId:int}',
                parent: 'ejerciciosHechos',
                param: 
                {
                    ejerciciosHechosId: null
                },
                views: 
                {
                    'detailView': 
                    {
                        templateUrl: basePath + 'ejerciciosHechos.delete.html',
                        controller: 'ejercicioHechoDeleteCtrl'
                    }
                }
            });*/
        }]);
})(window.angular);


