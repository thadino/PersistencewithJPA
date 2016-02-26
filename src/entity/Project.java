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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dino
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @ElementCollection
    List<ProjectUser> contributers = new ArrayList();
    @OneToMany
    List<Task> tasklist = new ArrayList();
    


    public List<ProjectUser> getContributers() {
        return contributers;
    }

    public void setContributers(List<ProjectUser> contributers) {
        this.contributers = contributers;
    }
    
    public void addContributers(String Username, String Projectname)
    {
      EntityManager em = getEM();
      Query query = em.createQuery("SELECT s from ProjectUser s WHERE s.userName = ?1");
      query.setParameter(1, Username);
      ProjectUser user = (ProjectUser)query.getSingleResult();
      
      System.out.println(user.getId());
      
      contributers.add(user);
      System.out.println(contributers);
      
      em.getTransaction().begin();

      em.persist(contributers);
      
      em.getTransaction().commit();
      
    }
    
    private EntityManager getEM()
    {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistencewithJPAPU");
     EntityManager em = emf.createEntityManager();
     return em;
    }
    
    

    public List<Task> getTasklist() {
        return tasklist;
    }

    public void setTasklist(List<Task> tasklist) {
        this.tasklist = tasklist;
    }
    public void addTask(Task task)
    {
      this.tasklist.add(task);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date Created) {
        this.Created = Created;
    }

    public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }
    String name;
    String description;
    
    @Temporal(TemporalType.DATE)
    Date Created, lastmodified;

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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Project[ id=" + id + " ]";
    }

    public void CreateProject(String name, String disc) {
     EntityManager em = getEM();
     
     Project newproject = new Project();
     newproject.setName(name);
     newproject.setDescription(disc);
     newproject.setCreated(Date.from(Instant.now()));
     newproject.setLastmodified(Date.from(Instant.now()));
     
     em.getTransaction().begin();
     em.persist(newproject);
     em.getTransaction().commit();
    }
    
}
