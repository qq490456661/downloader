package sample;

import sun.misc.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/4/19.
 */
public class DownloadThread extends Thread {

    public int thread_id;
    private static int buffer = 1024*4;
    private DownloaderEntry entry;
    private int start;
    private int end;

    public DownloadThread(int thread_id,int start,int end,DownloaderEntry entry){
        this.thread_id = thread_id;
        this.start = start;
        this.end = end;
        this.entry = entry;
    }

    public void run() {
        InputStream is = null;
        RandomAccessFile raf = null;
        long current = 0;
        try {
            URL url = new URL(entry.getUrl());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            //重要:请求服务器下载部分文件 指定文件的位置
            httpURLConnection.setRequestProperty("Range","bytes="+start+"-"+end);
            //从服务器请求全部资源返回200 ok如果从服务器请求部分资源 返回 206 ok
            int code = httpURLConnection.getResponseCode();
            System.out.println("code:"+code);
            if(code == 206) {
                is = httpURLConnection.getInputStream();
                raf = new RandomAccessFile(entry.getName(), "rw");
                raf.seek(start);
                byte[] b = new byte[buffer];
                int len = -1;
                while ((len = is.read(b)) != -1) {
                    current += len;
                    raf.write(b, 0, len);
                    synchronized (entry) {
                        entry.currentLength += len;
                        System.out.println("线程" + thread_id + ":" + current);
                        // System.out.println("文件当前的长度为:" + entry.currentLength);
                        if (entry.getStatus() == DownloaderEntry.DownloaderStatus.pause) {
                            //输出一个新的文件
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(entry.getName() + "~")));
                            String str = current + "=" + end + "\r\n";
                            writer.write(str);
                        }
                    }
                }
                System.out.println("线程" + thread_id + "下载完毕 " + start + "==>" + end);
            }else if(code == 200){
                //不支持多线程下载 , 需要单线程下载该文件

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null)
                    is.close();
                if (raf != null)
                    raf.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
