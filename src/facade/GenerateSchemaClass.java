/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.Persistence;

/**
 *
 * @author Dino
 */
public class GenerateSchemaClass {
    public static void main(String[] args) {
     Persistence.generateSchema("PersistencewithJPAPU", null);     
    } 

}
