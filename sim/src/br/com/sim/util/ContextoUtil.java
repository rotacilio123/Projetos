/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sim.util;

import br.com.sim.model.bean.Usuario;
import br.com.sim.rn.UsuarioRn;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author RodrigoOtacilio
 */
public class ContextoUtil {

    public static Usuario getUsuarioLogado() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            return new UsuarioRn().carregarPorLogin(login);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }
}
