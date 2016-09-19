package pl.gorilabrothers.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.EnumMap;

import pl.gorilabrothers.calculator.Enums.KeysForCoplection;
import pl.gorilabrothers.calculator.Operations.BasicOperation;

public class MainActivity extends AppCompatActivity {

    private EditText field;

    private EnumMap<KeysForCoplection, String> function = new EnumMap<KeysForCoplection, String>(KeysForCoplection.class);

    private BasicOperation operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field = (EditText) findViewById(R.id.editText);

        function.put(KeysForCoplection.OPERATION, "null");
        function.put(KeysForCoplection.EX_OPERATION, "null");
    }

    public void onButtonClick(View view) {

        operation = new BasicOperation();

        switch (view.getId()) {

            case R.id.buttonPlus:

                function.put(KeysForCoplection.FIRST_VALUE, field.getText().toString());
                function.put(KeysForCoplection.OPERATION, "plus");

                break;

            case R.id.buttonEquals:
                function.put(KeysForCoplection.SECOND_VALUE, field.getText().toString());

                field.setText(operation.Calc(function.get(KeysForCoplection.FIRST_VALUE),
                        function.get(KeysForCoplection.SECOND_VALUE),
                        function.get(KeysForCoplection.EX_OPERATION)));

                function.put(KeysForCoplection.OPERATION, "equals");


                break;



            default:

                if (field.getText().length() == 0 |
                        field.getText().toString().equals("0") |
                        !function.get(KeysForCoplection.OPERATION).equals("null")) {

                    field.setText(view.getContentDescription().toString());
                    function.put(KeysForCoplection.EX_OPERATION, function.get(KeysForCoplection.OPERATION));
                    function.put(KeysForCoplection.OPERATION, "null");


                } else {
                    field.setText(field.getText().toString() + view.getContentDescription().toString());
                }

        }
    }
}
