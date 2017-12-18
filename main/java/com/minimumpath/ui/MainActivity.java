package com.minimumpath.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.minimumpath.Matrix;
import com.minimumpath.MatrixVisitor;
import com.minimumpath.PathState;
import com.minimumpath.R;
import com.minimumpath.Utils;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final Matrix EXAMPLE_MATRIX_1 = new Matrix(new int[][]{
            {3, 4, 1, 2, 8, 6},
            {6, 1, 8, 2, 7, 4},
            {5, 9, 3, 9, 9, 5},
            {8, 4, 1, 3, 2, 6},
            {3, 7, 2, 8, 6, 4}
    });
    public static final Matrix EXAMPLE_MATRIX_2 = new Matrix(new int[][]{
            {3, 4, 1, 2, 8, 6},
            {6, 1, 8, 2, 7, 4},
            {5, 9, 3, 9, 9, 5},
            {8, 4, 1, 3, 2, 6},
            {3, 7, 2, 1, 2, 3}
    });
    public static final Matrix EXAMPLE_MATRIX_3 = new Matrix(new int[][]{
            {19, 10, 19, 10, 19},
            {21, 23, 20, 19, 12},
            {20, 12, 20, 11, 10}
    });

    private EditText dataEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        dataEditText = (EditText) findViewById(R.id.data_input_edit_text);
        resultTextView = (TextView) findViewById(R.id.result_text_view);
        findViewById(R.id.find_button).setOnClickListener(this);
        findViewById(R.id.clear_data).setOnClickListener(this);
        findViewById(R.id.example_matrix_1).setOnClickListener(this);
        findViewById(R.id.example_matrix_2).setOnClickListener(this);
        findViewById(R.id.example_matrix_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_button:
                onFindButtonClick();
                break;
            case R.id.clear_data:
                clearData();
                break;
            case R.id.example_matrix_1:
                if (checkIsDataEntered()) {
                    return;
                }
                clearData();
                dataEditText.setText(EXAMPLE_MATRIX_1.asDelimitedString(" "));
                break;
            case R.id.example_matrix_2:
                if (checkIsDataEntered()) {
                    return;
                }
                clearData();
                dataEditText.setText(EXAMPLE_MATRIX_2.asDelimitedString(" "));
                break;
            case R.id.example_matrix_3:
                if (checkIsDataEntered()) {
                    return;
                }
                clearData();
                dataEditText.setText(EXAMPLE_MATRIX_3.asDelimitedString(" "));
                break;
            default:
                break;
        }
    }

    private void clearData() {
        dataEditText.setText("");
        resultTextView.setText("");
    }

    private boolean checkIsDataEntered() {
        if (!TextUtils.isEmpty(dataEditText.getText())) {
            Toast.makeText(getApplicationContext(), "Please clear entered data", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void onFindButtonClick() {
        String data = dataEditText.getText().toString();
        if (TextUtils.isEmpty(data)) {
            Toast.makeText(getApplicationContext(), "Please enter data", Toast.LENGTH_SHORT).show();
            return;
        }
        Matrix matrix = parseData(data);
        if (matrix == null) {
            Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
            return;
        }
        showMinimumPath(matrix);
    }

    private void showMinimumPath(Matrix matrix) {
        MatrixVisitor visitor = new MatrixVisitor(matrix);
        PathState pathState = visitor.getBestPathForMatrix();
        displayResult(pathState);
    }

    private void displayResult(PathState pathState) {
        StringBuilder result = new StringBuilder();
        result.append(pathState.isComplete() ? "Yes" : "No");
        result.append("\n");
        result.append(pathState.getTotalCost());
        result.append("\n");
        Object[] objects = pathState.getRowsTraversed().toArray();
        String string = Arrays.deepToString(objects);
        result.append(string);

        resultTextView.setText(result);
    }

    private Matrix parseData(String data) {
        try {
            return Utils.createMatrixFrom(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
