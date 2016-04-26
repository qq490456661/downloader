package sample;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/4/19.
 */
public class Main {

    private static int threadNum = 4;//线程数量


    public static void main(String[] args) throws Exception {
       // String path = "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fimg4q.duitang.com%2Fuploads%2Fitem%2F201502%2F15%2F20150215194221_2fzmk.jpeg";
        String path = "http://desk.fd.zol-img.com.cn/t_s2560x1600c5/g5/M00/02/01/ChMkJ1bKxFaIFc8DALn8PlIf5jMAALHFQAaETMAufxW541.jpg";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        //获取文件长度
        int length = conn.getContentLength();
        //求出块长度
        int bukt = length/threadNum;
        //起始位置
        int start = 0,end = 0;
        for(int i=0;i<threadNum;i++){
            start = i*bukt;
            end = (i+1)*bukt;
            if(i==threadNum-1){
                end = length;
            }
            new Down(i+1,path,start,end).start();
        }
    }

    //线程
    public static class Down extends Thread{

        private int threadId;
        private long start;
        private long end;
        private String path;
        private int buffer = 1024*4;
        public Down(int threadId,String path,long start,long end){
            this.threadId = threadId;
            this.start = start;
            this.end = end;
            this.path = path;
        }

        public void run(){
            InputStream is = null;
            RandomAccessFile raf = null;
            try {
                System.out.println("线程"+threadId+" "+start+"==>"+end);
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                //设置输入流
                is = conn.getInputStream();
                is.skip(start);
                //设置输出流
                raf = new RandomAccessFile("bb.rar","rw");
                raf.seek(start);
                long size = end - start;
                int len = -1;
                byte[] b = new byte[buffer];
                //当前下载的字节数
                long current = 0;
                while((len = is.read(b)) != -1){
                    current += len;
                    raf.write(b,0,len);
                    System.out.println("线程"+threadId+"已下载:"+current+"字节");
                    if(current>size+32){  //超出这个字节数就结束
                        break;
                    }
                }
                System.out.println("======线程"+threadId+"总共下载了"+current+"字节");
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try{
                    if(is != null){
                        is.close();
                    }
                    if(raf != null){
                        raf.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }


        }

    }
}
