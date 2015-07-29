/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


jQuery(document).ready(function() {
    jQuery("#areaBusca").hide();
    
    jQuery(".btn-search-grid").click(function() {
        jQuery("#areaBusca").slideToggle();
        return false;
    });
});