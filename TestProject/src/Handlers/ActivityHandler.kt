package Handlers

import Database.DatabaseManager
import Entities.Activity
import Entities.Module

class ActivityHandler {
    companion object {
        @JvmStatic
        fun deleteActivity(type: String){
            DatabaseManager.deleteRowActivity(type)
        }
        @JvmStatic
        fun createActivity(modulename: String, type: String, day: Int, time: Int): Activity {
            val activity = Activity(modulename = modulename, type = type, day = day, time = time)
            return activity
        }
        @JvmStatic
        fun saveActivity(act: Activity){
            val actTemp = act
            DatabaseManager.insertRowActivity(actTemp)
        }

    }
}