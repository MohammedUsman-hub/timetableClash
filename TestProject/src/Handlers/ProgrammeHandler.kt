package Handlers

import Database.DatabaseManager
import Entities.Programme
import java.sql.Connection


class ProgrammeHandler {

    companion object {
        @JvmStatic
        fun saveProgramme(pr: Programme){
            var programmeTemp = pr

            if (programmeTemp.graduate == "undergraduate") {
                for (i in 1..3) {
                    programmeTemp.year = i
                    for (j in 1..2) {
                        programmeTemp.term = j
                        DatabaseManager.insertRowProgramme(programmeTemp)
                    }
                }
            }else {
                programmeTemp.year = 1
                for (j in 1..2) {
                    programmeTemp.term = j
                    DatabaseManager.insertRowProgramme(programmeTemp)
                }
            }
        }
        @JvmStatic
        fun deleteProgramme(name: String = ""){
            DatabaseManager.deleteRowProgramme(name)
        }
        @JvmStatic
        fun createProgramme(name: String = "", year: Int = 0, term: Int = 0, graduate: String = ""): Programme{
            val programme = Programme(name = name, year = year, term = term, graduate = graduate)
            print(programme.graduate)
            return programme
        }
    }
}