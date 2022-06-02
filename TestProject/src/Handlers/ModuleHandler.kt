package Handlers

import Database.DatabaseManager
import Entities.Module
import Entities.Programme

class ModuleHandler {

    companion object {
        @JvmStatic
        fun deleteModule(name: String){
            DatabaseManager.deleteRowModule(name)
        }
        @JvmStatic
        fun createModule(name: String, compulsory: Int, programme_id: Int): Module {
            val module = Module(name = name, compulsory = compulsory, programme_id = programme_id)
            return module
        }
        @JvmStatic
        fun saveModule(mod: Module){
            var moduleTemp = mod

            DatabaseManager.insertRowModule(moduleTemp)

        }

    }


}