(function (ng) {
    // Definición del módulo
    var mod = ng.module("EstadoModule", ['ui.router']);

    
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/usuarios/estado/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/estado");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('estadoList', {
                // Url que aparecerá en el browser
                url: '/usuarios/{UsuarioId:int}/',
                paretn:'usuarios',
                param: {
                    UsuarioId : null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'Estado.html',
                        controller: 'estadoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('estadocreate', {
                url: '/usuarios/{UsId:int}/estado/create',            
                paretn:'usuarios',
                param: {
                    UsId : null
               
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'Estado_create.html',
                        controller: 'estadoCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

