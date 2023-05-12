/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ee.omnifish.client;

import jakarta.ejb.EJB;

/**
 *
 * @author ondro
 */
public class Main {

    @EJB
    static RemoteEjb ejb;

    public static void main(String[] args) {
        System.out.println("Client");
        ejb.hello();
        System.out.println("Finished");
    }
}
