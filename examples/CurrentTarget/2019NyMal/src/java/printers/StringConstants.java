/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printers;

/**
 *
 * @author hallgeir
 */
public interface StringConstants {

        String DIV ="<div class = '%s'>";
        String FORM  = "<form action = '%s'  method = POST>";  
        String INP = "<input type='%s' & name='%s' & value='%s'>" +"<br>"; 
        String INPREAD = "<input type='%s' & name='%s' & value='%s' size= '%s' readonly>";
        String INPSUB = "<input type='%s' & name='%s' & value='%s'>" +"  ";
        String TABLE = "<table>";
        
        String STUDENT  = "<li><a href='studentDetail?snr=%s&firstName=%s&lastName=%s'>%s %s %s</a></li>\n"; 
        String ENSTUD  = "<option value='%s'>'%s'</option>"; 
        String LIST = "<select name=?>";
        String LISTSLUTT="</select>";
       
        String STUDENTLINK  = "<option value = '<a href='studentDetail?snr=%s&firstName=%s&lastName=%s'>%s %s %s</a>'</option>"; 
       // String STUDENTLINK  = "<option value = 'studentDetail?snr=%s&firstName=%s&lastName=%s'>%s %s %s</option>"; 
        
        String BUTTON = "<button> %s </button>";
        String LINK2 = "<a href='studentDetail?snr=%s&firstName=%s&lastName=%s'>%s %s %s</a>";
}       
