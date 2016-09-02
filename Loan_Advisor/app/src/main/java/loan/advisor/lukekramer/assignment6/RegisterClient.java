package loan.advisor.lukekramer.assignment6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import loan.advisor.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;

public class RegisterClient extends AppCompatActivity {
    private EditText emailAddress;
    private EditText firstName;
    private EditText income;
    private EditText lastName;
    private EditText phoneNumber;
    private Button previewButton;

    /* renamed from: loan.advisor.lukekramer.assignment6.RegisterClient.1 */
    class C02001 implements OnClickListener {
        C02001() {
        }

        public void onClick(View v) {
            Intent i = new Intent(RegisterClient.this.getApplicationContext(), RegisterClientPreview.class);
            i.putExtra("firstName", RegisterClient.this.firstName.getText().toString());
            i.putExtra("lastName", RegisterClient.this.lastName.getText().toString());
            i.putExtra("phoneNumber", RegisterClient.this.phoneNumber.getText().toString());
            i.putExtra("emailAddress", RegisterClient.this.emailAddress.getText().toString());
            i.putExtra(PersonRepositoryImpl.COLUMN_INCOME, RegisterClient.this.income.getText().toString());
            RegisterClient.this.startActivity(i);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_client);
        this.firstName = (EditText) findViewById(R.id.First_Name_EditText);
        this.lastName = (EditText) findViewById(R.id.Last_Name_EditText);
        this.phoneNumber = (EditText) findViewById(R.id.Phone_Number_EditText);
        this.emailAddress = (EditText) findViewById(R.id.Email_EditText);
        this.income = (EditText) findViewById(R.id.Income_EditText);
        this.previewButton = (Button) findViewById(R.id.preview_button);
        this.previewButton.setOnClickListener(new C02001());
    }
}
