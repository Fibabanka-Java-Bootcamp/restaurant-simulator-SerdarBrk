package org.kodluyoruz;

public class Chef implements Runnable{
    private Order order;
    private String chefName;
    public Chef(Order order,String chefName){
        this.order=order;
        this.chefName=chefName;
    }

    @Override
    public void run() {

        while(true){
            if(order.getTakeTheOrder().containsValue(false)){
                order.preparingAnOrder(chefName);
            }
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
