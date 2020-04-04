/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botjoke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.JSONObject;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author utilisateur
 */
public class Commands extends ListenerAdapter{
    
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
     
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "info")){
            event.getChannel().sendTyping().queue();
          
            if(!BotJoke.muted){
                MessageBuilder builder = new MessageBuilder();
                builder.append("Salut ! Avec moi tu vas bien rire ! Tape ~commandes pour connaitre toutes mes fonctionnalités.\n");
                builder.append("Tape ~Mute pour me faire taire et ~Unmute si tu veux entendre ma douce voix.");
                builder.setTTS(true);
                builder.sendTo(event.getChannel()).queue();
            } else {
                event.getChannel().sendMessage("Hey ! I am here to make you laugh !").queue();
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "commandes")){
            event.getChannel().sendTyping().queue();
            if(!BotJoke.muted){
                MessageBuilder builder = new MessageBuilder();
                builder.append("Voici la liste des commandes :");
                builder.setTTS(true);
                builder.sendTo(event.getChannel()).queue();
            } else {
                event.getChannel().sendMessage("Voici la liste des commandes :").queue();
            }
            EmbedBuilder commands = new EmbedBuilder();
            commands.setTitle("Liste des commandes");
            commands.addField("~info","Avoir des infos sur moi",false);
            commands.addField("~NombreDeBlagues","Nombre de blagues que je connais",false);
            commands.addField("~NombreDeBlaguesToto","Nombre de blagues de Toto que je connais",false);
            commands.addField("~NombreDeBlaguesDieu","Nombre de blagues sur Dieu que je connais",false);
            commands.addField("~NombreDeBlaguesDivers","Nombre de blagues de type Divers que je connais",false);
            commands.addField("~BlagueAleatoire","Si tu veux que je te fasses une blague",false);
            commands.addField("~BlagueAleatoireToto","Si tu veux que je te fasses une blague de Toto",false);
            commands.addField("~BlagueAleatoireDieu","Si tu veux que je te fasses une blague sur Dieu",false);
            commands.addField("~BlagueAleatoireDivers","Si tu veux que je te fasses une blague de type Divers",false);
            commands.addField("~BlagueToto IND","Si tu veux que je te fasses la blague de toto d'indice IND",false);
            commands.addField("~BlagueDieu IND","Si tu veux que je te fasses la blague sur Dieu d'indice IND",false);
            commands.addField("~BlagueDivers IND","Si tu veux que je te fasses la blague de type Divers d'indice IND",false);
            
            event.getChannel().sendMessage(commands.build()).queue();
        }
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "NombreDeBlagues")){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/nombreDeBlagues");
                String rep = readNombre(url);
                
                event.getChannel().sendTyping().queue();
                
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append("Je connais " + rep + " blagues.");
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage("Je connais " + rep + " blagues.").queue();
                }
                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "NombreDeBlaguesToto")){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/nombreDeBlaguesToto");
                String rep = readNombre(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append("Je connais " + rep + " blagues de Toto.");
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage("Je connais " + rep + " blagues de Toto.").queue();
                }
                                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "NombreDeBlaguesDieu")){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/nombreDeBlaguesDieu");
                String rep = readNombre(url);
                               
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append("Je connais " + rep + " blagues sur Dieu.");
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage("Je connais " + rep + " blagues sur Dieu.").queue();
                }
                                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "NombreDeBlaguesDivers")){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/nombreDeBlaguesDivers");
                String rep = readNombre(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append("Je connais " + rep + " blagues de type Divers.");
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage("Je connais " + rep + " blagues de type Divers.").queue();
                }
                                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "BlagueAleatoire")){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/blagueAleatoire");
                String rep = readBlague(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(rep);
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage(rep).queue();
                }
                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "BlagueAleatoireToto")){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/blagueTotoAleatoire");
                String rep = readBlague(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(rep);
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage(rep).queue();
                }
                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "BlagueAleatoireDieu")){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/blagueDieuAleatoire");
                String rep = readBlague(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(rep);
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage(rep).queue();
                }
                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "BlagueAleatoireDivers")){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/blagueDiversAleatoire");
                String rep = readBlague(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(rep);
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage(rep).queue();
                }

            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "BlagueDivers") && args[1]!=null){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/blagueDivers?indice="+args[1]);
                String rep = readBlague(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(rep);
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage(rep).queue();
                }
                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "BlagueToto") && args[1]!=null){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/blagueToto?indice="+args[1]);
                String rep = readBlague(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                builder.append(rep);
                builder.setTTS(true);
                builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage(rep).queue();
                }
                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "BlagueDieu") && args[1]!=null){
            try {
                URL url = new URL("https://viteuneblague.herokuapp.com/webresources/blague/blagueDieu?indice="+args[1]);
                String rep = readBlague(url);
                
                event.getChannel().sendTyping().queue();
                if(!BotJoke.muted){
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(rep);
                    builder.setTTS(true);
                    builder.sendTo(event.getChannel()).queue();
                } else {
                    event.getChannel().sendMessage(rep).queue();
                }

            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "Mute")){
            BotJoke.mute(true);
        }
        
        if(args[0].equalsIgnoreCase(BotJoke.prefix + "Unmute")){
            BotJoke.mute(false);
        }
    }
    
    public String readNombre(URL url){
        try {
            HttpsURLConnection connexion = (HttpsURLConnection) url.openConnection();
            connexion.connect();
                
            InputStream fluxReponse = connexion.getInputStream();
            BufferedReader buffeurReception = new BufferedReader(new InputStreamReader(fluxReponse));
            String ligne = "";
            StringBuilder reponse = new StringBuilder();
            while ((ligne = buffeurReception.readLine()) != null){
                reponse.append(ligne);
            }
               
            connexion.disconnect();
            
            return reponse.toString();
                
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }catch(IOException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        return "";
    }
    
    public String readBlague(URL url){
        try {
            HttpsURLConnection connexion = (HttpsURLConnection) url.openConnection();
            connexion.connect();
                
            InputStream fluxReponse = connexion.getInputStream();
            BufferedReader buffeurReception = new BufferedReader(new InputStreamReader(fluxReponse));
            String ligne = "";
            StringBuilder reponse = new StringBuilder();
            while ((ligne = buffeurReception.readLine()) != null){
                reponse.append(ligne + "\n");
            }
               
            connexion.disconnect();

            return reponse.toString();
            
            }catch(MalformedURLException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }catch(IOException ex){
                Logger.getLogger(BotJoke.class.getName()).log(Level.SEVERE, null, ex);
            }
        return "";
    }
   
    
}
