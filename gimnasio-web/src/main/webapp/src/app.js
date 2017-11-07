(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies       
        'objetivoModule',
        'entrenadorModule',
        'EstadoModule',
        'medidasModule',
        'maquinaModule',
        'usuarioModule',
        'ejercicioModule',
        'calidadModule',
        'tipoMedidaModule',
      
        'ejeobjetivoModule',
        'ejemaquinaModule',
        'instanciaModule'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
