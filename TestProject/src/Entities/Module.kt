package Entities

class Module(val name: String = "", val compulsory: Int = 0,
             val programme_id: Int = 0) {
    constructor(ModuleFromName: Module) : this() {

    }


}