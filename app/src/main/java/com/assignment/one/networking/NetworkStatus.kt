package com.assignment.one.networking

/*
* Set of constants used by HomeScreenTheme and HomeScreenViewModel
* to communicate with each other
*/
sealed class NetworkStatus (){
    object Fetching : NetworkStatus()
    object Success : NetworkStatus()
    object Failed : NetworkStatus()
}
