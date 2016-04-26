package sample;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/4/18.
 */
public abstract class DownloaderWatcher implements Observer{

    @Override
    public void update(Observable o, Object data) {
        if(data instanceof DownloaderEntry){
            notifyUpdate((DownloaderEntry )data);
        }
    }

    public abstract void notifyUpdate(DownloaderEntry data);
}
