/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dino
 */
@Entity
public class ProjectUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String userName;
    String email;
    @Temporal(TemporalType.DATE)
    Date Created;
    
    
         
    @ManyToMany(mappedBy = "contributers")
    @ElementCollection
    List<Project> projects = new ArrayList();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date Created) {
        this.Created = Created;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    public void addProject(Project project)
    {
      this.projects.add(project);
    }
    
        private EntityManager getEM()
    {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistencewithJPAPU");
     EntityManager em = emf.createEntityManager();
     return em;
    }
    
    public void addUser(String name, String email)
    {
      EntityManager em = getEM();
      
      ProjectUser newuser = new ProjectUser();
      newuser.setCreated(Date.from(Instant.now()));
      newuser.setUserName(name);
      newuser.setEmail(email);

      em.getTransaction().begin();
      em.persist(newuser);
      em.getTransaction().commit();
    }
    
    public ProjectUser FindUser(String name) {
     EntityManager em = getEM();
     
    Query query = em.createQuery("Select s from ProjectUser s WHERE s.userName = ?1");
    query.setParameter(1, name);
    ProjectUser user = (ProjectUser)query.getSingleResult();
        
    return user;
    }
    
    public List<ProjectUser> GetAllUsers() {
    EntityManager em = getEM();
     
    Query query = em.createQuery("Select s from ProjectUser s");
    List<ProjectUser> users = query.getResultList();
        return users;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectUser)) {
            return false;
        }
        ProjectUser other = (ProjectUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProjectUser[ id=" + id + " ]";
    }
    
}
