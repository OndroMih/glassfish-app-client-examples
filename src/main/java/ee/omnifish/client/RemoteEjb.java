/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ee.omnifish.client;

import jakarta.ejb.Remote;

/**
 *
 * @author ondro
 */
@Remote
interface RemoteEjb {
    void hello();
}
