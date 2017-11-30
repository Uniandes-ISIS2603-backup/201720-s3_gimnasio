//<!--Esta pagina ha sido desarrollada por Mateo Sicard 
//    m.sicard10 201512474  -->
(function (ng) {
    // Definición:
    var mod = ng.module("entrenadorModule", ['ui.router']);
    //constantes:
    mod.constant("entrenadorContext", "api/entrenadores");
    // Configuración:
    mod.config(['$stateProvider', '$urlRouterProvider',
        
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/entrenadores/';
            
            // default
            $urlRouterProvider.otherwise("/entrenadoresList");
            
            // estados
            $stateProvider.state('entrenadores', {
                url: '/entrenadores',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'entrenadores.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('createEntrenador',{
                url:'/crear',
                parent:'entrenadores',
                views: {
                    'listView':{
                        templateUrl: basePath + 'entrenador.create.html',
                        controller: 'entrenadoresCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('EntrenadorUsuarios',
            
            {
                url: '/{Eid:int}/usuarios',
                parent:'entrenadores',
                param:{
                    Eid: null,
                    e: null
                },
                views:{
                    'listView':
                            {
                                templateUrl: basePath + 'EntrenadoresUsuario/UsuariosEntrenador.html',
                                controller: 'entrenadoresCtrl',
                                controllerAs: 'ctrl'                            }
                }
            }
                    
                    ).state('entrenadoresList', {
                url: '/list',
                parent: 'entrenadores',
                views: {
                    'listView': {
                        templateUrl: basePath + 'entrenadores.list.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('crearEstudiante',{
                url: '/{Xid:int}/crearEstudiante',
                param:{
                    Xid: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'EntrenadoresUsuario/crearUsuarioAEntrenador.html',
                        controller: 'entrenadoresUsuarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }
            
            ).state('vistaUsuarioEntrenador',{
                url:'/{Enid:int}/usuarios/{Uid:int}',
                parent: 'entrenadores',
                params:{
                 Enid:null,
                 Uid:null
                 },
                 views: {
                    'mainView': {
                        templateUrl: basePath + 'EntrenadoresUsuario/vistaAUsuario.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    },'rutinasView': {
                        templateUrl: basePath + 'EntrenadoresUsuario/rutinas.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                 
        
            }).state('entrenadorEdit',{
                url:'/entrenadores/{entId:int}/editar',
                param:{
                    entId:null
                },
                views: {
                     'mainView': {
                        templateUrl: basePath + 'entrenador.edit.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('entrenadorDelete',{
                url:'/entrenadores/{entrenadorid:int}/delete',
                param:{
                    entrenadorid:null
                },
                views: {
                     'mainView': {
                        templateUrl: basePath + 'entrenador.delete.html',
                        controller: 'entrenadoresCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);



