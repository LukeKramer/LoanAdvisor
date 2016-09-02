package loan.advisor.lukekramer.assignment6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import loan.advisor.lukekramer.assignment6.email.SendMail;

public class Output extends AppCompatActivity {
    private EditText MailResult;
    private TextView Result;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        this.Result = (TextView) findViewById(R.id.ResultTextView);
        this.MailResult = (EditText) findViewById(R.id.MailResulteditText);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            this.Result.setText((String) b.get("Result"));
        }
    }

    public void Mail(View view) {
        new SendMail(this, this.MailResult.getText().toString(), "Loan Checker result", this.Result.getText().toString()).execute(new Void[0]);
    }

    public void Home(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
