package com.newsup.kernel.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Compressor {

    public static void compress(String data, FileOutputStream outstream) throws Exception {

        GZIPOutputStream gzip = new GZIPOutputStream(outstream);
        gzip.write(data.getBytes("UTF-8"));
        gzip.flush();
        gzip.close();

    }

    public static String decompress(FileInputStream instream) throws Exception {

        byte[] buff = new byte[instream.available()];
        instream.read(buff, 0, buff.length);
        instream.close();

        GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(buff));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gzip, "UTF-8"));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private static void debug(String msg) {
        android.util.Log.d("Compressor", msg);
    }

}
