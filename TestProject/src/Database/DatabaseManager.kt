package Database

import Entities.Activity
import Entities.Module
import Entities.Programme
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*
import javax.print.attribute.IntegerSyntax


object DatabaseManager {


    @JvmStatic
    fun main(args: Array<String>) {
        var dbConnection: Connection? = null
        try {
            val url = "jdbc:mysql://localhost:3306/mysql"
            val info = Properties()
            info["user"] = "root"
            info["password"] = "MarkusWolf"

            dbConnection = DriverManager.getConnection(url, info)
            if (dbConnection != null) {
                println("Successfully connected to MySQL database test")
                //insertRowModule(Module("HCI",0,1,1,20))
                //insertRowProgramme(Programme( "undergraduate","Computer Science",1,1))
            }
        } catch (ex: SQLException) {
            println("An error occurred while connecting MySQL databse")
            ex.printStackTrace()
        }


    }

    val c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "MarkusWolf");
    val schema = "jvm_cw"

    fun deleteRowProgramme(name: String) {
        val table = "programmes_table"
        val sql = "DELETE FROM $schema.$table WHERE name='$name';"
        with(c) {
            createStatement().execute(sql)
        }
    }

    fun insertRowProgramme(programme: Programme) {
        val table = "programmes_table"
        val name = programme.name
        val graduate = programme.graduate
        val year = programme.year
        val term = programme.term
        val sql =
            "INSERT INTO $schema.$table (graduate, name, year, term) VALUES (\"$graduate\", \"$name\", $year, $term);"
        with(c) {
            createStatement().execute(sql)
            //commit()
        }
    }

    fun deleteRowModule(name: String) {
        val table = "modules_table"
        val sql = "DELETE FROM $schema.$table WHERE name='$name';"
        with(c) {
            createStatement().execute(sql)
        }
    }

    fun insertRowModule(module: Module) {
        val table = "modules_table"
        val name = module.name
        val compulsory = module.compulsory
        val programme_id = module.programme_id
        val sql =
            "INSERT INTO $schema.$table (programme_id, name, compulsory) VALUES ($programme_id, \"$name\", $compulsory);"
        with(c) {
            createStatement().execute(sql)
            //commit()
        }
    }


    @JvmStatic
    //this function returns a list of all the programme names in the database
    fun getProgrammeList(): MutableList<String> {
        with(c) {
            val programmeList = mutableListOf<String>()
            val all = c.prepareStatement(
                "SELECT *\n" +
                        "FROM jvm_cw.programmes_table;"
            )
            val rs = all.executeQuery()
            while (rs.next()) {
                programmeList.add(rs.getString(2))
                // println(rs.getString(3))//here you can get data, the columnIndex parameter indicates column number based on your query
            }
            val set: Set<String> = HashSet<String>(programmeList)
            programmeList.clear()
            programmeList.addAll(set)
            return programmeList
            /*
            val count = c.prepareStatement("SELECT COUNT(*)\n" +
                    "FROM jvm_cw.programmes_table;" )
            val countNr = count.executeQuery().getString(2)
            println("number of elements" + countNr)
            */

        }
    }
    @JvmStatic
    fun getProgrammeId(programme: Programme): Int {
        val name = programme.name
        val graduate = programme.graduate
        val year = programme.year
        val term = programme.term
        var programme_id = 0
        val all = c.prepareStatement(
            "SELECT *\n" +
                    "FROM jvm_cw.programmes_table as t\n" +
                    "WHERE t.name = \"$name\" AND t.graduate = \"$graduate\" AND t.year = $year AND t.term = $term;"
        )
        val rs = all.executeQuery()
        while (rs.next()) {
            programme_id = rs.getInt(1)
            // println(rs.getString(3))//here you can get data, the columnIndex parameter indicates column number based on your query
        }
        return programme_id
    }

    @JvmStatic
    fun getModuleId(module: Module): Int {
        val name = module.name
        val compulsory = module.compulsory
        val programme_id = module.programme_id
        var module_id = 0
        val all = c.prepareStatement(
            "SELECT *\n" +
                    "FROM jvm_cw.modules_table as t\n" +
                    "WHERE t.name = \"$name\" AND t.compulsory = $compulsory AND t.programme_id = $programme_id;"
        )
        val rs = all.executeQuery()
        while (rs.next()) {
            module_id = rs.getInt(1)
            // println(rs.getString(3))//here you can get data, the columnIndex parameter indicates column number based on your query
        }
        return module_id
    }
    @JvmStatic
    fun getModuleFromName(name: String): Module {
        var compulsory = 0
        var programme_id = 0
        val all = c.prepareStatement(
            "SELECT *\n" +
                    "FROM jvm_cw.modules_table as t\n" +
                    "WHERE t.name = \"$name\";"
        )
        val rs = all.executeQuery()
        while (rs.next()) {
            compulsory = rs.getInt(3)
            programme_id = rs.getInt(4)
        // println(rs.getString(3))//here you can get data, the columnIndex parameter indicates column number based on your query
        }
        val module = Module(name, compulsory, programme_id)
        return module
    }

    @JvmStatic
    fun getModuleList(): MutableList<String> {
        with(c) {
            val moduleList = mutableListOf<String>()
            val all = c.prepareStatement(
                "SELECT *\n" +
                        "FROM jvm_cw.modules_table;"
            )
            val rs = all.executeQuery()
            while (rs.next()) {
                moduleList.add(rs.getString(2))
                // println(rs.getString(3))//here you can get data, the columnIndex parameter indicates column number based on your query
            }
            val set: Set<String> = HashSet<String>(moduleList)
            moduleList.clear()
            moduleList.addAll(set)
            return moduleList
        }
    }

    @JvmStatic
    fun getModuleListWhereProgrammeId(programme_id: Int): MutableList<String> {
        with(c) {
            val moduleList = mutableListOf<String>()
            val all = c.prepareStatement(
                "SELECT *\n" +
                        "FROM jvm_cw.modules_table as t WHERE t.programme_id = $programme_id;"
            )
            val rs = all.executeQuery()
            while (rs.next()) {
                moduleList.add(rs.getString(2))
                // println(rs.getString(3))//here you can get data, the columnIndex parameter indicates column number based on your query
            }
            return moduleList
        }
    }
    /*
    @JvmStatic
    fun getModule_idList(moduleList: List<String>): MutableList<Int> {
        with(c) {
            val module_idList = mutableListOf<Int>()
            for (i in 0..moduleList.size) {

                var modulename = moduleList[i]
                var previousmodulename: String ?= moduleList[i-1]

                if (modulename == previousmodulename) {
                    continue
                }
                val moduleName = modulename
                val all = c.prepareStatement(
                    "SELECT *\n" +
                            "FROM jvm_cw.modules_table as t WHERE t.name = $moduleName;"
                )
                val rs = all.executeQuery()
                while (rs.next()) {
                    module_idList.add(rs.getInt(1))
                //here you can get data, the columnIndex parameter indicates column number based on your query
                }
            }
            return module_idList
        }
    }

     */

    fun insertRowActivity(actTemp: Activity) {
        val type = actTemp.type
        val modulename = actTemp.modulename
        val time = actTemp.time
        val day = actTemp.day
        val sql =
            "INSERT INTO jvm_cw.activities_table (modulename, type, day, time) VALUES (\"$modulename\", \"$type\", $day, $time);"
        with(c) {
            createStatement().execute(sql)
            //commit()
        }
    }
    @JvmStatic
    fun getActivityList(): MutableList<String> {
        with(c) {
            val ActivityList = mutableListOf<String>()
            val all = c.prepareStatement(
                "SELECT *\n" +
                        "FROM jvm_cw.activities_table;"
            )
            val rs = all.executeQuery()
            while (rs.next()) {
                ActivityList.add(rs.getString(1))
                // println(rs.getString(3))//here you can get data, the columnIndex parameter indicates column number based on your query
            }
            val set: Set<String> = HashSet<String>(ActivityList)
            ActivityList.clear()
            ActivityList.addAll(set)
            return ActivityList
        }
    }

    @JvmStatic
    fun deleteRowActivity(type: String) {
        val table = "activities_table"
        val sql = "DELETE FROM $schema.$table WHERE type='$type';"
        with(c) {
            createStatement().execute(sql)
        }
    }
    @JvmStatic
    fun getActivityFromModuleName(modulename1: String): Activity {
        var type = ""
        var day = 0
        var time = 0
        val modulename = modulename1
        val all = c.prepareStatement(
            "SELECT *\n" +
                    "FROM jvm_cw.activities_table as t \n" +
                    "WHERE t.modulename = \"$modulename\";"
        )
        val rs = all.executeQuery()
        while (rs.next()) {
            type = rs.getString(2)
            day = rs.getInt(3)
            time = rs.getInt(4)
        }
        val activity = Activity(modulename, type, day, time)
        return activity
    }
    /*
    @JvmStatic
    fun getActivityFromId(id: Int): Activity {
        var module_id = 0
        var type = ""
        var day = 0
        var time = 0
        val all = c.prepareStatement(
            "SELECT *\n" +
                    "FROM jvm_cw.activities_table as t\n" +
                    "WHERE t.id = $id;"
        )
        val rs = all.executeQuery()
        while (rs.next()) {
            module_id = rs.getInt(2)
            type = rs.getString(3)
            day = rs.getInt(4)
            time = rs.getInt(5)
        }
        val activity = Activity(module_id, type, day, time)
        return activity
    }
*/

}