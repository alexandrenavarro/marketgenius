(function () {

'use strict';


    angular.module('MarketApp', ['ngRoute', 'ngAnimate', 'highcharts-ng'])

  .config([
    '$locationProvider',
    '$routeProvider',
    function($locationProvider, $routeProvider) {
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
            
            if ($scope.currentCriteria.timestampDuration == 'Last Hour') {
                $scope.currentCriteria.offset = 1;
                console.log($scope.currentCriteria.offset);
            }

            if ($scope.currentCriteria.timestampDuration == 'Last Day') {
                $scope.currentCriteria.offset = 24;
                console.log($scope.currentCriteria.offset);
            }

            if ($scope.currentCriteria.timestampDuration == 'Last Week') {
                $scope.currentCriteria.offset = 24 * 7;
                console.log($scope.currentCriteria.offset);
            }
            /*priorityDataService.retriveData($scope.currentCriteria).then(function (priorityData) {
                $scope.priorityList = priorityData.priorityList;
                $scope.highchartsNG = priorityData.chartData;
                $scope.highchartsNG.options = {
                    chart: {
                        type: 'line'
                    }
                };
                $('#priorityData').show();
            }, function(error){
                $('#dataRetrivalError').show();
            });*/

            priorityDataService.retriveData($scope.currentCriteria, 
                function (priorityData) {
                    $scope.priorityList = priorityData.priorityList;
                    $scope.highchartsNG = priorityData.chartData;
                    $scope.highchartsNG.options = {
                        chart: {
                            type: 'line'
                        }
                    };
                    $('#priorityData').show();
                }
                )

            //var retriveData = priorityDataService.retriveData($scope.currentCriteria);
            //$scope.priorityList = retriveData.ranks;
            //$scope.highchartsNG = retriveData.chartData;
            //$scope.highchartsNG.options = {
            //       chart: {
            //        type: 'line'
            //    }
            //};

            $scope.getPriorityClass = function (market) {
                if (market == 'Market 1') {
                    return 'glyphicon glyphicon-hand-right';
                }
                return 'glyphicon glyphicon-hand-right';
            };

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
              timestampDuration: 'Last Minute'
          }
      };    

      this.retriveData = function (priorityCriteria, success) {
   //       return {
   //           "ranks": [
   //{
   //    "name": "marketa",
   //    "rank": 0,
   //    "result": 5239.781957668107
   //},
   //{
   //    "name": "marketc",
   //    "rank": 1,
   //    "result": 5242.118976172505
   //},
   //{
   //    "name": "marketb",
   //    "rank": 2,
   //    "result": 5211.839162898056
   //},
   //{
   //    "name": "marketd",
   //    "rank": 3,
   //    "result": 17.331973142716777
   //}
   //           ],
   //           chartData: {                  
   //               series: [{
   //                   name: 'Market 1',
   //                   data: [10, 15, 12, 8, 7]
   //               },
   //               {
   //                   name: 'Market 2',
   //                   data: [23, 1, 14, 9, 17]
   //               }],
   //               title: {
   //                   text: 'Market Feed'
   //               },
   //               loading: false
   //           }
   //       };

          $http.post('feed/computePriority', priorityCriteria)
                .success(function (marketData) {
                    success(marketData);
                })
                .error(function (error) {
                    console.log("username check error: ", error);                    
                });
      };

      this.getTimeStampReferential = function () {
          return ['Last Minute', 'Last Hour', 'Last Day'];
      };
      
    })

    ;
}());