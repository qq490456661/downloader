package sample;

import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/4/18.
 */
public class DownloaderService {

    public static void doAction(int action,DownloaderEntry entry){
        if(action == 1){
            entry.status = DownloaderEntry.DownloaderStatus.downloading;
            //DownloaderChanger.getInstance().postStatus(entry);
            try {
                URL url = new URL(entry.getUrl());
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                int code = conn.getResponseCode();
                int times = 0;
                while((code = conn.getResponseCode())!=200 && times < 3){
                    Thread.sleep(1000);
                    conn = (HttpURLConnection)url.openConnection();
                    times ++;
                }
                if(code == 200) {
                    int num = entry.getThread_num();
                    int len = conn.getContentLength();
                    RandomAccessFile raf = new RandomAccessFile(entry.getName(),"rw");
                    raf.setLength(len);
                    raf.close();
                    int range = len / num;
                    int start = 0;
                    int end = 0;
                    for (int i = 0; i < num; i++) {
                        start = i * range;
                        end = (i+1) * range - 1;
                        if(i== num - 1)
                            end = len;
                        new DownloadThread(i,start,end,entry).start();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
