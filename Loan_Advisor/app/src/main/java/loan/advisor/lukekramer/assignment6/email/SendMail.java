package loan.advisor.lukekramer.assignment6.email;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail extends AsyncTask<Void, Void, Void> {
    private Context context;
    private String email;
    private String message;
    private ProgressDialog progressDialog;
    private Session session;
    private String subject;

    /* renamed from: loan.advisor.lukekramer.assignment6.email.SendMail.1 */
    class C03451 extends Authenticator {
        C03451() {
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
        }
    }

    public SendMail(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog = ProgressDialog.show(this.context, "Sending message", "Please wait...", false, false);
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.progressDialog.dismiss();
        Toast.makeText(this.context, "Message Sent", 1).show();
    }

    protected Void doInBackground(Void... params) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        this.session = Session.getDefaultInstance(props, new C03451());
        try {
            MimeMessage mm = new MimeMessage(this.session);
            mm.setFrom(new InternetAddress(Config.EMAIL));
            mm.addRecipient(RecipientType.TO, new InternetAddress(this.email));
            mm.setSubject(this.subject);
            mm.setText(this.message);
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
