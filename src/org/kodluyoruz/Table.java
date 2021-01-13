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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(k == 20){
                order.print(tableName+" got up from the table.");
                order.deleteOrder(tableName);
                break;
            }
            if(order.getFinished().containsKey(tableName)){
                long rnd=Math.round(Math.random());
                if(rnd == 1){
                    order.deleteOrder(tableName);
                    order.raiseHand(tableName);
                }else{
                    order.print(tableName+ " ate the order.");
                    order.deleteOrder(tableName);
                    break;
                }
            }
            k++;
        }
    }
}