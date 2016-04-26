package sample;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/4/18.
 */
public class DownloaderEntry {

    private String id;
    private String name;
    private String url;
    private int thread_num = 4;
    public int currentLength;
    public int totalLength;
    public enum DownloaderStatus{
        waiting,
        downloading,
        pause,
        resume,
        cancel
    }
    public DownloaderStatus status;

    public int getThread_num() {
        return thread_num;
    }

    public void setThread_num(int thread_num) {
        this.thread_num = thread_num;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public DownloaderStatus getStatus() {
        return status;
    }

    public void setStatus(DownloaderStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
