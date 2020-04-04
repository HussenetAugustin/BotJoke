/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botjoke;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;


/**
 *
 * @author utilisateur
 */
public class BotJoke {
    
    public static JDA jda;
    public static String prefix = "~";
    public static Boolean muted = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LoginException{
        jda = new JDABuilder(AccountType.BOT).setToken("Njk2MDY5NjY1OTAxOTA0MDIy.XojXug.DsI39NoMvnkt1iO3iue7oAJ4xs4").build();
        
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setPresence(Activity.playing("Telling Great Jokes"), true);
        
        jda.addEventListener(new Commands());
        
    }
    
    public static void mute(Boolean m){
        muted = m;
    }
    
}
