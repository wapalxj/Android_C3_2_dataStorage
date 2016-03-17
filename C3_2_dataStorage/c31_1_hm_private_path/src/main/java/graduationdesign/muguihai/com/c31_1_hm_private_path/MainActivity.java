package graduationdesign.muguihai.com.c31_1_hm_private_path;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private CheckBox cSave;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        remember();//账号密码的记录


    }

    public void init(){
        username= (EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        cSave= (CheckBox)findViewById(R.id.checkbox);
        save= (Button)findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cSave.isChecked()) {
//                    File file=new File("dinfo.txt");//没有权限写到根目录
//                    File file = new File("data/data/graduationdesign.muguihai.com.c31_1_hm_private_path/info.txt");
                    File file = new File(getFilesDir(),"info.txt");//等价上行写法
                    try {
                        String value = username.getText() + "------>" + password.getText();
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(value.getBytes());
                        fos.close();
                        Toast.makeText(getApplicationContext(), "勾选了，保存成功", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "勾选了，没有保存成功", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "没有勾选，没有保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void remember(){
        File file = new File(getFilesDir(),"info.txt");
        if(file.exists()&&file.length()>0){
            try {
                BufferedReader br=new BufferedReader(new FileReader(file));
                String line=br.readLine();
                String username=line.split("------>")[0];
                String password=line.split("------>")[1];

                this.username.setText(username);
                this.password.setText(password);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
