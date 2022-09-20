package com.assignment.one.utils

sealed class NetworkStatus (var status: String){
    object Fetching : NetworkStatus("Fetching")
    object Success : NetworkStatus("Success")
    object Failed : NetworkStatus("Failed")
}
