package esenteq.project.datepickerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateSetListener {
    private TextView setDate, setDateTwo;
    private Button resetButton;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializer();
        setGivenDate();
        setGivenDateTwo();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate.setText("Set Date\n(Default Theme)");
                setDateTwo.setText("Set Date\n(Spinner Theme)");
            }
        });
    }

    private void setGivenDate() {
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //using date-picker-dialog:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth, dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        };
    }

    private void setGivenDateTwo() {
        setDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                new SpinnerDatePickerDialogBuilder()
                        .context(MainActivity.this)
                        .callback(MainActivity.this)
                        .spinnerTheme(R.style.NumberPickerStyle)
                        .defaultDate(year, month, day)
                        .build()
                        .show();
            }
        });
    }

    private void initializer() {
        setDate = findViewById(R.id.tv_setdateID);
        setDateTwo = findViewById(R.id.tv_setdateTwoID);
        resetButton = findViewById(R.id.btn_resetID);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    }

    @Override
    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        setDateTwo.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
