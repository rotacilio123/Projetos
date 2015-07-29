/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jbjunior
 */
public class Utils {

    public static String textToMd5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(texto.getBytes()));
            return String.format("%32x",hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return null;
    }
}
