package sample;

/**
 * 处理来自用户的请求
 * Created by linjunjie(490456661@qq.com) on 2016/4/18.
 */
public class DownloaderManager {

    public static void add(DownloaderEntry entry){
        DownloaderService.doAction(1,entry);
    }

    public static void main(String[] args){
        DownloaderEntry entry = new DownloaderEntry();
        entry.setUrl("http://nb.poms.baidupcs.com/file/8f3e9d1b299362f9be1d67204efe4953?bkt=p2-nb-312&fid=2382770215-250528-944210183191487&time=1461255844&sign=FDTAXGERBH-DCb740ccc5511e5e8fedcff06b081203-Ku888jWias80elOcY%2BkiPUNStl8%3D&to=nbb&fm=Nin,B,U,nc&sta_dx=47&sta_cs=0&sta_ft=zip&sta_ct=7&fm2=Ningbo,B,U,nc&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=00002216162921c9534d8cf052e7116b08e5&expires=8h&rt=sh&r=522632750&mlogid=2597983840037942023&vuk=2382770215&vbdid=630043019&fin=-o-.zip&fn=-o-.zip&uta=0&rtype=0&iv=2&isw=0&dp-logid=2597983840037942023&dp-callid=0.1.1");
        entry.setName("苦怕.rar");
        entry.setThread_num(4);
        add(entry);
    }

}
