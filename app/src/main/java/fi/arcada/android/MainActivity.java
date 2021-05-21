package fi.arcada.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Två datamängder med längd och skonummer för 26 olika personer
    // hänger ihop på så vis att xData[0] och yData[0] är skonummer och längd  för den första personen, osv.
    // Observera att de är primitiva arrays, inte ArrayLists, så de behandlas lite annorlunda.
    double[] xData = {47, 42, 43, 42, 41, 48, 46, 44, 42, 43, 39, 43, 39, 42, 44, 45, 43, 44, 45, 42, 43, 32, 48, 43, 45, 45};
    double[] yData = {194, 188, 181, 177, 182, 197, 179, 176, 177, 188, 164, 171, 170, 180, 171, 185, 179, 182, 180, 178, 178, 148, 197, 183, 179, 198};
    // Deklarera yValue för längd, Denna variabel ska sedan få ett värde som hämtas från en EditText-box i appens GUI
    double yValue;
    EditText editTextNumber;
    TextView textView2;
    TextView textView3;
    Button button;
    // Deklarera övriga variabler och objekt du behöver, t.ex. TextViews osv.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber = findViewById(R.id.editTextNumber);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        button = findViewById(R.id.button);
    }

    // Gör så att den här metoden anropas vid ett knapptryck
    @SuppressLint("DefaultLocale")
    public void getEstimate(View view) {
        //initaliserar RegLine classen
        RegressionLine regLine = new RegressionLine(xData, yData);
        //korrelations storlek
        regLine.getCorrelationCoefficient();
        //korrelations grad
        regLine.getCorrelationGrade();
        //try/catch block för om de funkar körs allt om de blir fel(inte nummer inmatning) ger de en error toast istället för att crasha.
        try {
            yValue = Double.parseDouble(editTextNumber.getText().toString());
            textView2.setText(String.format("%s: %.2f", "Skostorlek: " , regLine.getX(yValue)));
            textView3.setText(String.format("%s : %.2f  (%s)"," Korrelationskoefficent" , regLine.correlationCoefficient, regLine.getCorrelationGrade));

        } catch (NumberFormatException e) {
            Context context = getApplicationContext();
            String outputMsg = "Ange ett nummer";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, outputMsg, duration);
            toast.show();
        }
    }
}
