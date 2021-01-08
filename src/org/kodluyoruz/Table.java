package org.kodluyoruz;

public class Table implements Runnable {
    private Order order;
    private String tableName;

    public Table(Order order, String tableName) {
        this.order = order;
        this.tableName = tableName;
    }

    @Override
    public void run() {
        int k=0;
        order.raiseHand(tableName);
        while(order.getRaiseHand().containsKey(tableName)){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(k == 1000){
                System.out.println(tableName+" got up from the table.");
                break;
            }
            if(order.getFinished().containsKey(tableName)){
                System.out.println(tableName+ " ate the order.");
                order.deleteOrder(tableName);
            }
            k++;

        }

    }
}
