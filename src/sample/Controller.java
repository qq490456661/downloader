package sample;

/**
 * 4个线程，按顺序1-4轮流执行
 */
public class Controller{
    private static Integer current = 1;

    public static void main(String[] args) throws Exception{

        Controller controller = new Controller();
        for(int i=1;i<=4;i++){
            new CommonThread(i,controller).start();
        }
    }

    public static class CommonThread extends Thread{

        private Integer id;
        private Controller controller;
        public CommonThread(Integer id,Controller controller){
            this.id = id;
            this.controller = controller;

        }
        public void run(){
            while(true){
                synchronized (controller) {
                    while(current != id){
                        try {
                            controller.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(id+"号线程执行完毕");
                    current = (current+1)%5 == 0 ?1:(current+1)%5;
                    controller.notifyAll();
                }

            }
        }
    }

}