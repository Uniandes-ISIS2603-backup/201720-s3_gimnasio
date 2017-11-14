(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies       
        'objetivoModule',
        'entrenadorModule',
        'EstadoModule',
        'medidaModule',
        'maquinaModule',
        'usuarioModule',
        'ejercicioModule',
        'calidadModule',
        'tipoMedidaModule',
        'ejemedidaModule',
        'ejeobjetivoModule',
        'ejemaquinaModule',
        'instanciaModule'
    ]);
    
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
