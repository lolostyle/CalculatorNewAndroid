package pl.gorilabrothers.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.EnumMap;

import pl.gorilabrothers.calculator.Enums.KeysForCoplection;
import pl.gorilabrothers.calculator.Operations.BasicOperation;

public class MainActivity extends AppCompatActivity {

    private EditText field;

    private EnumMap<KeysForCoplection, String> function = new EnumMap<KeysForCoplection, String>(KeysForCoplection.class);

    private BasicOperation operation;

    private Button buttonDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field = (EditText) findViewById(R.id.editText);

        function.put(KeysForCoplection.OPERATION, "null");
        function.put(KeysForCoplection.EX_OPERATION, "null");

        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                field.setText("0");
                return true;
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                field.setText(field.getText().delete(field.getText().length() - 1, field.getText().length()));
                if (field.getText().toString().trim().length() == 0) {
                    field.setText("0");
                }

            }
        });

    }

    public void onButtonClick(View view) {

        operation = new BasicOperation();

        switch (view.getId()) {

            case R.id.buttonPlus:

                if (function.get(KeysForCoplection.EX_OPERATION).equals("null")){

                    function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
                    function.put(KeysForCoplection.OPERATION, "plus");

                } else {
                    function.put(KeysForCoplection.SECOND_VALUE, field.getText().toString());
                    field.setText(operation.Calc(function.get(KeysForCoplection.FIRST_VALUE),
                            function.get(KeysForCoplection.SECOND_VALUE),
                            function.get(KeysForCoplection.EX_OPERATION)));

                    function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
                    function.put(KeysForCoplection.OPERATION, "plus");
                    function.put(KeysForCoplection.EX_OPERATION, "null");
                }

                break;

            case R.id.buttonMinus:

                if (function.get(KeysForCoplection.EX_OPERATION).equals("null")){

                    function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
                    function.put(KeysForCoplection.OPERATION, "minus");

                } else {
                    function.put(KeysForCoplection.SECOND_VALUE, field.getText().toString());
                    field.setText(operation.Calc(function.get(KeysForCoplection.FIRST_VALUE),
                            function.get(KeysForCoplection.SECOND_VALUE),
                            function.get(KeysForCoplection.EX_OPERATION)));

                    function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
                    function.put(KeysForCoplection.OPERATION, "minus");
                    function.put(KeysForCoplection.EX_OPERATION, "null");
                }

                break;

            case R.id.buttonMultime:

                if (function.get(KeysForCoplection.EX_OPERATION).equals("null")){

                    function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
                    function.put(KeysForCoplection.OPERATION, "multime");

                } else {
                    function.put(KeysForCoplection.SECOND_VALUE, field.getText().toString());
                    field.setText(operation.Calc(function.get(KeysForCoplection.FIRST_VALUE),
                            function.get(KeysForCoplection.SECOND_VALUE),
                            function.get(KeysForCoplection.EX_OPERATION)));

                    function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
                    function.put(KeysForCoplection.OPERATION, "multime");
                    function.put(KeysForCoplection.EX_OPERATION, "null");
                }

                break;

            case R.id.buttonDivide:


//                if (function.get(KeysForCoplection.EX_OPERATION).equals("null")){
//
//                    if (!field.getText().toString().equals("0")) {
//                        function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
//                        function.put(KeysForCoplection.OPERATION, "divide");
//
//                    }
//
//                } else {
//
//
//                    function.put(KeysForCoplection.SECOND_VALUE, field.getText().toString());
//                    field.setText(operation.Calc(function.get(KeysForCoplection.FIRST_VALUE),
//                            function.get(KeysForCoplection.SECOND_VALUE),
//                            function.get(KeysForCoplection.EX_OPERATION)));
//
//                    function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
//                    function.put(KeysForCoplection.OPERATION, "divide");
//                    function.put(KeysForCoplection.EX_OPERATION, "null");
//
//
//                }

                doCalc("divide");


                break;

            case R.id.buttonEquals:

                if (!function.get(KeysForCoplection.EX_OPERATION).equals("null")) {

                    function.put(KeysForCoplection.SECOND_VALUE, field.getText().toString());

                    field.setText(operation.Calc(function.get(KeysForCoplection.FIRST_VALUE),
                            function.get(KeysForCoplection.SECOND_VALUE),
                            function.get(KeysForCoplection.EX_OPERATION)));

                    function.put(KeysForCoplection.OPERATION, "equals");
                    function.put(KeysForCoplection.EX_OPERATION, "null");
                }


                break;

            case R.id.buttonPoint:

                if (!field.getText().toString().contains(",")) {
                    field.setText(field.getText() + ",");
                }

                break;

            default:

                if (field.getText().length() == 0 |
                        field.getText().toString().equals("0") |
                        !function.get(KeysForCoplection.OPERATION).equals("null")) {

                    if (function.get(KeysForCoplection.OPERATION).equals("equals")) {

                        field.setText(view.getContentDescription().toString());
                        function.put(KeysForCoplection.EX_OPERATION, "null");
                        function.put(KeysForCoplection.OPERATION, "null");

                    } else {

                        field.setText(view.getContentDescription().toString());
                        function.put(KeysForCoplection.EX_OPERATION, function.get(KeysForCoplection.OPERATION));
                        function.put(KeysForCoplection.OPERATION, "null");
                    }

                } else {
                    field.setText(field.getText().toString() + view.getContentDescription().toString());
                }

        }

    }

    private void doCalc(String o) {

        if (function.get(KeysForCoplection.EX_OPERATION).equals("null")){

            if (!field.getText().toString().equals("0")) {
                function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
                function.put(KeysForCoplection.OPERATION, o);

            }

        } else {


            function.put(KeysForCoplection.SECOND_VALUE, field.getText().toString());
            field.setText(operation.Calc(function.get(KeysForCoplection.FIRST_VALUE),
                    function.get(KeysForCoplection.SECOND_VALUE),
                    function.get(KeysForCoplection.EX_OPERATION)));

            function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
            function.put(KeysForCoplection.OPERATION, o);
            function.put(KeysForCoplection.EX_OPERATION, "null");


        }

    }

}
