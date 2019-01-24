import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        File f = new File("company.json");
        StringBuilder s = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String str;
        while ((str = br.readLine()) != null) {
            s.append(str);
        }
        br.close();
        System.out.println(s);
        Company c = gson.fromJson(s.toString(), Company.class);
        System.out.println(c);
        String from = gson.toJson(c);
        System.out.println(from);

    }
}
