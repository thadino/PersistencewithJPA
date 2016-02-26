/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Project;
import entity.ProjectUser;
import entity.Task;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Dino
 */
public class facade {
    

    private static Project p = new Project();
    private static ProjectUser pu = new ProjectUser();
    private static Task t = new Task();

    public static void main(String[] args) {
      int dowhat = 5;  
      
      if(dowhat == 1)
      {
      CreateUser("Name!!..", "mymail!!@provider.language");
      }
      
      if(dowhat == 2)
      {
      ProjectUser finduser = FindUser("Name.."); // date format is wrong (would fix if it mattered alot for the assignment.)
      System.out.println("Username: " + finduser.getUserName() + " Email: " + finduser.getEmail() + " User Created:" + finduser.getCreated());
      }
      
     if(dowhat == 3)
     {
      List<ProjectUser> ProjectUsers = FindAllUsers();
         for (ProjectUser ProjectUser1 : ProjectUsers) {
             System.out.println(ProjectUser1.getUserName());  
         }
     }
     
     if(dowhat == 4)
     {
         CreateProject("Project 27", "About deez nuts");
     }
     
     AssignUserToProject("Name..", "1");
     
    }

    private static void CreateUser(String name, String email) {
      
      pu.addUser(name, email);
      
    }

    private static ProjectUser FindUser(String name) {
     return pu.FindUser(name);
    }

    private static List<ProjectUser> FindAllUsers() {
        
        return pu.GetAllUsers();
    }

    private static void CreateProject(String name, String disc) {
     
       p.CreateProject(name, disc);
    }

    private static void AssignUserToProject(String Username, String Projectname) {
      p.addContributers(Username, Projectname);
    }

    private static void FindProject(String name) {

    }

    private static void CreateTaskAndAssignToProject() {
   
    }

}
