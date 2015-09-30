(function () {

    'use strict';


    angular.module('MarketApp', ['ngRoute', 'ngAnimate', 'highcharts-ng'])

        .config([
            '$locationProvider',
            '$routeProvider',
            function ($locationProvider, $routeProvider) {
                $locationProvider.hashPrefix('!');
                // routes
                $routeProvider
                    .when("/", {
                        templateUrl: "./partials/priorityViewer.html",
                        controller: "MainController"
                    })
                    .otherwise({
                        redirectTo: '/'
                    });
            }
        ]);

    //Load controller
    angular.module('MarketApp')

        .controller('MainController', [
            '$scope', 'priorityDataService',
            function ($scope, priorityDataService) {
                var self = this;
                $scope.highchartsNG = {};
                $scope.priorityList = {};
                $scope.currentCriteria = priorityDataService.getCurrentMarketCriteria();
                $scope.saveWeightage = function () {
                    $('#weightageErrorDiv').hide();
                    //Checking the validation
                    var totalWeightage = $scope.currentCriteria.weightage.bestPriceWeight +
                        $scope.currentCriteria.weightage.filledRatioWeight + $scope.currentCriteria.weightage.latencyWeight;

                    if (totalWeightage == 1) {
                        $scope.retreivePriorityData();
                    } else {
                        $('#weightageErrorDiv').show();
                    }
                };

                $scope.retreivePriorityData = function () {
                    $('#dataRetrivalError').hide();

                    if ($scope.currentCriteria.timestampDuration  == 'Last Hour') {
                        $scope.currentCriteria.offset = 1;
                        console.log( $scope.currentCriteria.offset);
                    }

                    if ($scope.currentCriteria.timestampDuration  == 'Last Day') {
                        $scope.currentCriteria.offset = 24;
                        console.log( $scope.currentCriteria.offset);
                    }

                    if ($scope.currentCriteria.timestampDuration  == 'Last Week') {
                        $scope.currentCriteria.offset = 24*7;
                        console.log( $scope.currentCriteria.offset);
                    }
                    priorityDataService.retriveData($scope.currentCriteria).then(function (priorityData) {
                        $scope.priorityList = priorityData.priorityList;
                        $scope.highchartsNG = priorityData.chartData;
                        $scope.highchartsNG.options = {
                            chart: {
                                type: 'line'
                            }
                        };s
                        $('#priorityData').show();
                    }, function(error){
                        $('#dataRetrivalError').show();
                    });


                    //var retriveData = priorityDataService.retriveData($scope.currentCriteria);
                    //$scope.priorityList = retriveData.priorityList;
                    //$scope.highchartsNG = retriveData.chartData;

                    $('#priorityData').show();
                };
                $('#priorityData').hide();

                $scope.timeStampReferential = priorityDataService.getTimeStampReferential();
            }
        ])

        .service('priorityDataService', function ($http) {

            this.getCurrentMarketCriteria = function () {
                return {
                    weightage: {
                        bestPriceWeight: 0,
                        latencyWeight: 0,
                        filledRatioWeight: 0
                    },
                    timestampDuration: 'Last Hour'
                };
            };

            this.retriveData = function (priorityCriteria) {


                $http.post('feed/computePriority', priorityCriteria)
                 .success(function (marketData) {
                 return marketData;
                 })
                 .error(function (error) {
                 console.log("username check error: ", error);
                 return error;
                 });
            };

            this.getTimeStampReferential = function () {
                return ['Last Hour', 'Last Day', 'Last Week'];
            };

        })

    ;
}());