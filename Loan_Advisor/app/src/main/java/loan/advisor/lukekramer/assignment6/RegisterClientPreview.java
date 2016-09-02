package loan.advisor.lukekramer.assignment6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import loan.advisor.lukekramer.assignment6.clientapi.PersonLoginPost;
import loan.advisor.lukekramer.assignment6.clientapi.RestPerson;
import loan.advisor.lukekramer.assignment6.email.SendMail;
import loan.advisor.lukekramer.assignment6.entity.Login;
import loan.advisor.lukekramer.assignment6.entity.Person;
import loan.advisor.lukekramer.assignment6.entity.Person.Builder;
import loan.advisor.lukekramer.assignment6.repository.login.Impl.LoginRepositoryImpl;
import loan.advisor.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;
import loan.advisor.lukekramer.assignment6.repository.tables.CreateTables;
import loan.advisor.lukekramer.assignment6.services.client.ActivateAddClientService;
import loan.advisor.lukekramer.assignment6.services.client.Impl.ActivateClientServiceImpl;

public class RegisterClientPreview extends AppCompatActivity {
    private TextView Textemail;
    private TextView Textfirstname;
    private TextView Textincome;
    private TextView Textlastname;
    private TextView Textphonenum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_client_preview);
        this.Textfirstname = (TextView) findViewById(R.id.firstname_TextView);
        this.Textlastname = (TextView) findViewById(R.id.lastname_TextView);
        this.Textphonenum = (TextView) findViewById(R.id.phonenumber_TextView);
        this.Textemail = (TextView) findViewById(R.id.email_TextView);
        this.Textincome = (TextView) findViewById(R.id.income_TextView);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            this.Textfirstname.setText((String) b.get("firstName"));
            this.Textlastname.setText((String) b.get("lastName"));
            this.Textphonenum.setText((String) b.get("phoneNumber"));
            this.Textemail.setText((String) b.get("emailAddress"));
            this.Textincome.setText((String) b.get(PersonRepositoryImpl.COLUMN_INCOME));
        }
    }

    public void btnSubmit(View view) throws Exception {
        new CreateTables(getApplicationContext()).createTables();
        ActivateAddClientService clientService = ActivateClientServiceImpl.getInstance();
        Long income = Long.valueOf(Long.parseLong(this.Textincome.getText().toString()));
        Person person = new Builder().FirstName(this.Textfirstname.getText().toString()).Email(this.Textemail.getText().toString()).Income(income.longValue()).LastName(this.Textlastname.getText().toString()).PhoneNumber(this.Textphonenum.getText().toString()).build();
        clientService.addClient(getApplication(), person);
        long id = person.getId();
        Context applicationContext = getApplicationContext();
        long loginid = ((Login) new LoginRepositoryImpl().save(new Login.Builder().Userid(id).Username(this.Textfirstname.getText().toString()).Password(this.Textfirstname.getText().toString() + this.Textlastname.getText().toString() + "#1").build())).getId();
        String login = String.valueOf(loginid);
        RestPerson p = new RestPerson();
        p.setEmail(this.Textemail.getText().toString());
        p.setFname(this.Textfirstname.getText().toString());
        p.setNumber(this.Textphonenum.getText().toString());
        p.setLname(this.Textlastname.getText().toString());
        p.setuserIncome(income.longValue());
        p.execute(new Void[0]);
        Thread.sleep(2000);
        String userid = p.getId().substring(p.getId().lastIndexOf("/") + 1);
        String password = this.Textfirstname.getText().toString() + this.Textlastname.getText().toString() + "#1";
        PersonLoginPost l = new PersonLoginPost();
        l.setClientid(Long.parseLong(userid));
        l.setUsername(this.Textfirstname.getText().toString());
        l.setPassword(password);
        l.execute();
        Thread.sleep(2000);
        String logid = l.getId().substring(l.getId().lastIndexOf("/") + 1);
        String str = "Login Details";
        new SendMail(this, this.Textemail.getText().toString(), str, "username:" + logid + "\n" + "password:" + password).execute(new Void[0]);
        startActivity(new Intent(this, MainActivity.class));
    }
}
