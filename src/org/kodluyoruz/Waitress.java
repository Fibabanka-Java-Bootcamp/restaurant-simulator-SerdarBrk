package org.kodluyoruz;

public class Waitress implements Runnable{
    private Order order;
    private String waitressName;

    public Waitress(Order order, String waitressName) {
        this.order = order;
        this.waitressName = waitressName;
    }

    @Override
    public void run() {
        while (true){
            try {
                if (order.getRaiseHand().containsValue(false)) {
                    order.takeTheOrder(waitressName);
                }
                if (order.getPreparingAnOrder().containsValue(false)) {
                    order.finishedOrder(waitressName);
                }
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
