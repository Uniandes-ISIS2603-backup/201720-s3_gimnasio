(function (ng) {
    // Definición:
    var mod = ng.module("usuarioModule", ['ui.router']);
    //constantes:
    mod.constant("usuarioContext", "api/usuarios");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuarios/';
            
            // default
            $urlRouterProvider.otherwise("/usuariosList");
            
            // estados
            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('createUsuario',{
                url:'/crear',
                parent:'usuarios',
                views: {
                    'listView':{
                        templateUrl: basePath + 'usuario.create.html',
                        controller: 'usuarioscreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuariosList', {
                url: '/list',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'usuarios.list.html',
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuarioDetalle',{
                 url: '/{Uid:int}',
                parent: 'usuarios',
                param:{
                    Uid: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'usuarios.detalle.html',
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);