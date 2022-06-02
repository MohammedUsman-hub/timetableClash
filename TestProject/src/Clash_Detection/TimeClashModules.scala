package Clash_Detection

import sun.security.pkcs11.Secmod.Module

object TimeClashModules {
  // It is omega(n^2)
  def clashFree(listModules: List[Module]): Boolean = {
    (for (x <- listModules; y <- listModules if x clash y)                          //for (i=0, i<N ; i++)
      yield (x, y)                     //new pair with a result of a list              //for(j=0), i<N ; j++)
      ) isEmpty                                                                         //if cla(1).cla(jh) return true
  }

  def clash_Free(listModules: List[hour]): Boolean = {
    (for (x <- listModules; y <- listModules if x clashfree y)                        //
      yield (x, y)
      ) isEmpty
  }

  def ModuleID(list: List[Module], id: String): Lecturer = {
    (list filter { lecturer => lecturer.id == id }) (0)
  }

}
