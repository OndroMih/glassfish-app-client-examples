/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ee.omnifish.client;

import jakarta.ejb.Stateless;

/**
 *
 * @author ondro
 */
@Stateless
public class RemoteEjbInstance implements RemoteEjb {

    @Override
    public void hello() {
        System.out.println("Hello from a remote EJB");
    }
}
