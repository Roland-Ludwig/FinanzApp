package com.magicsmsboat.finanzapp.finanzapp.data;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rol on 02.08.2015.
 */
public class StoreData
{
    private static final String FILE_NAME = "jsonData";

    public static void writeFile(String data, Context ctx)
    {
        FileOutputStream os = null;

        try
        {
            os = ctx.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            os.write(data.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        finally {
            try
            {
                if (null != os) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String readFile(Context ctx)
    {
        FileInputStream is = null;

        try
        {
            is = ctx.openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            reader.close();
            return sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
