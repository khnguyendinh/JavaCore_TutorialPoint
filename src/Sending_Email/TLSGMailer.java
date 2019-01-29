package Sending_Email;
import java.io.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class TLSGMailer {
    private static Properties gMailConfig = new Properties();

    static {
        fetchPassword();
        gMailConfig.put("mail.transport.protocol", "smtp");
        gMailConfig.put("mail.smtp.auth", "true");
        gMailConfig.put("mail.smtp.starttls.enable", "true");
        gMailConfig.put("mail.smtp.port", "587");
        gMailConfig.put("mail.smtp.host", "smtp.gmail.com");
    }

    public TLSGMailer()
    {
        System.out.println("Loading TLSGMailer class...");
    }

    public static void main(String[] args) {
        sendTestNGResult("khoas401@gmail.com","khnguyendinh@gmail.com","Title","content","sssss");
    }
    public static void sendTestNGResult(String mFrom, String mTo, String mTitle, String mText, String htmlPayload)
    {
        Session sessionTLS = Session.getInstance( gMailConfig );
        sessionTLS.setDebug(true);

        MimeMessage messageTLS = new MimeMessage(sessionTLS);
        try {
            messageTLS.setFrom( new InternetAddress( mFrom ) );
            MimeMultipart multipart = new MimeMultipart();
            messageTLS.setRecipients( Message.RecipientType.TO, InternetAddress.parse( mTo ) );
            MimeBodyPart tmpBp1 = new MimeBodyPart();
            MimeBodyPart tmpBp2 = new MimeBodyPart();
            messageTLS.setSubject( mTitle);
            tmpBp1.setContent( mText, "text/plain");
            multipart.addBodyPart(tmpBp1);
            tmpBp2.setContent( htmlPayload, "text/html");
            multipart.addBodyPart(tmpBp2);
            messageTLS.setContent(multipart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Transport transportTLS;
        try {
            transportTLS = sessionTLS.getTransport();
            transportTLS.connect( "smtp.gmail.com" , 587, mFrom, gMailConfig.getProperty("mail.smtp.password") );
            transportTLS.sendMessage( messageTLS, messageTLS.getAllRecipients() );
            transportTLS.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException ex){
            System.err.println("Cannot send email. " + ex);
        }

    }

    public void sendRegularEmail(String mFrom, String mTo, String mTitle, String mText )
    {
        Session sessionTLS = Session.getInstance( gMailConfig );
        sessionTLS.setDebug(true);

        MimeMessage messageTLS = new MimeMessage(sessionTLS);
        try {
            messageTLS.setFrom( new InternetAddress( mFrom ) );
            MimeMultipart multipart = new MimeMultipart();
            messageTLS.setRecipients( Message.RecipientType.TO, InternetAddress.parse( mTo ) );
            MimeBodyPart tmpBp1 = new MimeBodyPart();
            messageTLS.setSubject( mTitle);
            tmpBp1.setContent( mText, "text/plain");
            multipart.addBodyPart(tmpBp1);
            messageTLS.setContent(multipart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Transport transportTLS;
        try {
            transportTLS = sessionTLS.getTransport();
            transportTLS.connect( "smtp.gmail.com" , 587, mFrom, gMailConfig.getProperty("mail.smtp.password") );
            transportTLS.sendMessage( messageTLS, messageTLS.getAllRecipients() );
            transportTLS.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException ex){
            System.err.println("Cannot send email. " + ex);
        }

    }

    private static void fetchPassword() {
        InputStream input = null;
        try {
            input = new FileInputStream( "gmail.password" );
            gMailConfig.load( input );
        }
        catch ( IOException ex ){
            System.err.println("Cannot open or load gmail.password file.");
        }
        finally {
            try {
                if ( input != null ) input.close();
            }
            catch ( IOException ex ){
                System.err.println( "Cannot close gmail.password file." );
            }
        }
    }
}
