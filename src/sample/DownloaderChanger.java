package sample;

import java.util.Observable;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/4/18.
 */
public class DownloaderChanger extends Observable {
    private  static DownloaderChanger downloaderChanger = new DownloaderChanger();
    public static DownloaderChanger getInstance(){
        return downloaderChanger;
    }
    public void postStatus(DownloaderEntry entry){
        setChanged();
        notifyObservers(entry);
    }


}
