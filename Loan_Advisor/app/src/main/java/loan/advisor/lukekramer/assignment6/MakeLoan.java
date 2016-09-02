package loan.advisor.lukekramer.assignment6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import loan.advisor.lukekramer.assignment6.chain.ClientChainSetup;
import loan.advisor.lukekramer.assignment6.chain.LoanChainSetup;
import loan.advisor.lukekramer.assignment6.clientapi.GetPerson;
import loan.advisor.lukekramer.assignment6.repository.login.Impl.LoginRepositoryImpl;

public class MakeLoan extends AppCompatActivity {
    private EditText amount;
    private EditText earning;
    private String loginid;
    private String result;
    private String userid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeloan);
        this.amount = (EditText) findViewById(R.id.Result_EditText);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            this.userid = (String) b.get(LoginRepositoryImpl.COLUMN_USERID);
            this.loginid = (String) b.get("loginid");
        }
    }

    public void MailResult(View view) throws InterruptedException {
        GetPerson p = new GetPerson();
        p.setUserid(this.userid);
        p.execute(new Void[0]);
        Thread.sleep(3000);
        String type = ClientChainSetup.getClientType(Long.parseLong(p.getIncome()));
        Long cash = Long.valueOf(Long.parseLong(this.amount.getText().toString()));
        if (cash.longValue() >= 1000 && cash.longValue() <= 1000000) {
            this.result = LoanChainSetup.getLoanType(type, Long.parseLong(this.amount.getText().toString()));
            Toast.makeText(this, "OutCome " + this.result, 1).show();
        }
        Intent i = new Intent(getApplicationContext(), Output.class);
        i.putExtra("Result", this.result.toString());
        startActivity(i);
    }

    public void Logout(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
