import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class Main {


    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();


        URL url = new URL("http://61.175.196.174:9091/api/Stats/Storage/Reagent");


        URLConnection urlConnection=url.openConnection();
        urlConnection.setRequestProperty("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoi5byg54Ca5paHIiwic2lkIjoieW50eXMiLCJqdGkiOiIyMzA3IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9leHBpcmF0aW9uIjoiMjAyMi80LzE3IDE3OjUxOjIxIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiIiwibmJmIjoxNjUwMTg4MTgxLCJleHAiOjE2NTAxODkwODEsImlzcyI6IlVuaXRlLkFwaSIsImF1ZCI6IndyIn0.AOXcWzNOXrj7DRXZpZcRGkJJHQQ1OINSOcQSaKo-B8w");
        urlConnection.setRequestProperty("Connection", "close");
        int length = urlConnection.getContentLength();
        urlConnection.getDate()
        InputStream in= (InputStream) urlConnection.getInputStream();
        in = new BufferedInputStream(in);
        InputStreamReader reader = new InputStreamReader(in);
        int ch;
        while ((ch = reader.read()) != -1) {
            System.out.print((char) ch);
        }
        System.out.println();
        long endTime = System.currentTimeMillis();

        System.out.println("花销时间 "+(endTime-startTime));
    }
}

