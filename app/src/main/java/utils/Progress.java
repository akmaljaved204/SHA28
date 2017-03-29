package utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.fs.sha28.R;


/**
 * Created by WebCom on 10/24/2016.
 */
public class Progress extends Dialog {
    public Progress(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        Window window = getWindow();

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.progress_dialogue);
    }
}
