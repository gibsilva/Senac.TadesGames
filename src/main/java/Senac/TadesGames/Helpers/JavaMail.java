/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Helpers;

import Senac.TadesGames.Models.UsuarioModel;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Gi
 */
public class JavaMail {

    private static final Properties props = new Properties();

    public static void enviarEmail(UsuarioModel usuario, String novaSenha) {
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("gamestades@gmail.com", "tadesGames#1");
                    }
                });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gamestades@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(usuario.getEmail());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Reset de senha - Tades Games");//Assunto
            message.setContent(
                    "\n"
                    + "<body>\n"
                    + "<h2>Tades Games</h2>\n"
                    + "<h3>Reset de senha</h3>\n"
                    + "<p>Olá, <b>" + usuario.getNome() + "</b> sua senha foi resetada com sucesso, a nova senha é:<p/>\n"
                    + "<strong>" + novaSenha + "</strong>\n"
                    + "<p>Recomendamos que você altere sua senha após o login e exclua esse e-mail.</p>\n"
                    + "<p>Atenciosamente, </p>\n"
                    + "<p><i>&copy;TadesGames</i></p>\n"
                    + "</body>\n",
                    "text/html");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
