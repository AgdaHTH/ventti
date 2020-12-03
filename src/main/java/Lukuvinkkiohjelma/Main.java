/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lukuvinkkiohjelma;

import Lukuvinkkiohjelma.dao.VinkkiJsonDao;
import Lukuvinkkiohjelma.io.ConsoleIO;
import Lukuvinkkiohjelma.io.IO;
import Lukuvinkkiohjelma.ui.Tekstikayttoliittyma;

/**
 *
 * @author mari
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Tekstikayttoliittyma tekstikayttoliittyma
                = new Tekstikayttoliittyma(new ConsoleIO(), new VinkkiJsonDao("vinkit.json"));

        tekstikayttoliittyma.kaynnista();
    }

}
