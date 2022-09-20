package com.assignment.one.networking

/*
* Set of constants used by HomeScreenTheme and HomeScreenViewModel
* to communicate with each other
*/
sealed class NetworkStatus (var status: String){
    object Fetching : NetworkStatus("Fetching")
    object Success : NetworkStatus("Success")
    object Failed : NetworkStatus("Failed")
}
