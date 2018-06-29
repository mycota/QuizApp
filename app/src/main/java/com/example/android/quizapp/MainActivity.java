package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// Total marks
        int totalMarks = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
// Checking and displaying results for user

public void checkResult(View view) {

    EditText studentName = (EditText) findViewById(R.id.sname);
    String name = studentName.getText().toString();

    EditText studentClass = (EditText) findViewById(R.id.clas);
    String clas = studentClass.getText().toString();

    RadioButton radioButtonQ1a = (RadioButton) findViewById(R.id.radio_q1a);
    boolean radioButtonQ1aChecked = radioButtonQ1a.isChecked();


    RadioButton radioButtonQ2c = (RadioButton) findViewById(R.id.radio_q2c);
    boolean radioButtonQ2cCheched = radioButtonQ2c.isChecked();

    CheckBox checkBoxQ3a = (CheckBox) findViewById(R.id.check_q3a);
    CheckBox checkBoxQ3b = (CheckBox) findViewById(R.id.check_q3b);
    CheckBox checkBoxQ3c = (CheckBox) findViewById(R.id.check_q3c);

    CheckBox checkBoxQ4a = (CheckBox) findViewById(R.id.check_q4a);
    CheckBox checkBoxQ4b = (CheckBox) findViewById(R.id.check_q4b);
    CheckBox checkBoxQ4c = (CheckBox) findViewById(R.id.check_q4c);

    EditText question5 = (EditText) findViewById(R.id.q5ans);
    int answer = Integer. parseInt(question5.getText().toString());

    EditText question6 = (EditText) findViewById(R.id.q6ans);
    String answer6 = question6.getText().toString();

    RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radio_group1);
    RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radio_group2);

    // Check if all input text and fields are all filled and all questions are attempted

    if (name.isEmpty() || clas.isEmpty()) {
        Toast.makeText(this, "Sorry you must provide your name and class to proceed", Toast.LENGTH_LONG).show();
    } else if (radioGroup1.getCheckedRadioButtonId() == -1) {
        Toast.makeText(this, "Sorry you didin't select option in Question 1", Toast.LENGTH_LONG).show();
    } else if (radioGroup2.getCheckedRadioButtonId() == -1) {
        Toast.makeText(this, "Sorry you didin't select option in Question 2", Toast.LENGTH_LONG).show();
    } else if (!checkBoxQ3a.isChecked() && !checkBoxQ3b.isChecked() && !checkBoxQ3c.isChecked()) {
        Toast.makeText(this, "Sorry you didin't select option in Question 3", Toast.LENGTH_LONG).show();

    } else if (!checkBoxQ4a.isChecked() && !checkBoxQ4b.isChecked() && !checkBoxQ4c.isChecked()) {
        Toast.makeText(this, "Sorry you didin't select option in Question 4", Toast.LENGTH_LONG).show();
    } else if (answer <= 0) {
        Toast.makeText(this, "You must provide answer for Question 5", Toast.LENGTH_LONG).show();
    } else if (answer6.equals("")){
        Toast.makeText(this, "You must provide answer for Question 6 or type NA", Toast.LENGTH_LONG).show();
    } else {

        // passing values to a called method, but only the correct answers are pass to the method

        int scores = calculateResult(radioButtonQ1aChecked, radioButtonQ2cCheched, checkBoxQ3a, checkBoxQ3c, checkBoxQ4b, checkBoxQ4c, answer, answer6);

        String exc = "Name: " + name +"\n"+ "Class: " +clas+"\n" + " Good job! you are an Excellent student, you score: " + scores + " / " + totalMarks;
        String vGood = "Name: " + name +"\n"+ "Class: " +clas+"\n" + "Good job! you are a Very Good student, you score: " + scores + " / " + totalMarks + "\n";
        vGood += "You can score all by trying again, WHAT DO YOU THINK?";
        String good = "Name: " + name +"\n"+ "Class: " +clas+"\n" + "Good job! you did Good, you score: " + scores + " / " + totalMarks + "\n";
        good += "You can score more by trying again, WHAT DO YOU THINK?";
        String half = "Name: " + name +"\n"+ "Class: " +clas+"\n" + "Good job! you manage to get half, you score: " + scores + " / " + totalMarks + "\n";
        half += "You can score more by trying again, WHAT DO YOU THINK?";
        String notGood = "Name: " + name +"\n"+ "Class: " +clas+"\n" + "Ohh ! your score is below average, you score: " + scores + " / " + totalMarks + "\n";
        notGood += "You can score more by trying again, WHAT DO YOU THINK?";
      // Display message based on marks score
        if (scores == totalMarks) {
            Toast.makeText(this, exc, Toast.LENGTH_LONG).show();
        } else if (scores == 10) {
            Toast.makeText(this, vGood, Toast.LENGTH_LONG).show();
        } else if (scores == 8) {
            Toast.makeText(this, good, Toast.LENGTH_SHORT).show();
        } else if (scores == 4 || scores == 6) {
            Toast.makeText(this, half, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, notGood, Toast.LENGTH_LONG).show();
        }
    }
}
// to calculate the marks
private int calculateResult(boolean radioButtonQ1aChecked, boolean radioButtonQ2cCheched,
    CheckBox checkBoxQ3a, CheckBox checkBoxQ3c, CheckBox checkBoxQ4b, CheckBox checkBoxQ4c, int answer, String answer6){

    // The answers are further check, if they were not selected then the answer is incorrect
        int totalScore = 0;

    if (radioButtonQ1aChecked){
        totalScore = totalScore + 1;
       }

        if (radioButtonQ2cCheched){
        totalScore = totalScore + 1;
       }
    if (checkBoxQ3a.isChecked() && checkBoxQ3c.isChecked()) {
        totalScore = totalScore + 1;
       }

    if (checkBoxQ4b.isChecked() && checkBoxQ4c.isChecked()){

        totalScore = totalScore + 1;
       }

    if (answer == 1){
        totalScore = totalScore +1;
       }
    if (answer6.equals("Physicist") || answer6.equals("physicist")){
        totalScore = totalScore +1;
    }

    // each mark score is 2
    return totalScore * 2;
    }
    // To handle the radiobuttons so that when one is active the rest is inactive
    public void onRadioButtonClickedQ1(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_q1a:
                if (checked)

                    break;
            case R.id.radio_q1b:
                if (checked)

                break;
            case R.id.radio_q1c:
                if (checked)

                    break;
        }
    }
// To handle the radiobuttons so that when one is active the rest is inactive
    public void onRadioButtonClickedQ2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_q2a:
                if (checked)
                    break;
            case R.id.radio_q2b:
                if (checked)
                    break;
            case R.id.radio_q2c:
                if (checked)

                    break;
        }
    }

// To reset all fields when the try again button is press
    public void tryAgain(View v){

        RadioButton radioButtonQ1a = (RadioButton) findViewById(R.id.radio_q1a);
        radioButtonQ1a.setChecked(false);
        RadioButton radioButtonQ1b = (RadioButton) findViewById(R.id.radio_q1b);
        radioButtonQ1b.setChecked(false);
        RadioButton radioButtonQ1c = (RadioButton) findViewById(R.id.radio_q1c);
        radioButtonQ1c.setChecked(false);

        RadioButton radioButtonQ2a = (RadioButton) findViewById(R.id.radio_q2a);
        radioButtonQ2a.setChecked(false);
        RadioButton radioButtonQ2b = (RadioButton) findViewById(R.id.radio_q2b);
        radioButtonQ2b.setChecked(false);
        RadioButton radioButtonQ2c = (RadioButton) findViewById(R.id.radio_q2c);
        radioButtonQ2c.setChecked(false);

        CheckBox checkBoxQ3a = (CheckBox) findViewById(R.id.check_q3a);
        checkBoxQ3a.setChecked(false);
        CheckBox checkBoxQ3b = (CheckBox) findViewById(R.id.check_q3b);
        checkBoxQ3b.setChecked(false);
        CheckBox checkBoxQ3c = (CheckBox) findViewById(R.id.check_q3c);
        checkBoxQ3c.setChecked(false);

        CheckBox checkBoxQ4a = (CheckBox) findViewById(R.id.check_q4a);
        checkBoxQ4a.setChecked(false);
        CheckBox checkBoxQ4b = (CheckBox) findViewById(R.id.check_q4b);
        checkBoxQ4b.setChecked(false);
        CheckBox checkBoxQ4c = (CheckBox) findViewById(R.id.check_q4c);
        checkBoxQ4c.setChecked(false);

        EditText question5 = (EditText) findViewById(R.id.q5ans);
        question5.setText("");

        EditText question6 = (EditText) findViewById(R.id.q6ans);
        question6.setText("");

        EditText studentClass = (EditText) findViewById(R.id.clas);
        studentClass.requestFocus();
    }
}
