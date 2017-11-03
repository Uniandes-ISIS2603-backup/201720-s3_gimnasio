(function (ng) {
    // Definición del módulo
    var mod = ng.module("medidaModule", ['ui.router']);

    
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/usuarios/medida/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/medida");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('medidaList', {
                // Url que aparecerá en el browser
                url: 'estado/{estadoID:int}/medida/',
                paretn:'estadoList',
                param: {
                    estadoID: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'medida.list.html',
                        controller: 'medidaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

