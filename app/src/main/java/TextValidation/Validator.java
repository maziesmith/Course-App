package TextValidation;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public Validator() {
    }

    public boolean isEmail(EditText editText) {
        CharSequence email = editText.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

   public boolean isEmpty(EditText editText){
        CharSequence empty = editText.getText().toString();
        return TextUtils.isEmpty(empty);
    }

   public boolean isPhoneNumber(EditText editText){
        CharSequence number = editText.getText().toString();
        return (!TextUtils.isEmpty(number) && Patterns.PHONE.matcher(number).matches());

    }

}
